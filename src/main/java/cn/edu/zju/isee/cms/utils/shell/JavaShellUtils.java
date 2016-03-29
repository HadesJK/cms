package cn.edu.zju.isee.cms.utils.shell;

import cn.edu.zju.isee.cms.GlobalConstant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jql on 2016/3/18.
 */


public class JavaShellUtils {

    // 获取交互文件夹下的文件进行预测，并将结果从文件中读入
    // ATTENTION: 当返回的LIST长度为1时，就是对单张切片进行预测
    public static List<String> execShellAndMatlab() throws Exception {
        List<String> rstList = new ArrayList<>();
        Process process = Runtime.getRuntime().exec(GlobalConstant.SHELL);
        File rstFile = new File(GlobalConstant.RESULT_FILE);
        process.waitFor(20, TimeUnit.SECONDS);  // 等待20s，然后获取 matlab 执行的结果
        if (rstFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(rstFile));
            String result;
            while ((result = reader.readLine()) != null) {
                rstList.add(result);
            }
            reader.close();
        }
        if (rstFile.exists() && rstFile.isFile()) {
            rstFile.delete();
        }
        return rstList;
    }

}
