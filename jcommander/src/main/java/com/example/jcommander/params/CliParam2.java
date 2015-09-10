package com.example.jcommander.params;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/7
 * Time: 15:30
 */
@Parameters(commandNames = "cli2")
public class CliParam2{
    @Parameter(names = "param2")
    private String param2;

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
