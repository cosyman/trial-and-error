package httpclient.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class CookieTest extends HttpClientTest {

	@Test
	public void testAutoCookie() throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet("http://www.baidu.com/");
		httpClient.execute(request);
		HttpResponse response = httpClient.execute(request);

		try {
			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			EntityUtils.consume(response.getEntity());
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}
}