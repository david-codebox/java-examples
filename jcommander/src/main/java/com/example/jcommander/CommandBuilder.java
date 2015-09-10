package com.example.jcommander;

import com.beust.jcommander.JCommander;
import com.example.jcommander.params.CliParam1;
import com.example.jcommander.params.CliParam2;
import com.example.jcommander.params.CliParam3;
import com.example.jcommander.params.CliParam4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/7
 * Time: 15:40
 */
public class CommandBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandBuilder.class);
    public static JCommander build() {
        LOGGER.debug("CommandBuilder.build: Building JCommander...." );
        JCommander jc = new JCommander(new Program());
//        JCommander jc = new JCommander();
        jc.setProgramName("main");

        jc.addCommand(new CliParam1());
        jc.addCommand(new CliParam2());
        CliParam3 param3 = new CliParam3();
        CliTask cliTask = param3.getClass().getAnnotation(CliTask.class);
        LOGGER.info("CommandBuilder.build: [{}]", cliTask.impl());
        jc.addCommand(param3);
        jc.addCommand(new CliParam4());
        return jc;
    }
}
