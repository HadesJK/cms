package cn.edu.zju.isee.cms.utils.dicom;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by ljy&jql on 2016/4/14.
 */
public class DicomMark {
    public static final String SRC_PATH = "E:\\src\\";
    public static final String DEST_PATH = "E:\\dest\\";
    public static void main(String[] args) throws IOException {
        File srcRoot = new File(SRC_PATH);
        File[] srcFiles = srcRoot.listFiles();
        Document dom;
        BufferedWriter writer = null;
        TreeMap<Roi, Object> roiMap;
        for (File src : srcFiles){
            try {
                dom = getDocument(src);
                writer = new BufferedWriter(new FileWriter((DEST_PATH + src.getName()), true));
                roiMap = new TreeMap<Roi, Object>();
                Element root = dom.getRootElement();
                dfs(root, "roi", roiMap);
                writeRois(roiMap, writer);
            } catch (DocumentException e) {
                e.printStackTrace();
                System.out.println(src.getName() + " : failed");
                continue;
            } finally {
                if (writer != null){
                    writer.flush();
                    writer.close();
                }
            }
        }
    }

    public static Document getDocument(File file) throws DocumentException, MalformedURLException {
        SAXReader sr = new SAXReader();
        Document document = sr.read(file);
        return document;
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

    public static void writeRois(TreeMap<Roi, Object> roiMap, BufferedWriter writer) throws IOException {
        while (!roiMap.isEmpty()){
            Roi roi = roiMap.pollFirstEntry().getKey();
            writer.write(roi.getZ());
            writer.newLine();
            List<Roi.EdgeMap> edgeMaps = roi.getEdgeMaps();
            for (Roi.EdgeMap edgeMap : edgeMaps){
                writer.write(edgeMap.getX() + "," + edgeMap.getY());
                writer.newLine();
            }
        }
    }
}

class Roi implements Comparable{
    class EdgeMap{
        private String x;
        private String y;

        public EdgeMap(){}
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
}
