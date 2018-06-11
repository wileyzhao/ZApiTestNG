package cases.order;

import common.BaseTest;
import library.log.logLib;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by wanli on 16/11/25.
 */
public class order_demo3 extends BaseTest {

    @BeforeClass
    public void loadConfig(){
        String method="app.payment.findPayListDetail";
        String datasName = this.getClass().getName();
        logLib.logDebug(datasName);
        //caseJson = paramsLib.getParamsAndMethodByClassName(datasName);
        logLib.logDebug("order_demo2 loadConfig");
    }



    @Test
    public void order_demo2_test2(Integer n){
        logLib.logDebug("order_demo2 order_demo2_test1 n is " + n);
        Assert.assertEquals("317","317");

//        String params = paramsLib.getParamsToString(caseJson);
//        ret = httplib.httpPostMD5(apiurl, params, caseJson.getString("MD5"));
//        retJson = JSONObject.fromObject(ret);

    }
}
