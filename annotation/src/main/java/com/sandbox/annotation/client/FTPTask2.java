package com.sandbox.annotation.client;

import com.sandbox.annotation.annotation.TaskInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 22:00
 */
@TaskInfo(id = "ftp-task-2", name = "FTP Task Two")
public class FTPTask2 extends AbstractFtpTask {
    @Override
    public void doExecute() {
        logger.debug("FTPTask2.doExecute: running task: [{}]", getClass() );
    }
}
