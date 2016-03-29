package cn.edu.zju.isee.cms.entity;

import java.util.Date;

/**
 * Created by jql on 2016/3/25.
 */
public class CT {
    private Integer id;
    private Integer slideNum;
    private Date buildTime;
    private String baseDir;
    private String zipDir;
    private String jpgDir;
    private String zipName;
    private String patientName;
    private String doctors;
    private String descr;
    private String hospital;
    private String item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSlideNum() {
        return slideNum;
    }

    public void setSlideNum(Integer slideNum) {
        this.slideNum = slideNum;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctors() {
        return doctors;
    }

    public void setDoctors(String doctors) {
        this.doctors = doctors;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getZipDir() {
        return zipDir;
    }

    public void setZipDir(String zipDir) {
        this.zipDir = zipDir;
    }

    public String getJpgDir() {
        return jpgDir;
    }

    public void setJpgDir(String jpgDir) {
        this.jpgDir = jpgDir;
    }

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }
}
