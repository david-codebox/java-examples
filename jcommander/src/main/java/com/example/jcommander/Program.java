package com.example.jcommander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/7
 * Time: 15:29
 */
@Parameters(commandNames = {"main"})
public class Program {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Parameter(names = "-debug", description = "Debug mode", required = true)
    private boolean debug = false;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void execute() {
        if (debug) {
            logger.debug("Program.execute: running [{}]", LocalDateTime.now());
        }
        logger.info("Program.execute: running program [{}]",LocalDateTime.now() );
    }
}
