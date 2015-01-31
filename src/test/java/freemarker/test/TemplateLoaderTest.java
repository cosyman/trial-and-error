package freemarker.test;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

public class TemplateLoaderTest {

	@Test
	public void testStringTL() throws IOException {
		StringTemplateLoader stl = new StringTemplateLoader();
		String template = "${key}";
		stl.putTemplate("hello", template);
		Object source = stl.findTemplateSource("hello");
		Reader reader = stl.getReader(source, "utf-8");
		String dest = IOUtils.toString(reader);
		Assert.assertEquals(template, dest);

	}

	@Test
	public void testMultiTL() throws IOException {
		TemplateLoader ctl = new ClassTemplateLoader(TemplateLoaderTest.class,
				"");
		File data = Paths.get(System.getProperty("user.dir"), "test-data",
				"freemarker").toFile();
		TemplateLoader ftl1 = new FileTemplateLoader(data);

		MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[] {
				ctl, ftl1 });

		Object source = mtl.findTemplateSource("test.ftl");
		Reader reader = mtl.getReader(source, "utf-8");
		String dest = IOUtils.toString(reader);
		Assert.assertEquals("${hello}", dest);
	}

	@Test
	public void testInConfiguration() throws IOException {
		Configuration configuration = new Configuration(
				Configuration.VERSION_2_3_21);
		configuration.setDefaultEncoding("utf-8");
		TemplateLoader ctl = new ClassTemplateLoader(TemplateLoaderTest.class,
				"/");
		TemplateLoader ftl1 = new FileTemplateLoader(new File(
				System.getProperty("user.dir")));

		MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[] {
				ftl1, ctl });

		configuration.setTemplateLoader(mtl);

		configuration.setCacheStorage(new freemarker.cache.NullCacheStorage());

		configuration.clearTemplateCache();

		// configuration.getTemplate("test.ftl").process(data, out);
	}

}
