package guava;

import com.google.common.base.Splitter;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.Map;

/**
 * @author 十境
 */
public class SplitTest {
    @Test
    public void test (){
        String text = "C=US, O=Ning Zhang, OU=EN8NW92PM6, CN=iPhone Developer: Ning Zhang (ENA6G7XAW3), UID=JMHMGK442L";
        text="a=3";
        final Map<String, String> splitKeyValues = Splitter.on(",").trimResults()
                .withKeyValueSeparator("=")

                .split(text);
        for (String  key :splitKeyValues.keySet()){
            System.out.println(String.format("%s:%s",key,splitKeyValues.get(key)));
        }
        System.out.println(splitKeyValues.get("CN"));;
    }
}
