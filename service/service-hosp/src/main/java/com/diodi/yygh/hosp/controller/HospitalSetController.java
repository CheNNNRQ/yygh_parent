package com.diodi.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diodi.yygh.common.result.Result;
import com.diodi.yygh.hosp.service.HospitalSetService;
import com.diodi.yygh.model.hosp.HospitalSet;
import com.diodi.yygh.vo.hosp.HospitalSetQueryVo;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.security.spec.DSAGenParameterSpec;
import java.sql.ResultSet;
import java.util.List;
import java.util.zip.Adler32;

/**
 * @author diodi
 * @create 2021-07-24-20:04
 */
@Api(tags = "医院设置管理")
// rest.. 主要是两个注解的整合 一个是controller 一个是ResponseBody用于返回json数据
@RestController
//访问的地址
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 查询医院设置表的所有信息
     * @return
     */
    @ApiOperation(value = "获取医院设置表的所有信息")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        Result<List<HospitalSet>> ok = Result.ok(list);
        return ok;
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @ApiOperation("根据id删除信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean b = hospitalSetService.removeById(id);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }


    /**
     * 条件查询带分页
     * @param current            当前页
     * @param limit              每页记录数
     * @param hospitalSetQueryVo 条件对象vo
     * @return
     */
    @ApiOperation("条件查询带分页")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable Long current,
                                  @PathVariable Long limit,
            /*以json方式接受数据*/
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        //创建分页对象 传入当前页和每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);
        //构造条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        //判断是否为空
        String hosname = hospitalSetQueryVo.getHosname();
        if (!StringUtils.isEmpty(hosname)) {
            wrapper.like("hosname", hosname);
        }
        String hoscode = hospitalSetQueryVo.getHoscode();
        if (!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode", hoscode);
        }
        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);
        //返回结果
        return Result.ok(pageHospitalSet);
    }



}
