package cn.edu.zju.isee.cms.utils.dicom;

/**
 * Created by jql on 2016/4/11.
 */
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DicomUtils {

    static BufferedImage myJpegImage=null;

    public static void  dicomToJpg(String path, String saveName) {
        File file = new File(path);
        Iterator<ImageReader> iterator =ImageIO.getImageReadersByFormatName("DICOM");
        while (iterator.hasNext()) {
            ImageReader imageReader = iterator.next();
            ImageReadParam dicomImageReadParam = imageReader.getDefaultReadParam();
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(file);
                imageReader.setInput(iis,false);
                myJpegImage = imageReader.read(0, dicomImageReadParam);
                iis.close();
                if(myJpegImage == null){
                    System.out.println("Could not read image!!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file2 = new File(saveName);
            File parent = file2.getParentFile();
            if (!parent.exists() || !parent.isDirectory()) {
                parent.mkdirs();
            }
            try {
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2));
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
                encoder.encode(myJpegImage);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Completed");
        }
    }

    public static void main(String[] args) {
        String rootDir = "D:\\min\\lidc-min";
        List<String> list = getDicomList(new File(rootDir), new ArrayList<>());
        for (String fileName : list) {
            String subStr = fileName.replace("lidc-min", "lidc-min-jpgs");
            System.out.println("@@@@######" + subStr);
            dicomToJpg(fileName, subStr + ".jpg");
        }
    }

    private static List<String> getDicomList(File parentDir, List<String> list) {
        File[] files = parentDir.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".dcm")) {
                System.out.println(file.getAbsoluteFile());
                list.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                getDicomList(file, list);
            }
        }
        return list;
    }
}