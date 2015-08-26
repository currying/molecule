package com.toparchy.platform.security.model;

public enum ApplicationRealm {
	AH;
	// AH("Anhui", "安徽"), BJ("Geijing", "北京"), FJ("Fujian", "福建"), GS("Gansu",
	// "甘肃"), GD("Guangdong", "广东"), GX("Guangxi", "广西"), GZ("Guizhou",
	// "贵州"), HI("Hainan", "海南"), HE("Hebei", "河北"), HA("Henan", "河南"), HL(
	// "Heilongjiang", "黑龙江"), HB("Hubei", "湖北"), HN("Hunan", "湖南"), JL(
	// "Jilin", "吉林"), JS("Jiangsu", "江苏"), JX("Jiangxi", "江西"), LN(
	// "Liaoning", "辽宁"), NM("Inner Mongoria IM", "内蒙古自治区"), NX("Ningxia",
	// "宁夏"), QH("Qinghai", "青海"), SD("Shandong", "山东"), SX("Shanxi", "山西"), SN(
	// "Shaanxi", "陕西"), SH("Shanghai", "上海"), SC("Sichuan", "四川"), TJ(
	// "Tianjin", "天津"), XZ("Tibet", "西藏"), XJ("Xinjiang", "新疆"), YN(
	// "Yuannan", "云南"), ZJ("Zhejiang", "浙江"), CQ("Chongqing", "重庆"), MO(
	// "Macao", "澳门"), HK("Hong Kong", "香港"), TW("Taiwan", "台湾");
	private ApplicationRealm() {
	}

	private ApplicationRealm(String _name, String _value) {
		this._name = _name;
		this._value = _value;
	}

	private String _name;
	private String _value;

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_value() {
		return _value;
	}

	public void set_value(String _value) {
		this._value = _value;
	}

}
