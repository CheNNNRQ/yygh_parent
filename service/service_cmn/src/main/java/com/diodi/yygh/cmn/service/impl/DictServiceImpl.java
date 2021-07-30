package com.diodi.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diodi.yygh.cmn.listener.DictListener;
import com.diodi.yygh.cmn.mapper.DictMapper;
import com.diodi.yygh.cmn.service.DictService;
import com.diodi.yygh.model.cmn.Dict;
import com.diodi.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author diodi
 * @create 2021-07-24-19:50
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    /**
     * 根据数据id查询子数据列表
     * @param id
     * @author diodi
     * @returns java.util.List<com.diodi.yygh.model.cmn.Dict>
     * @Date 20:17 2021/7/29
     */
    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        for (Dict dict : dictList) {
            Long id1 = dict.getId();
            dict.setHasChildren(isChildren(id1));
        }
        return dictList;
    }

    /**
     * 导出数据字典
     * @param response
     */
    @Override
    public void exportDictData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");

        String fileName = "aa";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        //查询数据库
        List<Dict> list = baseMapper.selectList(null);
        //Dict->dictEeVo
        ArrayList<DictEeVo> dictEeVos = new ArrayList<>();
        for (Dict dict : list) {
            DictEeVo dictEeVo = new DictEeVo();
            BeanUtils.copyProperties(dict, dictEeVo);
            dictEeVos.add(dictEeVo);
        }
        //调用方法 写数据库
        try {
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictEeVos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 导出数据字典
     * @param file
     */
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断id下面是否有子节点
     * @param id
     * @return true 有子节点 false 无子节点
     */
    private Boolean isChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
