package com.example.spring.springfeatures.quoter;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

import static java.lang.Thread.sleep;

@SpringBootTest
public class RobotQuoterTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void sayQuoteTest(){
        Quoter quoter = context.getBean("robotQuoter", Quoter.class);
        quoter.setMessage("Test message");
        quoter.sayQuote();
    }

    /*
        After a few cycles, launch 'jconsole' in the 'bin' folder and go to MBeans,
        there you will find the 'profiling' folder which you defined in ProfilingHandlerBeanPostProcessor constructor.
        You can set Enabled to 'true' to start seeing profiling.

        However in this particular example we access to mBeanServer directly and enable `profiling`.

        The important part in this example is to look at the way how our 'sayQuote' method is invoked:
        you may notice that it has been invoked right before the actual invocation in the test below with
        `null` message. It is because we invoke it at the stage at ContextRefreshedEvent via PostProxyInvokerContextListener
        class which is implemented by us.
     */
    @Test
    public void sayQuoteCalledAtContextRefreshedEvent() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // we use the same objectName that we used to register our `profilin controller` in MBean server
        // look at how we did in ProfilingHandlerBeanPostProcessor class (line 23)
        ObjectName objectName = new ObjectName("profiling", "name", "controller");
        mBeanServer.setAttribute(objectName, new Attribute("Enabled", true));
        Quoter quoter = context.getBean("robotQuoter", Quoter.class);
        int counter = 0;
        while (counter<5) {
            counter++;
            quoter.setMessage("testable message : " + counter);
            sleep(2000);
            quoter.sayQuote();
        }
    }
}
