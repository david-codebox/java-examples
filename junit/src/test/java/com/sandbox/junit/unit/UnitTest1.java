package com.sandbox.junit.unit;

import com.sandbox.junit.BaseTest;
import com.sandbox.junit.categories.UnitTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/6/26
 * Time: 16:43
 */
@Category(UnitTests.class)
public class UnitTest1 extends BaseTest {
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
