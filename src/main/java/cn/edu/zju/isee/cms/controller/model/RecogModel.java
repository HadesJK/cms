package cn.edu.zju.isee.cms.controller.model;

import cn.edu.zju.isee.cms.entity.CTSlide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 16-3-30.
 */
public class RecogModel {
    private static final String DJJ = "大结节";
    private static final String XJJ = "小结节";
    private static final String NOR = "正常";

    private int ctId;
    private int slideId;
    private int serialNum;
    private String pic;
    private String label;
    private String probably;

    private RecogModel() {

    }

    public static List<RecogModel> build(List<CTSlide> slides, List<String> msg) {
        List<RecogModel> rm = new ArrayList<>(slides.size());
        for (int i = 0; i <slides.size(); i ++) {
            CTSlide slide = slides.get(i);
            String[] valStr = msg.get(i).split("@#@#@");
            double[] val = new double[valStr.length];
            for (int j = 0; j < valStr.length; j ++) {
                val[j] = Double.parseDouble(valStr[j]);
            }
            RecogModel model = new RecogModel();
            model.ctId = slide.getCtId();
            model.slideId = slide.getId();
            model.serialNum = slide.getSerialNum();
            model.pic = "sorry @mch";
            model.label = getLabel(val);
            model.probably = getProbably(val);
            rm.add(model);
        }
        return rm;
    }

    private static String getLabel(double[] arr) {
        double max = Arrays.stream(arr).max().getAsDouble();
        if (max == arr[0])
            return DJJ;
        else if (max == arr[1])
            return XJJ;
        else
            return NOR;
    }

    private static String getProbably(double[] arr) {
        double[] newValue = new double[arr.length];
        double sum = 0;
        for (int i = 0; i < arr.length; i ++) {
            sum += arr[i];
        }
        for (int i = 0; i < arr.length; i ++) {
            newValue[i] = arr[i] / sum;
        }
        DecimalFormat df = new DecimalFormat("#0.000");
        StringBuffer sb = new StringBuffer();
        sb.append("结节：");
        sb.append(df.format(newValue[0] + newValue[1]));
        sb.append("      正常：");
        sb.append(df.format(newValue[2] + newValue[3]));
        return sb.toString();
    }

    public int getCtId() {
        return ctId;
    }

    public void setCtId(int ctId) {
        this.ctId = ctId;
    }

    public int getSlideId() {
        return slideId;
    }

    public void setSlideId(int slideId) {
        this.slideId = slideId;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public String getPic() {
        return pic;
    }

    public String getLabel() {
        return label;
    }

    public String getProbably() {
        return probably;
    }
}
