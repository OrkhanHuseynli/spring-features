package com.example.spring.springfeatures.quoter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TerminatorProcessor {
    private final TerminatorQuoter terminatorQuoter;

    public TerminatorProcessor(@Autowired TerminatorQuoter terminatorQuoter){
        this.terminatorQuoter = terminatorQuoter;
    }

    public void setMessage(String message){
        this.terminatorQuoter.setMessage(message);
    }

    public String getMessage(){
        return this.terminatorQuoter.getMessage();
    }
}
