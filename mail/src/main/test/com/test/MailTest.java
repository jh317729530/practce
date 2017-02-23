package com.test;

import mail.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Gunn on 2017/2/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class MailTest {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MailTest.class);

    @Autowired
    private MailUtil mailUtil;

    @Test
    public void sendSingleTest(){
        log.info("sendSingleTest");
        mailUtil.send("317729530@qq.com", "this is a test single mail", "hello!");
    }


}
