package com.example.spring.springfeatures;

import com.example.spring.springfeatures.profiling.ProfilingController;
import com.example.spring.springfeatures.profiling.ProfilingControllerMBean;
import com.example.spring.springfeatures.profiling.ProfilingHandlerBeanPostProcessor;
import com.example.spring.springfeatures.quoter.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    InjectRandomIntAnnotationBeanPostProcessor injectRandomIntAnnotationBeanPostProcessor(){
        return new InjectRandomIntAnnotationBeanPostProcessor();
    }

    @Bean(name = "terminatorQuoter")
    TerminatorQuoter terminatorQuoter(){
        return new TerminatorQuoter();
    }

    @Bean(name="robotQuoter")
    RobotQuoter robotQuoter(){
        return new RobotQuoter();
    }

    @Bean
    TerminatorProcessor terminatorProcessor(final @Qualifier("terminatorQuoter") Quoter quoter){
        return new TerminatorProcessor(quoter);
    }

    @Bean
    ProfilingHandlerBeanPostProcessor profilingHandlerBeanPostProcessor() throws Exception {
        return  new ProfilingHandlerBeanPostProcessor();
    }

    @Bean
    PostProxyInvokerContextListener postProxyInvokerContextListener(final ConfigurableListableBeanFactory factory){
        return  new PostProxyInvokerContextListener(factory);
    }
}
