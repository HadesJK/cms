package cn.edu.zju.isee.cms;

/**
 * Created by jql on 2016/3/24.
 */
public class GlobalConstant {
    public static final String BASE_PATH = "/home/jql/";

    // CT 图像压缩包存放的基本路径
    public static final String ZIP_BASE_DIR = BASE_PATH + "ct/zip/";
    // CT 图像DICOM存放的基本路径
    public static final String DICOM_BASE_DIR = BASE_PATH + "ct/dicom/";
    // CT 图像JPG存放的基本路径
    public static final String JPG_BASE_DIR = BASE_PATH + "ct/jpg/";

    // CT 医院ZIP图像的基本路径
    public static final String ZIP_ZHEYI = ZIP_BASE_DIR + "zheyi/";
    // CT 医院DICOM图像的基本路径
    public static final String DICOM_ZHEYI = DICOM_BASE_DIR + "zheyi/";
    // CT 医院JPG图像的基本路径
    public static final String JPG_ZHEYI = JPG_BASE_DIR + "zheyi/";

    // CT 医院ZIP图像的基本路径
    public static final String ZIP_ZHEER = ZIP_BASE_DIR + "zheer/";
    // CT 医院DICOM图像的基本路径
    public static final String DICOM_ZHEER = DICOM_BASE_DIR + "zheer/";
    // CT 医院JPG图像的基本路径
    public static final String JPG_ZHEER = JPG_BASE_DIR + "zheer/";

    // CT 图像器官的基本路径
    public static final String ZIP_ZHEYI_LUNG = ZIP_ZHEYI + "lung/";
    public static final String DICOM_ZHEYI_LUNG = DICOM_ZHEYI + "lung/";
    public static final String JPG_ZHEYI_LUNG = JPG_ZHEYI + "lung/";

    // JAVA MATLAB 交互的共享文件夹
    public static final String JAVA_MATLAB_DIR = BASE_PATH + "ct/jm/";
    // JAVA 给 MATLAB 的信息写在 时间戳info.txt
    public static final String JAVA_MATLAB_FILE_SUFFIX = "info.txt";
    // shell
    public static final String SHELL = "/home/jql/test.sh";
    public static final String RESULT_FILE = "/home/jql/dicom/DcmDir/lung.rst";

}
