package com.sandbox.core.nio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/22
 * Time: 15:32
 */
public class PathTest {
    public static void main(String[] args) throws IOException {
        String userHome= System.getProperty("user.home");
        Path path = Paths.get(String.format("%s\\AppData\\..\\.hp",userHome));
        System.out.printf("path to string %s\n", path);
        System.out.printf("path %s\n", path.normalize());
        System.out.printf("path %s\n", path.toRealPath());
        System.out.printf("path %s\n", path.toAbsolutePath());
    }
}
