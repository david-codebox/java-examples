package com.sandbox.mvn.m;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class M2Test {
    private M2 m2;

    @Before
    public void setUp() throws Exception {
        m2 = new M2();
    }

    @Test
    public void testShowVersion() throws Exception {
        m2.showVersion();
    }
}
