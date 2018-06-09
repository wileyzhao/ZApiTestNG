package commons;

import java.io.IOException;
import java.util.Iterator;

import librarys.database.RedisDataSource;
import librarys.database.mysqlConn;
import librarys.log.logLib;
import librarys.utils.loadProperies;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import librarys.utils.paramsLib;

/**
 *
 * Created by wanli on 16/11/3.
 */

public abstract class BaseTest {
    public static String apiurl; //case接口的请求apiurl
    public static String ret;   //case接口请求返回的String类型的json字符串
    public static JSONObject retJson;   //case接口请求返回的json对象
    public static JSONObject caseJson;  //case接口的参数json对象
    public static mysqlConn mysqlConn = null;
    public static RedisDataSource redisConn = null;

    @BeforeSuite
    protected void beforeSuite() throws IOException {

        logLib.LOG_LEVEL = 2;
        logLib.logToStandardOut = true;
        logLib.logDebug("Hello ZApiTestNG!");

        //加载configs下的所有propery文件,注意configs的路径问题
        String currentDir = System.getProperty("user.dir");
        String filepath = "/../src/main/java/configs";
        loadProperies.readProperites(currentDir + filepath);

        apiurl = loadProperies.get("apiUrl");

        //连接mysql
        if(false) {
            String host = loadProperies.get("yohodatabase_ip");
            String user = loadProperies.get("yohodatabase_user");
            String passwd = loadProperies.get("yohodatabase_passwd");
            mysqlConn = new mysqlConn(host, user, passwd);
            //连接redis
            String redisHost = loadProperies.get("redis_ip");
            int redisPort = Integer.valueOf(loadProperies.get("redis_port")).intValue();
            RedisDataSource.initJedisPool(redisHost, redisPort);
        }

    }

    @BeforeTest
    protected void beforeTest(){
        logLib.logDebug("Hello beforeTest!");
    }

    @AfterTest
    protected void afterTest(){
        logLib.logDebug("Hello afterTest!");
    }

    @AfterSuite
    protected void afterSuite() throws IOException {
        if(null != mysqlConn)
            mysqlConn.closeDBcon();
        RedisDataSource.closePool();
        logLib.logDebug("Goodbye ZApiTestNG!");
    }
}


