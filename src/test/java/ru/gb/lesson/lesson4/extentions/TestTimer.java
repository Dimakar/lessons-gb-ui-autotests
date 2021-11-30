package ru.gb.lesson.lesson4.extentions;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTimer implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static final Logger logger = LoggerFactory.getLogger(TestTimer.class);

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        long currentTime = System.currentTimeMillis();
        long startTime = (long) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("start time");
        logger.info("Ending test: " + extensionContext.getDisplayName() + ": Duration: " + (currentTime - startTime) + " ms");
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        long startTime = System.currentTimeMillis();
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("start time", startTime);
        logger.info("Starting test: " + extensionContext.getDisplayName());
    }
}
