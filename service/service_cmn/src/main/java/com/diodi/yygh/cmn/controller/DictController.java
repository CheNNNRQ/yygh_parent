package com.diodi.yygh.cmn.controller;

import com.diodi.yygh.cmn.service.DictService;
import com.diodi.yygh.common.result.Result;
import com.diodi.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * 导入数据字典
     * @param file
     * @return
     */
    @ApiOperation("导入数据字典")
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        dictService.importData(file);
        return Result.ok();
    }

    /**
     * 导出数据字典
     * @param response
     * @return
     */
    @GetMapping("exportData")
    @ApiOperation("导出数据字典")
    public void exportDict(HttpServletResponse response) {
        dictService.exportDictData(response);
    }

    /**
     * 根据数据id查询子数据列表
     * @param id 前端传过来的id
     * @author diodi
     * @returns com.diodi.yygh.common.result.Result
     * @Date 20:09 2021/7/29
     */
    @GetMapping("findChildData/{id}")
    @ApiOperation("根据数据id查询子数据列表")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }
}
