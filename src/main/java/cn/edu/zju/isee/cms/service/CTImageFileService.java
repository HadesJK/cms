package cn.edu.zju.isee.cms.service;

import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 16-4-16.
 */
@Service
public class CTImageFileService {

    @Resource
    private CTSlideMapper slideMapper;

    @Resource
    private CTMapper ctMapper;

    public InputStream getCtImage(int slideId) throws IOException {
        CTSlide slide = slideMapper.selectById(slideId);
        CT ct = ctMapper.selectById(slide.getCtId());
        String filePath = ct.getBaseDir() + slide.getSlideName();
        return new FileInputStream(filePath);
    }
}
