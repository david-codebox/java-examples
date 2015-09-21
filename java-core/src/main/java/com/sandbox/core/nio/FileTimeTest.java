package com.sandbox.core.nio;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/18
 * Time: 10:28
 */
public class FileTimeTest {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get(System.getProperty("user.home"), ".sandbox");
        Files.createDirectories(dir);
        Path file = Paths.get(dir.toString(), "timestamp-test.txt");
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lines.add(RandomStringUtils.randomAscii(20));
        }
        Files.write(file, lines);

        FileTime fileTime = Files.getLastModifiedTime(file);
        System.out.println(fileTime);
        System.out.println(LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault()));
    }
}
