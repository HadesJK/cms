package cn.edu.zju.isee.cms.service;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.utils.file.FileUtils;
import cn.edu.zju.isee.cms.utils.file.ZipUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * Created by jql on 2016/3/24.
 */
@Service
public class LungImgSlideService {
    @Resource
    private CTMapper mapper;

    public void saveFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            try {
                // 1. 保存上传的zip数据
                FileUtils.saveFile(file.getInputStream(), GlobalConstant.ZIP_ZHEYI_LUNG, name);
                // 2. 解压zip数据成dicom
                ZipUtils.unzip(GlobalConstant.ZIP_ZHEYI_LUNG + name, GlobalConstant.DICOM_ZHEYI_LUNG);
                // 3. 将dicom输出转成jpg
                // 4. 将记录插入到数据库中
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
