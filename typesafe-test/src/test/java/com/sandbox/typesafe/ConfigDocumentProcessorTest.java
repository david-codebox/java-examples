package com.sandbox.typesafe;

import com.sandbox.typesafe.util.TypeSafeConfigUtils;
import com.typesafe.config.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ConfigDocumentProcessorTest{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static ConfigRenderOptions renderOptions = ConfigRenderOptions.defaults()
            .setJson(false)
            .setOriginComments(false);
    private URL url;
    private ConfigDocumentProcessor processor;
    private Config config;

    @Before
    public void setUp() throws Exception {
       String resource = "workflow.conf";
        url = getClass().getClassLoader().getResource(resource);
        logger.debug("ConfigDocumentProcessorTest.setUp: url - [{}]", url.toExternalForm());
        config = ConfigFactory.parseResources(resource);
        if (config.isEmpty()) {
            fail(String.format("Failed to load config content from [%s]", resource));
        }
        processor = new ConfigDocumentProcessor();

//        TypeSafeConfigUtils.print(config);
    }

    @After
    public void tearDown() throws Exception {

    }

    private Config update(Config value) {
        return TypeSafeConfigUtils.withValue(value, "description",
                ConfigValueFactory.fromAnyRef(String.format("test update description @%s", LocalDateTime.now())));
    }
    private ConfigObject update(ConfigObject value) {
        return (ConfigObject) TypeSafeConfigUtils.withValue(value, "description",
                ConfigValueFactory.fromAnyRef(String.format("test update description @%s", LocalDateTime.now())));
    }

    @Test
    public void testConfigObjectList() throws Exception {
        logger.debug("ConfigDocumentProcessorTest.testConfigObjectList: ..." );
        List<? extends ConfigObject> list=config.getObjectList(CONFIG_KEY_WORKFLOW_LIST);

        ConfigObject value = list.get(0);
        System.out.println(value.render(renderOptions));

        System.out.println("Updated entry...");
//        ConfigObject updated = update(value);
        ConfigValue updated =
                TypeSafeConfigUtils.withValue(value, "description",
                        ConfigValueFactory.fromAnyRef(String.format("test update description @%s", LocalDateTime.now())));
        TypeSafeConfigUtils.print(updated);
    }
    @Test
    public void testConfigObjectList_updateValue() throws Exception {
        logger.debug("ConfigDocumentProcessorTest.testConfigObjectList_updateValue: ..." );
        List<? extends ConfigObject> list=config.getObjectList(CONFIG_KEY_WORKFLOW_LIST);

        ConfigObject value = list.get(0);
        System.out.println(value.render(renderOptions));

        System.out.println("Updated entry...");
        ConfigObject updated = update(value);
        TypeSafeConfigUtils.print(updated);
    }

    @Test
    public void testIterateConfigList() throws Exception {
        ConfigList configList = config.getList(CONFIG_KEY_WORKFLOW_LIST);

        ConfigValue value = configList.get(0);
        System.out.println(value.render(renderOptions));

        Config manual1 = ConfigFactory.parseString(TypeSafeConfigUtils.asString(value));
        logger.debug("ConfigDocumentProcessorTest.testIterateConfigList: Config created from Config Value." );
        TypeSafeConfigUtils.print(manual1);
    }

    @Test
    public void testUpdateEntry_config_list() throws Exception {
        int index=0;
        List<? extends Config> list = config.getConfigList(CONFIG_KEY_WORKFLOW_LIST);
        Config workflowConfig = list.get(index);
        workflowConfig = update(workflowConfig);
        System.out.println("=====================");
        TypeSafeConfigUtils.print(workflowConfig);
//        processor.updateListItem(workflowConfig, index);

    }




    @Test
    public void testUpdateEntry() throws Exception {
        int index=0;
        List<? extends Config> list = config.getConfigList(CONFIG_KEY_WORKFLOW_LIST);
        Config workflowConfig = list.get(index);
        workflowConfig = TypeSafeConfigUtils.withValue(workflowConfig, "description",
                ConfigValueFactory.fromAnyRef(String.format("test update description @%s", LocalDateTime.now())));
//        TypeSafeConfigUtils.print(workflowConfig);
        processor.updateListItem(workflowConfig, index);

    }



    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateConfigList_getConfigList() throws Exception {
        //TODO: comments are lost in this method
        int index=0;
        List<Config> list = (List<Config>) config.getConfigList(CONFIG_KEY_WORKFLOW_LIST);
        Config workflowConfig = list.get(index);
        Config updated = update(workflowConfig);
        System.out.println("=====================");
        System.out.println(">>>>>>>> Updated entry <<<<<<<<<<");
        TypeSafeConfigUtils.print(updated);

        list.set(index, updated);
        System.out.println(">>>>>>>> Updated List <<<<<<<<<<");
        list.forEach(TypeSafeConfigUtils::print);

        System.out.println(">>>>>>>> Updated Configuration File <<<<<<<<<<");
//        Config updatedDoc = config.withValue(CONFIG_KEY_WORKFLOW_LIST, ConfigValueFactory.fromIterable(list));
        Config updatedDoc = config.withValue(CONFIG_KEY_WORKFLOW_LIST,
                ConfigValueFactory.fromIterable(TypeSafeConfigUtils.toValueMap(list)));
        TypeSafeConfigUtils.print(updatedDoc);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateConfigList_getConfigList_workaround() throws Exception {

        int index=0;
        List<Config> list = (List<Config>) config.getConfigList(CONFIG_KEY_WORKFLOW_LIST);
        Config workflowConfig = list.get(index);
        Config updated = update(workflowConfig);
        System.out.println("=====================");
        System.out.println(">>>>>>>> Updated entry <<<<<<<<<<");
        TypeSafeConfigUtils.print(updated);

        list.set(index, updated);
        System.out.println(">>>>>>>> Updated List <<<<<<<<<<");
        list.forEach(TypeSafeConfigUtils::print);

        List<ConfigObject> updatedList = list.stream().map(Config::root).collect(Collectors.toList());
        System.out.println(">>>>>>>> Updated Configuration File <<<<<<<<<<");
        Config updatedDoc = config.withValue(CONFIG_KEY_WORKFLOW_LIST,
                ConfigValueFactory.fromIterable(updatedList));
        TypeSafeConfigUtils.print(updatedDoc);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateConfigList_getObjectList() throws Exception {

        int index=0;
        List<ConfigObject> list = (List<ConfigObject>) config.getObjectList(CONFIG_KEY_WORKFLOW_LIST);
        ConfigObject workflowConfig = list.get(index);
        ConfigObject updated = update(workflowConfig);
        System.out.println("=====================");
        System.out.println(">>>>>>>> Updated entry <<<<<<<<<<");
        TypeSafeConfigUtils.print(updated);

        list.set(index, updated);
        System.out.println(">>>>>>>> Updated List <<<<<<<<<<");
        list.forEach(TypeSafeConfigUtils::print);

        System.out.println(">>>>>>>> Updated Configuration File <<<<<<<<<<");
//        Config updatedDoc = config.withValue(CONFIG_KEY_WORKFLOW_LIST, ConfigValueFactory.fromIterable(list));
        Config updatedDoc = config.withValue(CONFIG_KEY_WORKFLOW_LIST,
                ConfigValueFactory.fromIterable(list));
        TypeSafeConfigUtils.print(updatedDoc);
    }

    /**
     * This does not work for update config list
     * @throws Exception
     */
    @Test
    public void testUpdateConfigList_getList() throws Exception {
        int index=0;
        ConfigList list = config.getList(CONFIG_KEY_WORKFLOW_LIST);

        ConfigValue value = list.get(index);
        System.out.println(value.render(renderOptions));

        ConfigValue updated = update((ConfigObject) value);
        System.out.println(">>>>>>>> Updated entry <<<<<<<<<<");
        TypeSafeConfigUtils.print(updated);

        List<ConfigObject> updatedList = new ArrayList<>();
        for (ConfigValue configValue : list) {
            if (configValue instanceof ConfigObject) {
                ConfigObject configObject = (ConfigObject) configValue;
                updatedList.add(configObject);
            } else {
                throw new IllegalArgumentException(String.format("Configuration value not supported. value type: %s", configValue.valueType().name()));
            }
        }
        updatedList.set(index, (ConfigObject) updated);
        System.out.println(">>>>>>>> Updated List <<<<<<<<<<");
        updatedList.forEach(TypeSafeConfigUtils::print);

        System.out.println(">>>>>>>> Updated Configuration File <<<<<<<<<<");
//        Config updatedDoc = config.withValue(CONFIG_KEY_WORKFLOW_LIST, ConfigValueFactory.fromIterable(list));
        Config updatedDoc = config.withValue(CONFIG_KEY_WORKFLOW_LIST,
                ConfigValueFactory.fromIterable(updatedList));
        TypeSafeConfigUtils.print(updatedDoc);
    }



    @Test
    public void testRemoveEntry() throws Exception {

    }

    @Test
    public void testFindEntries() throws Exception {
        List<? extends Config> list = processor.findEntries();
        assertTrue(!list.isEmpty());
        System.out.println(String.format("%d entries loaded.",list.size()));
    }

    @Test
    public void testToString() throws Exception {
//        System.out.println(processor);
        TypeSafeConfigUtils.print(processor.getDoc());
    }

    public static final String CONFIG_RESOURCE = "workflow.conf";
    public static final String CONFIG_KEY_WORKFLOW_LIST = "workflow-list";

}
