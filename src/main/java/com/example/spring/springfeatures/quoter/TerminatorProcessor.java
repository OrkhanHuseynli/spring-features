package com.example.spring.springfeatures.quoter;

import org.springframework.beans.factory.annotation.Autowired;

public class TerminatorProcessor {
    private final Quoter quoter;

    public TerminatorProcessor(Quoter quoter){
        this.quoter = quoter;
    }

    public void setMessage(String message){
        this.quoter.setMessage(message);
    }

    public String getMessage(){
        return this.quoter.getMessage();
    }
}
