package md;


import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.pegdown.plugins.PegDownPlugins;

/**
 * @author 十境
 */
public class PegDownTest {

    @Test
    public void testRender() {


        PegDownPlugins pegDownPlugins = PegDownPlugins.builder().build();

        PegDownProcessor pdp = new PegDownProcessor(Extensions.ALL, pegDownPlugins);

        String html = pdp.markdownToHtml("1. h\n" +
                "2. dfj\n" +
                "\n" +
                "### Table\n" +
                "\n" +
                "| Tables        | Are           | Cool  |\n" +
                "| ------------- |:-------------:| -----:|\n" +
                "| col 3 is      | right-aligned | $1600 |\n" +
                "| col 2 is      | centered      |   $12 |\n" +
                "| zebra stripes | are neat      |    $1 |\n" +
                "\n" +
                "### Image\n" +
                "\n" +
                "![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png \"Logo Title Text 1\")\n" +
                "\n" +
                "### Code\n" +
                "```java\n" +
                "\t@Test\n" +
                "\tpublic void testStringTL() throws IOException {\n" +
                "\t\tStringTemplateLoader stl = new StringTemplateLoader();\n" +
                "\t\tString template = \"${key}\";\n" +
                "\t\tstl.putTemplate(\"hello\", template);\n" +
                "\t\tObject source = stl.findTemplateSource(\"hello\");\n" +
                "\t\tReader reader = stl.getReader(source, \"utf-8\");\n" +
                "\t\tString dest = IOUtils.toString(reader);\n" +
                "\t\tAssert.assertEquals(template, dest);\n" +
                "\n" +
                "\t}\n" +
                "```\n"
        );

        System.out.println(html);
    }
}
