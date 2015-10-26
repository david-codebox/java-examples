package com.sandbox.typesafe.util;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.typesafe.config.*;
import com.typesafe.config.parser.ConfigDocument;
import com.typesafe.config.parser.ConfigDocumentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/6/9
 * Time: 16:21
 */
public class TypeSafeConfigUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeSafeConfigUtils.class);
    private static ConfigParseOptions parseOptions = ConfigParseOptions.defaults()
            .setAllowMissing(false);
    private static ConfigRenderOptions renderOptions = ConfigRenderOptions.defaults()
            .setJson(false)
            .setOriginComments(false);

    public static Config parse(Path file) {
        Preconditions.checkNotNull(file);
        if (Files.notExists(file)) {
            String msg = String.format("File does not exist: [%s]", file);
            LOGGER.error("TypeSafeConfigUtils.parse: File does not exist: [{}]", file);
            throw new RuntimeException(new FileNotFoundException(msg));
        }
        if (Files.isDirectory(file)) {
            String msg = String.format("File is a directory: [%s]", file);
            LOGGER.error("TypeSafeConfigUtils.parse: File is a directory: [{}]", file);
            throw new RuntimeException(new IllegalArgumentException(msg));
        }

        if (Files.notExists(file)) {
            LOGGER.error("ConfigurationFileParser.parse: File not found: {}", file);
            return null;
        }
        return ConfigFactory.defaultOverrides()
                .withFallback(ConfigFactory.parseFileAnySyntax(file.toFile(), parseOptions))
                .resolve(ConfigResolveOptions.defaults());
    }
    /**
     *
     * @param file
     * @return
     */
    public static Config parse(File file) {
        Preconditions.checkNotNull(file);
        return parse(file.toPath());
    }

    public static void print(ConfigValue config, OutputStream outputStream) {
        try {
            outputStream.write(config.render(renderOptions).getBytes(Charsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("TypeSafeConfigUtils.print: [{}]",e );
        }
    }
    public static void print(Config config, OutputStream outputStream) {
        print(config.root(),outputStream);
    }

    public static void print(Config config) {
        print(config, System.out);
    }

    public static void print(ConfigValue config)  {
        print(config, System.out);
    }

    /**
     * The former a Config takes in the parameter list, the higher priority it has.
     * @param configs
     * @return The merged config
     */
    public static Config mergeConfigurations(Config... configs) {
        checkArgument(configs.length > 1, "At least 2 configs should be provided for merge. But given %s", configs.length);
        Config merged= ConfigFactory.empty();
        for (Config config : configs) {
            if (config == null) continue;
            merged = merged.withFallback(config);
        }

        return merged.resolve(ConfigResolveOptions.defaults());
    }

    /**
     * Pretty print the provided configuration object
     * @param config
     * @return
     */
    public static String convertConfigToString(Config config) {
        if(config==null) return "{}";
        return config.root().render(renderOptions);
    }

    /**
     * Converts the string list to a file set
     * @param config Config object
     * @param path configuration path to the string list
     * @return Path set
     */
    public static Set<Path> getFilesFromStringList(Config config, String path) {
        List<String> paths = config.getStringList(path);
        if (paths.isEmpty()) {
            throw new RuntimeException(new IllegalArgumentException(
                    String.format("The string list is empty. Configuration path: [%s].", path)));
        }
        return paths.stream().map(Paths::get).collect(Collectors.toSet());
    }

    public static List<Map<String, Object>> toValueMap(List<? extends Config> configList) {
        checkNotNull(configList);
        List<Map<String, Object>> valueMap =  configList.stream().map(wfConf -> wfConf.root().unwrapped()).collect(Collectors.toList());
        LOGGER.debug("TypeSafeConfigUtils.toValueMap: [{}] items loaded from the config list.", valueMap.size());
        return valueMap;
    }

    /**
     * Updates the config data with the given config list
     * @param data
     * @param list
     * @param listPath
     * @return
     */
    public static Config updateConfigList(Config data, List<? extends Config> list, String listPath) {
        checkNotNull(data);
        checkNotNull(list);
        checkArgument(!Strings.isNullOrEmpty(listPath));
        List<ConfigObject> updatedList = list.stream().map(Config::root).collect(Collectors.toList());
//        return data.withValue(listPath, ConfigValueFactory.fromIterable(updatedList));
        return withValue(data, listPath, ConfigValueFactory.fromIterable(updatedList));
    }

    /**
     * Converts the given config list to a Config object
     * @param list
     * @param listPath
     * @return
     */
    public static Config toConfig(List<? extends Config> list, String listPath) {
        checkNotNull(list);
        checkArgument(!Strings.isNullOrEmpty(listPath));
        List<ConfigObject> updatedList = list.stream().map(Config::root).collect(Collectors.toList());
        return ConfigFactory.empty().withValue(listPath, ConfigValueFactory.fromIterable(updatedList));
    }

    /**
     * Get the differences between the given two config objects.
     * @param base The base config object
     * @param updated The config object to check.
     * @param includeNewEntries true to include new keys in this object will be kept in the result.
     * @return The differences.
     */
    public static Config differentiate(Config base, Config updated, boolean includeNewEntries) {
        Config delta=ConfigFactory.empty();
        Set<Map.Entry<String, ConfigValue>> updatedEntries=updated.entrySet();
        for (Map.Entry<String, ConfigValue> updatedEntry : updatedEntries) {
            String key = updatedEntry.getKey();
            ConfigValue value=updatedEntry.getValue();

            //New config entry
            if (!base.hasPath(key) && includeNewEntries) {
                delta = delta.withValue(key, updatedEntry.getValue());
            }

            //updated value
            if (base.hasPath(key) && !value.equals(base.getValue(key))) {
                delta = delta.withValue(key, updatedEntry.getValue());
            }
        }
        return delta;
    }

    /**
     * Convert config to ConfigDocument
     * @param config
     * @return
     */
    public static ConfigDocument toConfigDocument(Config config) {
        return ConfigDocumentFactory.parseString(config.root().render(renderOptions));
    }

    public static String asString(Config config) {
        return asString(config.root());
    }

    public static String asString(ConfigValue value) {
        return value.render(renderOptions);
    }

    public static String asString(ConfigDocument value) {
        return value.render();
    }

    public static ConfigValue withValue(ConfigValue config, String path, ConfigValue value) {
        ConfigDocument document = ConfigDocumentFactory.parseString(asString(config));
        Config updated= ConfigFactory.parseString(document.withValue(path, value).render());
        return updated.root();
    }
    public static Config withValue(Config config, String path, ConfigValue value) {
        if (value.valueType().equals(ConfigValueType.LIST)) {
            return config.withValue(path, value);
        }
        ConfigDocument document = ConfigDocumentFactory.parseString(asString(config));
        return ConfigFactory.parseString(document.withValueText(path, asString(value)).render());
    }
}
