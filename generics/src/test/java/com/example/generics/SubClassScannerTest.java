package com.example.generics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubClassScannerTest {

    private SubClassScanner scanner;
    @Before
    public void setUp() throws Exception {
        scanner = new SubClassScanner();
    }

    @Test
    public void testScan() throws Exception {
        scanner.scan(Base.class);

    }
}
