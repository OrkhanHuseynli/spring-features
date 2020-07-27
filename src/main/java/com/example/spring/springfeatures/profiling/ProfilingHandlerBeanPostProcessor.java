package com.example.spring.springfeatures.profiling;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    // All objects registered in MBeanServer can be accessed through JMX Console
    private ProfilingController controller = new ProfilingController();
    private Map<String, Class> map = new HashMap<>();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)){
            this.map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);
        if(beanClass!=null){

            // An Object which encapsulate the logic which is going to be added to the all methods
            InvocationHandler invocationHandler = new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    if (controller.isEnabled()) {
                        System.out.println("Profiling...");
                        long before = System.nanoTime();
                        Object retValues = method.invoke(bean, args);
                        long after = System.nanoTime();
                        System.out.println("Profiling time : " + (after-before));
                        return retValues;
                    }
                    return method.invoke(bean, args);
                }
            };
            // Creates an object from a NEW class which he is going to generate on the fly
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), invocationHandler);
        }
        return bean;
    }

}
