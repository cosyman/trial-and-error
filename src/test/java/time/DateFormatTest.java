package time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.util.Date;

/**
 * @author 十境
 */
public class DateFormatTest {

    @Test
    public void testF() {

        String name = FastDateFormat.getInstance("yyyyMM/dd").format(new Date());
        System.out.println(name);

    }
}
