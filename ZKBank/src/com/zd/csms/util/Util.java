package com.zd.csms.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.ctc.wstx.util.URLUtil;
import com.zd.core.annotation.table;
import com.zd.tools.StringUtil;

/**
 * 提供系统中相关操作的工具类集合。
 * 该类主要通过相关的工具类来实现，只是通过一层包装，方便统一调用。
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
public class Util {

    public Util() {
    }

    /** */
    /**
     * @param source    需要处理的字符串
     * @param oldString 需要被替换的字符
     * @param newString 替换后的字符
     * @return 比如 需要替换字符串中的?
     *         String str = "Who am I ?";
     *         replace(str,"?","%3F");
     *         <p/>
     *         TODO:此方法好像完全和String 的replace相同的作用，不过性能差好多，应该删除这个方法，都用String的Replace来代替？
     */
    /*
    public static String replace(String source, String oldString,
                                 String newString) {
        StringBuffer output = new StringBuffer();
        int lengthOfSource = source.length();
        int lengthOfOld = oldString.length();
        int posStart = 0;
        int pos; //
        while ((pos = source.indexOf(oldString, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));
            output.append(newString);
            posStart = pos + lengthOfOld;
        }
        if (posStart < lengthOfSource) {
            output.append(source.substring(posStart));
        }
        return output.toString();
    }
    */
    //---------------------Begin DBUtil------------------------------------//
    /**
     * 取得某个表的主健id值
     *
     * @param tableName String
     * @return int
     * @throws Exception
     */
    public static int getID(String tableName) throws Exception {
        return DBUtil.getID(tableName.toLowerCase());
    }
    
    public static int getID(Class<?> classType) throws Exception{
    	String tableName = classType.getAnnotation(table.class).name();
    	return getID(tableName);
    }

    /**
     * 取得一个sql查询的分页sql字符串
     *
     * @param sql    String 要分页的sql字符串
     * @param begin  int 要取得数据的开始记录
     * @param number int 要去的记录的数量
     * @return String
     */
    public static String getLimitString(String sql, int begin, int number) {
        return DBUtil.getLimitString(sql, begin, number);
    }

    /**
     * 关闭数据库操作对象
     *
     * @param conn  Connection
     * @param pstmt PreparedStatement
     * @param rs    ResultSet
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        DBUtil.close(conn, pstmt, rs);
    }
    public static void close(Connection conn, Statement pstmt,
            ResultSet rs) {
    	DBUtil.close(conn, pstmt, rs);
    }
    

    //---------------------End DBUtil--------------------------------------//
    //---------------------Begin TextUtil------------------------------------//
    public static String getLongDateFormatByTimestamp(java.sql.Timestamp argDate) {
        String str = "";
        try {
            str = TextUtil.getLongDateFormatByTimestamp(argDate);
        } catch (Exception ex) {
        }
        return str;
    }

    /**
     * 比较两个文本，找出不同的地方并标记
     *
     * @param referText String
     * @param text      String
     * @return String
     */
    public static String diffText(String referText, String text) {
        String str = "";
        try {
            str = TextUtil.diffText(referText, text);
        } catch (Exception ex) {
        }
        return str;
    }

    public static String getShortDateFormatByTimestamp(java.sql.Timestamp argDate) {
        String str = "";
        try {
            str = TextUtil.getShortDateFormatByTimestamp(argDate);
        } catch (Exception ex) {
        }
        return str;
    }

    public static java.sql.Timestamp getDateFormatByString(String argDate) throws
            Exception {
        return TextUtil.getDateFormatByString(argDate);
    }

    public static boolean isIntNumb(String str) {
        return TextUtil.isIntNumb(str);
    }

    public static boolean isDoubleNumb(String str) {
        return TextUtil.isDoubleNumb(str);
    }

    public static boolean isFloatNumb(String str) {
        return TextUtil.isFloatNumb(str);
    }

    public static boolean isLetterOrNum(String str) {
        return TextUtil.isLetterOrNum(str);
    }

    /**
     * 传入以分为单位的金额，返回元为单位的字符串;
     * 带有小数点
     *
     * @param amount long
     * @return String
     */
    public static String getMoneyString(long amount) {
        return TextUtil.getMoneyString(amount);
    }

    public static String getMoneyString(BigDecimal amount) {
        if (amount == null) {
            return "0.00";
        }
        DecimalFormat fmt = new DecimalFormat("0.00");
        return fmt.format(amount);
    }

    /**
     * 根据数值与精度位数返回相应精度位数的数值
     *
     * @param amount
     * @param presicion
     * @return
     */
    public static String getMoneyString(BigDecimal amount, int presicion) {
        if (amount == null) {
            return "0";
        }
        StringBuffer sb = new StringBuffer(presicion);
        if (presicion > 0) {
            sb.append("0.");
            for (int i = 0; i < presicion; i++) {
                sb.append("0");
            }
        } else {
            sb.append("0");
        }
        DecimalFormat fmt = new DecimalFormat(sb.toString());
        return fmt.format(amount);

    }

    /**
     * 传入以分为单位的金额，返回元为单位的字符串;
     * 不带小数点
     *
     * @param amount long
     * @return String
     */
    public static String getMoneyStringNoDot(long amount) throws Exception {
        return TextUtil.getMoneyStringNoDot(amount);
    }

    /**
     * 返回一个字符串的int值
     * 用于商品价格转换
     *
     * @param str String
     * @return int
     */
    public static int getMoneyValueFromString(String str) throws Exception {
        return TextUtil.getMoneyValueFromString(str);
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
        return TextUtil.cutString(src, len, plac);
    }

    //---------------------End TextUtil--------------------------------------//
    
    /**
     * 从绝对路径的真实配置文件中取得（Key＝Value）的字符串值。
     *
     * @param key        String
     * @param configFile String
     * @return String
     * @throws IOException
     */
    public static String getCfgStringByKey(String key, String configFile) throws
            IOException {
        String rlt = "";
        File file = new File(configFile);
        if (file.exists() == false) {
            return "";
        }
        if (file.canRead() == false) {
            return "";
        }
        if (file.canWrite() == false) {
            return "";
        }
        InputStream in = null;
        try {
            Properties obj = new Properties();

            in = new BufferedInputStream(new FileInputStream(configFile));
            obj.load(in);
            rlt = obj.getProperty(key);
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return rlt;
    }

    /**
     * 从工程的类路径中获取真实配置文件中取得（Key＝Value）的字符串值
     *
     * @param key        String
     * @param configFile String
     * @return String
     * @throws IOException
     */
    public static String getCfgCPStringByKey(String key, String configFile) throws
            IOException {
        String rlt = "";
        try {
            Properties obj = new Properties();
            URL confURL = Util.class.getClassLoader().getResource(configFile);
            if (confURL == null) {
                return "";
            }
            obj.load(confURL.openStream());
            rlt = obj.getProperty(key);
        } catch (IOException e) {
            throw e;
        }
        return rlt;
    }

    /**
     * ResourceBundle资源文件读取。
     *
     * @param key          String
     * @param filePathName String
     * @param locale       Locale
     * @return String
     * @throws MissingResourceException
     * @throws UnsupportedEncodingException
     */
    public static String getCfgStringByKey(String key, String filePathName,
                                           Locale locale) throws
            MissingResourceException, UnsupportedEncodingException {
        String rlt = "";
        try {
            if (locale == null) {
                locale = Locale.SIMPLIFIED_CHINESE;
            }
            ResourceBundle rb = ResourceBundle.getBundle(filePathName.trim(),
                    locale);
            if (rb == null) {
                return "";
            }
            rlt = rb.getString(key);
        } catch (MissingResourceException e) {
            throw e;
        }
        return rlt;
    }

    //根据传入的长度截取字符串（商品名称，带...）。
    public static String getTitleBytes(String Title, int len) {
        if (Title == null) {
            return "";
        }
        int j = Title.length() - 1;
        int j0 = j;
        String s1 = Title;
        try {
            for (s1 = Title; s1.getBytes("gbk").length > len;) {
                s1 = Title.substring(0, j) + "...";
                j--;
            }
        } catch (Exception e) {
            return Title;
        }
        if (j < 0) {
            return "";
        } else {
            return s1;
        }
    }

    //根据传入的长度截取字符串（商品名称，带...）。
    public static String getTitleBytesNoDot(String Title, int len) {
        if (Title == null) {
            return "";
        }
        int j = Title.length() - 1;
        int j0 = j;
        String s1 = Title;
        try {
            for (s1 = Title; s1.getBytes("gbk").length > len;) {
                s1 = Title.substring(0, j) + "";
                j--;
            }
        } catch (Exception e) {
            return Title;
        }
        if (j < 0) {
            return "";
        } else {
            return s1;
        }
    }

    public static String getStringPath(Collection strings, String placeholder) {
        StringBuffer sb = new StringBuffer();
        Iterator it = strings.iterator();
        String string = null;
        if (placeholder == null | placeholder.trim().length() == 0) { //默认字符串间以逗号隔开
            placeholder = "、";
        }
        while (it.hasNext()) {
            string = (String) it.next();
            sb.append(string);
            if (it.hasNext()) {
                sb.append(placeholder);
            }
        }

        return sb.toString();
    }

    /**
     * 返回例如："s1、s2、s3" 的字符串
     *
     * @param strings     String[]
     * @param placeholder String 如:“、”号
     * @return String
     */
    public static String getStringPath(String strings[], String placeholder) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i != strings.length - 1) {
                sb.append(placeholder);
            }
        }

        return sb.toString();
    }

    /**
     * 把查询的关键子突出
     *
     * @return Collection
     */
    public static String keywordReplace(String title, String keyword) {
        if (keyword != null && !keyword.equals("") && keyword.length() > 0) {
            String[] key = null;
            String reg = "";

            key = analyzeKeyWord(keyword);
            if (key.length > 0) {
                for (int ikey = 0; ikey < key.length; ikey++) {
                    if (ikey != 0) {
                        reg += "|" + key[ikey];
                    } else {
                        reg += key[ikey];
                    }
                }
            }
            Pattern p = Pattern.compile("(" + reg + ")",
                    Pattern.CASE_INSENSITIVE);

            title = p.matcher(title).replaceAll("<font color=#FF6600>$1</font>");

        }
        return title;
    }

    private static String[] analyzeKeyWord(String keyword) {
        String[] a = keyword.split(" ");
        String b = "";
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].trim().length() > 0) {
                b += a[i] + ",";
                j++;
            }
        }
        if (b.length() > 0) {
            a = b.substring(0, b.length() - 1).split(",");
        } else {
            a = b.split(",");
        }
        return a;

    }


    //将两个字符串相加
    public static String addString(String s1, String s2) {
        return s1 + s2;
    }


    /*
    分析像 a=1; b=2; 等这样的字符串
    用于从配置中获取配置的值
     */
    public static Map parseNameValuePairs(String content, String delimiter) {
        Map result = new ConcurrentHashMapExt();

        if (content == null) {
            return result;
        }
        String NameValuePairs[] = content.split(delimiter);

        for (String str : NameValuePairs) {
            if (str != null) {
                String[] nv = str.split("=");
                if (nv.length != 2) {
                    continue;
                }
                result.put(nv[0], nv[1]);
            }
        }
        return result;
    }

    /**
     * 返回今天的凌晨所对应的时间
     *
     * @return Date
     */
    public static java.util.Date getTodayMidNight() {
        Calendar cal = Calendar.getInstance(); //取操作系统的默认locale
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(year, month, day, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 返回前后n天的日期
     */
    public static List<java.util.Date> getDays(int before, int after) {
        Calendar cal = Calendar.getInstance(); //取操作系统的默认locale
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Vector result = new Vector();
        cal.set(year, month, day, 0, 0, 0);
        for (int i = -before; i <= after; i++) {
            Calendar calNew = (Calendar) cal.clone();
            calNew.add(Calendar.DATE, i);

            result.add(calNew.getTime());
        }
        return result;
    }

    public static String getStackStraceString(StackTraceElement[] traces) {
        StringBuffer buffer = new StringBuffer();
        for (StackTraceElement elem : traces) {
            buffer.append(elem.getClassName() + "." + elem.getMethodName() +
                    "," + elem.getLineNumber() + "\n");
        }
        return buffer.toString();

    }

    public static String dumpCurrentThread() {
        Thread t = Thread.currentThread();
        StackTraceElement[] traces = t.getStackTrace();
        return getStackStraceString(traces);
    }

    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    public static String toUpperCase(String text) {
        return text.toUpperCase();
    }

    public static java.util.Date getDateFromJsonString(String jsonString) throws
            ParseException {
        java.text.SimpleDateFormat dateFmt = new java.text.SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ");
        jsonString = jsonString.replace("Z", "");
        jsonString = jsonString + "+0000";
        return dateFmt.parse(jsonString);
    }

    public static Timestamp getTimestampFromJsonString(String jsonString) throws
            ParseException {
        return new Timestamp(getDateFromJsonString(jsonString).getTime());
    }

    public static int parseInt(String value) {
        int result = 0;
        try {
            result = Integer.parseInt(value);
        } catch (Exception e) {
        }
        return result;
    }

    public static BigDecimal parseBigDecimal(String value) {
        BigDecimal result = null;
        try {
            result = new BigDecimal(value);
        } catch (Exception ex) {
            result = new BigDecimal(0);
        }
        return result;
    }

    public static int[] parseIntArray(String[] values) {
        int[] result = null;
        try {
            result = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                result[i] = Integer.parseInt(values[i]);
            }
        } catch (Exception ex) {

        }
        return result;
    }

   

    //对prototype提交的字符串进行编码
    public static String parseToGBK(String value) throws
            UnsupportedEncodingException {
        String cmd = "";
        if (value != null) {
            //cmd = new String(value.getBytes("ISO-8859-1"), "gbk");
            cmd = new String(value.getBytes("gbk"), "utf-8");
        }
        return cmd;
    }

    /**
     * 集合分页
     *
     * @param origObjects
     * @param page        当前页(-1表示不分页,返回所有)
     * @param pageSize    每页数(-1表示不分页,返回所有)
     * @return
     */
    public static Collection<Object> getObjectsPage(Collection<Object> origObjects, int page, int pageSize) {
        Collection<Object> pageObjects = new Vector<Object>();
        if (origObjects == null || origObjects.size() == 0) {
            return pageObjects;
        }
        if (page == -1 || pageSize == -1) {
            return origObjects;
        }
        int count = origObjects.size();
        if (pageSize >= count) {
            return origObjects;
        }

        if (pageSize < 0) {
            pageSize = 0;
        }
        if (page <= 0) {
            page = 1;
        }
        int begin = pageSize * (page - 1);
        if (begin <= 0) {
            begin = 0;
        }
        int totalPage = 0;
        if (pageSize > 0) {
            totalPage = (count + pageSize - 1) / pageSize;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        Iterator it = origObjects.iterator();
        int i = 1;
        while (it.hasNext()) {
            Object o = it.next();
            if (i > begin && i <= (begin + pageSize)) {
                pageObjects.add(o);
            }
            if (pageObjects.size() >= pageSize) {
                break;
            }
            i++;
        }

        return pageObjects;
    }

    public static Collection<Object> getObjectsPageByLimit(Collection<Object> origObjects, int begin, int number) {
        Collection<Object> pageObjects = new Vector<Object>();
        if (origObjects == null || origObjects.size() == 0) {
            return pageObjects;
        }
        if (begin == -1 || number == -1) {
            return origObjects;
        }

        if (begin < 0) {
            begin = 0;
        }
        if (number <= 0) {
            number = 1;
        }
        List list = new ArrayList(origObjects);
        for(int i=begin;i<=(begin+number);i++){
           if(list.size()>begin){
                pageObjects.add(list.get(i));
           }else{
               break;
           }
        }  
        return pageObjects;
    }
    /**
     * 从集合中随机取出N个无素 wwh
     *
     * @param origObjects Collection 源集合
     * @param num         int 取出的数量
     * @return Collection
     */
    public static Collection<Object> getRandomObjectsFromCollection(Collection<Object> origObjects, int num) {
        Collection<Object> ranObjects = new Vector<Object>();
        if (origObjects == null || num < 1) {
            return ranObjects;
        }
        int maxSize = origObjects.size();
        ConcurrentHashMapExt origObjectsMap = new ConcurrentHashMapExt();
        Iterator it = origObjects.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object origObj = it.next();
            ++i;
            origObjectsMap.put(String.valueOf(i), origObj);
        }

        //如果要取的数量比最大可取量大，则取所有元素
        if (num > maxSize) {
            num = maxSize;
        }

        int ranTimes = 0;
        while (ranTimes < num) { //取多少个就随机置换多少次，最后取前num个
            //要置换的是哪一个
            int tempRan1 = (int) (Math.random() * num + 1);
            if (tempRan1 >= num) {
                tempRan1 = num - 1;
            }
            if (tempRan1 < 1) {
                tempRan1 = 1;
            }
            Object tmpObj1 = origObjectsMap.get(String.valueOf(tempRan1));

            int tempRan2 = (int) (Math.random() * maxSize + 1);
            if (tempRan2 < 1) {
                tempRan2 = 1;
            }
            if (tempRan2 > maxSize) {
                tempRan2 = maxSize;
            }

            Object tmpObj12 = origObjectsMap.get(String.valueOf(tempRan2));

            //tmpObj2 与 tmpObj1 置换
            origObjectsMap.put(String.valueOf(tempRan2), tmpObj1);
            origObjectsMap.put(String.valueOf(tempRan1), tmpObj12);
            ranTimes++;
        }

        //取出前num个元素，并返回
        for (int j = 1; j <= num; j++) {
            ranObjects.add(origObjectsMap.get(String.valueOf(j)));
        }

//此随机取算法效率低，应该用置换的算法
//      ConcurrentHashMapExt ranObjectsMap=new ConcurrentHashMapExt();
//      while (ranObjectsMap.values().size() < num) {
//        int temp = (int) (Math.random() * maxSize + 1);
//        if (temp > maxSize) {
//          temp = maxSize;
//        }
//        Object tmpOrigObj=origObjectsMap.get(String.valueOf(temp));
//        ranObjectsMap.put(String.valueOf(temp),tmpOrigObj);
//      }

        return ranObjects;
    }

   


    public static String getParamsString(Map paramMap, String excludeArgs) {
        //获得参数

        String args = "";

        String[] excludeArgsArr = null;
        boolean first = true;
        if (excludeArgs != null) {
            excludeArgsArr = excludeArgs.split(",");
        }
        boolean exclude = false;
        for (java.util.Iterator it = paramMap.entrySet().iterator(); it.hasNext();) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            exclude = false;
            if (excludeArgsArr != null) {
                for (String s : excludeArgsArr) {
                    if (s.equals(entry.getKey().toString())) {
                        exclude = true;
                        break;
                    }
                }
            }
            if (exclude) {
                continue; //next param
            }
            String key = entry.getKey().toString();
            Object objValue = paramMap.get(key);
            if (objValue instanceof String) {
                String value = (String) paramMap.get(key);
                value = StringUtil.replaceXssCharacter(value);
                if(!value.isEmpty()){
                  if (first) {
                      first = false;
                      args = key + "=" + value;
                  } else {
                      args += "&" + key + "=" + value;
                  }
                }
            } else if (objValue instanceof String[]) {
                String[] values = (String[]) objValue;
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    value = StringUtil.replaceXssCharacter(value);
                    if(!value.isEmpty()){
                      if (first) {
                          first = false;
                          args = key + "=" + value;
                      } else {
                          args += "&" + key + "=" + value;
                      }
                    }
                }

            }
        }
        
        return args;
    }

    /**
     * 取得以separator隔分的字符串 如：s1,s2,s3....
     *
     * @param strArray  String[] [s1,s2,s3...]
     * @param separator String 如：“,”
     * @return String
     */
    public static String getSplitString(String[] strArray, String separator) {
        StringBuffer s = new StringBuffer();
        if (strArray == null || strArray.length == 0) {
            return s.toString();
        }
        if (separator == null) {
            separator = ",";
        }
        for (int i = 0; i < strArray.length; i++) {
            s.append(strArray[i]);
            if (i != strArray.length - 1) {
                s.append(separator);
            }
        }
        return s.toString();
    }

    public static Timestamp getDatabaseTimestamp() throws Exception {
        return DBUtil.getDatabaseTimestamp();
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Collection getStringSplit(String str) {
        Collection result = new Vector();
        if (str != null) {
            String[] strArray = str.split(",");
            for (String s : strArray) {
                if (StringUtils.isNotEmpty(s)) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    /**
     * 用 encodeSymbol 对字符串 s 进行编码, 目前的实现是将字符串s中的/t替换成encodeSymbol;
     *
     * @param s
     * @param encodeSymbol
     * @return
     */
    public static String stringTabEncode(String s, String encodeSymbol) {
        if (s != null) {
            String s1 = s.replace(encodeSymbol, "\\" + encodeSymbol);
            return s1.replace("\t", encodeSymbol);
        } else {
            return "";
        }
    }

    /**
     * 用 encodeSymbol 对字符串 s 进行解码, 目前的实现是将字符串s中的decodeSymbol替换成/t;
     *
     * @param s
     * @param decodeSymbol
     * @return
     */
    public static String stringTabDecode(String s, String decodeSymbol) {
        if (s != null) {
            String s1 = s.replace(decodeSymbol, "\t");
            return s1.replace("\\" + "\t", decodeSymbol);
        } else {
            return "";
        }
    }

    /**
     * 取得所有组合元素集合MAP(元素按原字符串从左到右顺序排列)
     *
     * @param s         String 如:"A,B,C"
     * @param separator String 如:","
     * @return ConcurrentHashMapExt key:元素个数i;value:有i个元素的所有组合 如:i=3,那么value是{A,B,C};i=2,那么value是{A,B},{A,C}{B,C}
     *         wwh
     */
    public static ConcurrentHashMapExt getMaximizeCombinatoricsMap(String s, String separator) {
        ConcurrentHashMapExt resultMap = new ConcurrentHashMapExt();
        if (s == null || separator == null) {
            return resultMap;
        }

        String[] sArray = s.split(separator);
        int len = sArray.length;
        if (len == 0) {
            return resultMap;
        }

        if (sArray.length == 1) {
            Collection cTmp = new Vector();
            cTmp.add(sArray[0]);
            resultMap.put(String.valueOf(1), cTmp);
            return resultMap;
        }

        for (int i = 1; i < 2 << (len - 1); i++) {
            int t = i;
            int p = 0; //元素个数
            ConcurrentHashMapExt eMap = new ConcurrentHashMapExt();
            for (int j = 1; j < len + 1; j++) {
                if (t % 2 == 1) {
                    eMap.put(String.valueOf(j), sArray[j - 1]);
                }
                p += t % 2;
                t /= 2;
            }
            t = i;
            Collection eCol = (Collection) resultMap.get(String.valueOf(p));
            if (eCol == null) {
                eCol = new Vector();
            }
            Collection eCollTmp = new Vector();
            for (int j = 1; j < len + 1; j++) {
                String sTmp = (String) eMap.get(String.valueOf(j));
                if (sTmp != null) {
                    eCollTmp.add(sTmp);
                }
            }
            eCol.add(eCollTmp);
            resultMap.put(String.valueOf(p), eCol);
        }

        return resultMap;
    }

    /**
     * 取得所有最佳组合元素集合
     *
     * @param s         String
     * @param separator String
     * @return Collection 每个元素均是一个集合,子集合的元素为每一种组合方式,如:第一个是{"中国","奥运,"北京"},第二个是{"中国","奥运"},第三个是{"中国","北京"}..等
     *         wwh
     */
    public static Collection getMaximizeCombinatorics(String s, String separator) {
        Collection coll = new Vector();
        if (s == null || separator == null) {
            return coll;
        }

        String[] array = s.split(separator);
        int len = 0;
        if (array != null) {
            len = array.length;
        }
        ConcurrentHashMapExt resultMap = getMaximizeCombinatoricsMap(s, separator);
        for (int i = len; i > 0; i--) {
            Collection c = (Collection) resultMap.get(String.valueOf(i));
            if (c != null) {
                coll.addAll(c);
            }
        }
        return coll;
    }

    //将数字转为指定位数的字符串,不够的话,前面补0
    public static String getNumberString(int number,int digit){
       StringBuffer result = new StringBuffer();
       String origNumberString=String.valueOf(number);
       int len=origNumberString.length();
       if(len<digit){
         for(int i=0;i<digit-len;i++){
             result.append("0");
         }
       }
       result.append(origNumberString);
       return result.toString();
    }

    /**
     * 返回long数字的正数.
     * @param bd
     * @return
     */
    public static BigDecimal getItsPlus(BigDecimal bd){
    	BigDecimal zero = new BigDecimal(0);
    	int amount = bd.compareTo(zero);
    	if(amount < 0)
    		return zero.subtract(bd);
    	return bd;
    }
    /**
     * 返回long数字的负数
     * @param bd
     * @return
     */
    public static BigDecimal getItsNegative(BigDecimal bd){
    	BigDecimal zero = new BigDecimal(0);
    	int amount = bd.compareTo(zero);
    	if(amount > 0)
    		return zero.subtract(bd);
    	return bd;
    }
     
}
