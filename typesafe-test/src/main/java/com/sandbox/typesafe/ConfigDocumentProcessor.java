package com.sandbox.typesafe;

import com.google.common.base.MoreObjects;
import com.sandbox.typesafe.util.ConfigurationFileWriter;
import com.sandbox.typesafe.util.TypeSafeConfigUtils;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigRenderOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/10/26 <br>
 * Time: 10:11 <br>
 * </div>
 */
public class ConfigDocumentProcessor {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ConfigurationFileWriter writer;
    private Config doc;
    private URL configFileURL;

    public ConfigDocumentProcessor() {
        writer = new ConfigurationFileWriter();
        configFileURL = getClass().getClassLoader().getResource(CONFIG_RESOURCE);
        init();
    }

    private void init() {
//        doc = ConfigFactory.parseURL(configFileURL);
        doc = ConfigFactory.parseURL(configFileURL);
    }


    public void updateEntry(Config workflowEntry, int index) {
        List<? extends Config> entries=findEntries();
    }

    public void updateListItem(Config item, int index) throws IOException, URISyntaxException {
        List<Config> entries=findEntries();
        entries.set(index, item);
        doc = TypeSafeConfigUtils.updateConfigList(doc, entries, CONFIG_KEY_WORKFLOW_LIST);
        System.out.println(">>>>>>>>>>>>>>>> Config document after update <<<<<<<<<<<<<<");
        TypeSafeConfigUtils.print(doc);
        writer.update(configFileURL, doc);
    }

    public void removeEntry() {

    }

    public Config getDoc() {
        return doc;
    }

    public void setDoc(Config doc) {
        this.doc = doc;
    }

    public List<Config> findEntries() {
        return (List<Config>) doc.getConfigList(CONFIG_KEY_WORKFLOW_LIST);
    }

    @Override
    public String toString() {
        return doc.root().render(ConfigRenderOptions.defaults()
                .setOriginComments(false)
                .setFormatted(true));
    }

    public static final String CONFIG_RESOURCE = "workflow.conf";
    public static final String CONFIG_KEY_WORKFLOW_LIST = "workflow-list";
}
