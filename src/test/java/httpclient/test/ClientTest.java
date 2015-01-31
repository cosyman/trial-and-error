package httpclient.test;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.hamcrest.core.StringContains;
import org.junit.Test;

public class ClientTest {

	@Test
	public void testQuickStart() throws URISyntaxException,
			ClientProtocolException, IOException {

		URI uri = new URIBuilder().setScheme("http").setHost("www.baidu.com")
				.setPort(80).setPath("/s").addParameter("wd", "美")
				.setCharset(Charset.forName("utf-8")).build();

		String entity = Request.Get(uri).connectTimeout(1000)
				.socketTimeout(1000).execute().returnContent().asString();

		assertThat(entity, new StringContains("美"));
	}
}
