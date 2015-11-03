package com.sandbox.mvn.m;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class M1Test {
    private M1 m1;
    @Before
    public void setUp() throws Exception {
        m1 = new M1();
    }

    @Test
    public void testShowVersion() throws Exception {
        m1.showVersion();
    }
}
