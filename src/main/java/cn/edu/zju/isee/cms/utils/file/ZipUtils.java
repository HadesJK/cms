package cn.edu.zju.isee.cms.utils.file;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.*;
import java.util.zip.*;

/**
 * Created by jql on 2016/3/25.
 */
public class ZipUtils {

    /** 压缩有中文乱码问题
     * @param zipFileName 压缩后的文件(夹)
     * @param file        待压缩的文件(夹)
     * @throws IOException
     */
    @Deprecated
    public static void zip(String zipFileName, File file) throws IOException {
        if (!zipFileName.endsWith(".zip")) {
            zipFileName += ".zip";
        }
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zip(out, bo, file, file.getName());
        bo.close();
        out.close();
    }

    /**
     * @param out  zip流
     * @param bo   zip构建的缓冲流
     * @param file 需要压缩的文件（夹）
     * @param base name
     * @throws IOException
     */
    private static void zip(ZipOutputStream out, BufferedOutputStream bo, File file, String base) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                out.putNextEntry(new ZipEntry(base + File.separator));
            } else {
                for (File f : files) {
                    zip(out, bo, f, base + File.separator + f.getName());
                }
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            BufferedInputStream bi = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len;
            while ((len = bi.read(buf, 0, 1024)) != -1) {
                bo.write(buf, 0, len);
            }
            bi.close();
        }
    }

    public static void unzip(String zipFileName, String to) throws IOException {
        ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileName));
        BufferedInputStream bin = new BufferedInputStream(zin);
        File fileOut;
        ZipEntry entry = null;
        while ((entry = zin.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                //TODO:
            } else {
                fileOut = new File(to, entry.getName());
                if (!fileOut.getParentFile().exists()) {
                    fileOut.getParentFile().mkdirs();
                }
                BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(fileOut));
                byte[] buf = new byte[1024];
                int len;
                while ((len = bin.read(buf)) != -1) {
                    bout.write(buf, 0, len);
                }
                bout.close();
            }
        }
        bin.close();
        zin.close();
    }

    public static void zip(String from, String to) {
        File srcDir = new File(from);
        if (!srcDir.exists()) {
            throw new RuntimeException(from + "不存在！");
        }
        Project prj = new Project();

        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(new File(to));

        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcDir);
        //fileSet.setIncludes("**/*.java");
        //fileSet.setExcludes(...);
        zip.addFileset(fileSet);
        zip.execute();
    }
//    public static void main(String[] args) throws IOException {
//        String from = "C:\\Users\\jql\\Desktop\\dicom";
//        String to = "C:\\Users\\jql\\Desktop";
//        String newName = "hades";
//        zip(to + File.separator + newName, new File(from));
//        unzip(to + File.separator + newName + ".zip", to + File.separator + "abc");
//    }
}
