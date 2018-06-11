package cases.order;

import common.BaseTest;
import library.log.logLib;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by wanli on 16/11/25.
 */
public class order_demo2 extends BaseTest {

    @BeforeClass
    public void loadConfig(){
        logLib.logDebug("order_demo2 loadConfig");
    }

    @Test(dependsOnMethods = { "order_demo2_test2"} )
    public void order_demo2_test1(){
        logLib.logDebug("order_demo2 order_demo2_test1");
        Assert.assertEquals("317","317");

    }

    @Test(dataProvider = "order_demo1_create", dataProviderClass = order_demo1.class)
    public void order_demo2_test2(Integer n){
        logLib.logDebug("order_demo2 order_demo2_test1 n is " + n);
        Assert.assertEquals("317","317");

    }
}
