/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.myxilove.gift.model;

import com.myxilove.gift.allforyou.R;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class Constants {

	public static final String[] IMAGES = new String[] {
			// Heavy images

			"https://lh4.googleusercontent.com/-e9NHZ5k5MSs/URqvMIBZjtI/AAAAAAAAAbs/1fV810rDNfQ/s1024/Yosemite%252520Tree.jpg",
			// Light images
			"http://tabletpcssource.com/wp-content/uploads/2011/05/android-logo.png",

			// Special cases
			"http://cdn.urbanislandz.com/wp-content/uploads/2011/10/MMSposter-large.jpg", // Very
																							// large
																							// image
			"http://4.bp.blogspot.com/-LEvwF87bbyU/Uicaskm-g6I/AAAAAAAAZ2c/V-WZZAvFg5I/s800/Pesto+Guacamole+500w+0268.jpg", // Image
																															// with
																															// "Mark has been invalidated"
																															// problem
			"file:///sdcard/Universal Image Loader @#&=+-_.,!()~'%20.png", // Image
																			// from
																			// SD
																			// card
																			// with
																			// encoded
																			// symbols
			"assets://Living Things @#&=+-_.,!()~'%20.jpg", // Image from assets
			"drawable://" + R.drawable.ic_launcher, // Image from drawables
			"http://upload.wikimedia.org/wikipedia/ru/b/b6/Как_кот_с_мышами_воевал.png", // Link
																							// with
																							// UTF-8
			"https://www.eff.org/sites/default/files/chrome150_0.jpg", // Image
																		// from
																		// HTTPS
			"http://bit.ly/soBiXr", // Redirect link
			"http://img001.us.expono.com/100001/100001-1bc30-2d736f_m.jpg", // EXIF
			"", // Empty link
			"http://wrong.site.com/corruptedLink", // Wrong link
	};

	public static final CodeClass[] JXX_IMAGES_CODECLASS = new CodeClass[] {

			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/12/20140112181945_jxvvc.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/12/20140112235644_va4mM.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201401/06/20140106143927_xEkK4.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/23/20140123034517_SS5aH.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201312/31/20131231145011_zYMjr.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/17/20140117203418_Vum8y.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/19/20140119131845_jGPFZ.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/20/20140120182750_Frcw4.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201401/06/20140106212714_xBmzT.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/08/20140108042633_22viZ.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/10/20140110162433_whkJX.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201401/20/20140120193022_yJ3FY.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/15/20140115174640_AGAVu.thumb.600_0.png",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/07/20140107185925_HBumt.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/19/20140119081259_RJcNc.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/13/20140113231045_mJkzz.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/07/20140107184215_EcXYm.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/12/20140112103452_vnhwJ.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201312/27/20131227213515_3Qi5Q.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/16/20140116131443_UwX4N.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/08/20140108042209_cyCcM.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201401/07/20140107183753_G3Va8.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/12/20140112183651_VrzdF.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/07/20140107185542_Rrwwd.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/15/20140115040442_jZ8fK.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/15/20140115041432_5Cz2F.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/12/20140112183857_LmLZt.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/07/20140107131854_EEnEU.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201402/16/20140216232239_3JSST.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201402/19/20140219224041_ZU4tT.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201402/09/20140209225226_zydLf.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201402/27/20140227155919_aRhWn.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201403/02/20140302033855_zXjkw.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201402/27/20140227100459_TiMsP.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/09/20140209031120_XWiEN.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/20/20140220012326_dBGdG.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201402/19/20140219195905_LCQ4c.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/10/20140210034022_mrhGK.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/02/20140202030450_wVZ48.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/13/20140213034616_rcuKM.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201402/20/20140220013056_YKyyC.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/05/20140205054841_AQXwn.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/29/20140129135706_vunA2.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/05/20140205060321_uNmcS.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/23/20140123002135_URRWV.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201402/02/20140202032358_w3cs3.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/24/20140124012611_HFfji.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/07/20140107182415_2uyWc.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201311/06/20131106193207_h32KJ.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201402/04/20140204012137_UdMWA.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/12/20140112235313_eFT5f.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/12/20140112235000_PmaMC.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201402/02/20140202021411_QJaXm.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201401/12/20140112133930_54xxZ.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/15/20140115040328_WBNVx.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201309/13/20130913111443_mahwU.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/18/20140118215213_WeVke.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/19/20140119074835_kUEcv.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/30/20140130070852_fZfvx.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201401/30/20140130180644_zsJPT.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/15/20140115202326_2hziF.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/18/20140118221709_tTS8m.thumb.600_0.png",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201401/24/20140124000330_Vstwe.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/23/20140123003058_hjZXK.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/29/20140129181958_P28dV.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/13/20140113203857_Si8fh.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/19/20140119004230_rVKvS.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/19/20140119004230_rVKvS.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/17/20140117192715_hW8JU.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201401/31/20140131194158_JZQKZ.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/31/20140131194039_f3yYn.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/19/20140119080327_2Ks5e.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/15/20140115202211_xCmKT.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/blog/201401/24/20140124130058_AhTxj.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/29/20140129051050_QekGs.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/17/20140117203358_mRS4T.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img5.duitang.com/uploads/item/201401/21/20140121180755_wPBGP.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/19/20140119074228_ZwHEc.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/30/20140130070858_SsSJM.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/23/20140123134425_8Sn4e.thumb.600_0.jpeg",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/18/20140118220821_vwx2M.thumb.600_0.png",
					"xiuxian"),
			new CodeClass(
					"http://img4.duitang.com/uploads/item/201401/15/20140115040345_BuiuM.thumb.600_0.jpeg",
					"xiuxian") };

	public static final CodeClass[] BIG_IMAGES = new CodeClass[] {
			//
			// "drawable://" + R.drawable.j_2, // Image from drawables
			// "drawable://" + R.drawable.a_2, // Image from drawables
			// "drawable://" + R.drawable.a_3, // Image from drawables
			// "drawable://" + R.drawable.a_4, // Image from drawables
			// "drawable://" + R.drawable.a_5, // Image from drawables
			// "drawable://" + R.drawable.a_6, // Image from drawables
			// "drawable://" + R.drawable.a_7, // Image from drawables
			// "drawable://" + R.drawable.a_8, // Image from drawables
			// "drawable://" + R.drawable.a_9, // Image from drawables
			// "drawable://" + R.drawable.a_1, // Image from drawables
			// "drawable://" + R.drawable.j_3, // Image from drawables
			// "drawable://" + R.drawable.a_10, // Image from drawables
			// "drawable://" + R.drawable.j_5, // Image from drawables
			// "drawable://" + R.drawable.a_21, // Image from drawables
			// "drawable://" + R.drawable.a_8, // Image from drawables
			// "drawable://" + R.drawable.a_9, // Image from drawables
			// "drawable://" + R.drawable.a_19, // Image from drawables
			// "drawable://" + R.drawable.app_25, // Image from drawables
			// "drawable://" + R.drawable.a_20, // Image from drawables
			// "drawable://" + R.drawable.a_22, // Image from drawables
			// "drawable://" + R.drawable.a_23, // Image from drawables
			new CodeClass("drawable://" + R.drawable.j_1, "xiuxian"),// Image
																		// from
			new CodeClass("drawable://" + R.drawable.j_2, "xiuxian"),// Image
																		// from
			// drawables
			new CodeClass("drawable://" + R.drawable.j_3, "xiuxian"),// Image
																		// from
			// drawables
			new CodeClass("drawable://" + R.drawable.j_4, "xiuxian"), // Image
																		// from
																		// drawables
			new CodeClass("drawable://" + R.drawable.j_5, "xiuxian"),// Image
																		// from
																		// drawables
			new CodeClass("drawable://" + R.drawable.j_6, "xiuxian"),// Image
																		// from
																		// drawables
			new CodeClass("drawable://" + R.drawable.j_7, "xiuxian"), // Image
																		// from
																		// drawables

	};

	private Constants() {
	}

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}

	public static class Extra {
		public static final String IMAGES = "com.nostra13.example.universalimageloader.IMAGES";
		public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
	}
}
