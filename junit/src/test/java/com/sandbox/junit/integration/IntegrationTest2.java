package com.sandbox.junit.integration;

import com.sandbox.junit.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/6/26
 * Time: 16:43
 */
public class IntegrationTest2 extends BaseTest {
    @Before
    public void setUp() throws Exception {
        logger.info("IntegrationTest.setUp: [{}]" );
    }

    @After
    public void tearDown() throws Exception {
        logger.info("IntegrationTest.tearDown: [{}]" );
    }

    @Test
    public void testRun() throws Exception {
        logger.info("IntegrationTest.testRun: [{}]" );

    }
}
