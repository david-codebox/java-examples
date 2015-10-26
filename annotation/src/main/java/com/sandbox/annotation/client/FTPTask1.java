package com.sandbox.annotation.client;

import com.sandbox.annotation.annotation.TaskInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 22:00
 */
@TaskInfo(id = "ftp-task-1", name = "FTP Task One", description = "hello 1")
public class FTPTask1 extends AbstractFtpTask {
    @Override
    public void doExecute() {
        logger.debug("FTPTask1.doExecute: running task: [{}]", getClass() );
    }
}
