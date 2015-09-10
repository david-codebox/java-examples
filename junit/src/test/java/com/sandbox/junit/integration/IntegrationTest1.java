package com.sandbox.junit.integration;

import com.sandbox.junit.BaseTest;
import com.sandbox.junit.categories.IntegrationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/6/26
 * Time: 16:43
 */
@Category(IntegrationTests.class)
public class IntegrationTest1 extends BaseTest {
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
