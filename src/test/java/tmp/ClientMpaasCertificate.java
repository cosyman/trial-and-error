package tmp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 十境
 */
public class ClientMpaasCertificate implements Serializable {

    public static final String PLATFORM_ANDROID = "android";
    public static final String PLATFORM_IOS = "ios";
    /**
     * 主键
     */
    private Integer id;

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
     * 产品信息
     */
    private String app;

    /**
     * android/ios
     */
    @JsonIgnore
    private String platform;

    /**
     * 证书文件名称
     */
    private String type;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 证书  oss key
     */
    private String certificateOssKey;

    /**
     * key 密码加密字符
     */
    private String keyPassword;

    /**
     * keystore 密码加密字符
     */
    private String keystorePassword;

    /**
     * p12 密码加密
     */

    private String p12Password;

    /**
     * 证书别名
     */
    private String keyAlias;

    /**
     * 过期时间
     */
    private Date expirationTime;

    /**
     * 1/0 是否删除
     */
    @JsonIgnore
    private Integer valid;

    /**
     * md5
     */
    private String md5;

    /**
     * sha1
     */
    private String sha1;

    /**
     * 创建者
     */
    private String creator;

    /**
     * iOS 证书常用名称 标识
     */
    private String normalName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateOssKey() {
        return certificateOssKey;
    }

    public void setCertificateOssKey(String certificateOssKey) {
        this.certificateOssKey = certificateOssKey;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getP12Password() {
        return p12Password;
    }

    public void setP12Password(String p12Password) {
        this.p12Password = p12Password;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getNormalName() {
        return normalName;
    }

    public void setNormalName(String normalName) {
        this.normalName = normalName;
    }
}
