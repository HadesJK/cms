package cn.edu.zju.isee.cms.utils.file;

import cn.edu.zju.isee.cms.GlobalConstant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jql on 2016/3/24.
 */
public class FileUtils {

    public static void saveFile(InputStream in, String name) throws IOException {
        org.apache.commons.io.FileUtils.copyInputStreamToFile(in, new File(GlobalConstant.BASE_PATH + name));
    }
}
