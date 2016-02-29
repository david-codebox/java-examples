package com.sandbox.core.regex;

import com.google.common.base.Strings;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2016/2/29 <br>
 * Time: 16:41 <br>
 * </div>
 */

public class RecursiveRegexSearchTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveRegexSearchTest.class);
    private static String regex = "\\$\\{([a-z0-9._-]+)\\}";
    private static Pattern PATTERN = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    private static Map<String, Object> context;
    @BeforeClass
    public static void startUp() throws Exception {
        context = new HashMap<>();
        context.put("project.dropNumber", 123456);
        context.put("app.project.name", "DEMO");
        context.put("app.locale", Locale.SIMPLIFIED_CHINESE);
    }

    private String getContextAttribute(String pattern) {
        return pattern.replaceAll("(\\$\\{|\\})", "");
    }

    private String format(String input) {
        if (Strings.isNullOrEmpty(input) || !input.contains("${")) {
            return input;
        }
        String quotedInput = Pattern.quote(input);
        LOGGER.debug("RecursiveRegexSearchTest.format: [{}]", quotedInput);
        Matcher m = PATTERN.matcher(input);
        String output = input;
        int start = 0;
        while (m.find(start)) {
            String match = m.group(1);
            LOGGER.debug("RecursiveRegexSearchTest.format: [{}]", match );
            String attrName = getContextAttribute(match);
            LOGGER.debug("RecursiveRegexSearchTest.format: attribute name: [{}]", attrName);
            if (context.containsKey(attrName)) {
                String value = Objects.toString(context.get(attrName));
                LOGGER.debug("RecursiveRegexSearchTest.format: attribute value: [{}]", value);
                output = output.replace(m.group(0), value);
                start = m.start() + value.length();
                m.reset(output);
            } else {
                start = m.end();
            }
            LOGGER.debug("RecursiveRegexSearchTest.format: next start point: [{}]", start );
            LOGGER.info("RecursiveRegexSearchTest.format: current content: [{}]", output );
        }
        LOGGER.info("RecursiveRegexSearchTest.format: final output:\n{}",output );
        return output;
    }

    @Test
    public void testFormat_null() throws Exception {
        String output = format(null);
        assertNull(output);
    }

    @Test
    public void testFormat_empty() throws Exception {
        String output = format("");
        assertTrue(output.equals(""));
    }

    @Test
    public void testNoMatch() throws Exception {
        String input = "Invoking this method changes this matcher's state. If the matcher is to be used in further matching operations then it should first be reset.";
        String output = format(input);
        assertEquals(input, output);
    }

    @Test
    public void testFormat_single_match() throws Exception {
        String input = "${app.project.name} Invoking this method changes this matcher's state. ${app.project.name} If the matcher is to be used in further matching operations then it should first be reset. ${app.project.name}";
        String output = format(input);
        System.out.println(output);
        assertTrue(output.contains("DEMO"));
    }

    @Test
    public void testFormat_match_multiple() throws Exception {
        String input = "${app.locale} Invoking this method changes this matcher's state. ${app.project.name} If the matcher is to be used in further matching operations then it should first be reset. ${project.dropNumber}";
        String output = format(input);
        System.out.println(output);
        assertTrue(output.contains("123456"));
    }


    @Test
    public void testFormat_match_multiple_with_unwanted() throws Exception {
        String input = "${app.locale} Invoking this method changes this matcher ${user.name} state. ${app.project.name} If the matcher is to be used in further matching operations then it should first be reset. ${project.dropNumber}";
        String output = format(input);
        System.out.println(output);
        assertTrue(output.contains("${user.name}"));
    }
    @Test
    public void testFormat_match_multiple_with_unwanted_1() throws Exception {
        String input = "${user.home} ${app.locale} Invoking this method changes this matcher ${user.name} state. ${app.project.name} If the matcher is to be used in further matching operations then it should first be reset. ${project.dropNumber}";
        String output = format(input);
        System.out.println(output);
        assertTrue(output.contains("${user.name}"));
    }
}
