package com.sandbox.annotation.client;

import com.sandbox.annotation.Module;
import com.sandbox.annotation.annotation.TaskModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 21:51
 */
public abstract class AbstractTask implements ITask{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public abstract void doExecute();

    protected abstract void initialize();
    @Override
    public final void execute() {
        logger.debug("AbstractTask.execute: Initializing..." );
        initialize();
        logger.debug("AbstractTask.execute: Running" );
        doExecute();
    }
}
