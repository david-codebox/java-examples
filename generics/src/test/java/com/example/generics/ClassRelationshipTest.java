package com.example.generics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassRelationshipTest {
    Class<? extends Base> child;
    @Before
    public void setUp() throws Exception {
        child = Base.class;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsAssignableFrom() throws Exception {
        boolean isAssignable = Base.class.isAssignableFrom(child);
        assertTrue(isAssignable);
    }
}
