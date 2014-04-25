package com.myxilove.gift.allforyou.fragmets;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.beardedhen.androidbootstrap.FontAwesomeText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myxilove.gift.allforyou.R;
import com.myxilove.gift.allforyou.activities.MainActivity;
import com.myxilove.gift.allforyou.adapter.DrawerAdapter;
import com.myxilove.gift.application.BaseApplication;
import com.myxilove.gift.model.City;
import com.myxilove.gift.model.SimpleWeather;
import com.myxilove.gift.model.SimpleWeather2;
import com.myxilove.gift.model.SimpleWeatherinfo;
import com.myxilove.gift.model.SimpleWeatherinfo2;
import com.way.db.CityDB;
import com.way.util.NetUtil;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint({ "ValidFragment", "HandlerLeak" })
public class DrawerFragment extends Fragment implements BDLocationListener {
	private static final int GET_WEATHER_RESULT = 3;
	private static final int GET_WEATHER_RESULT2 = 2;
	private DrawerAdapter adapter;
	private String cityName;
	private LocationClient mLocationClient = null;
	// private GeofenceClient mGeofenceClient;
	private TextView city;
	private TextView duTv;
	private TextView week_today;
	private TextView temperature;
	private TextView climate;
	private TextView wind;
	private ImageView weatherImg, weatherImg1, weatherImg2;
	private RelativeLayout relativeLayout;
	private FrameLayout framelayout;
	private CityDB mCityDB;
	private Typeface typeface, chineseTypeface;
	private Gson mGson;
	public static final String WEATHER_SIMPLE_URL = "http://www.weather.com.cn/data/sk/";// 简要天气信息
	public static final String WEATHER_SIMPLE_URL2 = "http://www.weather.com.cn/data/cityinfo/";// 简要天气信息2
	private City mCity;
	private SimpleWeatherinfo mCurSimpleWeatherinfo;
	private SimpleWeatherinfo2 mCurSimpleWeatherinfo2;
	private RelativeLayout relative;
	private BaseApplication maApplication = BaseApplication.getInstance();
	private boolean touchEableFlag = true;
	private MainActivity a;
	private String value;

	public DrawerFragment(MainActivity a) {
		this.a = a;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new DrawerAdapter(getActivity());
		mCityDB = maApplication.getCityDB();
		mGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		initLocation();
		typeface = BaseApplication.englishTypeface;
		chineseTypeface = BaseApplication.chineseTypeface;
		value = getActivity().getIntent().getStringExtra("Key");
	}

	private void initLocation() {
		// TODO Auto-generated method stub
		mLocationClient = new LocationClient(getActivity());
		/**
		 * ——————————————————————————————————————————————————————————————————
		 * 这里的AK和应用签名包名绑定，如果使用在自己的工程中需要替换为自己申请的Key
		 * ——————————————————————————————————————————————————————————————————
		 */
		mLocationClient.setAK("dYiYlK17EaDkWS1P37CGUGGy");
		mLocationClient.registerLocationListener(this);
		// mGeofenceClient = new GeofenceClient(getActivity());
		mLocationClient.setLocOption(getLocationClientOption());
	}

	public synchronized LocationClient getLocationClient() {
		if (mLocationClient == null)
			mLocationClient = new LocationClient(getActivity(),
					getLocationClientOption());
		return mLocationClient;
	}

