package Utilities;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;

import java.io.File;


public class LogsUtils {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(LogsUtils.class);
    //Logs File path
    public static String LOGS_PATH = "test-outputs/Logs";

    // Log levels
    public static void trace(String message) {
        LOGGER.trace(message);
        Allure.addAttachment("Trace-log", message);

    }

    public static void debug(String message) {
        LOGGER.debug(message);
        Allure.addAttachment("Debug-log", message);
    }

    public static void info(String message) {
        LOGGER.info(message);
        Allure.addAttachment("Info-log", message);

    }

    public static void warn(String message) {
        LOGGER.warn(message);
        Allure.addAttachment("Warn-log", message);

    }

    public static void error(String message) {
        LOGGER.error(message);
        Allure.addAttachment("Error-log", message);

    }

    public static void fatal(String message) {
        LOGGER.fatal(message);
        Allure.addAttachment("Fatal-log", message);

    }

    // Clean old logs
    public static void cleanOldLogs() {
        File logFolder = new File("test-outputs/Logs"); // Your logs folder
        File[] files = logFolder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".log")) { // Delete only .log files
                    file.delete();
                }
            }
        }
    }
}