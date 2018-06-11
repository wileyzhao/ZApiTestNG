package cases.order;

import common.BaseTest;
import library.log.logLib;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * Created by wanli on 2016/11/21.
 *
 */

public class order_demo1 extends BaseTest {

    @BeforeClass
    public void loadConfig(){
        logLib.logDebug("order_demo1 loadConfig");

    }
    /**
     * 提供给其他类的test提供数据,该DataProvider必须声明为static
     * @return
     */
    @DataProvider(name = "order_demo1_create")
    public static Object[][] createData() {
        return new Object[][] {
                new Object[] { new Integer(42) }
        };
    }

    @Test(groups = { "order_demo_1" } )
    public void order_demo1_test1(){
        logLib.logDebug("order_demo1 order_demo1");
        String code = "200";
        Assert.assertEquals(code,"200");
    }

    @Test(dependsOnGroups = {"order_demo_1"})
    public void order_demo1_test2(){
        logLib.logDebug("order_demo1 order_demo2");
        String code = "200";
        Assert.assertEquals(code,"200");
    }

}


