package com.sandbox.integration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WindowsCMDRunnerTest {
    private WindowsCMDRunner runner;
    @Before
    public void setUp() throws Exception {
        runner = new WindowsCMDRunner();
    }

    @Test
    public void testRun() throws Exception {
        runner.run("ping","lpm02.chn.hp.com");

    }

    @Test
    public void testRun1() throws Exception {

    }
}
