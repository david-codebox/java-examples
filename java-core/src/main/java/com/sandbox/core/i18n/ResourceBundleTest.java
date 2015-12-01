package com.sandbox.core.i18n;

import com.google.common.base.Charsets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/12/1 <br>
 * Time: 11:15 <br>
 * </div>
 */
public class ResourceBundleTest {
    public static void main(String[] args) throws IOException {
        String path = "D:\\temp\\Elli\\SAW\\workflow_resource_bundle_messages_pl.properties";
        PropertyResourceBundle bundle = new PropertyResourceBundle(new InputStreamReader(new FileInputStream(path), Charsets.UTF_8));
        bundle.keySet().forEach( key -> {
            System.out.printf("%s: [%s]\n", key, bundle.getString(key));
        });
    }
}
