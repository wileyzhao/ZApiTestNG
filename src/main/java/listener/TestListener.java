package listener;

import library.log.logLib;
import net.sf.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by wanli on 16/11/15.
 */
public class TestListener extends TestListenerAdapter{
    public static JSONObject caseresult = new JSONObject(); //单个case的运行结果
    public static double caseStartTime;


    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);

        double caseRunTime = System.currentTimeMillis() - caseStartTime;
        caseresult.put("caseRunstate",2);
        caseresult.put("caseKey",tr.getName());
        caseresult.put("caseSpeedmillis",caseRunTime/1000);
        caseresult.put("caseRunpic","");
        caseresult.put("caseLogs","失败");
        logLib.logError("######onTestFailure#####");
        logLib.logError(caseresult.toString());
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        logLib.logError("######onTestSkipped#####");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        double caseRunTime = System.currentTimeMillis() - caseStartTime;

        caseresult.put("caseRunstate",1);
        caseresult.put("caseKey",tr.getName());
        caseresult.put("caseSpeedmillis",caseRunTime/1000);
        caseresult.put("caseRunpic","");
        caseresult.put("caseLogs","成功");
        logLib.logDebug("######onTestSuccess#####");
        logLib.logDebug(caseresult.toString());
    }

    @Override
    /**
     * 每个@Test前调用
     */
    public void onTestStart(ITestResult tr) {
        caseStartTime = System.currentTimeMillis();
        logLib.logDebug("######onTestStart#####");


    }

    @Override
    /**
     * 当测试集<test></test>结束后调用
     */
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        logLib.logDebug("######onFinish#####");

    }

    @Override
    /**
     * 当测试集<test></test>开始执行前调用
     */
    public void onStart(ITestContext testContext)
    {
        logLib.logDebug("########Start######");
    }
}
