package com.bmw.boss.infos.app.ehcache;

import net.sf.ehcache.*;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.List;


public class EhCacheManager {
	private static Logger logger = Logger.getLogger(EhCacheManager.class);
    /** 
     * Ehanche  manager
     */  
    private static CacheManager cacheManager = null;

    //configuration file
    private static String configPath="ehcache-setting.xml";
	
	//Cache definition  ：  Define the cache configured in a file
	public final static String CACHE_MYCOMMONCACHE="cacheWeather";
	
	//Instantiation cacheManager, singleton mode
	public static CacheManager getCacheManagerInstance(){
		if(cacheManager==null){
			synchronized(EhCacheManager.class){
	    		URL configUrl=null;
	    		configUrl = EhCacheManager.class.getClassLoader().getResource(configPath);
	    		cacheManager = CacheManager.create(configUrl);
			}
		}
		return cacheManager; 
	}
    
    public static CacheManager getCacheManager() {
        //Single case cache management
	    return getCacheManagerInstance();
    }  
    
    public static void setCacheManager(CacheManager cacheManager) {
        EhCacheManager.cacheManager = cacheManager;
    }



	//add new  cache
    public static void addCacheByName(String cacheName){  
        if(cacheName==null||cacheName.trim().equals("")){  
            logger.error("cacheName is null"); 
        }else{  
            if(getCacheManager().getCache(cacheName.trim())!=null){  
                getCacheManager().removeCache(cacheName.trim());  
            }  
            getCacheManager().addCache(cacheName.trim());  
            logger.info(cacheName+ "is add complete");  
        }  
    }
    
    //get cache  by  name
    public static Cache getCacheByName(String cacheName){
        Cache cache=null;
        if(cacheName==null||cacheName.trim().equals("")){  
        	logger.error("cacheName is null");   
        }else{  
            if(getCacheManager().getCache(cacheName.trim())!=null){  
                cache=getCacheManager().getCache(cacheName.trim());  
            }else{
                logger.error("cacheName is not existent");
                addCacheByName(cacheName);
                cache=getCacheManager().getCache(cacheName.trim());
            }
        }            
        return cache;  
    }  
    
    //add  element  to   cacheName
    public static void putElementToCache(String cacheName,String elementKey,Object elementValue){  
        Cache cache=null;
        if(cacheName==null||cacheName.trim().equals("")){  
        	logger.error("Failed to add cache element,cacheName is null");  
        }else if("".equals(elementKey)||elementKey==null||"".equals(elementValue)||elementValue==null){
        	logger.error("Failed to add cache element,elementKey or elementValue is null");  
        }else{
            if(getCacheByName(cacheName.trim())!=null){//
                cache=getCacheByName(cacheName.trim());
            }else{
                addCacheByName(cacheName.trim());  
                cache=getCacheByName(cacheName.trim());  
            }  
            //Adding Element to the cache object
            Element element=null;
            if(cache.get(elementKey.trim())!=null){  
                cache.remove(elementKey.trim());  
            }  
            element=new Element(elementKey.trim(),elementValue);
            cache.put(element);  
            logger.info("add element:"+elementKey+"  success！");  
        }           
    }

    //add  element  to  cache
    public static void putElementToCache(Cache cache, String elementKey, Object elementValue){
        Element element=null;
        if(cache.get(elementKey.trim())!=null){
            cache.remove(elementKey.trim());
        }
        element=new Element(elementKey.trim(),elementValue);
        cache.put(element);
        logger.info("add element:"+elementKey+"  success！");
    }

    //get element by elementKey and cacheName
    public static Object getElementValueFromCache(String cacheName,String elementKey){  
        Object result=null;  
        Cache cache=null;
        if(cacheName==null||cacheName.trim().equals("")){  
        	logger.error("Failed to get cache element,cacheName is null");  
        }else if(elementKey==null){  
        	logger.error("Failed to get cache element,elementKey is null");  
        }else{  
            if(getCacheByName(cacheName.trim())!=null){
                cache=getCacheByName(cacheName.trim());                   
                Element element=null;
                if(cache.get(elementKey.trim())!=null){  
                    element=cache.get(elementKey.trim());  
                    if(element.getObjectValue()==null){  
                    	logger.error("The value of "+elementKey+" in the cache is empty");
                    }else{  
                        result=element.getObjectValue();  
                    }  
                }else{  
                	logger.error("There is no:"+elementKey+"  in the cache");
                }  
            }else{
            	logger.error("Failed to get cache element,The element:"+cacheName+" in the cache is empty");  
            }  
        }            
        return result;  
    }

