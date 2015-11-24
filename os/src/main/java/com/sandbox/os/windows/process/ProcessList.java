package com.sandbox.os.windows.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/19 <br>
 * Time: 17:49 <br>
 * </div>
 */
public class ProcessList {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public void listProcess(String keyword) throws IOException, InterruptedException {
//        String command= MessageFormat.format("wmic PROCESS where \"name like ''%{0}%''\" get name /value", keyword);
        String command= MessageFormat.format("wmic PROCESS where \"name like ''%{0}%''\" delete", keyword);
        logger.debug("{}.listProcess: [{}]", getClass().getName(),command );
        Process process=Runtime.getRuntime().exec(command);
        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        logger.debug("{}.listProcess: Printing command output", getClass().getName() );
        br.lines().forEach(System.out::println);
        logger.debug("{}.listProcess: Printing command output END", getClass().getName());

        process.waitFor();
        process.destroy();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String task="notepad++";
        while (true) {
            new ProcessList().listProcess(task);
            Thread.sleep(3000);
        }
    }
}
