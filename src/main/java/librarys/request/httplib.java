package librarys.request;

import librarys.log.logLib;
import librarys.utils.loadProperies;
import librarys.utils.secretUtil;

/**
 * Created by wanli on 16/11/3.
 */
public class httplib {

    /**
     * http的get请求
     * @param urlStr 请求的url
     * @param params 请求的参数字符串
     * @return String 接口请求返回json字符串
     */
    public static String httpGet(String urlStr, String params){
        String ret  = httpRequestUtil.sendGet(urlStr, params);
        return ret;

    }

    /**
     * 请求httpRequestUtil中sendGet,加上了client_secret的加密参数
     * @param urlStr 请求的url
     * @param params 请求的参数字符串
     * @return String 接口请求返回json字符串
     */
    public static String httpGetMD5(String urlStr, String params, String Md5Type){
        params += "&" + loadProperies.get("md5") + "=" + secretUtil.getRequestSecretString(Md5Type, params);
        logLib.logDebug(urlStr+'?'+params);
        String ret  = httpRequestUtil.sendGet(urlStr, params);
        return ret;
    }

    /**
     * http的post请求
     * @param urlStr 请求的url
     * @param params 请求的参数字符串
     * @return String 接口请求返回json字符串
     */
    public static String httpPost(String urlStr, String params){
        String ret  = httpRequestUtil.sendPost(urlStr, params,false);
        return ret;
    }

    /**
     * http的post请求,参数为json字符串
     * @param urlStr 请求的url
     * @param params 请求的Json字符串
     * @return String 接口请求返回json字符串
     */
    public static String httpPostJson(String urlStr, String params){
        String ret  = httpRequestUtil.sendPost(urlStr, params,true);
        return ret;
    }


    /**
     * 请求httpRequestUtil中sendPost,加上了client_secret的加密参数
     * @param urlStr 请求的url
     * @param params 请求的参数字符串
     * @return String 接口请求返回json字符串
     */
    public static String httpPostMD5(String urlStr, String params, String Md5Type){
        params += "&" + loadProperies.get("md5") + "=" + secretUtil.getRequestSecretString(Md5Type, params);
        String ret  = httpRequestUtil.sendPost(urlStr, params,false);
        return ret;
    }
}
