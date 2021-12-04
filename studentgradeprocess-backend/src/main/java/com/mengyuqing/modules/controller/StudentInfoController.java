package com.mengyuqing.modules.controller;

import com.mengyuqing.modules.dto.StudentInfoDto;
import com.mengyuqing.modules.service.StudentInfoService;
import com.mengyuqing.modules.util.Result;
import com.mengyuqing.modules.util.page.PageData;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Yuqing
 * @date 2021/11/13 16:26
 */
@RestController
@RequestMapping("studentInfo")
public class StudentInfoController {

    private final StudentInfoService studentInfoService;

    public StudentInfoController(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params) {
        return new Result<PageData<StudentInfoDto>>().ok(studentInfoService.page(params));
    }

    @GetMapping("{id}")
    public Result<StudentInfoDto> get(@PathVariable("id") Long id){
        return new Result<StudentInfoDto>().ok(studentInfoService.get(id));
    }
}
