package com.example.spring.springfeatures.quoter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TerminatorProcessorTest {

    @Autowired
    private Quoter terminatorQuoter;

    @Autowired
    private TerminatorProcessor quoteProcessor;

    // We prove that by default the Spring IoC inits
    // beans in a singletone mode
    @Test
    public void testTerminatorProcessor(){
        String message = "terminator message";
        terminatorQuoter.setMessage(message);
        String  qMessage = quoteProcessor.getMessage();
        Assert.assertEquals(message, qMessage);
    }
}
