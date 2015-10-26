package com.sandbox.icu4j.charsetdetection;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 19:06
 */
public class EncodingDetector {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void detect(Path path) {
        Collection<File> files=FileUtils.listFiles(path.toFile(), new String[]{
                "*.html"
        }, true);
        CharsetDetector detector = new CharsetDetector();
    }
}
