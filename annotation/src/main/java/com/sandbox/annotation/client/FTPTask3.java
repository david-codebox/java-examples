package com.sandbox.annotation.client;

import com.sandbox.annotation.Module;
import com.sandbox.annotation.annotation.TaskInfo;
import com.sandbox.annotation.annotation.TaskModule;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 22:00
 */
@TaskInfo(id = "ftp-task-3", name = "FTP Task 3")
@TaskModule(module = Module.JENKINS)
public class FTPTask3 extends AbstractFtpTask {
    @Override
    public void doExecute() {
        logger.debug("FTPTask2.doExecute: running task: [{}]", getClass() );
    }
}
