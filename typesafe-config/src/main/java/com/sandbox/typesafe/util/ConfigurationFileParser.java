package com.sandbox.typesafe.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigResolveOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/16
 * Time: 14:21
 */
public class ConfigurationFileParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFileParser.class);

    private static ConfigParseOptions parseOptions= ConfigParseOptions.defaults()
            .setAllowMissing(false);
    private ConfigResolveOptions resolveOptions;
    private Config appConfig;
    public ConfigurationFileParser() {
        parseOptions = ConfigParseOptions.defaults()
                .setAllowMissing(false);
        resolveOptions = ConfigResolveOptions.defaults();
        resolveOptions.setAllowUnresolved(true);
        appConfig = ConfigFactory.load();
    }

    public static Config parse(File file) {
        return TypeSafeConfigUtils.parse(file);
    }

    public static Config parse(Path file) {
        return TypeSafeConfigUtils.parse(file);
    }

    /**
     * Get a merged version Config for the incoming config objects.
     * Overwrites: The Config objects with lower index will override the values in the objects with higher objects.
     * For example, If the same key is defined in configs[0] and configs[1], the value in configs[1] will be overwritten.
     * @param appConfig
     * @param projectConfig
     * @param toolConfig
     * @return merged object
     */
    public Config mergeConfigurations(Config toolConfig, Config projectConfig, Config appConfig) {
        return TypeSafeConfigUtils.mergeConfigurations(toolConfig, projectConfig, appConfig);
    }

    public Config mergeConfigurations(Config... configs) {
        return TypeSafeConfigUtils.mergeConfigurations(configs);
    }


}
