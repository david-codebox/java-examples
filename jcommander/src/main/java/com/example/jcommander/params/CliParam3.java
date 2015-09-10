package com.example.jcommander.params;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.example.jcommander.App;
import com.example.jcommander.CliTask;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/7
 * Time: 15:30
 */
@Parameters(commandNames = "cli3")
@CliTask(impl = App.class)
//@CliTask
public class CliParam3 extends CliParam2{
    @Parameter(names = "param3")
    private String param3;
}
