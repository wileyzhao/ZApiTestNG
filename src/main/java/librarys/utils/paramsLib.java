package librarys.utils;

import net.sf.json.JSONObject;

import java.util.Iterator;

/**
 * Created by wanli on 16/11/8.
 */
public class paramsLib{

    /**
     * 通过method来获取接口请求参数,如果参数中不包含method则默认加上
     * @param method 接口方法名
     * @return JSONObject 接口请求参数
     */
    public static JSONObject getParamsByMethod(String method){
        String jsonFile = method.replace('.','_');
        JSONObject jsonDemo = getParams(jsonFile, "");
        //默认加上methon参数
        if (!jsonDemo.containsKey("method")){
            jsonDemo.put("method",method);
        }
        return jsonDemo;
    }

    /**
     * 通过classname获取接口请求参数
     * @param className 接口方法名
     * @return JSONObject 接口请求参数
     */
    public static JSONObject getParamsByClassName(String className){
        //例如className为yoho.cases.activity.app_activity_get,需要转成activity/app_activity_get格式
        className = className.substring(11);
        String jsonFile = className.replace('.','/');
        JSONObject jsonDemo = getParams(jsonFile, "");
        return jsonDemo;
    }

    /**
     * 通过接口除域名外的url字符串来获取请求参数,大部分使用于service请求
     * @param className 接口方法名
     * @return JSONObject 接口请求参数
     */
    public static JSONObject getParamsAndMethodByClassName(String className){

        JSONObject jsonDemo = getParamsByClassName(className);
        return jsonDemo;
    }

    /**
     * 获取接口对应返回较验的接口校验数据
     * @param className 接口方法名
     * @return JSONObject 接口较验预期数据
     */
    public static JSONObject getResultByClassName(String className){
        //例如className为yoho.cases.activity.app_activity_get,需要转成activity/app_activity_get格式
        className = className.substring(11);
        String jsonFile = className.replace('.','/');

        String jsonTile = jsonFile.split("/")[1];
        JSONObject jsonDemo = getParams(jsonFile, jsonTile+"_result");
        return jsonDemo;
    }

    /**
     * 通过请求测试数据的文件名和json的数据title来获取该case的测试预置数据。
     * @param jsonFile 对应case的json文件,与method对应,只是把"."改成了"_"
     * @param jsonTitle json的数据title名,默认与jsonFile对应。但也可能出现其他的title的json数组。
     * @return JSONObject 接口请求参数
     */
    public static JSONObject getParams(String jsonFile, String jsonTitle){
        JSONObject jsonDemo;
        String filepath = loadProperies.get("datas_path")  + "/" + jsonFile + ".json";
        String[] jsonFileName = jsonFile.split("/");

        if("".equals(jsonTitle)){
            jsonDemo = jsonParselib.fileToJsonObject(filepath).getJSONObject(jsonFileName[1]);
        }
        else{
            jsonDemo = jsonParselib.fileToJsonObject(filepath).getJSONObject(jsonTitle);
        }
        return jsonDemo;
    }


    /**
     * 将JSONObject的接口请求参数转成String字符串
     * @param jsonParams JSONObject的接口请求参数
     * @return String类型的接口请求串
     */
    public static String getParamsToString(JSONObject jsonParams){
        putPublicParams(jsonParams);
        Iterator iterator =  jsonParams.keys();
        String key = "";
        String value ="";
        String params="";
        while(iterator.hasNext()){
            key = (String) iterator.next();
            value = jsonParams.getString(key);
            params += key+"="+value+"&";
        }
        //去掉最后一个&
        params = params.substring(0,params.length()-1);
        return params;

    }

    public static String getParamsToStringNoPublic(JSONObject jsonParams){
        Iterator iterator =  jsonParams.keys();
        String key = "";
        String value ="";
        String params="";
        while(iterator.hasNext()){
            key = (String) iterator.next();
            value = jsonParams.getString(key);
            params += key+"="+value+"&";
        }
        //去掉最后一个&
        params = params.substring(0,params.length()-1);
        return params;
    }

    /**
     * 默认添加补全接口的公共参数,app_version、v、os_version、client_type、screen_size,为了client_secret的加密,添加了MD5的参数,与client_type对应
     * @param jsonParams JSONObject的接口请求参数
     */
    private static void putPublicParams(JSONObject jsonParams) {
        //if (!jsonParams.containsKey("app_version")) {
          //  jsonParams.put("app_version", loadProperies.get("app_version"));
        //}
        //接口的app_version字段,需要带上最新版本的版本号,现通过环境变量APP_VERSION来确定。
        //jsonParams.put("app_version", APPVERSION);

        if (!jsonParams.containsKey("v")) {
            jsonParams.put("v", loadProperies.get("v"));
        }
        if (!jsonParams.containsKey("os_version")) {
            jsonParams.put("os_version", loadProperies.get("os_version"));
        }
        if (!jsonParams.containsKey("client_type")) {
            jsonParams.put("client_type", loadProperies.get("client_type"));
        }
        else if(!jsonParams.containsKey("MD5")){
            if(jsonParams.getString("client_type").equalsIgnoreCase("Android"))
                jsonParams.put("MD5", "Android");
            else if(jsonParams.getString("client_type").equalsIgnoreCase("iOS"))
                jsonParams.put("MD5", "iOS");
        }
        if (!jsonParams.containsKey("screen_size")) {
            jsonParams.put("screen_size", loadProperies.get("screen_size"));
        }
        if (!jsonParams.containsKey("gender")) {
            jsonParams.put("gender", loadProperies.get("gender"));
        }
        if (!jsonParams.containsKey("MD5")) {
            jsonParams.put("MD5", loadProperies.get("MD5"));
            jsonParams.put("MD5", loadProperies.get("MD5"));
        }
        if (!jsonParams.containsKey("client_secret")) {
            jsonParams.remove("client_secret");
        }
    }
}
