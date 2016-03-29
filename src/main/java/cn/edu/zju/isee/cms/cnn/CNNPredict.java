//package cn.edu.zju.isee.cms.cnn;
//
//import cn.edu.zju.isee.cms.GlobalConstant;
//import cn.edu.zju.isee.cms.utils.shell.JavaShellUtils;
//
//import java.io.*;
//
///**
// * Created by jql on 2016/3/16.
// */
//public class CNNPredict {
//    // 进程交互的目录
//    private static final String DcmDir = GlobalConstant.JAVA_MATLAB_DIR;
//    private static final String DcmFile = GlobalConstant.JAVA_MATLAB_FILE_SUFFIX;
//    static {
//        File dir = new File(DcmDir);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//    }
//
//    public int getPred(File file) throws Exception {
//        double[] data = reader.readTmp128DataInLine(file, DcmConstant.XRESIZE, DcmConstant.YRESIZE);
//        int rst = cnn.predict(data);
//        if (rst < 0)
//            throw new Exception("predict error...");
//        return rst;
//    }
//
//    public int getPred(String uuid) throws Exception {
//        int val = -1;
//
//        String outFileName = fileService(uuid);
//        if (outFileName == null)
//            throw new Exception("file Service 服务执行失败。");
//
//        // shell 调用 matlab 执行任务
//        double[] result = JavaShellUtils.execShellAndMatlab();
//        double large = result[0];
//        double small = result[1];
//        double normal = result[2];
//        if (large > small && large > normal)
//            val = 0;
//        else if (small > large && small > normal)
//            val = 1;
//        else
//            val = 2;
//
//        // shell 任务执行结束，删除 文件
//        deleteFile(outFileName);
//        return  val;
//    }
//
//    // 将数据库中的文件存放到进程交互的文件夹DcmDir下，成功则返回写出的文件名；否则返回null。
//    private String fileService(String uuid) throws IOException {
//        String outFileName = null;
//
//        BufferedInputStream reader = null;
//        BufferedOutputStream writer = null;
//
//        try {
//            reader = new BufferedInputStream(new FileInputStream(new File("upload/" + uuid)));
//            outFileName = DcmDir + System.currentTimeMillis();
//            writer = new BufferedOutputStream(new FileOutputStream(new File(outFileName)));
//            int dat = -1;
//            while ((dat = reader.read()) != -1) {
//                writer.write(dat);
//            }
//            writer.flush();
//            reader.close();
//            writer.close();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        return outFileName;
//    }
//
//    // 删除任务产生的中间文件
//    private boolean deleteFile(String delName) {
//        File file = new File(delName);
//        if (file.exists() && file.isFile()) {
//            return file.delete();
//        }
//        return false;
//    }
//}
