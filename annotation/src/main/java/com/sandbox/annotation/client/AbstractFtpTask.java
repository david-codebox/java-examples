package com.sandbox.annotation.client;

import com.sandbox.annotation.Module;
import com.sandbox.annotation.annotation.TaskModule;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:57
 */
@TaskModule(module = Module.FTP)
public abstract class AbstractFtpTask extends AbstractTask {

    @Override
    protected void initialize() {
        logger.debug("AbstractFtpTask.initialize: Initializing abstract FTP task..." );
    }
}
