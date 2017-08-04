package com.zd.csms.salary.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 城市类型登记常量
 * 优先判断区县
 * 然后城市
 * 最后省份
 * 
 * 除去一类和特殊城市其他均为二类
 *
 */
public class CityLevelConstants {
	/**
	 * 特殊城市
	 */
	public static final List<String> especialCity = Arrays.asList("北京市", "上海市", "广州市", "深圳市", "杭州市", "建德市", "临安市", "富阳市");
	/**
	 * 一类省份
	 */
	public static final List<String> firstProvince = Arrays.asList("广东省");

	/**
	 * 一类城市
	 */
	public static final List<String> firstCity = Arrays.asList("哈尔滨市", "长春市", "沈阳市", "大连市", "石家庄市", "唐山市", "秦皇岛市", "呼和浩特市",
			"鄂尔多斯市", "天津市", "太原市", "济南市", "青岛市", "西安市", "郑州市", "合肥市", "南京市", "苏州市", "宁波市", "义乌市", "温州市", "南昌市", "长沙市",
			"武汉市", "重庆市", "成都市", "西宁市", "兰州市", "乌鲁木齐市", "克拉玛依市", "拉萨市", "昆明市", "贵阳市", "南宁市", "桂林市", "福州市", "厦门市", "泉州市",
			"海口市", "三亚市");
	/**
	 * 远疆省份
	 */
	public static final List<String> distantProvince = Arrays.asList("宁夏回族自治区", "青海省", "新疆维吾尔自治区", "西藏自治区");

	/**
	 * 远疆城市
	 */
	public static final List<String> distantCity = Arrays.asList("黑河市", "酒泉市", "张掖市", "金昌市", "武威市", "嘉峪关市", "平凉市", "临夏市",
			"保山市", "普洱市", "临沧市", "昭通市", "安顺市");

	/**
	 * 远疆区县
	 */
	public static final List<String> distantCounty = Arrays.asList("加格达奇区", "松岭区", "新林区", "呼中区", "呼玛县", "塔河县", "漠河县", "蒙自市",
			"个旧市", "开远市", "绿春县", "建水县", "石屏县", "弥勒县", "泸西县", "元阳县", "红河县", "金平苗族瑶族傣族自治县", "河口瑶族自治县", "屏边苗族自治县", "文山市",
			"砚山县", "丘北县", "广南县", "富宁县", "马关县", "西畴县", "麻栗坡县", "景洪市", "勐海县", "勐腊县", "芒市", "瑞丽市", "陇川县", "梁河县", "盈江县",
			"泸水县", "福贡县", "兰坪白族普米族自治县", "贡山独龙族怒族自治县", "香格里拉市", "维西傈僳族自治县", "德钦县", "东胜区", "达拉特旗", "准格尔旗", "鄂托克前旗",
			"鄂托克旗", "杭锦旗", "乌审旗", "伊金霍洛旗", "海拉尔区", "满洲里市", "牙克石市", "扎兰屯市", "额尔古纳市", "根河市", "阿荣旗", "鄂伦春自治旗",
			"莫力达瓦达斡尔族自治旗", "鄂温克族自治旗", "陈巴尔虎旗", "新巴尔虎左旗", "新巴尔虎右旗", "临河区", "五原县", "磴口县", "乌拉特前旗", "乌拉特中旗", "乌拉特后旗",
			"杭锦后旗", "集宁区", "丰镇市", "卓资县", "化德县", "商都县", "兴和县", "凉城县", "察哈尔右翼前旗", "察哈尔右翼中旗", "察哈尔右翼后旗", "四子王旗", "乌兰浩特市",
			"阿尔山市", "科尔沁右翼前旗", "科尔沁右翼中旗", "扎赉特旗", "突泉县", "锡林浩特市", "二连浩特市", "阿巴嘎旗", "苏尼特左旗", "苏尼特右旗", "东乌珠穆沁旗", "西乌珠穆沁旗",
			"太仆寺旗", "镶黄旗", "正镶白旗", "正蓝旗", "多伦县", "阿拉善左旗", "阿拉善右旗", "额济纳旗", "海勃湾区", "海南区", "乌达区"
	);

}
