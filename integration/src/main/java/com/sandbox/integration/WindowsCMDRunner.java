package com.sandbox.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/10 <br>
 * Time: 16:14 <br>
 * </div>
 */
public class WindowsCMDRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void run(File workDir, String... command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(workDir);
        logger.info("WindowsCMDRunner.run: work directory: [{}]", workDir );
        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            logger.info(line );
        }
        process.waitFor();
        int returnCode = process.exitValue();
        logger.info("WindowsCMDRunner.run: returnCode [{}]",returnCode );
    }

    public void run(String... command) throws IOException, InterruptedException {
        run(new File(System.getenv("temp")), command);
    }
}
