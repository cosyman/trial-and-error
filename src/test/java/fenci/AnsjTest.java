package fenci;

import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.Test;

/**
 * @author 十境
 */
public class AnsjTest {

    @Test
    public void testHi(){

//        UserDefineLibrary.insertWord("支付宝",);
        String str = "支付宝微信遭央行双限，为何马云马化腾的反应截然不同？" ;
        System.out.println(ToAnalysis.parse(str));
    }
}
