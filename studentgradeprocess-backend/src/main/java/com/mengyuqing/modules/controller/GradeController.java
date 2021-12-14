package com.mengyuqing.modules.controller;
import com.mengyuqing.modules.dto.GradeDto;
import com.mengyuqing.modules.util.CommonMethod;
import com.mengyuqing.modules.util.Result;
import com.mengyuqing.modules.service.GradeService;
import com.mengyuqing.modules.util.page.PageData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Yuqing
 * @date 2021/10/24 18:01
 */
@RequestMapping("grade")
@RestController
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params) {
        return new Result<PageData<GradeDto>>().ok(gradeService.page(params));
    }

    @PostMapping("/import")
    public Result importCSV(@RequestParam("file") MultipartFile file) {
        /**
         * import CSV files
         */
        try {
            List<String> grade = CommonMethod.importCsv(file.getInputStream());
            new Thread(()->{
                gradeService.importCSV(grade, file.getOriginalFilename());
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result();
    }
}
