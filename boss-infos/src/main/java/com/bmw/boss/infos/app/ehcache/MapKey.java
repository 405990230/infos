package com.bmw.boss.infos.app.ehcache;

import org.apache.log4j.Logger;

public class MapKey {
	private static Logger logger = Logger.getLogger(MapKey.class);
//	double latMin = 3.86;
//	double latMax = 53.55;
//	double lonMin = 73.66;
//	double lonMax = 135.03;
	final static double latMin = 3.50;
	final static double latMax = 54.00;
	final static double lonMin = 73.50;
	final static double lonMax = 135.50;

	public static String getKeyString(String lat, String lon,String language){
		int latD = (int)((Double.valueOf(lat)-latMin)/0.5+1);
		int lonD = (int)((Double.valueOf(lon)-lonMin)/0.5+1);
		String result = latD+"#"+lonD+"#"+language;
		//logger.info(result);
		return result;
	}

	public static void main(String[] args) {
		getKeyString("23.228403","113.169074","zh");
		getKeyString("54.793001","135.463199","zh_CN");
		//System.out.println((int)(41.793001-3.5)/0.5);
	}
}
