package com.diodi.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.diodi.yygh.model.cmn.Dict;
import com.diodi.yygh.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author diodi
 * @create 2021-07-24-19:50
 */
public interface DictService extends IService<Dict> {
    /**
     * 根据数据id查询子数据列表
     * @param id
     * @return
     */
    List<Dict> findChildData(Long id);

    /**
     * 导出数据字典
     * @param response
     */
    void exportDictData(HttpServletResponse response) ;

    /**
     * 导出数据字典
     * @param file
     */
    void importData(MultipartFile file);
}
