package listener;
import library.log.logLib;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int retryCount         = 0;
    private int maxRetryCount     = 2;   // retry a failed test 2 additional times


    public boolean retry(ITestResult result) {
        if (retryCount <maxRetryCount) {
            retryCount++;
            try {
                //重试之间等待两秒钟
                logLib.logError("重试之间等待两秒钟");
                Thread.sleep(1000*2);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
