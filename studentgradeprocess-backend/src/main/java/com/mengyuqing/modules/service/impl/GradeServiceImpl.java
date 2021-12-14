package com.mengyuqing.modules.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengyuqing.modules.dto.GradeDto;
import com.mengyuqing.modules.dto.ScoreRuleDto;
import com.mengyuqing.modules.entity.*;
import com.mengyuqing.modules.mapper.*;
import com.mengyuqing.modules.service.GradeService;
import com.mengyuqing.modules.util.CommonMethod;
import com.mengyuqing.modules.util.page.PageData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Yuqing
 * @date 2021/10/25 10:25
 */
@Service
public class GradeServiceImpl implements GradeService {

    private final StudentInfoMapper studentInfoMapper;
    private final DicDataMapper dicDataMapper;
    private final SourceTemplateMapper sourceTemplateMapper;
    private final GradeMapper gradeMapper;
    private final StudentSubjectLevelMapper studentSubjectLevelMapper;
    private final UserMapper userMapper;

    public GradeServiceImpl(StudentInfoMapper studentInfoMapper, DicDataMapper dicDataMapper, SourceTemplateMapper sourceTemplateMapper, GradeMapper gradeMapper, StudentSubjectLevelMapper studentSubjectLevelMapper, UserMapper userMapper) {
        this.studentInfoMapper = studentInfoMapper;
        this.dicDataMapper = dicDataMapper;
        this.sourceTemplateMapper = sourceTemplateMapper;
        this.gradeMapper = gradeMapper;
        this.studentSubjectLevelMapper = studentSubjectLevelMapper;
        this.userMapper = userMapper;
    }


    private QueryWrapper<Grade> getWrapper(Map<String, Object> params) {

        Long id = Long.parseLong(params.get("id").toString());

        QueryWrapper<Grade> wrapper = new QueryWrapper<>();

        wrapper.eq(true, "g.student_info_id", id);

        return wrapper;
    }

