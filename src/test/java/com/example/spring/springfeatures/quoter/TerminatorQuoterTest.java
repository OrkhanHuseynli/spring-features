package com.example.spring.springfeatures.quoter;


import com.example.spring.springfeatures.quoter.TerminatorQuoter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TerminatorQuoterTest {

    @Autowired
    private TerminatorQuoter t;

    @Test
    public void sayQuoteTest(){
        t.setMessage("testable message");
        t.sayQuote();
    }
}
