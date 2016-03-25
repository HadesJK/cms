package cn.edu.zju.isee.cms.entity;

/**
 * Created by 508_1 on 2016/3/25.
 */
public class CTSlide {

    private Integer id;
    private Integer ctId;
    private Integer serialNum;
    private String slideName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }
}
