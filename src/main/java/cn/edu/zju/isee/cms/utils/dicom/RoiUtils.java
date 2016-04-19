package cn.edu.zju.isee.cms.utils.dicom;

/**
 * Created by zhyc&pdx&jql on 2016/4/13.
 */

import cn.edu.zju.isee.cms.utils.file.FileUtils;
import javafx.util.Pair;

import java.io.*;
import java.util.*;


public class RoiUtils {
    private final static int len = 512; // 图片是 512*512
    private final static int less = 32; // 小于这个值的认为是小血管，去除假阳性的第一步
    private final static int patchSize = 40;    // patch size，用于CNN
    private static int count;
    private static int[][] num = new int[len][len];
    private static boolean[][] mark = new boolean[len][len];
    private static int[][] Div = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static int xmin, xmax, ymin, ymax;
    private static List<XYPos> xyPoses;

    public static void process(String file) {
        xyPoses = new ArrayList<>();
        File f = new File(file);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)))) {
            for (int i = 0; i < len; ++i) {
                String str = reader.readLine();
                String[] msg = str.split(",");
                assertLenTrue(msg, len);
                for (int j = 0; j < len; j++) {
                    num[i][j] = Integer.parseInt(msg[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < len; ++i)
            for (int j = 0; j < len; ++j)
                mark[i][j] = false;

        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                if (num[i][j] == 255 && !mark[i][j]) {
                    int t = dfs(i, j);
                    if (t > less) {
                        print(f, patchSize);
                        count++;
                    }
                }
            }
        }
        writePosToFile(file);
    }

    public static int dfs(int x, int y) {
        int sum = 0;
        Queue<Pair<Integer, Integer>> Q = new LinkedList<>();
        mark[x][y] = true;

        xmin = 513;
        xmax = -1;

        ymin = 513;
        ymax = -1;

        Q.offer(new Pair<>(x, y));
        while (Q.size() != 0) {
            sum++;
            int xx = Q.peek().getKey();
            int yy = Q.peek().getValue();
            Q.poll();

            xmax = xmax > xx ? xmax : xx;
            xmin = xmin < xx ? xmin : xx;

            ymax = ymax > yy ? ymax : yy;
            ymin = ymin < yy ? ymin : yy;

            for (int i = 0; i < 8; ++i) {
                int tx = xx + Div[i][0];
                int ty = yy + Div[i][1];
                if (tx < 0 || ty < 0 || tx >= len || ty >= len || mark[tx][ty] || num[tx][ty] == 0) {
                    continue;
                }
                mark[tx][ty] = true;
                Q.offer(new Pair<>(tx, ty));
            }
        }
        return sum;
    }

    public static void print(File file, int len) {
        String bpatch = file.getAbsolutePath() + "@" + count + ".bpatch";
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(bpatch))))) {
            int xlen = xmax - xmin + 1;
            int ylen = ymax - ymin + 1;

            String ss = "0 ";
            String s1;
            for (int i = 0; i < (len - xlen) / 2; ++i) {
                for (int j = 0; j < len; ++j)
                    bw.write(ss, 0, ss.length());
                bw.write("\r\n");
            }

            for (int i = xmin; i <= xmax; i++) {

                for (int j = 0; j < (len - ylen) / 2; j++)
                    bw.write(ss, 0, ss.length());
                for (int j = ymin; j <= ymax; j++) {
                    s1 = Integer.toString(num[i][j]) + " ";
                    bw.write(s1, 0, s1.length());
                }
                for (int j = 0; j < (len - ylen + 1) / 2; j++)
                    bw.write(ss, 0, ss.length());
                bw.write("\r\n");
            }

            for (int i = 0; i < (len - xlen + 1) / 2; i++) {
                for (int j = 0; j < len; j++)
                    bw.write(ss, 0, ss.length());
                bw.write("\r\n");
            }

            XYPos xy = new XYPos(xmin - (len - xlen) / 2, ymin - (len - ylen) / 2);
            xyPoses.add(xy);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writePosToFile(String oriFile) {
        System.out.println(xyPoses.size());
        Iterator<XYPos> iterator = xyPoses.iterator();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(oriFile + "@@.pos"))));
            while (iterator.hasNext()) {
                XYPos xyPos = iterator.next();
                writer.write(xyPos.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void assertLenTrue(String[] msg, int len) {
        if (msg.length != len) {
            throw new IllegalArgumentException("The string array length is illegal.");
        }
    }

    public static void main(String[] args) {
        String rootDir = "/run/media/root/d9cfb292-d889-4b63-b220-d81968d410c3/lidc/kaola/lidc-min-over";
        List<String> list = FileUtils.getFileList(new File(rootDir), new ArrayList<>(), ".jpg.txt");
        for (String fileName : list) {
            process(fileName);
        }
    }

    private static class XYPos {
        private int x;
        private int y;

        public XYPos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }
}