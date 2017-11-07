package tmp;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 十境
 * Date 2017-08-09
 * <p>
 * mpass 构建记录
 */
public class ClientMpassBuild implements Serializable {

    /**
     * 主键
     */
    private long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 租户
     */
    private String tenant;

    /**
     * 产品
     */
    private String app;

    /**
     * git url
     */
    private String gitRepoUrl;

    /**
     * git ssh key
     */
    private String gitSshKey;

    /**
     * android/ios
     */
    private String platform;

    /**
     * debug/release
     */
    private String buildType;

    /**
     * extra cmd params
     */
    private String buildParams;

    /**
     * 证书 ID
     */
    private int certificateId;

    /**
     * 描述文件 ID
     */
    private int provisionId;

    /**
     * cocoapods url 列表
     */
    private String cocoapodsUrls;

    /**
     * nexus url
     */
    private String mvnUrl;

    /**
     * 回掉 GET  ?result
     */
    private String callbackUrl;

    /**
     * SUCCESS/FAILURE
     */
    private String buildResult;

    /**
     * 包
     */
    private String packageOssKey;

    /**
     * dsym
     */
    private String dsymOssKey;

    /**
     * plist oss key
     */
    private String qrcodeOssKey;

    /**
     * log key
     */
    private String logOssKey;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getGitRepoUrl() {
        return gitRepoUrl;
    }

    public void setGitRepoUrl(String gitRepoUrl) {
        this.gitRepoUrl = gitRepoUrl;
    }

    public String getGitSshKey() {
        return gitSshKey;
    }

    public void setGitSshKey(String gitSshKey) {
        this.gitSshKey = gitSshKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getBuildParams() {
        return buildParams;
    }

    public void setBuildParams(String buildParams) {
        this.buildParams = buildParams;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public int getProvisionId() {
        return provisionId;
    }

    public void setProvisionId(int provisionId) {
        this.provisionId = provisionId;
    }

    public String getCocoapodsUrls() {
        return cocoapodsUrls;
    }

    public void setCocoapodsUrls(String cocoapodsUrls) {
        this.cocoapodsUrls = cocoapodsUrls;
    }

    public String getMvnUrl() {
        return mvnUrl;
    }

    public void setMvnUrl(String mvnUrl) {
        this.mvnUrl = mvnUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getBuildResult() {
        return buildResult;
    }

    public void setBuildResult(String buildResult) {
        this.buildResult = buildResult;
    }

    public String getPackageOssKey() {
        return packageOssKey;
    }

    public void setPackageOssKey(String packageOssKey) {
        this.packageOssKey = packageOssKey;
    }

    public String getDsymOssKey() {
        return dsymOssKey;
    }

    public void setDsymOssKey(String dsymOssKey) {
        this.dsymOssKey = dsymOssKey;
    }

    public String getQrcodeOssKey() {
        return qrcodeOssKey;
    }

    public void setQrcodeOssKey(String qrcodeOssKey) {
        this.qrcodeOssKey = qrcodeOssKey;
    }

    public String getLogOssKey() {
        return logOssKey;
    }

    public void setLogOssKey(String logOssKey) {
        this.logOssKey = logOssKey;
    }
}