package com.example.jcommander.params;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.example.jcommander.CliTask;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/7
 * Time: 15:30
 */
@Parameters(commandNames = "cli4", commandDescription = "Command line four")
@CliTask
public class CliParam4 {
    @Parameter(names = "param4")
    private String param4;
}
