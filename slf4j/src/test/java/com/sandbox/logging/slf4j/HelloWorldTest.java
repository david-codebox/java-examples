package com.sandbox.logging.slf4j;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class HelloWorldTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private HelloWorld hw;

    @Before
    public void setUp() throws Exception {
        logger.debug("HelloWorldTest.setUp: Creating instance for [{}]", HelloWorld.class.getName() );
        hw = new HelloWorld();
    }

    @Test
    public void testHi() throws Exception {
        logger.debug("HelloWorldTest.testHi: Running test hi method...." );
        String userName=hw.hi();
        assertNotNull(userName);
    }
}
