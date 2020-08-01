package com.example.spring.springfeatures.quoter;

import org.springframework.beans.factory.annotation.Qualifier;

public class TerminatorProcessor {
    private final Quoter quoter;

    public TerminatorProcessor(@Qualifier("terminatorQuoter") Quoter quoter){
        this.quoter = quoter;
    }

    public void setMessage(String message){
        this.quoter.setMessage(message);
    }

    public String getMessage(){
        return this.quoter.getMessage();
    }
}