    //get element by elementKey and cache
    public static Object getElementValueFromCache(Cache cache, String elementKey){
        Object result=null;
        Element element=null;
        if(cache.get(elementKey.trim())!=null){
            element=cache.get(elementKey.trim());
            if(element.getObjectValue()==null){
                logger.error("The value of "+elementKey+" in the cache is empty");
            }else{
                result=element.getObjectValue();
            }
        }
            return result;
    }

    /** 
     * get cache Keys
     *  
     * @param cacheName 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public static List<String> getKeys(String cacheName) {  
        Ehcache cache = getCacheManager().getEhcache(cacheName);
        return (List<String>) cache.getKeys();  
    }  
    
    /**
     * Clears the contents of all caches in the CacheManager, 
     *  but without removing any caches. 
     */  
    public static void clearAllFromCacheManager(){  
        if(getCacheManager()!=null){  
            getCacheManager().clearAll();  
            logger.info("CacheManager was clearAll...");  
        }  
    } 
    
    /** 
     * Delete the specified object
     *  
     * @param cacheName 
     * @param key 
     * @return 
     */  
    public static boolean remove(String cacheName, String key) {  
        return getCacheManager().getCache(cacheName).remove(key);  
    }
    
    /** 
     * 获取缓存大小 
     *  
     * @param cacheName 
     * @return 
     */  
    public static int getSize(String cacheName) {  
        return getCacheManager().getCache(cacheName).getSize();  
    }

    /**
     * When you don't caching, turn it off, or you'll take up CPU and memory resources
     *
     */
    public static void shutdownCacheManager(){  
        if(getCacheManager()!=null){  
            getCacheManager().shutdown();  
            logger.info("CacheManager was shutdown...");  
        }  
    } 
    
    
    /**
     * for test1
     * @param cache
     */
	public static void printCache(Cache cache){
        logger.info("缓存状态： "+cache.getStatus().toString());
        if(cache==null){
        	logger.info("cache is null,no print info.");
        }else if(cache.getStatus().toString().equals(Status.STATUS_UNINITIALISED)){
            logger.info("缓存状态： 未初始化"+cache.getStatus().toString());  
        }else if(cache.getStatus().toString().equals(Status.STATUS_SHUTDOWN)){
            logger.info("缓存状态： 已关闭"+cache.getStatus().toString());  
        }else if(cache.getStatus().toString().trim().equals(Status.STATUS_ALIVE)){
        	System.out.println("进入缓存取数据");
            if(cache.getKeys().size()==0){  
                logger.info(cache.getName()+" exits,but no value.");  
            }else{  
                for(int i=0;i<cache.getKeys().size();i++){  
                    Object thekey=cache.getKeys().get(i);  
                    Object thevalue=cache.get(thekey);  
                    logger.info(cache.getName()+"--"+i+",key:"+thekey.toString()+",value:"+thevalue.toString());  
                }  
            }  
        }   
    }

    /**
     * for test1
     * @param cacheName
     */
    public static void printCacheByName(String cacheName){  
        if(cacheName==null||cacheName.trim().equals("")){  
            logger.info("cacheName is null,no print info.");  
        }else{  
            if(getCacheManager().getCache(cacheName.trim())!=null){  
                Cache cache=getCacheManager().getCache(cacheName.trim());
                printCache(cache);  
            }else{  
                logger.info(cacheName+" --null");  
            }  
        }     
    } 
    
    public static void main(String[] sdfsf){  
        Cache cache1= EhCacheManager.getCacheByName(EhCacheManager.CACHE_MYCOMMONCACHE);
        printCache(cache1);  
          
        EhCacheManager.putElementToCache(EhCacheManager.CACHE_MYCOMMONCACHE, "111", "111haah");
        EhCacheManager.putElementToCache(EhCacheManager.CACHE_MYCOMMONCACHE, "222", "222haah");
        EhCacheManager.putElementToCache(EhCacheManager.CACHE_MYCOMMONCACHE, "333", "333haah");
          
        printCache(cache1);  
          
        EhCacheManager.putElementToCache(EhCacheManager.CACHE_MYCOMMONCACHE, "111", "111的新值。");
          
        logger.info(EhCacheManager.getElementValueFromCache(EhCacheManager.CACHE_MYCOMMONCACHE, "111"));
        printCache(cache1);  
          
        clearAllFromCacheManager();  
        printCache(cache1);  
          
        //removalAllFromCacheManager();
        printCache(cache1);  
          
        shutdownCacheManager();  
    }

}
