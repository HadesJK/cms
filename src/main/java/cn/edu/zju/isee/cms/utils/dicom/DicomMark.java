package cn.edu.zju.isee.cms.utils.dicom;

import cn.edu.zju.isee.cms.utils.file.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by ljy&jql on 2016/4/14.
 */
public class DicomMark {
    private static final String SRC_PATH = "D:\\min\\lidc-min";
    private static final String MK_PATH = "D:\\min\\lidc-min-mark";

    private static DecimalFormat df = new DecimalFormat("######000.00");

    public static void mark() {
        Document dom;
        List<String> fileNameList = FileUtils.getFileList(new File(SRC_PATH), ".xml");
        for (String name : fileNameList){
            System.out.println("处理文件：" + name);
            File file = new File(name);
            try {
                dom = getDocument(file);
                TreeMap<Roi, Object> roiMap = new TreeMap<>();
                Element root = dom.getRootElement();
                dfs(root, "roi", roiMap);
                writeRois(roiMap, file.getParentFile().getAbsolutePath().replace("lidc-min", "lidc-min-mark"));
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Document getDocument(File file) throws DocumentException, MalformedURLException {
        SAXReader sr = new SAXReader();
        return sr.read(file);
    }

    public static void handleRoi(Element roi, TreeMap<Roi, Object> roiMap) throws IOException {
        Element imageZposition = roi.element("imageZposition");
        if (imageZposition == null)
            return;
        List<Element> edgeMaps = roi.elements("edgeMap");
        if (edgeMaps.size() < 2)
            return;
        Roi roiEntity = new Roi(imageZposition.getTextTrim());
        for (Element edgeMap : edgeMaps) {
            String xCoord = edgeMap.element("xCoord").getTextTrim();
            String yCoord = edgeMap.element("yCoord").getTextTrim();
            roiEntity.setEdgeMap(xCoord, yCoord);
        }
        roiMap.put(roiEntity, null);
    }

    public static void dfs(Element root, String tar, TreeMap<Roi, Object> roiMap) throws IOException {
        Stack<Element> stack = new Stack<Element>();
        stack.push(root);
        while (!stack.isEmpty()){
            Element top = stack.pop();
            if (top.getName().equals(tar))
                handleRoi(top, roiMap);
            else {
                List<Element> children = top.elements();
                if (children != null && children.size() > 0)
                    for (Element element : children)
                        stack.push(element);
            }
        }
    }

    public static void writeRois(TreeMap<Roi, Object> roiMap, String dir) throws IOException {
        BufferedWriter writer = null;
        while (!roiMap.isEmpty()){
            try {
                Roi roi = roiMap.pollFirstEntry().getKey();
                File file = new File(dir, df.format(Math.abs(Double.parseDouble(roi.getZ()))) + ".mk");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                List<Roi.EdgeMap> edgeMaps = roi.getEdgeMaps();
                for (Roi.EdgeMap edgeMap : edgeMaps) {
                    writer.write(edgeMap.getX() + "," + edgeMap.getY());
                    writer.newLine();
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    public static void scale() throws IOException {
        List<String> fileNameList = FileUtils.getFileList(new File(MK_PATH), ".mk");

        for (String name : fileNameList) {
            BufferedReader reader = null;
            BufferedWriter writer = null;
            int xMin = 512;
            int xMax = 1;
            int yMin = 512;
            int yMax = 1;
            try {
                File file = new File(name);
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().length() == 0) {
                        continue;
                    }
                    String[] xyStr = line.split(",");
                    int x = Integer.parseInt(xyStr[0]);
                    int y = Integer.parseInt(xyStr[1]);
                    xMin = xMin > x ? x : xMin;
                    xMax = xMax < x ? x : xMax;
                    yMin = yMin > y ? y : yMin;
                    yMax = yMax < y ? y : yMax;
                }
                File result = new File(file.getParent(), "rst.edge");
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(result, true)));
                String zPos = df.format(Math.abs(Double.parseDouble(file.getName().replace(".mk", ""))));
                writer.write(zPos + ".jpg" + " " + xMin + " " + xMax + " " + yMin + " " + yMax);
                writer.newLine();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // find roi in label xml
        mark();
        // find x-min x-max y-min y-max
        scale();
    }
}

class Roi implements Comparable{
    private String z;
    private List<EdgeMap> edgeMaps;

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public List<EdgeMap> getEdgeMaps() {
        return edgeMaps;
    }

    public void setEdgeMaps(List<EdgeMap> edgeMaps) {
        this.edgeMaps = edgeMaps;
    }

    public void setEdgeMap(String x, String y) {
        this.edgeMaps.add(new EdgeMap(x, y));
    }

    public Roi(){
        edgeMaps = new ArrayList<EdgeMap>();
    }
    public Roi(String z){
        this.z = z;
        edgeMaps = new ArrayList<EdgeMap>();
    }

    @Override
    public int compareTo(Object o) {
        double z1 = Double.valueOf(this.z);
        double z2 = Double.valueOf(((Roi)o).getZ());
        return z1 < z2 ? -1 : z1 == z2 ? 0 : 1;
    }

    class EdgeMap{
        private String x;
        private String y;

        public EdgeMap(){

        }

        public EdgeMap(String x, String y){
            this.x = x;
            this.y = y;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }
    }
}
