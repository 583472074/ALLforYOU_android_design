package com.myxilove.gift.model;

import com.google.gson.annotations.Expose;

public class SimpleWeather2 {
	@Expose
	private SimpleWeatherinfo2 weatherinfo;

	public SimpleWeatherinfo2 getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo2(SimpleWeatherinfo2 weatherinfo) {
		this.weatherinfo = weatherinfo;
	}

}
