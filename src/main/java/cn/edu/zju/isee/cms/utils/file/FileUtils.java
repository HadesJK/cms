package cn.edu.zju.isee.cms.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
public class FileUtils {

    public static String saveFile(InputStream in, String toPath, String name) throws IOException {
        File file = new File(toPath);
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            throw new IllegalArgumentException("path " + toPath + " is not a directory.");
        }

        String newName = null;
        File[] list = file.listFiles();
        if (list == null || list.length == 0) {
            newName = "1" + File.separator;
        } else {
            int[] num = new int[list.length];
            for (int i = 0; i < num.length; i ++) {
                try {
                    num[i] = Integer.parseInt(list[i].getName());
                } catch (Exception e) {
                    // nothing to do
                }
            }
            int max = Arrays.stream(num).max().getAsInt();
            max = max + 1;
            newName = max + File.separator;
        }

        org.apache.commons.io.FileUtils.copyInputStreamToFile(in, new File(toPath + newName + name));
        return newName;
    }

    public static List<String> getFileList(File parentDir, List<String> list, String fileType) {
        File[] files = parentDir.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(fileType)) {
                System.out.println(file.getAbsoluteFile());
                list.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                getFileList(file, list, fileType);
            }
        }
        return list;
    }

    public static List<String> getFileList(File parentDir,String fileType) {
        return getFileList(parentDir, new ArrayList<>(), fileType.toLowerCase());
    }
}
