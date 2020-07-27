package com.example.spring.springfeatures.quoter;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static java.lang.Thread.sleep;

@SpringBootTest
public class TerminatorQuoterTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void sayQuoteTest(){
        Quoter quoter = context.getBean(Quoter.class);
        quoter.setMessage("Test message");
        quoter.sayQuote();
    }

    /*
        After a few cycles, launch 'jconsole' in the 'bin' folder and go to MBeans,
        there you will find the 'profiling' folder which you defined in ProfilingHandlerBeanPostProcessor constructor.
        You can set Enabled to 'true' to start seeing profiling.
     */
    @Test
    public void sayQuoteWithProfilingTest() throws InterruptedException {
        Quoter quoter = context.getBean(Quoter.class);
        int counter = 0;
        while (counter<50) {
            counter++;
            quoter.setMessage("testable message : " + counter);
            sleep(2000);
            quoter.sayQuote();
        }
    }
}
