package cn.edu.zju.isee.cms.entity;

/**
 * Created by 508_1 on 2016/4/7.
 */
public class User {

    private Integer id;
    private String userName;
    private String passWord;
    private Integer status;
    private boolean upload;
    private boolean download;
    private boolean importData;
    private boolean checkData;
    private boolean deleteData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public boolean isImportData() {
        return importData;
    }

    public void setImportData(boolean importData) {
        this.importData = importData;
    }

    public boolean isCheckData() {
        return checkData;
    }

    public void setCheckData(boolean checkData) {
        this.checkData = checkData;
    }

    public boolean isDeleteData() {
        return deleteData;
    }

    public void setDeleteData(boolean deleteData) {
        this.deleteData = deleteData;
    }
}
