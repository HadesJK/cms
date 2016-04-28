package cn.edu.zju.isee.cms;

import java.io.File;

/**
 * Created by jql on 2016/4/28.
 */
public class FileRename {
    public static void main(String[] args) {
        renameFile("D:\\lung\\result\\temp", "LIDC-IDRI-00185&");
    }
    public static void renameFile(String dir, String flag) {
        File file = new File(dir);
        if (!file.isDirectory()) {
            System.err.println(dir + "不是文件夹！");
        }
        File[] files = file.listFiles();
        if (file.length() == 0) {
            return;
        }
        for (File f : files) {
            String oldName = f.getName();
            if (oldName.startsWith(flag)) {
                continue;
            } else {
                String newName = f.getParent() + File.separator +  flag + oldName;
                File newFile = new File(newName);
                if (newFile.exists()) {
                    System.err.println(newName + "文件已经存在！");
                } else {
                    f.renameTo(newFile);
                }
            }
        }
    }
}