    @Override
    public PageData<GradeDto> page(Map<String, Object> params) {
        IPage<GradeDto> page = gradeMapper.selectPage(
                CommonMethod.getPage(params, null, false),
                getWrapper(params)
        );
        return  new PageData<>(page.getRecords(), page.getTotal());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importCSV(List<String> importCSVData, String fileName) {

        // List--store grad
        List<Grade> gradeList = new ArrayList<>();
        // List-store level
        List<StudentSubjectLevel> studentSubjectLevelList = new ArrayList<>();

        // get the rule
        SourceTemplate sourceTemplate = sourceTemplateMapper.selectRule();
        // get table data
        Map<String, Object> map = this.convertData(importCSVData);
        List<String> subjectNameList = (List<String>) map.get("titleList");
        List<String> proportionList = (List<String>) map.get("proportion");
        List<String> importGradeList = (List<String>) map.get("grade");

        String newFileName = fileName.substring(3, 23);

        for (String grade : importGradeList) {

            BigDecimal total = new BigDecimal("120");

            String[] grades = grade.split(",");

            StudentInfo studentInfo = StudentInfo.builder()
                    .firstName(grades[0])
                    .lastName(grades[1])
                    .name(grades[2])
                    .studentId(grades[3].trim())
                    .fileName(newFileName)
                    .build();
            studentInfoMapper.insert(studentInfo);

            List<BigDecimal> bigDecimalList = new ArrayList<>();

            User user = User.builder()
                    .studentInfoId(studentInfo.getId())
                    .userName(grades[3].trim())
                    .passWord(grades[3].trim())
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            userMapper.insert(user);


            // add data on the list of storing grade
            this.addGradeList(gradeList, subjectNameList, sourceTemplate, grades, studentInfo, proportionList, bigDecimalList);

            if (!CollectionUtils.isEmpty(bigDecimalList)) {
                BigDecimal sum = bigDecimalList.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
                total = total.subtract(sum);
            }

            //  update overall grade
            this.updateOverallGrade(gradeList, studentInfo, sourceTemplate.getRule(), total);

            // add subject level on the list
            this.addStudentSubjectLevelList(gradeList, studentSubjectLevelList);

            gradeList.clear();
            studentSubjectLevelList.clear();
        }
    }

    private void addGradeList(List<Grade> gradeList, List<String> subjectNameList, SourceTemplate sourceTemplate, String[] grades, StudentInfo studentInfo, List<String> proportionList, List<BigDecimal> bigDecimalList) {


        int foot = 0;
        // control the grade foot title
        int gradeFoot = 4;
        for (String subjectName : subjectNameList) {

            // get subject credit
            String proportion = proportionList.get(foot);

            String source = grades[gradeFoot];

            // 判断是否可以转为数字 justify whether can be changed to number
            boolean type= StringUtils.isNumeric(source);

            if (!type) {
                // if the grade is "AB" OR"NM"
                if ("AB".equals(source) || "NM".equals(source)) {
                    // get subject level code
                    Grade grade = getGrade(studentInfo, subjectName, source, proportion);
                    gradeList.add(grade);
                    foot ++;
                    continue;
                }
                bigDecimalList.add(new BigDecimal(proportion));
                Grade grade = getGrade(studentInfo, subjectName, source, proportion);
                gradeList.add(grade);
                foot ++;
                continue;
            }

            // get template message
            ScoreRuleDto scoreRuleDto = this.calculationLevel(source, sourceTemplate.getRule(), true);
            // get level
            String level = scoreRuleDto.getGrade();
            // credit*grade
            BigDecimal actualScore = (new BigDecimal(Integer.valueOf(proportion).toString()).multiply(BigDecimal.valueOf(scoreRuleDto.getRate())))
                    .setScale(2, RoundingMode.HALF_UP);

            Grade grade = Grade.builder()
                    .source(actualScore)
                    .subjectName(subjectName)
                    .studentInfoId(studentInfo.getId())
                    .createTime(new Date())
                    .level(level)
                    .credit(proportion)
                    .build();
            gradeList.add(grade);
            foot ++;
            gradeFoot ++;
        }

        gradeMapper.insertList(gradeList);
    }

    private Grade getGrade(StudentInfo studentInfo, String subjectName, String level, String proportion) {
        return Grade.builder()
                .source(new BigDecimal("0"))
                .subjectName(subjectName)
                .studentInfoId(studentInfo.getId())
                .createTime(new Date())
                .level(level)
                .credit(proportion)
                .build();
    }

    private void addStudentSubjectLevelList(List<Grade> gradeList, List<StudentSubjectLevel> studentSubjectLevelList) {
        for (Grade grade : gradeList) {

            StudentSubjectLevel studentSubjectLevel = StudentSubjectLevel.builder()
                    .gradeId(grade.getId())
                    .dicDataCode(CommonMethod.getSubjectOrLevelCode(grade.getLevel()))
                    .credit(grade.getCredit())
                    .createTime(new Date())
                    .build();

            studentSubjectLevelList.add(studentSubjectLevel);
        }

        studentSubjectLevelMapper.insertList(studentSubjectLevelList);
    }

    private void updateOverallGrade(List<Grade> gradeList, StudentInfo studentInfo, String rule, BigDecimal total) {

        BigDecimal overallGrade = gradeList.stream()
                .map(i -> i.getSource() == null ? BigDecimal.ZERO : i.getSource())
                .reduce(BigDecimal.ZERO, BigDecimal::add).divide(total, 2, BigDecimal.ROUND_DOWN);

        // get overall grade level
        ScoreRuleDto scoreRuleDto = this.calculationLevel(overallGrade.setScale(0, BigDecimal.ROUND_HALF_UP).toString(), rule, false);

        StudentInfo overallGradeInfo = StudentInfo.builder()
                .id(studentInfo.getId())
                .overallGrade(scoreRuleDto.getGrade() + "(" + overallGrade.toString() + ")")
                .build();

        studentInfoMapper.updateById(overallGradeInfo);
    }

    /**
     * calculate grade :true-calculate the level of each subject grade   false-calculate the level of overall grade
     * @param source
     * @param rule
     * @return
     */
    private ScoreRuleDto calculationLevel(String source, String rule, Boolean flag) {
        ScoreRuleDto ruleDto = new ScoreRuleDto();
        //sort
        List<ScoreRuleDto> scoreRuleDtoList = sortRule(rule);
        for (ScoreRuleDto scoreRuleDto : scoreRuleDtoList) {
            // calculate the level of each subject grade
            if (flag) {
                if (scoreRuleDto.getOpenScore() <= Long.parseLong(source)) {
                    ruleDto.setGrade(scoreRuleDto.getGrade());
                    ruleDto.setRate(scoreRuleDto.getRate());
                    break;
                }

            } else {
                // calculate the level of overall grade
                if (scoreRuleDto.getRate() == Long.parseLong(source) || scoreRuleDto.getRate() < Long.parseLong(source)) {
                    ruleDto.setGrade(scoreRuleDto.getGrade());
                    ruleDto.setRate(scoreRuleDto.getRate());
                    break;
                }
            }
        }
        return ruleDto;
    }


    private List<ScoreRuleDto> sortRule(String rule) {
        List<ScoreRuleDto> scoreRuleDtoList = JSONArray.parseArray(rule, ScoreRuleDto.class);
        //sort by grade
        scoreRuleDtoList.sort((ScoreRuleDto o1, ScoreRuleDto o2) -> o2.getOpenScore().compareTo(o1.getOpenScore()));

        return scoreRuleDtoList;
    }


    // processing import data
    private Map<String, Object> convertData(List<String> grade) {
        Map<String, Object> map = new HashMap<>();
        //subject title list
        List<String> titleList = new ArrayList<>();
        // credit list
        List<String> proportion = new ArrayList<>();

        if (!CollectionUtils.isEmpty(grade)) {
            // 获取除学生信息外科目的信息 get the information besides subject title
            String titleStrings = grade.get(0);
            String[] titles = titleStrings.split(",");
            this.getSubjectOtherInfo(titles, titleList);

            // 获取除学生信息外科目占比的信息 get the information besides credit
            String proportionStrings = grade.get(1);
            String[] proportions = proportionStrings.split(",");
            this.getSubjectOtherInfo(proportions, proportion);

            grade.remove(0); //remove first line
            grade.remove(0); //remove second line
        }

        map.put("titleList", titleList);
        map.put("proportion", proportion);
        map.put("grade", grade);

        return map;
    }

    public void getSubjectOtherInfo(String[] strings, List<String> StringList) {
        for (int x = 0; x < strings.length; x ++) {
            if (x < 4) {
                continue;
            }
            StringList.add(strings[x]);
        }
    }
}
