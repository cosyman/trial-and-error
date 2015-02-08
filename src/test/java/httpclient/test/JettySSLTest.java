package httpclient.test;

import java.io.File;

import jetty.HelloHandler;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * http://blog.anvard.org/articles/2013/10/05/jetty-ssl-server.html
 * http://blog.denevell.org/jetty-9-ssl-https.html
 * 
 * @author 兰天
 *
 */
public class JettySSLTest {

	private static Server server;

	@BeforeClass
	public static void setup() throws Exception {
		server = new Server();

		// HTTP Configuration
		// HttpConfiguration is a collection of configuration information
		// appropriate for http and https. The default scheme for http is
		// <code>http</code> of course, as the default for secured http is
		// <code>https</code> but we show setting the scheme to show it can be
		// done. The port for secured communication is also set here.
		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setSecurePort(8443);
		http_config.setOutputBufferSize(32768);

		// HTTP connector
		// The first server connector we create is the one for http, passing in
		// the http configuration we configured above so it can get things like
		// the output buffer size, etc. We also set the port (8080) and
		// configure an idle timeout.
		ServerConnector http = new ServerConnector(server,
				new HttpConnectionFactory(http_config));
		http.setPort(8080);
		http.setIdleTimeout(30000);

		// SSL Context Factory for HTTPS and SPDY
		// SSL requires a certificate so we configure a factory for ssl contents
		// with information pointing to what keystore the ssl connection needs
		// to know about. Much more configuration is available the ssl context,
		// including things like choosing the particular certificate out of a
		// keystore to be used.
		SslContextFactory sslContextFactory = new SslContextFactory();
		sslContextFactory.setKeyStorePath(new File(System
				.getProperty("user.dir"), "data/httpclient.jks")
				.getAbsolutePath());
		sslContextFactory.setKeyStorePassword("httpclient");
		sslContextFactory.setKeyManagerPassword("httpclient");

		// HTTPS Configuration
		// A new HttpConfiguration object is needed for the next connector and
		// you can pass the old one as an argument to effectively clone the
		// contents. On this HttpConfiguration object we add a
		// SecureRequestCustomizer which is how a new connector is able to
		// resolve the https connection before handing control over to the Jetty
		// Server.
		HttpConfiguration https_config = new HttpConfiguration(http_config);
		https_config.addCustomizer(new SecureRequestCustomizer());

		// HTTPS connector
		// We create a second ServerConnector, passing in the http configuration
		// we just made along with the previously created ssl context factory.
		// Next we set the port and a longer idle timeout.
		ServerConnector https = new ServerConnector(server,
				new SslConnectionFactory(sslContextFactory,
						HttpVersion.HTTP_1_1.asString()),
				new HttpConnectionFactory(https_config));
		https.setPort(8443);
		https.setIdleTimeout(500000);

		// Here you see the server having multiple connectors registered with
		// it, now requests can flow into the server from both http and https
		// urls to their respective ports and be processed accordingly by jetty.
		// A simple handler is also registered with the server so the example
		// has something to pass requests off to.

		server.setConnectors(new Connector[] { http, https });

		server.setHandler(new HelloHandler());

		server.start();
		server.join();
	}

	@Test
	public void echo() {

	}

	@AfterClass
	public static void tear() throws Exception {
		server.stop();
	}
}
