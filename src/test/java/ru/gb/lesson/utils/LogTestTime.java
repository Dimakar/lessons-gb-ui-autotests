package ru.gb.lesson.utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.lesson.TriangleTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class LogTestTime implements AfterTestExecutionCallback, BeforeTestExecutionCallback {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        long currentTimeMillis = System.currentTimeMillis();
        long startTime = (long) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("start time");

        logger.info("Test: " + extensionContext.getDisplayName() + " has Duration " + (currentTimeMillis - startTime));
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("start time", System.currentTimeMillis());
    }
}
