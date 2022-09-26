package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTest {
    private static Logger logger = LoggerFactory.getLogger(MyTest.class);

    public static void main(String[] args) {
        logger.info("Hello");
        logger.trace("Hello");
        logger.debug("Hello");
        logger.error("Hello");
        logger.warn("Hello");
    }

}
