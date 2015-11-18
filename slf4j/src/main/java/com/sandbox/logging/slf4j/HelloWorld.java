package com.sandbox.logging.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/18 <br>
 * Time: 13:27 <br>
 * </div>
 */
public class HelloWorld {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public HelloWorld() {
    }

    public String hi() {
        String userName = System.getProperty("user.name");
        logger.debug("HelloWorld.hi: debug level: [{}]", userName );
        logger.info("HelloWorld.hi: system user name: [{}]", userName );
        logger.info("HelloWorld.hi: info level: [{}]", userName );
        logger.warn("HelloWorld.hi: warn level: [{}]", userName );
        logger.error("HelloWorld.hi: error level: [{}]", userName );
        logger.trace("HelloWorld.hi: trace level: [{}]", userName );
        System.out.println(String.format("Hello, %s", userName));
        return userName;
    }
}
