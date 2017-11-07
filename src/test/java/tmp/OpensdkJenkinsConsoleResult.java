package tmp;

import java.util.List;
import java.util.Map;

/**
 * @author 十境
 */
public class OpensdkJenkinsConsoleResult {

    public static class AndroidProjectConsoleResult {
        private boolean result;
        private String version;
        private String codeBranchRevision;
        private String md5;
        private int size;
        private String downloadUrl;

        public boolean getResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getCodeBranchRevision() {
            return codeBranchRevision;
        }

        public void setCodeBranchRevision(String codeBranchRevision) {
            this.codeBranchRevision = codeBranchRevision;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }
    }

    public static class AndroidPackageConsoleResult {
        private boolean result;
        private String version;
        private List<Map<String, Object>> downloadUrls;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<Map<String, Object>> getDownloadUrls() {
            return downloadUrls;
        }

        public void setDownloadUrls(List<Map<String, Object>> downloadUrls) {
            this.downloadUrls = downloadUrls;
        }
    }

}
