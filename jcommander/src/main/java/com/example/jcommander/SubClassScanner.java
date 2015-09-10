package com.example.jcommander;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/10
 * Time: 14:21
 */
public class SubClassScanner {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public void scan(Class<?> baseclass) {
        Reflections reflections = new Reflections("com.example.generics");
        Set<? extends Class<?>> subTypes = reflections.getSubTypesOf(baseclass);
        for (Class<?> subType : subTypes) {
            logger.info("SubClassScanner.scan: [{}]",subType.getName() );
        }
    }
}
