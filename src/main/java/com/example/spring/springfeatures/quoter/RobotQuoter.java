package com.example.spring.springfeatures.quoter;

import com.example.spring.springfeatures.profiling.Profiling;

import javax.annotation.PostConstruct;

@Profiling
public class RobotQuoter implements Quoter {

    @InjectRandomInt(min=1, max=4)
    private int repeat;

    private String message;

    public RobotQuoter(){
        System.out.println("Phase 1: Spring inits Object through Reflection");
    }

    @PostConstruct
    public void init(){
        System.out.println("Phase 2: Spring configures the init-ed Object");
        System.out.println("repeat: the filed is configured via annotation as the Sprig is already in action \n repeat = " + this.repeat);
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    @PostProxy  // we want to launch this method right after all the proxies are initiated
    @Override
    public void sayQuote() {
        for(int i = 0; i < this.repeat; i++){
            System.out.println("Quote : " + this.message);
        }
    }
}
