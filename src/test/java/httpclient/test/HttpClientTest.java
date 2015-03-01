package httpclient.test;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class HttpClientTest {

	protected static HttpClient httpClient;

	@BeforeClass
	public static void setup() {
		
		HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		
		httpClient = HttpClientBuilder.create()
				.setProxy(proxy)
				.setDefaultCookieStore(new BasicCookieStore()).build();
	}

	@AfterClass
	public static void teardown() {
		HttpClientUtils.closeQuietly(httpClient);
	}
}
