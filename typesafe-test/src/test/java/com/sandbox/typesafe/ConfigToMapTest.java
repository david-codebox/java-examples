package com.sandbox.typesafe;

import com.google.common.base.Strings;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/3 <br>
 * Time: 14:15 <br>
 * </div>
 */
@SuppressWarnings("unchecked")
public class ConfigToMapTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static Config config;
    @BeforeClass
    public static void startUp() throws Exception {
        String resource = "app-1";
//        config = ConfigFactory.load(resource);
        config = ConfigFactory.parseResourcesAnySyntax(resource).resolve();
        if (config.isEmpty()) {
            fail(String.format("Unable to parse resource [%s]", resource));
        }
    }

    @Test
    public void testFlatten() throws Exception {
        Map<String, Object> map= config.root().unwrapped();
        explodeMap(map, "");

    }

    private void explodeMap(Map<String, Object> map, String parent) {
        map.forEach( (key, value) -> {
            StringBuilder path = new StringBuilder();
            if (!Strings.isNullOrEmpty(parent)) {
                path.append(parent).append('.');
            }
            logger.debug("key [{}], type: [{}], path: [{}]", key, value.getClass().getName(), path);
            if (value instanceof Map) {
                Map value1 = (Map) value;
                path.append(key);
//                logger.debug("ConfigToMapTest.testFlatten: updated path before recursive: [{}]", path);
                explodeMap(value1, path.toString());
            } else if (value instanceof List) {
                List list = (List) value;
                path.append(key);
                for (int i = 0; i < list.size(); i++) {
                    Object item = list.get(i);
                    logger.debug("ConfigToMapTest.explodeMap: list item: [{}]", item.getClass().getName());
                    if (item instanceof Map) {
                        Map listItem = (Map) item;
                        explodeMap(listItem, String.format("%s[%d]", path,i));
                    }else if (item instanceof String) {


                        logger.debug("ConfigToMapTest.testFlatten: list item path: [{}]", String.format("%s[%d]", path,i));
                    }
                }

            } else {
//                logger.debug("ConfigToMapTest.testFlatten: current path: [{}]", parent);
                path.append(key);
                logger.debug("ConfigToMapTest.testFlatten: updated path: [{}]", path);
            }

            logger.info("ConfigToMapTest.explodeMap: ==============================" );
        });
    }
}
