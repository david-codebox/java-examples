package com.sandbox.annotation.client;

import com.sandbox.annotation.annotation.TaskInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/10/15
 * Time: 22:05
 */
@TaskInfo(id = "svn-task-1", name = "SVN Task 1")
public class SvnTask1 extends AbstractSvnTask {
    @Override
    public void doExecute() {
        logger.debug("SvnTask1.doExecute: [{}]", getClass().getName() );
    }
}
