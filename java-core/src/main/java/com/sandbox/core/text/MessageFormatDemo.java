package com.sandbox.core.text;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * <div>
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao <br>
 * Date: 2015/11/24 <br>
 * Time: 14:08 <br>
 * </div>
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        String userName = System.getProperty("user.name");
        String ts = LocalDateTime.now().toString();
        System.out.println(MessageFormat.format("Hello {0}, the time now is {1}", userName, ts));
        System.out.println(MessageFormat.format("Hello ''{0}'', the time now is {1}", userName, ts));
        System.out.println(MessageFormat.format("Hello '{0}', the time now is {1}", userName, ts));
        System.out.println(MessageFormat.format("Hello '''{0}''', the time now is {1}", userName, ts));
        System.out.println(MessageFormat.format("Hello '''{'0}'', the time now is {1}", userName, ts));
        System.out.println(MessageFormat.format("Hello '''{'0}, the time now is {1}", userName, ts));
        System.out.println(MessageFormat.format("Hello '{'0}, the time now is {1}", userName, ts));
    }
}
