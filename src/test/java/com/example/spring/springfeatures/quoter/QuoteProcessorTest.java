package com.example.spring.springfeatures.quoter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuoteProcessorTest {

    @Autowired
    private TerminatorQuoter terminatorQuoter;

    @Autowired
    private QuoteProcessor quoteProcessor;

    // We prove that by default the Spring IoC inits
    // beans in a singletone mode
    @Test
    public void testQuoterProcessor(){
        String message = "terminator message";
        terminatorQuoter.setMessage(message);
        String  qMessage = quoteProcessor.getMessage();
        Assert.assertEquals(message, qMessage);
    }
}
