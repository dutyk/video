package cn.ictgu.parse.video;


import cn.ictgu.bean.response.Video;
import org.junit.Test;

public class LetvTest {
    @Test
    public void testKey() {
        System.out.println(Letv.getTkey(1556945145));
    }

    @Test
    public void testParse() {
        Letv letv = new Letv();
        Video video = letv.parse("http://www.le.com/ptv/vplay/31562416.html");
        System.out.println(video.getPlayUrl());
    }

}
