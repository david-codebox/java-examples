package com.sandbox.mvn.m;

import com.sandbox.mvn.core.BaseMvn;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/10/29 <br>
 * Time: 17:44 <br>
 * </div>
 */
public class M2 extends BaseMvn {
    @Override
    public void showVersion() {
        logger.debug("M1.showVersion: ..." );
        super.showVersion();
    }
}
