package librarys.log;

import org.testng.Reporter;

/**
 * Created by wanli on 16/11/9.
 */
public class logLib {
    public static int LOG_LEVEL = 0;  //0 = Error , 1 = Info , 2 = Debug
    public static boolean logToStandardOut = false;

    public static void logDebug(String content){
        if(LOG_LEVEL >= 2){
            Reporter.log("[DEBUG]---" + content);
            if(logToStandardOut)
                System.out.println("[DEBUG]---" + content);
        }
    }
    public  static void logInfo(String content){
        if(LOG_LEVEL >= 1) {
            Reporter.log("[INFO]---" + content);
            if(logToStandardOut)
                System.out.println("[INFO]---" + content);
        }
    }
    public  static void logError(String content){
        if(LOG_LEVEL >= 0) {
            Reporter.log("[ERROR]---" + content);
            if(logToStandardOut)
                System.out.println("[ERROR]---" + content);
        }
    }
}
