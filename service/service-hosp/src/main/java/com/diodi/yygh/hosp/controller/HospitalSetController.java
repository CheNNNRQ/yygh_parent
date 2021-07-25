package com.diodi.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diodi.yygh.common.exception.YyghException;
import com.diodi.yygh.common.result.Result;
import com.diodi.yygh.common.utils.MD5;
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
import java.util.Random;
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

        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new YyghException("1/0" ,201);
        }
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
    @DeleteMapping("/remove/{id}")
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

    /**
     * 添加医院设置
     * @param hospitalSet bean
     * @return
     */
    @ApiOperation("添加医院设置")
    @PostMapping("saveHospSet")
    public Result saveHospSet(@RequestBody HospitalSet hospitalSet) {
        //设置状态 0不能使用 1可以使用
        hospitalSet.setStatus(1);
        //签名秘钥
        Random random = new Random();
        String encrypt = MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000));
        hospitalSet.setSignKey(encrypt);
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 根据id获取医院信息
     * @param id 医院的id信息
     * @return
     */
    @ApiOperation("根据id获取医院信息")
    @GetMapping("get/{id}")
    public Result getHospitalSetById(@PathVariable Long id) {
        HospitalSet byId = hospitalSetService.getById(id);
        return Result.ok(byId);
    }

    /**
     * 修改医院设置
     * @param hospitalSet bean
     * @return
     */
    @ApiOperation("修改医院设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 批量删除医院设置
     * @param idList id的list集合
     * @return
     */
    @ApiOperation("批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        boolean b = hospitalSetService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 医院的锁定和解锁功能
     * @param id     传入要更改的医院的id值
     * @param status 改变后的状态码
     * @return
     */
    @ApiOperation("医院的锁定和解锁功能")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        HospitalSet byId = hospitalSetService.getById(id);
        byId.setStatus(status);
        boolean b = hospitalSetService.updateById(byId);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 发送签名秘钥
     * @param id
     * @author diodi
     * @returns com.diodi.yygh.common.result.Result
     * @Date 17:00 2021/7/25
     */

    @ApiOperation("发送签名秘钥")
    @PutMapping("sendKey/{id}")
    public Result sendKey(@PathVariable Long id) {
        HospitalSet byId = hospitalSetService.getById(id);
        String hoscode = byId.getHoscode();
        String signKey = byId.getSignKey();
        //TODO 发送短信
        return Result.ok();
    }
}
