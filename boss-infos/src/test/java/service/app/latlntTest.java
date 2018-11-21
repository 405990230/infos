package service.app;

import base.BaseTest;
import com.alibaba.fastjson.JSON;
import com.bmw.boss.infos.app.ehcache.EhCacheManager;
import com.bmw.boss.infos.app.pojo.json.ResponseDataItemPojo;
import com.bmw.boss.infos.app.service.LinxService;
import com.bmw.boss.infos.app.service.IWeatherService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class latlntTest extends BaseTest{
	private static Logger logger = LoggerFactory.getLogger(latlntTest.class);
	@Autowired
	IWeatherService weatherService;
	@Autowired
	LinxService weatherPathHandlerService;
    @SuppressWarnings("deprecation")
	@Test  
    public void testWeather() {
    	try {

        	List<ResponseDataItemPojo> list = weatherService.getCurrentWeather("31.25715", "120.76453", "cn","1");
        	for(ResponseDataItemPojo po : list){
        		logger.info("天气信息："+ JSON.toJSONString(po));
        	}
        	Thread.sleep(5000);
        	System.out.println("第二次查询！");
        	List<ResponseDataItemPojo> list2 = weatherService.getCurrentWeather("31.25715", "120.76453", "cn","1");
    		CacheManager cacheManager = EhCacheManager.getCacheManager();
    		Cache cache = cacheManager.getCache("cacheWeather");
    		
        	System.out.println(list2.size());
        	// 得到缓存中的对象数
    		System.out.println(cache.getSize());
    		// 得到缓存对象占用内存的大小
    		cache.getMemoryStoreSize();
            //System.out.println("ReflectionSizeOf: " + calculate(new ReflectionSizeOf(), cache));
    		Thread.sleep(2000);
        	weatherService.getCurrentWeather("32.25715", "120.76453", "cn","1");
        	// 得到缓存中的对象数
    		System.out.println(cache.getSize());
    		// 得到缓存对象占用内存的大小
    		cache.getMemoryStoreSize();
            //System.out.println("ReflectionSizeOf: " + calculate(new ReflectionSizeOf(), cache));
            //System.out.println("UnsafeSizeOf: " + calculate(new UnsafeSizeOf(), cache));
           
            weatherService.getCurrentWeather("33.25715", "120.76453", "cn","1");
            //System.out.println("AgentSizeOf: " + calculate(new AgentSizeOf(), cache));
            
        	weatherService.getCurrentWeather("32.25715", "120.76453", "cn","1");
        	// 得到缓存中的对象数
    		System.out.println(cache.getSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

	public static void main(String[] args) {
//		double latMin = 3.86;
//		double latMax = 53.55;
//		double lonMin = 73.66;
//		double lonMax = 135.03;
		double latMin = 3.50;
		double latMax = 54.00;
		double lonMin = 73.50;
		double lonMax = 135.50;
		double latSize,lonSize;
		latSize = (latMax - latMin)/0.5;
		lonSize = (lonMax - lonMin)/0.5;
		System.out.println(latSize*lonSize);
		CacheManager cacheManager = EhCacheManager.getCacheManager();
		int size = cacheManager.getCache("cacheWeather").getSize();
		System.out.println(size);
	}

}
