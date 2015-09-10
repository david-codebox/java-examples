package com.example.jcommander.params;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/7
 * Time: 15:30
 */
@Parameters(commandNames = {"cli1"})
public class CliParam1 {
    @Parameter(names = "-debug", description = "Debug mode from param1")
    protected boolean debug = false;

    @Parameter(names = "-name", required = true, description = "mock up name")
    protected String name;
}
