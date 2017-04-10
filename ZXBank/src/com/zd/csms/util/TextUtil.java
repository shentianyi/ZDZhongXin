package com.zd.csms.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.util.HtmlUtils;

import com.zd.tools.StringUtil;

/**
 * 文本操作相关工具类
 * <p/>
 * <p>Title: </p>
 * <p/>
 * <p>Description: </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p/>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TextUtil {
    private static MathContext mctx = new MathContext(10);
    private static BigDecimal base100 = new BigDecimal("100.00");

    private static String hexString = "0123456789ABCDEF";

    /**
     * TEXTAREA的输入内容格式化
     *
     * @return String
     * @throws Exception
     */
    public static String HtmlFormatText(String text) {
        if (text == null || text.equals(""))
            return "";
        String tmp;
        tmp = text.replaceAll("\r\n", "<BR>");
        tmp = tmp.replaceAll(" ", "&nbsp;");
        tmp = tmp.replaceAll("\"", "&quot;");
        tmp = tmp.replaceAll("'", "&#39;");
        return tmp;

    }

    /**
     * 格式化标题
     *
     * @param text String
     * @return String
     * @throws Exception
     */
    public static String HtmlTextFormat(String text) {
        return text.replaceAll("\r\n", "").replaceAll("<br>", "").replaceAll("<p>", "").replaceAll("</p>", "")
        			.replaceAll("&nbsp;", "").replaceAll("&quot;", "\"").replaceAll("&#39;", "'")
        			.replaceAll("&ldquo;", "“").replaceAll("&rdquo;", "”").replaceAll("&amp;", "&");

    }

    /**
     * 转义字符处理
     * @param text
     * @return
     */
    public static String escapeCharacter(String text){
        if(text == null || text.equals(""))
            return "";
        String tmp = text;
        tmp= tmp.replaceAll("\\\\", "\\\\\\\\");
        tmp= tmp.replaceAll("\"", "\\\\\"");
        tmp= tmp.replaceAll("\'", "\\\\\'");
        tmp= tmp.replaceAll("\r", "\\\\\r");
        tmp = tmp.replaceAll("\n", "\\\\\n");
        return tmp;
    }

    /**
     * 反转义字符处理
     * @param text
     * @return
     */
    public static String reEscapeCharacter(String text){
        if(text == null || text.equals(""))
            return "";
        String tmp = text;
        tmp= tmp.replaceAll( "\\\\\\\\","\\\\");
        tmp= tmp.replaceAll("\\\\\"","\"");
        tmp= tmp.replaceAll( "\\\\\'","\'");
        tmp= tmp.replaceAll( "\\\\\r","\r");
        tmp = tmp.replaceAll( "\\\\\n","\n");
        return tmp;
    }

    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的长日期时间字符串
     *
     * @param argDate Timestamp
     * @return String
     * @throws Exception
     */
    public static String getLongDateFormatByTimestamp(java.sql.Timestamp argDate) throws
            Exception {
        java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String strOrderDate = objSDF.format(argDate);
        return strOrderDate;
    }

    public static String getJavascriptFromHtml(String html) {
        //1.将所有的回车变成\n

        html = html.replace("\\", "\\\\");
        html = html.replace("'", "\\'");
        html = html.replace("\r\n", "\\n");
        html = html.replace("\n", "\\n");
        html = "document.write('" + html + "');";
        return html;
    }

    /**
     * 返回yyyy-MM-dd 格式的短日期字符串
     *
     * @param argDate Timestamp
     * @return String
     * @throws Exception
     */
    public static String getShortDateFormatByTimestamp(java.sql.Timestamp argDate) throws
            Exception {
        String strOrderDate = null;
        try {
            /*java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat("yyyy-MM-dd");
            strOrderDate = objSDF.format(argDate);*/
            strOrderDate = DateUtil.getStringFormatByTimestamp(argDate, "yyyy-MM-dd");
        } catch (Exception e) {
            throw new Exception("日期格式错误。正确的格式应该是：yyyy-MM-dd");
        }
        return strOrderDate;
    }

    public static java.sql.Timestamp getDateFormatByString(String argDate) throws
            Exception {
        java.sql.Timestamp tt = null;
        try {
            //int lng = argDate.trim().length();
            //String format = "yyyy-MM-dd HH:mm:ss.S";
            //java.text.SimpleDateFormat objSDF = new java.text.SimpleDateFormat(format);
            //objSDF.applyPattern(format.substring(0, lng));
            //tt = new java.sql.Timestamp(objSDF.parse(argDate).getTime());
            tt = DateUtil.getDateTimeFormatByString(argDate, "yyyy-MM-dd HH:mm:ss.S");

        } catch (Exception e) {
            throw new Exception("日期格式错误。正确的格式应该是：yyyy-MM-dd HH:mm:ss.S");
        }
        return tt;
    }

    /**
     * 得到晚上 23:59:59 的时间
     *
     * @param dateString
     * @param pattern:   dateString 的表式模式, 例如:  yyyy-MM-dd
     * @return
     * @throws Exception
     */
    public static java.sql.Timestamp getNightTimestamp(String dateString, String pattern) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Timestamp tempDate = new Timestamp(format.parse(dateString).getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 传入以分为单位的金额，返回元为单位的字符串;
     * 带有小数点
     *
     * @param amount long
     * @return String
     */
    public static String getMoneyString(long amount) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        return fmt.format((double) (amount) / 100);
        //return (new BigDecimal(amount)).divide(base100).toString();
    }
    
    public static final BigDecimal HUNDRED = new BigDecimal("100");
    
    
    /**
     * 获取价格的 分制表达形式
     * @author zhangzhikun
     * at 2014年5月20日 下午5:27:35
     * @param amount
     * @return
     */
    public static long getMoneyLongType(BigDecimal amount){
    	if(amount == null){
    		return 0;
    	}
    	
    	return amount.multiply(HUNDRED).longValue();
    }

    /**
     * 传入以分为单位的金额，返回元为单位的字符串;
     * 不带小数点
     *
     * @param amount long
     * @return String
     */
    public static String getMoneyStringNoDot(long amount) throws Exception {
        String s = getMoneyString(amount);
        s = s.substring(0, s.lastIndexOf("."));
        return s;
    }

    /**
     * 返回一个字符串的int值
     * 用于商品价格转换
     * 精确值为小数点后两位
     *
     * @param str String
     * @return int
     */
    public static int getMoneyValueFromString(String str) throws Exception {
        //return (int) (Float.parseFloat(str) * 100);
        return (new BigDecimal(str)).multiply(base100).intValue();
    }

    public static int getMoneyValueFromStringNoDot(String str) throws Exception {
        //return (int) (Float.parseFloat(str));
        return (new BigDecimal(str)).intValue();
    }

    public static boolean isIntNumb(String str) {
        try {
            int i = Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public static boolean isDoubleNumb(String str) {
        try {
            double d = Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloatNumb(String str) {
        try {
            float f = Float.parseFloat(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLetterOrNum(String str) {
        Pattern p = Pattern.compile("[[1-9][a-z][A-Z]]+");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 根据传入的字符串返回字符长度
     * 
     * @param s	String
     * @return int
     */
    public static int calcStringLength(String s){
    	int result = 0;
    	try {
			result = s.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * 根据传入的长度截取字符串
     *
     * @param src  String
     * @param len  int
     * @param plac String 如果字符串超过，将以该字符串作为后缀
     * @return String
     */
    public static String cutString(String src, int len, String plac) {
        if (src == null) {
            return "";
        }
        if (plac == null || "".equals(plac.trim())) {
            plac = "";
        }
        src = HtmlTextFormat(src);
        try {
            if (src.getBytes("gbk").length <= len) {
                return src;
            }
            byte[] bytes = src.getBytes("gbk");
            int idx = len - plac.getBytes("gbk").length;
            //因...字符在前端显示时基本视觉感受只占了1个汉字，一般会要求显示14字，或者13 + '...'
            if("...".equals(plac)){
            	idx = len - 2;
            }
            if (idx < 0) {
                return "";
            }
            String result = new String(bytes, 0, idx, "gbk");
            String test = src.substring(0, result.length());
            if (test.equals(result)) {
                return result + plac;
            }
            idx--;
            result = new String(bytes, 0, idx, "gbk");
            return result + plac;

        } catch (Exception e) {
            e.printStackTrace();
            return src;
        }
    }
    
    //用于评论用户名显示，格式：字符**字符...  begin
    public static String cutStringStar(String str){
    	if(StringUtil.isEmpty(str)){
    		return "";
    	}
		if(str.length() <= 3){
			return str.substring(0, 1) + "**";//用于字符串短的时候，比如名字
		}
		if(isMobileNO(str)){
			return str.substring(0, 3) + "****" + str.substring(str.length()-4, str.length());//单独用于手机号
		}else{
			String strTemp = str.substring(0, 1) + "**" + str.substring(3, str.length());
			return TextUtil.cutString(strTemp,11,"...");
		}
	}
    
    
    public static String replaceMultiToSingleBrTag(String content){
    	if(StringUtil.isBlank(content)) return "";
    	String regex = "/>(<br/>)+";
    	content = content.replaceAll("\\s","");
    	content = content.replaceAll(regex, " />");
    	content = content.replaceAll("^<br />", "");	//替换掉第一个<br />标签
    	return content;
    }
	//用于评论用户名显示，格式：字符**字符...  end
    //验证是否为手机号
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 
	 * <p>方法名称: isAllNum|描述:验证数字 </p>
	 * @param mobiles
	 * @return
	 * @author: HuMengqi
	 * @date: Dec 17, 2015 3:31:13 PM
	 */
	public static boolean isAllNum(String mobiles){
		Pattern p = Pattern.compile("^\\d{6}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static String like(String str){
		return like(str, true, true);
	}
	
	public static String like(String str, boolean left){
		return like(str, left, true);
	}
	
	/**
	 * 去掉 " 或 '
	 * @param str
	 * @param left :是否使用 左边通配符
	 * 
	 * @return
	 */
	public static String like(String str, boolean left, boolean right){
		
		StringBuilder sb = new StringBuilder();
		if(left){
			sb.append("%");
		}
		if(!StringUtil.isBlank(str)){
			for(char c:str.toCharArray()){
				if(c == '\'' || c== '"'){
					continue;
				}
				sb.append(c);
			}

		}
		
		if(right){
			sb.append("%");
		}
		
		return sb.toString();
	}

    public static String diffText(String referText, String text) {
        try {
            String result = "";
            int i = 0;
            int j = 0;
            Collection diffs = new Vector();
            char[] rt = referText.toCharArray();
            char[] t = text.toCharArray();

            for (; i < referText.length() && j < text.length();) {
                if (rt[i] == ' ') {
                    i++;
                }
                if (t[j] == ' ') {
                    j++;
                }
                int begin = j;
                String diff = "";

                if (rt[i] != t[j]) {
                    for (; i < referText.length() && j < text.length();) {
                        if (rt[i] == ' ') {
                            i++;
                        }
                        if (t[j] == ' ') {
                            diff += t[j];
                            j++;
                        }

                        if (i >= referText.length() || j >= text.length()) {
                            break;
                        }
                        if (rt[i] == t[j]) {
                            diffs.add(new StringSeqReplacer(new String(diff),
                                    begin,
                                    j - 1));
                            diff = "";
                            break;
                        }

                        diff += t[j];

                        i++;
                        j++;
                    }

                }

                if (diff.length() > 0) {
                    diffs.add(new StringSeqReplacer(new String(diff), begin, j));
                }
                i++;
                j++;
            }

            return StringSeqReplacer.replace(diffs, text);
        } catch (Exception e) {
            e.printStackTrace();
            return text;
        }
    }

    public static String cleanHTML(String h, String replacement) {
        if (h == null) {
            return h;
        }
        if (replacement == null) {
            replacement = "";
        }
        String html = new String(h);
//    String[] htmlTag = {
//        "A", "ABBR", "ACRONYM", "ADDRESS", "APPLET", "AREA", "B", "BASE",
//        "BASEFONT", "BDO", "BIG", "BLOCKQUOTE", "BODY", "BR", "BUTTON",
//        "CAPTION", "CENTER", "CITE", "CODE", "COL", "COLGROUP", "DD", "DEL",
//        "DFN", "DIR", "DIV", "DL", "DT", "EM", "FIELDSET", "FONT", "FORM",
//        "FRAME", "FRAMESET", "H1", "H2", "H3", "H4", "H5", "H6", "HEAD", "HR",
//        "HTML", "I", "IFRAME", "IMG", "INPUT", "INS", "ISINDEX", "KBD", "LABEL",
//        "LEGEND", "LI", "LINK", "MAP", "MENU", "META", "NOFRAMES", "NOSCRIPT",
//        "OBJECT", "OL", "OPTGROUP", "OPTION", "P", "PARAM", "PRE"
//        , "Q", "S", "SAMP", "SCRIPT", "SELECT", "SMALL", "SPAN", "STRIKE",
//        "STRONG", "STYLE", "SUB", "SUP", "TABLE", "TBODY", "TD", "TEXTAREA",
//        "TFOOT", "TH", "THEAD", "TITLE", "TR", "TT", "U", "UL", "VAR"};
        String[] htmlTag = {""};
        String regex = "";

        html = Pattern.compile("<!--((?!<!--).)*-->", Pattern.DOTALL).matcher(html).
                replaceAll(replacement);
        html = Pattern.compile("<script((?!</script).)*</script>",
                Pattern.DOTALL |
                        Pattern.CASE_INSENSITIVE).matcher(html).replaceAll(
                replacement);
        html = Pattern.compile("<style((?!</style).)*</style>",
                Pattern.DOTALL |
                        Pattern.CASE_INSENSITIVE).matcher(html).replaceAll(
                replacement);

        for (int i = 0; i < htmlTag.length; i++) {
            regex = "<" + htmlTag[i] + "[^<]*>";
            html = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(html).
                    replaceAll(replacement);
            regex = "</" + htmlTag[i] + ">";
            html = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(html).
                    replaceAll(replacement);
        }
        //html = html.replaceAll("\\s+", replacement);
        html = html.replaceAll("&nbsp;", " ");
        html = html.replaceAll("\r\n", "");
        html = html.replaceAll("\n", "");
        html = HtmlUtils.htmlEscape(html);
        html = HtmlUtils.htmlUnescape(html);
        return html;
    }

    /**
     * 将beginIdx和endIdx之间的内容替换成replaceString
     *
     * @param origString    String
     * @param replaceString String
     * @param beginIdx      int
     * @param endIdx        int
     * @return String
     */
    public static String replaceRange(String origString, String replaceString, int beginIdx, int endIdx) {
        if (origString.length() <= endIdx) {
            endIdx = origString.length() - 1;
        }
        if (beginIdx < 0) {
            beginIdx = 0;
        }

        StringBuffer resultBuff = new StringBuffer();
        resultBuff.append(origString.substring(0, beginIdx));
        resultBuff.append(replaceString);
        resultBuff.append(origString.substring(endIdx));
        return resultBuff.toString();
    }

    public static String replace(String regex, String replacement) {
        if (regex == null || replacement == null) {
            return regex;
        }
        regex = regex.replaceAll(replacement, "");
        return regex;
    }

    /**
     * 将形如："aaa   bbb  c "这样的分解成“aaa”、“bbb”,“c”三个元素
     *
     * @param s      String
     * @param holder String 占位符
     * @return Collection
     */
    public static Collection<String> getSplitString(String s, String holder) {
        Vector coll = new Vector();
        if (s == null) {
            return coll;
        }
        String[] sArray = s.split(holder);
        for (int i = 0; i < sArray.length; i++) {
            sArray[i] = sArray[i].trim();
            if (sArray[i].length() > 0) {
                coll.add(sArray[i]);
            }
        }
        return coll;
    }

    public static String[] getSplitStringArray(String s, String holder) {
        Object[] Objs = getSplitString(s, holder).toArray();
        if (Objs == null) {
            return new String[0];
        }
        String[] result = new String[Objs.length];
        for (int i = 0; i < Objs.length; i++) {
            result[i] = (String) Objs[i];
        }
        return result;

    }

    public static boolean isEmpty(String cmd) {
        return (cmd == null || cmd.trim().length() == 0);
    }

    /*
    * 将字符串编码成16进制数字,适用于所有字符（包括中文）
    */

    public static String string2hex(String str) {
        //根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        //将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /*
    * 将16进制数字解码成字符串,适用于所有字符（包括中文）
    */

    public static String hex2string(String bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        //将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

//    public static void main(String[] args) {
////      String str = "<<abc测试ABC>>";
////      String strEncode = string2hex(str);
////      System.out.println("strEncode = " + strEncode);
////      String strDecode = hex2string(strEncode);
////      System.out.println("strDecode = " + strDecode);
////    	String test = "<img src=\"/send.redir?prefix=context&amp;size=67813&amp;url=/upload/2011/10/25/20111025043529936.jpg\" />Test,Test,Test,Test<img src=\"/send.redir?prefix=context&amp;size=67813&amp;url=/upload/2011/10/25/20111025042619244.jpg\" />";
////    	System.out.println( "替换后=" + TextUtil.replaceImgTag(test));
//    	
////    	String c = "<br /><br />规则的重复订单<br /><br />，我们将给予取消。请各位亲知晓。<br />  <br /><br />  <br />";
////    	System.out.println( "替换后:  " + TextUtil.replaceMultiToSingleBrTag(c));
//    }

    public static String Unescape(String x) {
        StringBuffer buf = new StringBuffer((int) (x.length() * 1.1));
        int stringLength = x.length();
        for (int i = 0; i < stringLength; i++) {
            char c = x.charAt(i);
            char c1;
            switch (c) {
                case '\\':
                    c1 = x.charAt(i + 1);
                    if (c1 == 'n') {
                        buf.append('\n');
                        i++;
                    } else if (c1 == 'r') {
                        buf.append('\r');
                        i++;
                    } else if (c1 == '\\') {
                        buf.append('\\');
                        i++;
                    } else if (c1 == 't') {
                        buf.append('\t');
                        i++;
                    } else if (c1 == '"') {
                        buf.append('"');
                        i++;
                    } else {
                        buf.append('\\');
                    }
                    break;
                default:
                    buf.append(c);
            } //end switch
        } //end for
        return buf.toString();
    }

    public TextUtil() {
    }
}

class StringSeqReplacer {

    public StringSeqReplacer(String replace, int begin, int end) {
        this.begin = begin;
        this.end = end;
        this.replace = replace;
    }

    public String replace = "";
    public int begin = 0;
    public int end = 0;

    public static String replace(Collection stringSeq, String text) {
        String t = "";
        int b = 0;
        Iterator it = stringSeq.iterator();
        while (it.hasNext()) {
            StringSeqReplacer sq = (StringSeqReplacer) it.next();
            String sub = text.substring(b, sq.begin);
            t += sub;
            t += "<span style='background-color=#FFC7C7' >" + sq.replace + "</span>";
            b = sq.end + 1;
        }
        if (b < text.length()) {
            String sub = text.substring(b, text.length());
            t += sub;
        }

        return t;
    }

    public static String escapeCharacter(String text) {
        if (text == null || text.equals(""))
            return "";
        String tmp = text;
        tmp = tmp.replaceAll("\\\\", "\\\\\\\\");
        tmp = tmp.replaceAll("\"", "\\\\\"");
        tmp = tmp.replaceAll("\'", "\\\\\'");
        tmp = tmp.replaceAll("\r", "\\\\\r");
        tmp = tmp.replaceAll("\n", "\\\\\n");
        return tmp;
    }
    
}
