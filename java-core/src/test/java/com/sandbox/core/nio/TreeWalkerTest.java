package com.sandbox.core.nio;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class TreeWalkerTest {
    private TreeWalker walker;
    private Path dir = Paths.get("D:\\temp\\BOMHandler\\test-data-SAW");
    private String glob = "**/*.json";
    private Collection<Path> files;

    @Before
    public void setUp() throws Exception {
        walker = new TreeWalker();
        files = new ArrayList<>();
    }

    @Test
    public void testWalkTreeDirectoryStream() throws Exception {
        walker.walkTreeDirectoryStream(files, dir, glob);
        assertFalse(files.isEmpty());


    }

    @Test
    public void testWalkTreeFileVisitor() throws Exception {
        files=walker.walkTreeFileVisitor(dir, glob);
        assertFalse(files.isEmpty());
        System.out.println(String.format("%d files found", files.size()));
    }
}
