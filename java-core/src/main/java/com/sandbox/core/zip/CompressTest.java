package com.sandbox.core.zip;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/21
 * Time: 13:01
 */
public class CompressTest {


    private static void compress(Path output, Collection<Path> files) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(output.toFile()));
        zos.setMethod(ZipOutputStream.DEFLATED);

        for (Path file : files) {
            ZipEntry entry = new ZipEntry(file.getFileName().toString());
            zos.putNextEntry(entry);
            byte[] content=Files.readAllBytes(file);
            zos.write(content);
        }

        zos.finish();
        zos.close();
    }

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("D:\\temp\\g11n-automation\\test-data\\logs");
        List<Path> files=Files.list(dir).collect(Collectors.toList());
        Path zip = Paths.get(System.getProperty("user.home"), String.format("test-zip_%s.zip", LocalDate.now().toString()));
        compress(zip,files);

        if (Files.notExists(zip)) {
            throw new NoSuchFileException(zip.toString());
        }
    }


}
