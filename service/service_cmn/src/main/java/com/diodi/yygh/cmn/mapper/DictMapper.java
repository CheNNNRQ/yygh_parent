package com.diodi.yygh.cmn.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diodi.yygh.model.cmn.Dict;
import com.diodi.yygh.model.hosp.HospitalSet;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author diodi
 * @create 2021-07-24-19:40
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
}
