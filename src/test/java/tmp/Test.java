package tmp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 十境
 */
public class Test {
    @org.junit.Test
    public void test() throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();

        om.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(om.writeValueAsString(new ClientProductLine()));
//        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        om
        om.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        Map<String, Object> map = new HashMap<>();
        map.put("helloVersion", true);
        System.out.println(om.writeValueAsString(map));

        String json = om.writeValueAsString(new ClientMpaasIOSProvision());
        System.out.println(json);

        json = om.writerWithView(JsonView.Android.class).writeValueAsString(new ClientMpaasIOSProvision());
        System.out.println(json);

        json = om.writeValueAsString(new ClientMpaasCertificate());
        System.out.println(json);
    }

    @org.junit.Test
    public void testCoding() {
//        Arrays.asList("I am 君山".getBytes()).stream().map(bytes -> bytes.toString()).collect(Collectors::toList);
    }

    @org.junit.Test
    public void testP() throws IOException {
        String yao = "{\"size\": 124946, \"downloadUrl\": \"http://mvn.test.alipay.net:8081/artifactory/service/local/repositories/mobile/content/com/alipay/liuke/portal/liukeportal/1.0.0.171102204851/liukeportal-1.0.0.171102204851.jar\", \"version\": \"1.0.0.171102204851\", \"result\": true, \"codeBranchRevision\": \"967ba9e992f8311b4f94235acf86e7807ba0485c\", \"artifactId\": \"liukeportal\", \"md5\": \"d2d7403cd5ad58b4859cb132d3068dc8\"}";

        ObjectMapper om = new ObjectMapper().configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        OpensdkJenkinsConsoleResult.AndroidProjectConsoleResult result = om.readValue(yao, OpensdkJenkinsConsoleResult.AndroidProjectConsoleResult.class);
        System.out.println(result.getResult());
    }
}
