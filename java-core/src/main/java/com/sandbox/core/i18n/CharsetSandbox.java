package com.sandbox.core.i18n;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/12/5 <br>
 * Time: 16:58 <br>
 * </div>
 */

public class CharsetSandbox {
    public static void main(String[] args) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        charsets.entrySet().forEach(e -> {
            Charset c = e.getValue();
            System.out.println(String.format("%s=[<%s>, %s]", e.getKey(), c, c.aliases()));
            System.out.println("================");
        });
    }
}
