package com.sandbox.junit.unit;

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
public class UnitTest3 extends BaseTest {
    @Before
    public void setUp() throws Exception {
        logger.info("UnitTest.setUp: [{}]" );
    }

    @After
    public void tearDown() throws Exception {
        logger.info("UnitTest.tearDown: [{}]" );
    }

    @Test
    public void testRun() throws Exception {
        logger.info("UnitTest.testRun: [{}]" );

    }
}
