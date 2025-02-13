package Utilities;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogsUtils {
    public static String LOGS_PATH = "test-outputs/Logs";
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(LogsUtils.class);

    public static void trace(String message) {
       log.trace(message);
        Allure.addAttachment("logs", message);

    }

    public static void debug(String message) {
        log.debug(message);

        Allure.addAttachment("logs", message);

    }

    public static void info(String message) {
        log.info(message);

        Allure.addAttachment("logs", message);

    }

    public static void warn(String message) {
        log.warn(message);

        Allure.addAttachment("logs", message);

    }

    public static void error(String message) {
        log.error(message);

        Allure.addAttachment("logs", message);

    }

    public static void fatal(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(message);
        Allure.addAttachment("logs", message);

    }
}