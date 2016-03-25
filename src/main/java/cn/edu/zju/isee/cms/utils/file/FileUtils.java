package cn.edu.zju.isee.cms.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jql on 2016/3/24.
 */
public class FileUtils {

    public static void saveFile(InputStream in, String toPath, String name) throws IOException {
        org.apache.commons.io.FileUtils.copyInputStreamToFile(in, new File(toPath + name));
    }
}
