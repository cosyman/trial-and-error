package tmp;



/**
 * 客户端产品线对象
 * 
 * @author hanxin
 * @version $Id: ClientProductLine.java, v 0.1 2015年1月17日 下午2:52:52 hanxin Exp $
 */
public class ClientProductLine   {
    /**
     * id       db_column: id 
     */
    private int    id;
    /**
     * 产品线      db_column: product_line 
     */
    private String productLine;
    /**
     * 描述       db_column: description 
     */
    private String description;
    /**
     * 默认渠道号        db_column: default_channel_id 
     */
    private String defaultChannelId;
    /**
     * android portal groupId       db_column: android_portal_group_id 
     */
    private String androidPortalGroupId;
    /**
     * android portal 代码库       db_column: android_portal_repo 
     */
    private String androidPortalRepo;
    /**
     * android portal 代码库地址         db_column: android_portal_repo_path 
     */
    private String androidPortalRepoPath;
    /**
     * android portal artifactId        db_column: android_portal_artifact_id 
     */
    private String androidPortalArtifactId;
    /**
     * iOS portal groupId       db_column: ios_portal_group_id 
     */
    private String iosPortalGroupId;
    /**
     * iOS portal artifactId        db_column: ios_portal_artifact_id 
     */
    private String iosPortalArtifactId;
    /**
     * mwalletmngFlag   db_column: mwalletmng_flag
     */
    private String mwalletmngFlag;

    public String getMwalletmngFlag() {
        return mwalletmngFlag;
    }

    public void setMwalletmngFlag(String mwalletmngFlag) {
        this.mwalletmngFlag = mwalletmngFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultChannelId() {
        return defaultChannelId;
    }

    public void setDefaultChannelId(String defaultChannelId) {
        this.defaultChannelId = defaultChannelId;
    }

    /**
     * Getter method for property <tt>androidPortalGroupD</tt>.
     * 
     * @return property value of androidPortalGroupD
     */
    public String getAndroidPortalGroupId() {
        return androidPortalGroupId;
    }

    /**
     * Setter method for property <tt>androidPortalGroupD</tt>.
     * 
     * @param androidPortalGroupD value to be assigned to property androidPortalGroupD
     */
    public void setAndroidPortalGroupId(String androidPortalGroupId) {
        this.androidPortalGroupId = androidPortalGroupId;
    }

    /**
     * Getter method for property <tt>androidPortalRepo</tt>.
     * 
     * @return property value of androidPortalRepo
     */
    public String getAndroidPortalRepo() {
        return androidPortalRepo;
    }

    /**
     * Setter method for property <tt>androidPortalRepo</tt>.
     * 
     * @param androidPortalRepo value to be assigned to property androidPortalRepo
     */
    public void setAndroidPortalRepo(String androidPortalRepo) {
        this.androidPortalRepo = androidPortalRepo;
    }

    /**
     * Getter method for property <tt>androidPortalRepoPath</tt>.
     * 
     * @return property value of androidPortalRepoPath
     */
    public String getAndroidPortalRepoPath() {
        return androidPortalRepoPath;
    }

    /**
     * Setter method for property <tt>androidPortalRepoPath</tt>.
     * 
     * @param androidPortalRepoPath value to be assigned to property androidPortalRepoPath
     */
    public void setAndroidPortalRepoPath(String androidPortalRepoPath) {
        this.androidPortalRepoPath = androidPortalRepoPath;
    }

    /**
     * Getter method for property <tt>androidPortalArtifactId</tt>.
     * 
     * @return property value of androidPortalArtifactId
     */
    public String getAndroidPortalArtifactId() {
        return androidPortalArtifactId;
    }

    /**
     * Setter method for property <tt>androidPortalArtifactId</tt>.
     * 
     * @param androidPortalArtifactId value to be assigned to property androidPortalArtifactId
     */
    public void setAndroidPortalArtifactId(String androidPortalArtifactId) {
        this.androidPortalArtifactId = androidPortalArtifactId;
    }

    /**
     * Getter method for property <tt>iosPortalGroupId</tt>.
     * 
     * @return property value of iosPortalGroupId
     */
    public String getIosPortalGroupId() {
        return iosPortalGroupId;
    }

    /**
     * Setter method for property <tt>iosPortalGroupId</tt>.
     * 
     * @param iosPortalGroupId value to be assigned to property iosPortalGroupId
     */
    public void setIosPortalGroupId(String iosPortalGroupId) {
        this.iosPortalGroupId = iosPortalGroupId;
    }

    /**
     * Getter method for property <tt>iosPortalArtifactId</tt>.
     * 
     * @return property value of iosPortalArtifactId
     */
    public String getIosPortalArtifactId() {
        return iosPortalArtifactId;
    }

    /**
     * Setter method for property <tt>iosPortalArtifactId</tt>.
     * 
     * @param iosPortalArtifactId value to be assigned to property iosPortalArtifactId
     */
    public void setIosPortalArtifactId(String iosPortalArtifactId) {
        this.iosPortalArtifactId = iosPortalArtifactId;
    }

    public String getAndroidPortalGa(){
        return this.androidPortalGroupId + "+" + this.androidPortalArtifactId;
    }

    public String getIosPortalGa() {
        return this.iosPortalGroupId + "+" + this.iosPortalArtifactId;
    }
    

}
