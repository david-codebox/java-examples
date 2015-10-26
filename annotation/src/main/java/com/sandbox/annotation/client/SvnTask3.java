package com.sandbox.annotation.client;

import com.sandbox.annotation.annotation.TaskInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 22:05
 */
public class SvnTask3 extends AbstractSvnTask {
    @Override
    public void doExecute() {
        logger.debug("SvnTask2.doExecute: [{}]", getClass().getName() );
    }
}
