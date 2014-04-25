package com.myxilove.gift.listener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class MyLocationListener implements BDLocationListener {

	private BDLocation location = null;
	public BDLocation getLocation() {
		return location;
	}

	public void setLocation(BDLocation location) {
		this.location = location;
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		if (location == null)
			return;

		this.location = location;
//		StringBuffer sb = new StringBuffer(256);
//		// sb.append("time : ");
//		// sb.append(location.getTime());
//		// sb.append("\nerror code : ");
//		// sb.append(location.getLocType());
//		// sb.append("\nlatitude : ");
//		// sb.append(location.getLatitude());
//		// sb.append("\nlontitude : ");
//		// sb.append(location.getLongitude());
//		// sb.append("\nradius : ");
//		// sb.append(location.getRadius());
//		if (location.getLocType() == BDLocation.TypeGpsLocation) {
//			sb.append("\nspeed : ");
//			sb.append(location.getSpeed());
//			sb.append("\nsatellite : ");
//			sb.append(location.getSatelliteNumber());
//		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//			/**
//			 * 格式化显示地址信息
//			 */
//			// sb.append("\n省：");
//			// sb.append(location.getProvince());
//			// sb.append("\n市：");
//			// sb.append(location.getCity());
//			// sb.append("\n区/县：");
//			// sb.append(location.getDistrict());
//			sb.append("\naddr : ");
//			sb.append(location.getAddrStr());
//		}
//		// sb.append("\nsdk version : ");
//		// sb.append(mLocationClient.getVersion());
//		// sb.append("\nisCellChangeFlag : ");
//		// sb.append(location.isCellChangeFlag());
	}

	public void onReceivePoi(BDLocation poiLocation) {
		if (poiLocation == null) {
			return;
		}
		
//		StringBuffer sb = new StringBuffer(256);
//		sb.append("Poi time : ");
//		sb.append(poiLocation.getTime());
//		sb.append("\nerror code : ");
//		sb.append(poiLocation.getLocType());
//		sb.append("\nlatitude : ");
//		sb.append(poiLocation.getLatitude());
//		sb.append("\nlontitude : ");
//		sb.append(poiLocation.getLongitude());
//		sb.append("\nradius : ");
//		sb.append(poiLocation.getRadius());
//		if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
//			sb.append("\naddr : ");
//			sb.append(poiLocation.getAddrStr());
//		}
//		if (poiLocation.hasPoi()) {
//			sb.append("\nPoi:");
//			sb.append(poiLocation.getPoi());
//		} else {
//			sb.append("noPoi information");
//		}
	}
}
