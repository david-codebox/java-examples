package com.sandbox.typesafe.util;

import com.google.common.base.Preconditions;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.parser.ConfigDocument;
import com.typesafe.config.parser.ConfigDocumentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/3/17
 * Time: 17:13
 */
public class ConfigurationFileWriter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ConfigRenderOptions renderOptions;

    public ConfigurationFileWriter(ConfigRenderOptions renderOptions) {
        this.renderOptions = renderOptions;
    }

    public ConfigurationFileWriter() {
        renderOptions = ConfigRenderOptions.defaults()
                .setJson(false)
                .setOriginComments(false)
                .setComments(true)
                .setFormatted(true);
    }

    public ConfigRenderOptions getRenderOptions() {
        return renderOptions;
    }

    public void setRenderOptions(ConfigRenderOptions renderOptions) {
        this.renderOptions = renderOptions;
    }

    @Deprecated
    public void write(File file, Config config) throws IOException {
        Objects.requireNonNull(file);
        if (file.isDirectory()) {
            logger.error("ConfigurationFileWriter.write: File is a directory: {}", file);
            return;
        }
        //update the render options per file extension
        String fileName=file.getName();
//        Files.write(file.toPath(), write(config, fileName.matches("(?i).+\\.json$")).getBytes());
        PrintWriter writer = new PrintWriter(file, "utf-8");
        writer.print(write(config, fileName.matches("(?i).+\\.json$")));
        writer.close();
    }

    public void update(URL url, Config config) throws IOException, URISyntaxException {
        update(Paths.get(url.toURI()), config, true);
    }

    public void update(Path path, Config config) throws IOException {
        update(path, config, true);
    }

    public void update(Path path, Config config, boolean includeNewEntries) throws IOException {
        Preconditions.checkNotNull(config);
        Preconditions.checkArgument(!config.isEmpty());
        if (Files.notExists(path)) {
            logger.debug("ConfigurationFileWriter.update: Path does not exist. Writing config to: [{}]", path);
            write(path.toFile(), config);
            return;
        }
/*

        Config configFromFile = TypeSafeConfigUtils.parse(path);
        Config delta = TypeSafeConfigUtils.differentiate(configFromFile, config, includeNewEntries);
        ConfigDocument document = ConfigDocumentFactory.parseFile(path.toFile());
        logger.debug("ConfigurationFileWriter.update: original: [{}]", document.render());
        Set<Map.Entry<String, ConfigValue>> entries=delta.entrySet();
        for (Map.Entry<String, ConfigValue> entry : entries) {
            document = document.withValue(entry.getKey(), entry.getValue());
        }
*/
        ConfigDocument document = TypeSafeConfigUtils.toConfigDocument(config);
//        String content = TypeSafeConfigUtils.asString(config);
        String content = document.render();
//        logger.debug("ConfigurationFileWriter.update: [{}]", content );
        Files.write(path, content.getBytes());
    }

    public String write(Config config, boolean asJson) {
        ConfigRenderOptions options=renderOptions;
        if (asJson) {
            options=renderOptions.setJson(true);
        }
        return config.root().render(options);
    }
}
