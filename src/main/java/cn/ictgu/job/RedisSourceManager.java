package cn.ictgu.job;

import cn.ictgu.bean.response.Video;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * 视频资源管理器
 */
@Component
@Slf4j
public class RedisSourceManager {

  public final String VIDEO_PREFIX_HOME_CAROUSEL_KEY = "HOME_VIDEO_CAROUSEL";
  public final String VIDEO_PREFIX_HOME_RECOMMEND_KEY = "HOME_VIDEO_RECOMMEND";
  public final String VIDEO_PREFIX_HOME_TV_KEY = "HOME_VIDEO_TV";
  public final String VIDEO_PREFIX_HOME_MOVIE_KEY = "HOME_VIDEO_MOVIE";
  public final String VIDEO_PREFIX_HOME_CARTOON_KEY = "HOME_VIDEO_CARTOON";
  public final String VIDEO_PREFIX_HOME_TV_HOT_KEY = "HOME_VIDEO_TV_HOT";
  public final String VIDEOS_KEY = "VIDEOS";

  @Value("${cache.local}")
  private Boolean cacheLocal;

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Autowired
  Cache cache;

  /**
   *  保存视频信息到 Redis
   */
  void saveVideos(String key, List<Video> videos){
    String value = JSONObject.toJSONString(videos);
    if(cacheLocal)
      cache.put(key, value);
    else
      stringRedisTemplate.opsForValue().set(key, value);
  }

  /**
   *  得到视频信息
   */
  public List<Video> getVideosByKeyAndTag(String key, String tag){
    key = key + "_" + tag;
    String cacheValue = "";

    if(cacheLocal) {
      try {
        cacheValue = (String)cache.get(key, new Callable() {
          @Override
          public Object call() throws Exception {
            log.info("get value");
            return "";
          }
        });
      }catch (ExecutionException e) {
        log.error("get key occurs exception:{}", e);
      }
    }else
      cacheValue = stringRedisTemplate.opsForValue().get(key);
    return JSONObject.parseArray(cacheValue, Video.class);
  }
}
