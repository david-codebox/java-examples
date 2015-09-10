package com.sandbox.core.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/4
 * Time: 10:41
 */
public class TreeWalker {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Collection<Path> walkTreeDirectoryStream(Collection<Path> result, Path dir, String pattern) throws IOException {
        logger.debug("TreeWalker.walkTreeDirectoryStream: walking through directory: [{}]",dir );
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir, pattern);

        for (Path path : stream) {
            logger.debug("TreeWalker.walkTreeDirectoryStream: Current Path: [{}]", path );
            if (Files.isDirectory(path)) {
                walkTreeDirectoryStream(result, path, pattern);
            } else {
                result.add(path);
            }
        }
        return result;
    }
    public Collection<Path> walkTreeFileVisitor(Path dir, String glob) throws IOException {
        logger.debug("TreeWalker.walkTreeFileVisitor: walking through directory: [{}]",dir );
        Collection<Path> result = new ArrayList<>();
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", glob));
        Files.walkFileTree(dir,new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (matcher.matches(file)) {
                    result.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                logger.debug("TreeWalker.preVisitDirectory: [{}]", dir );
                return super.preVisitDirectory(dir, attrs);
            }
        });
        return result;
    }

}
