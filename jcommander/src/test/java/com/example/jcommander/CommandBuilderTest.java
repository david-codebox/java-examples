package com.example.jcommander;

import com.beust.jcommander.JCommander;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRunNoArgs() {
        String[] args = {};
        JCommander jCommander = CommandBuilder.build();
        try {
            jCommander.parse(args);
        } catch (Exception e) {
            jCommander.usage();
        }
    }
    @Test
    public void testRunCmd() {
        String cmd = "cli1";
        String[] args = {
//                "prog",
                cmd
        };
        JCommander jCommander = CommandBuilder.build();
        try {
            jCommander.parse(args);
        } catch (Exception e) {
            jCommander.usage(cmd);
        }
    }


    @Test
    public void testRunProgram() {
        String cmd = "cli1";
        String[] args = {
//                "prog",
//                cmd
        };
        JCommander jCommander = CommandBuilder.build();
        try {
            jCommander.parse(args);
        } catch (Exception e) {
            jCommander.usage();
        }
    }
}
