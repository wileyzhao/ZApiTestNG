package cases.users;

import commons.BaseTest;
import librarys.log.logLib;
import librarys.request.httplib;
import librarys.utils.loadProperies;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 *
 * Created by wanli on 2016/11/22.
 *
 */

public class user_demo2 extends BaseTest {

    @BeforeClass
    public void loadConfig(){
        logLib.logDebug("user_demo2 loadConfig");
    }

    @Parameters({ "first-name" })
    @Test
    public void user_demo2_test1(String firstName){
        logLib.logDebug("user_demo2 user_demo_1 firstName is " + firstName);
        String code = "200";
        Assert.assertEquals(code,"200");

    }

    @Test
    public void user_demo2_test2(){
        logLib.logDebug("user_demo2 user_demo2_test2");
        String params = loadProperies.get("group_topic");
        ret = httplib.httpPost(apiurl+"/"+params,"name=hehe");
        logLib.logDebug(ret);

    }

}


