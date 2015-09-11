package com.sandbox.core.exceptions;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/11
 * Time: 20:22
 */
public class TryCatchTest {
    private static final Logger logger = LoggerFactory.getLogger(TryCatchTest.class);

    @Test
    public void testTryCatch_1() throws Exception {
        logger.debug("TryCatchTest.testTryCatch_1: Code before try." );
        try {
            logger.debug("TryCatchTest.testTryCatch_1: here is the try block");
            Math.floorDiv(9, 0);
        } catch (Exception e) {
            logger.debug("TryCatchTest.testTryCatch_1: Here is catch block!" );
            logger.error("TryCatchTest.testTryCatch_1:",e );
        }finally {
            logger.debug("TryCatchTest.testTryCatch_1: Here is the finally block" );
        }
        logger.debug("TryCatchTest.testTryCatch_1: block after finally" );
    }

    @Test
    public void testTryCatch_return() throws Exception {
        String result1 = getResult(true);
        System.out.println(result1);
        String result2 = getResult(false);
        System.out.println(result2);
    }

    private String getResult(boolean pass) {
        String result = null;
        logger.debug("TryCatchTest.getResult: before try" );
        try {
            logger.debug("TryCatchTest.getResult: try block");
            if (pass) {
                result = System.getProperty("user.name");
            } else {
                result = System.getProperty("os.name").substring(100);
            }
            logger.debug("TryCatchTest.getResult: Trying to return result in try block [{}]", result );
            return result;
        } catch (Exception e) {
            result = System.getProperty("os.name");
            logger.debug("TryCatchTest.getResult: exception block");
            logger.debug("TryCatchTest.getResult: returning result from catch: [{}]", result);
            return result;
        }finally {
            logger.debug("TryCatchTest.getResult: finally block" );
        }

//        logger.debug("TryCatchTest.getResult: code after finally" );
//        return result;
    }
}
