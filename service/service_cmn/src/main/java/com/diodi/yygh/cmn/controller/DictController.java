package com.diodi.yygh.cmn.controller;

import com.diodi.yygh.cmn.service.DictService;
import com.diodi.yygh.common.result.Result;
import com.diodi.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典
 * @author diodi
 * @create 2021-07-29-19:48
 */
@Api(tags = "数据字典接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * 根据数据id查询子数据列表
     * @author  diodi
     * @param id 前端传过来的id
     * @returns com.diodi.yygh.common.result.Result
     * @Date  20:09 2021/7/29
     */
    @GetMapping("findChildData/{id}")
    @ApiOperation("根据数据id查询子数据列表")
    public Result findChildData(@PathVariable Long id){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }
}
