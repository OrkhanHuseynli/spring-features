package com.example.spring.springfeatures.quoter;

import com.example.spring.springfeatures.profiling.Profiling;

import javax.annotation.PostConstruct;

@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min=1, max=4)
    private int repeat;

    private String message;

    public TerminatorQuoter(){
        System.out.println("Phase 1: Java Constructor => Spring inits `"+this.getClass().getName()+"`Object through Reflection \n" +
                "variables are set to : \n " +
                "repeat = " + this.repeat + "\n"+
                "message = "+this.message);

    }

    @PostConstruct
    public void init(){
        System.out.println("Phase 2: @PostConstruct annotation =>  Spring configures the init-ed Object `"+this.getClass().getName()+"`");
        System.out.println("repeat: the filed is configured via annotation as the Sprig is already in action \n" +
                "variables are set to : \n " +
                "repeat = " + this.repeat + "\n"+
                "message = "+this.message);
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public void sayQuote() {
        for(int i = 0; i < this.repeat; i++){
            System.out.println(this.getClass().getName() + " Quote : " + this.message);
        }
    }
}
