package cases.users;

import commons.BaseTest;
import librarys.database.mysqlConn;
import librarys.log.logLib;
import librarys.request.httplib;
import librarys.utils.loadProperies;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by wanli on 16/11/10.
 */
public class user_demo1 extends BaseTest{

    @BeforeClass
    public void loadConfig(){
        logLib.logDebug("user_demo1 loadConfig");
    }

    @Test
    public void user_domo_1(){
        logLib.logDebug("user_demo1 user_domo_1");
        String code = "200";
        Assert.assertEquals(code,"200");
        String params = loadProperies.get("group_topic");
        ret = httplib.httpGet(apiurl+"/"+params,"name=hehe");
        logLib.logDebug(ret);
    }

    /**
     * 通过function的Object来传递参数
     * @return
     */
    @DataProvider(name = "test1")
    public Object[][] user_createData1() {
        return new Object[][] {
                { "Cedric", new Integer(36) },
                { "Anne", new Integer(37)},
        };
    }

    @DataProvider(name = "test2")
    public static Object[][] user_createData2() {
        return new Object[][] {
                { new Integer(logLib.LOG_LEVEL)}
        };
    }

    @Test(dataProvider = "test1")
    public void user_demo1_1(String n1, Integer n2) {
        logLib.logDebug("user_demo_2");
        logLib.logDebug(n1 + " " + n2);


    }
    @Test(dataProvider = "test2")
    public void user_demo1_2(Integer n2) {
        logLib.logInfo("logLib.LOG_LEVEL is " + n2);
    }
}
