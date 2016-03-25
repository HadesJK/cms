package cn.edu.zju.isee.cms.service;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.utils.file.FileUtils;
import cn.edu.zju.isee.cms.utils.file.ZipUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                String dir = FileUtils.saveFile(file.getInputStream(), GlobalConstant.ZIP_ZHEYI_LUNG, name);
                // 2. 解压zip数据成dicom
                ZipUtils.unzip(GlobalConstant.ZIP_ZHEYI_LUNG + dir + name, GlobalConstant.DICOM_ZHEYI_LUNG + dir);
                // 3. 将dicom输出转成jpg
                // TODO: @hades
                // 4. 将记录插入到数据库中
                File parentDir = new File(GlobalConstant.DICOM_ZHEYI_LUNG  + dir);
                List<String> dcmNameList = new ArrayList<>();
//                getDcmNameList(parentDir, dcmNameList);


//                int dcms = parent.listFiles().length;
//
//                CT ct = new CT();
//                ct.setSlideNum(dcms);
//                ct.setBuildTime(new Date());
//                ct.setBaseDir(GlobalConstant.DICOM_ZHEYI_LUNG + name);
//                ct.setPatientName("zhangsan");
//                ct.setItem("肺结节");
//                int id = mapper.insert(ct);
//                System.out.println(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private List<String> getDcmNameList(File file, List list) {
        List<String> nameList = new ArrayList<>();
        if (file.isDirectory()) {

        } else if (file.isFile()) {
            nameList.add(file.getName());
        }
        return nameList;
    }

}
