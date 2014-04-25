package com.myxilove.gift.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.model.Constants.Config;
import com.myxilove.gift.model.SimpleWeatherinfo;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.way.db.CityDB;
import com.way.util.NetUtil;
import com.way.util.T;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;

public class BaseApplication extends Application {
	public static ArrayList<EventHandler> mListeners = new ArrayList<EventHandler>();
	private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
	private static BaseApplication mApplication;

	private CityDB mCityDB;
	private Map<String, Integer> mWeatherIcon;// 天气图标
	private SimpleWeatherinfo mCurSimpleWeatherinfo;
	// public static int mNetWorkState;
	public static Typeface chineseTypeface, englishTypeface;

	public static synchronized BaseApplication getInstance() {
		return mApplication;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		if (Config.DEVELOPER_MODE
				&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectAll().penaltyDeath().build());
		}

		initData();
		super.onCreate();

		initImageLoader(getApplicationContext());
		initTypeFace(getApplicationContext());
	}

	private void initTypeFace(Context context) {
		// TODO Auto-generated method stub
		englishTypeface = Typeface.createFromAsset(context.getAssets(),
				"font/Roboto-Light.ttf");
		chineseTypeface = Typeface.createFromAsset(context.getAssets(),
				"font/xiyuan.ttf");
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	private void initData() {
		mApplication = this;
		// mNetWorkState = NetUtil.getNetworkState(this);
		mCityDB = openCityDB();
		initWeatherIconMap();
		IntentFilter filter = new IntentFilter(NET_CHANGE_ACTION);
		registerReceiver(netChangeReceiver, filter);
	}

	public synchronized CityDB getCityDB() {
		if (mCityDB == null)
			mCityDB = openCityDB();
		return mCityDB;
	}

	private CityDB openCityDB() {
		String path = "/data"
				+ Environment.getDataDirectory().getAbsolutePath()
				+ File.separator + "com.myxilove.gift.allforyou"
				+ File.separator + CityDB.CITY_DB_NAME;
		File db = new File(path);
		if (!db.exists()) {
			try {
				InputStream is = getAssets().open("city.db");
				FileOutputStream fos = new FileOutputStream(db);
				int len = -1;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					fos.flush();
				}
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				T.showLong(mApplication, e.getMessage());
				System.exit(0);
			}
		}
		return new CityDB(this, path);
	}

	public Map<String, Integer> getWeatherIconMap() {
		return mWeatherIcon;
	}

	public int getWeatherIcon(String climate) {
		int weatherRes = R.drawable.biz_plugin_weather_qing;
		if (TextUtils.isEmpty(climate))
			return weatherRes;
		String[] strs = { "晴", "晴" };
		if (climate.contains("转")) {// 天气带转字，取前面那部分
			strs = climate.split("转");
			climate = strs[0];
			if (climate.contains("到")) {// 如果转字前面那部分带到字，则取它的后部分
				strs = climate.split("到");
				climate = strs[1];
			}
		}
		if (mWeatherIcon.containsKey(climate)) {
			weatherRes = mWeatherIcon.get(climate);
		}
		return weatherRes;
	}

	public SimpleWeatherinfo getCurSimpleWeatherinfo() {
		return mCurSimpleWeatherinfo;
	}

	public void setCurSimpleWeatherinfo(SimpleWeatherinfo simpleWeatherinfo) {
		this.mCurSimpleWeatherinfo = simpleWeatherinfo;
	}

	private void initWeatherIconMap() {
		mWeatherIcon = new HashMap<String, Integer>();
		mWeatherIcon.put("暴雪", R.drawable.biz_plugin_weather_baoxue);
		mWeatherIcon.put("暴雨", R.drawable.biz_plugin_weather_baoyu);
		mWeatherIcon.put("大暴雨", R.drawable.biz_plugin_weather_dabaoyu);
		mWeatherIcon.put("大雪", R.drawable.biz_plugin_weather_daxue);
		mWeatherIcon.put("大雨", R.drawable.biz_plugin_weather_dayu);

		mWeatherIcon.put("多云", R.drawable.biz_plugin_weather_duoyun);
		mWeatherIcon.put("雷阵雨", R.drawable.biz_plugin_weather_leizhenyu);
		mWeatherIcon.put("雷阵雨冰雹",
				R.drawable.biz_plugin_weather_leizhenyubingbao);
		mWeatherIcon.put("晴", R.drawable.biz_plugin_weather_qing);
		mWeatherIcon.put("沙尘暴", R.drawable.biz_plugin_weather_shachenbao);

		mWeatherIcon.put("特大暴雨", R.drawable.biz_plugin_weather_tedabaoyu);
		mWeatherIcon.put("雾", R.drawable.biz_plugin_weather_wu);
		mWeatherIcon.put("小雪", R.drawable.biz_plugin_weather_xiaoxue);
		mWeatherIcon.put("小雨", R.drawable.biz_plugin_weather_xiaoyu);
		mWeatherIcon.put("阴", R.drawable.biz_plugin_weather_yin);

		mWeatherIcon.put("雨夹雪", R.drawable.biz_plugin_weather_yujiaxue);
		mWeatherIcon.put("阵雪", R.drawable.biz_plugin_weather_zhenxue);
		mWeatherIcon.put("阵雨", R.drawable.biz_plugin_weather_zhenyu);
		mWeatherIcon.put("中雪", R.drawable.biz_plugin_weather_zhongxue);
		mWeatherIcon.put("中雨", R.drawable.biz_plugin_weather_zhongyu);
	}

	BroadcastReceiver netChangeReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(NET_CHANGE_ACTION)) {
				if (mListeners.size() > 0)// 通知接口完成加载
					for (EventHandler handler : mListeners) {
						handler.onNetChange();
					}
			}
			// mNetWorkState = NetUtil.getNetworkState(mApplication);
		}

	};

	public static abstract interface EventHandler {
		public abstract void onCityComplite();

		public abstract void onNetChange();
	}

}