	private LocationClientOption getLocationClientOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(false); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setServiceName("com.baidu.location.service_v2.9");
		option.setPoiExtraInfo(true);
		option.setAddrType("all");
		option.setScanSpan(2000);
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
		option.setPoiNumber(10);
		option.disableCache(true);
		return option;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.drawer_fragment, container,
				false);
		initWeather(rootView);
		initListView(rootView);
		initHeader(rootView);
		initFooter(rootView);
		mLocationClient.start();
		return rootView;
	}

	private void initFooter(View rootView) {
		BootstrapButton exitBt = (BootstrapButton) rootView
				.findViewById(R.id.btnTwo);
		exitBt.setOnClickListener(listener);
	}

	private void initHeader(View rootView) {
		LinearLayout linear = (LinearLayout) rootView.findViewById(R.id.linear);
		linear.setOnClickListener(listener);
		TextView titleName = (TextView) rootView.findViewById(R.id.title_name);
		titleName.setTypeface(chineseTypeface);
		TextView emptyTv = (TextView) rootView.findViewById(R.id.empty_text);
		emptyTv.setTypeface(typeface);
		TextView title_likes = (TextView) rootView
				.findViewById(R.id.title_likes);
		BootstrapCircleThumbnail image = (BootstrapCircleThumbnail) rootView
				.findViewById(R.id.title_pic);
		title_likes.setTypeface(typeface);
		if (value.equals("xiuxian")) {
			image.setImage(R.drawable.title_xiuxian);
			titleName.setText("都叫兽");
			title_likes.setText("1988-02-16");
		}

	}

	private void initWeather(View view) {
		// TODO Auto-generated method stub
		city = (TextView) view.findViewById(R.id.city);
		city.setTypeface(chineseTypeface);
		week_today = (TextView) view.findViewById(R.id.week_today);
		week_today.setTypeface(typeface);
		temperature = (TextView) view.findViewById(R.id.temperature);
		temperature.setTypeface(typeface);
		climate = (TextView) view.findViewById(R.id.climate);
		climate.setTypeface(chineseTypeface);
		duTv = (TextView) view.findViewById(R.id.du);
		duTv.setTypeface(typeface);
		wind = (TextView) view.findViewById(R.id.wind);
		wind.setTypeface(chineseTypeface);
		weatherImg = (ImageView) view.findViewById(R.id.weather_img);
		weatherImg1 = (ImageView) view.findViewById(R.id.weather_img1);

		weatherImg2 = (ImageView) view.findViewById(R.id.weather_img2);

		relativeLayout = (RelativeLayout) view
				.findViewById(R.id.relativeLayout);
		relativeLayout.setVisibility(View.GONE);
		framelayout = (FrameLayout) view.findViewById(R.id.framelayout);
		framelayout.setVisibility(View.VISIBLE);
		relative = (RelativeLayout) view.findViewById(R.id.relative);
		relative.setOnClickListener(listener);
	}

	private void initListView(View rootView) {
		// TODO Auto-generated method stub
		ListView listView = (ListView) rootView
				.findViewById(R.id.left_drawer_listview);
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View footer = layoutInflater.inflate(R.layout.listview_foot, listView,
				false);
		((TextView) footer.findViewById(R.id.textView))
				.setTypeface(chineseTypeface);
		FontAwesomeText view_1 = (FontAwesomeText) footer
				.findViewById(R.id.view_1);
		FontAwesomeText view_2 = (FontAwesomeText) footer
				.findViewById(R.id.view_2);
		view_2.startRotate(getActivity(), false,
				FontAwesomeText.AnimationSpeed.SLOW);
		view_1.startRotate(getActivity(), true,
				FontAwesomeText.AnimationSpeed.SLOW);
		listView.addFooterView(footer);
		listView.setAdapter(adapter);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.linear:

				break;

			case R.id.btnTwo:
				a.getmDrawerLayout().closeDrawer(GravityCompat.START);
				a.onBackPressed();
				break;

			case R.id.relative:

				if (touchEableFlag) {
					framelayout.setVisibility(View.VISIBLE);
					relativeLayout.setVisibility(View.GONE);
					touchEableFlag = false;
					updateWeatherByTouch();

				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onReceiveLocation(BDLocation location) {
		// TODO Auto-generated method stub
		if (location != null) {
			cityName = location.getCity() != null ? location.getCity() : "";
			if (!cityName.equals("")) {
				String[] split = cityName.split("市");
				mCity = mCityDB.getCity(split[0]);
				mLocationClient.stop();
				updateWeather(true);
				updateWeather2(true);
			}
		}
	}

	protected void updateWeatherByTouch() {
		// TODO Auto-generated method stub
		mLocationClient.start();
	}

	@Override
	public void onReceivePoi(BDLocation arg0) {
		// TODO Auto-generated method stub

	}

	private void updateWeather2(final boolean isRefresh) {
		// TODO Auto-generated method stub
		if (NetUtil.getNetworkState(getActivity()) == NetUtil.NETWORN_NONE
				&& isRefresh) {
			// T.showLong(this, R.string.net_err);
			return;
		}
		new Thread() {
			@Override
			public void run() {
				super.run();
				// getSimpleWeatherInfo(isRefresh);
				String connServerForResult = "";
				if (isRefresh) {
					String url = WEATHER_SIMPLE_URL2 + mCity.getNumber()
							+ ".html";
					connServerForResult = connServerForResult(url);
					SimpleWeather2 updateWeatherInfo = updateWeatherInfo2(connServerForResult);
					mCurSimpleWeatherinfo2 = updateWeatherInfo.getWeatherinfo();
				}

				Message msg = mHandler.obtainMessage();
				msg.what = GET_WEATHER_RESULT2;
				msg.obj = mCurSimpleWeatherinfo2;
				mHandler.sendMessage(msg);
			}

		}.start();
	}

	private void updateWeather(final boolean isRefresh) {
		if (NetUtil.getNetworkState(getActivity()) == NetUtil.NETWORN_NONE
				&& isRefresh) {
			// T.showLong(this, R.string.net_err);
			return;
		}
		new Thread() {
			@Override
			public void run() {
				super.run();
				// getSimpleWeatherInfo(isRefresh);
				String connServerForResult = "";
				if (isRefresh) {
					String url = WEATHER_SIMPLE_URL + mCity.getNumber()
							+ ".html";
					connServerForResult = connServerForResult(url);
					SimpleWeather updateWeatherInfo = updateWeatherInfo(connServerForResult);
					mCurSimpleWeatherinfo = updateWeatherInfo.getWeatherinfo();
				}

				Message msg = mHandler.obtainMessage();
				msg.what = GET_WEATHER_RESULT;
				msg.obj = mCurSimpleWeatherinfo;
				mHandler.sendMessage(msg);
			}

		}.start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GET_WEATHER_RESULT:
				SimpleWeatherinfo info = (SimpleWeatherinfo) msg.obj;
				temperature.setText(info.getTemp());
				city.setText(info.getCity());
				wind.setText(info.getWD());
				Time t = new Time();
				t.setToNow();
				int hour = t.hour;
				String day = "";
				int month = t.month + 1;
				String monthStr = "";
				if (month < 10) {
					monthStr = "0" + month;
				} else {
					monthStr = month + "";
				}
				if (t.monthDay < 10) {
					day = "0" + t.monthDay;
				} else {
					day = t.monthDay + "";
				}
				String time = "午夜";
				if (hour <= 22) {
					time = "夜晚";
				}
				if (hour <= 18) {
					time = "下午";
				}
				if (hour <= 13) {
					time = "中午";
				}
				if (hour <= 11) {
					time = "上午";
				}
				if (hour <= 6) {
					time = "凌晨";
				}
				week_today.setText(monthStr + "." + day);
				climate.setText(time);
				relativeLayout.setVisibility(View.VISIBLE);
				framelayout.setVisibility(View.GONE);
				touchEableFlag = true;
				break;

			case GET_WEATHER_RESULT2:
				SimpleWeatherinfo2 info2 = (SimpleWeatherinfo2) msg.obj;
				if (info2 != null) {
					String weather = info2.getWeather();
					int weatherIcon = maApplication.getWeatherIcon(weather);
					weatherImg.setBackgroundResource(weatherIcon);
					weatherImg1.setBackgroundResource(weatherIcon);
					weatherImg2.setBackgroundResource(weatherIcon);
					touchEableFlag = true;
				}

				break;
			default:
				break;
			}
		}

	};

	private SimpleWeather updateWeatherInfo(String obj) {
		return mGson.fromJson(obj, SimpleWeather.class);
	}

	private SimpleWeather2 updateWeatherInfo2(String connServerForResult) {
		// TODO Auto-generated method stub
		return mGson.fromJson(connServerForResult, SimpleWeather2.class);
	}

	// 请求服务器，获取返回数据
	private String connServerForResult(String url) {
		HttpGet httpRequest = new HttpGet(url);
		String strResult = "";
		if (NetUtil.getNetworkState(maApplication) != NetUtil.NETWORN_NONE) {
			try {
				// HttpClient对象
				HttpClient httpClient = new DefaultHttpClient();
				// 获得HttpResponse对象
				HttpResponse httpResponse = httpClient.execute(httpRequest);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					// 取得返回的数据
					strResult = EntityUtils.toString(httpResponse.getEntity());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strResult; // 返回结果
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		if (mLocationClient.isStarted()) {
			mLocationClient.stop();
		}
		super.onDestroy();
	}
}
