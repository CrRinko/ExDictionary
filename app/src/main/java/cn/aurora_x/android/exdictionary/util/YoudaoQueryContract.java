package cn.aurora_x.android.exdictionary.util;

/**
 * Created by Rinko on 2016/11/13.
 */
public class YoudaoQueryContract {
    public static final String KEY_FROM="haobaoshui";
    public static final String KEY="1650542691";
    public static final String JSON_TYPE="json";
    public static String getURL(String queryWord){
        return "http://fanyi.youdao.com/openapi.do?" +
                "keyfrom="+KEY_FROM+"&" +
                "key="+KEY+"&" +
                "type=data&" +
                "doctype="+JSON_TYPE+"&" +
                "version=1.1&" +
                "q="+queryWord;
    }
    public static final String JSON_ERROR_CODE ="errorCode";
    public static final String JSON_TRANSLATION="translation";
}
