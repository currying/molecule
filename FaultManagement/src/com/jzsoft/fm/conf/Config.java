package com.jzsoft.fm.conf;

import android.os.Environment;

public class Config {
	public static final String PHOTOPATH = Environment
			.getExternalStorageDirectory().getPath() + "/com.jzsoft.fm/photo/";
	public static final String AUDIOPATH = Environment
			.getExternalStorageDirectory().getPath() + "/com.jzsoft.fm/audio/";
	public static final String DATANAME = "fm.db";
	public static final String LOG_TAG = "com.jzsoft.fm";

	public static final String BAIDU_WEB_API_KEY = "W6GS86CEqOdsy4E3qpw2Pknz";
	public static final String CHANGEPOSITION = "http://api.map.baidu.com/geoconv/v1/?coords=";
	public static final String GEOCODER = "http://api.map.baidu.com/geocoder/v2/?ak=";
	public static final String FROM = "&from=";
	public static final String TO = "&to=";
	public static final String AK = "&ak=";
	public static final String SERVERURL = "http://192.168.111.102:8080/platform2/rest";

	public static final int CONNECTTIMEOUTEXCEPTION = 1;
	public static final int CLIENTPROTOCOLEXCEPTION = 2;
	public static final int IOEXCEPTION = 3;
	public static final int JSONEXCEPTION = 4;
}
