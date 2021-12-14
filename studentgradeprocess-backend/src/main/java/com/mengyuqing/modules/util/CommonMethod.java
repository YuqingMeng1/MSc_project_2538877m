package com.mengyuqing.modules.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import com.mengyuqing.modules.util.page.PageData;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yuqing
 * @date 2021/10/26 0:40
 */
public class CommonMethod {


    /**
     * get cut page object
     * @param params
     * @param defaultOrderField
     * @param isAsc
     */
    public static IPage getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        long curPage = 1;
        long limit = 10;

        if(params.get("page") != null){
            curPage = Long.parseLong((String)params.get("page"));
        }
        if(params.get("limit") != null){
            limit = Long.parseLong((String)params.get("limit"));
        }

        //cut page object
        Page page = new Page<>(curPage, limit);

        //cut page parameter
        params.put("page", page);


        String orderField = (String)params.get("orderField");
        String order = (String)params.get("order");

        //front-page field sort
        if(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(orderField) && com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(order)){
            if("asc".equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }
        //without field ,not sort
        if(com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        //default sort
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }

    /**
     * import
     * @param inputStream csv file(address+file)
     * @return
     */
    public static List<String> importCsv(InputStream inputStream) {
        List<String> dataList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                    br = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    /**
     * field change to object (user_name to userName)
     *
     * @param field
     * @return
     */
    public static String fieldToProperty(String field) {
        if (null == field) {
            return "";
        }
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])));
                    i++;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * get reflection
     * @param tClass
     * @param fieldName filed name of reflection
     * @param <T>
     * @return
     */
    public static <T> Object reflex(T tClass, String fieldName) {
        Object value = null;
        try {
            Method method = tClass.getClass().getMethod(fieldName);
            value = method.invoke(tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     *
     * @param tClass
     * @param column
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> Object getBatchModify(T tClass, String column) {
        String fieldName = "get" + column.substring(0,1).toUpperCase() + (column.length() > 1 ? column.substring(1) : "");
        return reflex(tClass, fieldName);
    }


    /**
     * get code by subject name
     * @param subjectName
     * @return
     */
    public static String getSubjectOrLevelCode(String subjectName) {
        Map<String, String> STRING_STRING_MAP =
                new ImmutableMap.Builder<String, String>().
                        put("agileSprintOne", "1001").
                        put("agileSprintTwo", "1002").
                        put("agileEssay", "1003").
                        put("groceryStoresDatabase", "1004").
                        put("databaseResearchEssay", "1005").
                        put("operationsManagementReport", "1006").
                        put("operationsManagementCaseStudy", "1007").
                        put("dataAnalysisReport", "1008").
                        put("changeManagementReport", "1009").
                        put("crossSiteScriptingEssay", "1010").
                        put("riskAssessmentReport", "1011").
                        put("wblReport", "1012").
                        put("wblJournal", "1013").
                        put("A1", "2001").
                        put("A2", "2002").
                        put("A3", "2003").
                        put("A4", "2004").
                        put("A5", "2005").
                        put("B1", "2006").
                        put("B2", "2007").
                        put("B3", "2008").
                        put("C1", "2009").
                        put("C2", "2010").
                        put("C3", "2011").
                        put("D1", "2012").
                        put("D2", "2013").
                        put("D3", "2014").
                        put("M1", "2015").
                        put("M2", "2016").
                        put("M3", "2017").
                        put("CF", "2018").
                        put("BF", "2019").
                        put("AB", "2020").
                        put("CA", "2021").
                        put("DC", "2022").
                        put("DS", "2023").
                        put("MC", "2024").
                        put("ME", "2025").
                        put("NM", "2026").
                        put("QF", "2027").
                        put("ST", "2028").
                        put("WD", "2029").
                        put("P", "2030").
                        put("F", "2031")
                        .build();
        return STRING_STRING_MAP.get(subjectName);
    }

    public static <T> PageData<T> getPageData(List<?> list, long total, Class<T> target){
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);

        return new PageData<>(targetList, total);
    }

    public static <T> PageData<T> getPageData(IPage page, Class<T> target){
        return getPageData(page.getRecords(), page.getTotal(), target);
    }

}
