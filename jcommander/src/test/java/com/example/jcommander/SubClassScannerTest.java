package com.example.jcommander;

import org.junit.Before;
import org.junit.Test;

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
