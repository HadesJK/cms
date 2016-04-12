package cn.edu.zju.isee.cms.utils.dicom;

/**
 * Created by jql on 2016/4/11.
 */
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.dcm4che3.imageio.plugins.dcm.DicomMetaData;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DicomUtils {

    static BufferedImage myJpegImage=null;

    public static void  dicomToJpg(String path, String saveName) {
        File file = new File(path);
        Iterator<ImageReader> iterator =ImageIO.getImageReadersByFormatName("DICOM");
        double slicePosition = -1.0;
        DecimalFormat df = new DecimalFormat("######000.00");
        while (iterator.hasNext()) {
            try {
                ImageReader imageReader = iterator.next();
                imageReader.addIIOReadWarningListener(new WarningListener());
                imageReader.setInput(new FileImageInputStream(file));

                // 获取切片位置信息
                IIOMetadata iio = imageReader.getStreamMetadata();
                slicePosition = Double.parseDouble(((DicomMetaData) iio).getAttributes().getString(0x201041));
                slicePosition = Math.abs(slicePosition);
                System.out.println(slicePosition);

                ImageReadParam dicomImageReadParam = imageReader.getDefaultReadParam();
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
            if (!saveName.endsWith(".jpg")) {
                int index = saveName.lastIndexOf("\\");
                saveName = saveName.substring(0, index);
                saveName = saveName + "\\" + df.format(slicePosition) + ".jpg";
            } else {
                System.err.println("请注意文件名。");
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

        String rootDir = "D:\\min\\lidc-min\\";
        List<String> list = getDicomList(new File(rootDir), new ArrayList<>());
        for (String fileName : list) {
            String subStr = fileName.replace("lidc-min", "lidc-min-jpgs");
            System.out.println("@@@@######" + subStr);
            dicomToJpg(fileName, subStr);
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


//    public static void convert(String file) {
//        try {
//            ImageIO.scanForPlugins();
//
//            File f = new File(file);
//            Iterator readers = ImageIO.getImageReadersByFormatName("dicom");
//            DicomReader reader = (DicomReader)readers.next();
//            reader.addIIOReadWarningListener(new WarningListener());
//            reader.setInput(new FileImageInputStream(f));
//
//            DicomMetadata dmd = reader.getDicomMetadata();
//
//            int n = reader.getNumImages(true);
//
//            for (int i=0; i<n; i++) {
//                BufferedImage bi_stored = reader.read(i);
//                if (bi_stored == null) {
//                    System.err.println("read error");
//                    System.exit(1);
//                }
//
//                BufferedImage bi = dmd.applyGrayscaleTransformations(bi_stored, i);
//
//                BufferedImage bi_out = new BufferedImage(bi.getWidth(),
//                        bi.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//                bi_out.createGraphics().drawImage(bi, 0, 0, null);
//
//                File f_out = new File("i"+i+".jpg");
//                f_out.delete();
//                ImageIO.write(bi_out, "jpeg", f_out);
//                System.out.println("written: "+f_out);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}