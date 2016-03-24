package cn.edu.zju.isee.cms.service;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.entity.LungImgSlide;
import cn.edu.zju.isee.cms.mapper.LungImgSlideMapper;
import cn.edu.zju.isee.cms.utils.file.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
@Service
public class LungImgSlideService {
    @Resource
    private LungImgSlideMapper mapper;

    public void saveFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            try {
                LungImgSlide slide = new LungImgSlide();
                slide.setFileName(name);
                slide.setAbsolutePath(GlobalConstant.BASE_PATH);
                int id = mapper.insert(slide);
                FileUtils.saveFile(file.getInputStream(), GlobalConstant.BASE_PATH + id + "/" + name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void batchInsert(List<LungImgSlide> list) {
        for (LungImgSlide slide : list) {
            //insert(slide);
        }
    }

    public LungImgSlide selectBySlideId(int slideId) {
        return mapper.selectBySlideId(slideId);
    }

    public List<LungImgSlide> selectById(int id) {
        return mapper.selectById(id);
    }

}
