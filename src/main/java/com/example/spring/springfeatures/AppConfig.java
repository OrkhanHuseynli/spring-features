package com.example.spring.springfeatures;

import com.example.spring.springfeatures.profiling.ProfilingHandlerBeanPostProcessor;
import com.example.spring.springfeatures.quoter.InjectRandomIntAnnotationBeanPostProcessor;
import com.example.spring.springfeatures.quoter.Quoter;
import com.example.spring.springfeatures.quoter.TerminatorProcessor;
import com.example.spring.springfeatures.quoter.TerminatorQuoter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    InjectRandomIntAnnotationBeanPostProcessor injectRandomIntAnnotationBeanPostProcessor(){
        return new InjectRandomIntAnnotationBeanPostProcessor();
    }

    @Bean
    TerminatorQuoter terminatorQuoter(){
        return new TerminatorQuoter();
    }

    @Bean
    TerminatorProcessor terminatorProcessor(final Quoter quoter){
        return new TerminatorProcessor(quoter);
    }

    @Bean
    ProfilingHandlerBeanPostProcessor profilingHandlerBeanPostProcessor() throws Exception {
        return  new ProfilingHandlerBeanPostProcessor();
    }
}
