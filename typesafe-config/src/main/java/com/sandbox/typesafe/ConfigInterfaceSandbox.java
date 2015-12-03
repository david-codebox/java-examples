package com.sandbox.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/12/3 <br>
 * Time: 20:38 <br>
 * </div>
 */
public class ConfigInterfaceSandbox {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInterfaceSandbox.class);

    private static Config config = ConfigFactory.parseResources("app-1.conf");

    private static void parseNumberAsStringType() {
        String val= config.getString("project.tasks.drop.number");
        LOGGER.debug("ConfigInterfaceSandbox.parseNumberAsStringType: [{}]",val );
    }
    public static void main(String[] args) {
        parseNumberAsStringType();

    }
}
