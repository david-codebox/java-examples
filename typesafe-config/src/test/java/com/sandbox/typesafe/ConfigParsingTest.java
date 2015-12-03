package com.sandbox.typesafe;

import com.sandbox.typesafe.util.TypeSafeConfigUtils;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigRenderOptions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/17 <br>
 * Time: 11:01 <br>
 * </div>
 */
public class ConfigParsingTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static ConfigRenderOptions renderOptions = ConfigRenderOptions.defaults()
            .setJson(false)
            .setOriginComments(false);
    private URL url;
    private ConfigDocumentProcessor processor;
    private static Config config;

    @BeforeClass
    public static void startUp() throws Exception {
        String resource = "app-1.conf";
        config = ConfigFactory.parseResources(resource);
        if (config.isEmpty()) {
            fail(String.format("Failed to load config content from [%s]", resource));
        }
    }

    @Test
    public void testGetConfigObjectList() throws Exception {
        List<? extends ConfigObject> list=config.getObjectList("ftp.local-working-folders");
        for (ConfigObject configObject : list) {
//            TypeSafeConfigUtils.print(configObject);
            logger.debug("ConfigParsingTest.testGetConfigObjectList: value type: [{}]",configObject.valueType() );

//            configObject.values().forEach( item -> logger.debug("ConfigParsingTest.testGetConfigObjectList: [{}]", item.render() ));
//            configObject.entrySet().forEach( entry -> logger.debug("ConfigParsingTest.testGetConfigObjectList: [{}]=[{}]", entry.getKey(), entry.getValue().render() ));
            configObject.entrySet().forEach(entry -> {
                logger.debug("ConfigParsingTest.testGetConfigObjectList: value class: [{}]", entry.getValue().unwrapped().getClass().getName());
                logger.debug("ConfigParsingTest.testGetConfigObjectList: [{}]=[{}]", entry.getKey(), entry.getValue());
            });
            System.out.println("===========================================");
        }
    }

}
