package com.example.jcommander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/9
 * Time: 16:58
 */
public class MyProcessor extends AbstractProcessor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        Set<? extends Element> elements = env.getElementsAnnotatedWith(CliTask.class);
        for (Element element : elements) {
            if (element.getKind().isClass()) {
                CliTask cliTask=element.getAnnotation(CliTask.class);
                logger.info("MyProcessor.process: [{}]", cliTask.impl().getName() );
            }
        }
        return false;
    }
}
