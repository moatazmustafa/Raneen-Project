package Listeners;

import Utilities.LogsUtils;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ITestResultListenerClass implements ITestListener {
    // Declare a static WebDriver instance
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    @Override
    public void onTestStart(@NotNull ITestResult result) {
        LogsUtils.info("Test Case '" + result.getName() + "' started");
    }

    @Override
    public void onTestSuccess(@NotNull ITestResult result) {
        LogsUtils.info("Test Case '" + result.getName() + "' passed");
    }

    @Override
    public void onTestSkipped(@NotNull ITestResult result) {
        LogsUtils.warn("Test Case '" + result.getName() + "' skipped");
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(@NotNull ITestResult result) {
        LogsUtils.warn("Test Case '" + result.getName()
                + "' failed but is within the success percentage.");
    }

    @Override
    public void onTestFailedWithTimeout(@NotNull ITestResult result) {
        LogsUtils.error("Test Case '" + result.getName() + "' failed due to a timeout.");
        onTestFailure(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtils.error("Test Case '" + result.getName() + "' failed");

        WebDriver driver = getDriverFromTestInstance(result);
        if (driver != null) {
            takeScreenshot(driver, result.getName());
        } else {
            LogsUtils.error("WebDriver instance is null, cannot take screenshot.");
        }
    }


    public WebDriver getDriverFromTestInstance(ITestResult result) {
        Object testClass = result.getInstance();
        try {
            return (WebDriver) testClass.getClass().getDeclaredField("driver").get(testClass);
        } catch (Exception e) {
            LogsUtils.error("Failed to get WebDriver instance: " + e.getMessage());
            return null;
        }
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            LogsUtils.error("WebDriver instance is null, cannot take screenshot.");
            return;
        }

        try {
            // Capture Screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Generate timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            // Set file path
            String screenshotPath = "test-outputs/Screenshots/" + testName + "_" + timestamp + ".png";
            Path destination = Paths.get(screenshotPath);

            // Save file
            Files.copy(srcFile.toPath(), destination);

            LogsUtils.info("Screenshot taken: " + screenshotPath);

            // Attach screenshot to Allure report
            Allure.addAttachment("Failed Test Screenshot", "image/png", new ByteArrayInputStream(Files.readAllBytes(destination)), ".png");

        } catch (Exception e) {
            LogsUtils.error("Failed to take or save screenshot: " + e.getMessage());
        }
    }
}


