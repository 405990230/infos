/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmw.boss.infos.app.pojo.json;


import com.bmw.boss.infos.app.util.WeatherServerEnvironment;

import java.io.Serializable;

/**
 *
 * @author q378058
 */
public class ResponseWindPojo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String windPower;
    private String windDirection;

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWindDirection() {
        return wrapWindDirection(windDirection);
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }
    
    public static String wrapWindDirection(String windDirection){
        if(windDirection != null){
            return WeatherServerEnvironment.getWeatherMap().getProperty("WIND_DIRECTION_" + windDirection);
        }else{
            return null;
        }
    }
    
}
