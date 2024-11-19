package Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.qameta.allure.Allure;

public class LogsUtils {
        //PATH
   public static String LOGS_PATH = "Test-outputs/Logs";

    public static void Trace (String message)
    {
            LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                    .trace(message);
    }
    public static void Debug (String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .debug(message);
    }
    public static void Info (String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .info(message);
    }
    public static void Warn (String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .warn(message);
    }
    public static void Error (String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .error(message);
    }
    public static void Fatal (String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(message);
    }
}

