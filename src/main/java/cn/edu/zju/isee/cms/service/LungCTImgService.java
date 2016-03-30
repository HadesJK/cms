package cn.edu.zju.isee.cms.service;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.controller.model.RecogModel;
import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import cn.edu.zju.isee.cms.utils.shell.JavaShellUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jql on 2016/3/28.
 */
@Service
public class LungCTImgService {

    @Resource
    private CTMapper ctMapper;

    @Resource
    private CTSlideMapper slideMapper;

    public List<CT> getCTList() {
        return ctMapper.selectAll();
    }

    public CT getCT(int id) {
        return ctMapper.selectById(id);
    }

    public List<RecogModel> ctPredict(int ctId) throws Exception {
        CT ct = ctMapper.selectById(ctId);

        // 1. 将基本信息写入到 java 和 matlab 交互的文件夹下， 文件名为时间戳+info.txt
        List<CTSlide> slides = slideMapper.selectByCtId(ct.getId());
        String toFileName = writeToFile(ct.getBaseDir(), slides);

        // 2. 执行 linux shell，调用 matlab 代码进行预测， 并获取结果
        JavaShellUtils.execShellAndMatlab(slides.size() * 1 + 30);
        File rstFile = new File(toFileName + GlobalConstant.RESULT_FILE_SUFFIX);
        List<String> list = new ArrayList<>();
        if (rstFile.exists() && rstFile.isFile()) {
            BufferedReader reader = new BufferedReader(new FileReader(rstFile));
            String str;
            while ((str = reader.readLine()) != null) {
                list.add(str);
            }
            reader.close();
            rstFile.delete();
        }

        // 3. 删除预测结果的文件 和 2 中产生的结果
        File toFile = new File(toFileName);
        if (toFile.exists() && toFile.isFile()) {
            toFile.delete();
        }

        // 4. 返回结果
        return RecogModel.build(slides, list);
    }

    private String writeToFile(String baseDir,  List<CTSlide> slides) throws IOException {
        File dir = new File(GlobalConstant.JAVA_MATLAB_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = GlobalConstant.JAVA_MATLAB_DIR + System.currentTimeMillis() + GlobalConstant.JAVA_MATLAB_FILE_SUFFIX;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (CTSlide slide : slides) {
            writer.write(baseDir + slide.getSlideName());
            writer.newLine();
        }
        writer.close();
        return fileName;
    }

}
