package cn.edu.zju.isee.cms.service;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import cn.edu.zju.isee.cms.utils.file.FileUtils;
import cn.edu.zju.isee.cms.utils.file.ZipUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
@Service
public class LungImgSlideService {
    @Resource
    private CTMapper ctMapper;

    @Resource
    private CTSlideMapper ctSlideMapper;

    public void saveFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            try {
                // 1. 保存上传的zip数据
                String dir = FileUtils.saveFile(file.getInputStream(), GlobalConstant.ZIP_ZHEYI_LUNG, name);
                // 2. 解压zip数据成dicom
                ZipUtils.unzip(GlobalConstant.ZIP_ZHEYI_LUNG + dir + name, GlobalConstant.DICOM_ZHEYI_LUNG + dir);
                // 3. 将dicom输出转成jpg
                // TODO: @hades
                // 4. 将记录插入到数据库中
                // 4.1 获取dicom切片的路径
                File parentDir = new File(GlobalConstant.DICOM_ZHEYI_LUNG  + dir);
                List<String> nameList = new ArrayList<>();
                getDcmNameList(parentDir, "", nameList);
                // 4.2 存入数据库
                CT ct = new CT();
                ct.setSlideNum(nameList.size());
                ct.setBuildTime(new Date());
                ct.setBaseDir(GlobalConstant.DICOM_ZHEYI_LUNG + dir);
                ct.setZipDir(dir);
                ct.setZipName(name);
                ct.setPatientId(null);
                ct.setPatientName(null);
                ct.setDoctorId(null);
                ct.setDoctorName(null);
                ct.setDescr(null);
                ct.setHospital(null);
                ct.setItem("肺结节");
                ctMapper.insert(ct);
                // 4.2 将每一张切片存到数据库
                for (String slideName : nameList) {
                    CTSlide slide = new CTSlide();
                    slide.setCtId(ct.getId());
                    String[] serialNums = slideName.split("\\.");
                    slide.setSerialNum(Integer.parseInt(serialNums[serialNums.length - 2]));
                    slide.setSlideName(slideName);
                    ctSlideMapper.insert(slide);
                }
            } catch (IOException e) {
                System.out.println("存储图片失败...");
                e.printStackTrace();
            }
        }
    }

    private void getDcmNameList(File dir, String parentPath, List<String> nameList) {
        asserDirectory(dir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getDcmNameList(file, parentPath + file.getName() + File.separator, nameList);
            } else if (file.isFile()) {
                nameList.add(parentPath + file.getName());
            }
        }
    }

    private void asserDirectory(File file) {
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file.getName() + " is not a directory.");
        }
    }

    public List<CTSlide> getCTSlides(int ctId) {
        return ctSlideMapper.selectByCtId(ctId);
    }

}
