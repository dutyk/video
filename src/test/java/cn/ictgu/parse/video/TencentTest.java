package cn.ictgu.parse.video;


import org.junit.Test;

public class TencentTest {
    @Test
    public void testParse() {
        Tencent tencent = new Tencent();
        tencent.parse("https://v.qq.com/x/cover/tyyx4oj6ejkooa0.html");
    }

    @Test
    public void testParsePianDuan() {
        Tencent tencent = new Tencent();
        tencent.parsePart("t0027m7qd9n.p201.1.mp4", 0);
    }

}
