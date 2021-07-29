package com.diodi.yygh.cmn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diodi.yygh.cmn.mapper.DictMapper;
import com.diodi.yygh.cmn.service.DictService;
import com.diodi.yygh.model.cmn.Dict;
import com.diodi.yygh.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author diodi
 * @create 2021-07-24-19:50
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    /**
     * 根据数据id查询子数据列表
     * @author  diodi
     * @param id
     * @returns java.util.List<com.diodi.yygh.model.cmn.Dict>
     * @Date  20:17 2021/7/29
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
     * 判断id下面是否有子节点
     * @param id
     * @return true 有子节点 false 无子节点
     */
    private Boolean isChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
