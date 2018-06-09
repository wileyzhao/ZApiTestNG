package librarys.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanli on 16/11/1.
 */
public class jsonParselib {

    /**
     * 从json字符串中根据key获取对应的value
     * @param jsonString
     * @param key
     * @return
     */
    public static String getJSONString(String jsonString, String key) {
        JSONObject jsonObject = JSONObject.fromObject(stringToJson(jsonString));
        if (!jsonObject.has(key)) {
            return null;
        }
        String layerOne = jsonObject.getString(key);
        return layerOne;

    }

    /**
     * 从json对象中获取key对应的json对象
     * @param jsonObj,key
     * @param key
     * @return 返回json对象
     */
    public static JSONObject getJSONByKey(JSONObject jsonObj, String key) {
        JSONObject jsonObject = JSONObject.fromObject(jsonObj.getString(key));
        if (jsonObject.isEmpty()) {
            return null;
        }
        return jsonObject;

    }

    /**
     * 从json对象中根据key获取对应的value
     * @param jsonObject
     * @param key
     * @return String value
     */
    public static String getJSONString(JSONObject jsonObject, String key) {
        if (!jsonObject.has(key)) {
            return null;
        }
        String layerOne = jsonObject.getString(key);
        return layerOne;

    }

    /**
     * 将String转换成json格式
     * @param jsonString
     * @return
     */
    public static String stringToJson(String jsonString) {
        if (jsonString != null) {
            jsonString = jsonString.replace("\r\n", "\\r\\n");
            jsonString = jsonString.replace("\r", "\\r");
            jsonString = jsonString.replace("\n", "\\n");
            jsonString = jsonString.replace("&nbsp;", " ");
        }
        //System.out.println(jsonString);
        return jsonString;
    }

    /**
     * 根据文件名将Json文件转换成JsonObject
     * @param filepath
     * @return
     */
    public static JSONObject fileToJsonObject(String filepath) {
        try{
            InputStream in = new BufferedInputStream(new FileInputStream(filepath));
            String fileStr = readJsonFileToJString(in);
            //JSONObject jsonObject = JSONObject.fromObject(stringToJson(fileStr));

            JSONObject jsonObject = JSONObject.fromObject(fileStr);
            return jsonObject;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 根据InputStream流从json文件中解析返回JsonObject
     * @param in
     * @return
     */
    private static String readJsonFileToJString (InputStream in){
        try{
            StringBuilder result = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line ="";
            while (line != null) {
                line = br.readLine();
                if(line == null){
                    continue;
                }
                //屏蔽Json文件中的注释
                line = line.trim();
                if(line.startsWith("//")){
                    continue;
                }
                result.append(line.split("//")[0]);
            }
            return result.toString();
        }
        catch(IOException e){
            System.out.println(" -- Read JsonFile Faild!--");
        }
        return "";
    }

    /**
     * 暂不清楚有什么用
     * @param jsonString
     * @return
     */
    public static List<String> getJSONArray(String jsonString) {
        JSONArray myJsonArray = JSONArray.fromObject(stringToJson(jsonString.toString()));
        List<String> JsonArraycollection = new ArrayList<String>();
        for (int i = 0; i < myJsonArray.size(); i++) {
            JsonArraycollection.add(myJsonArray.getString(i));
        }
        return JsonArraycollection;
    }
}
