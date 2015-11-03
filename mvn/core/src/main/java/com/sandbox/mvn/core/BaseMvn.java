package com.sandbox.mvn.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/10/29 <br>
 * Time: 16:59 <br>
 * </div>
 */
public class BaseMvn {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected String version = "1.1-SNAPSHOT";

    public BaseMvn() {
        logger.debug("BaseMvn.BaseMvn: [{}]", getClass() );
    }

    public void showVersion() {
        String msg = String.format("Core module version: %s", version);
        System.out.println(msg);
        logger.info("BaseMvn.showVersion: [{}]", msg );
    }
}
