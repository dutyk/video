package cn.ictgu.job;

import cn.ictgu.tools.JsoupUtils;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class QrawlerTest {
    @Test
    public void testLetvCrawHomePage() {
        Document pcDocument = JsoupUtils.getDocWithPC(LetvCrawler.HOME_PAGE_PC);
        LetvCrawler letvCrawler = new LetvCrawler(null);
        letvCrawler.saveCarouselsToRedis(pcDocument);
        //System.out.println(pcDocument);
    }
}
