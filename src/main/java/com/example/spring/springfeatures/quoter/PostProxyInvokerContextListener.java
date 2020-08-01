package com.example.spring.springfeatures.quoter;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.MethodMetadata;

import java.lang.reflect.Method;

/*
    This is an example of workaround for methods that you want call before right after proxies are initiated
 */
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {


    private final ConfigurableListableBeanFactory configurableListableBeanFactory;

    public PostProxyInvokerContextListener(ConfigurableListableBeanFactory factory) {
        this.configurableListableBeanFactory = factory;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println(
                "***********************************************************************\n" +
                        "***********************************************************************\n" +
                        "***********************************************************************\n" +
                        "*********************** APPLICATION IS REFRESHHED *********************\n" +
                        "***********************************************************************\n" +
                        "*****************************///////////===========********************\n" +
                        "*****************************////////////==========********************\n" +
                        "*****************************/////////////=========********************\n" +
                        "*****************************//////////////========********************\n" +
                        "*****************************///////////////=======********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************////////////////======********************\n" +
                        "*****************************////////////////======********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************/////////////////=====********************\n" +
                        "*****************************////////////////======********************\n" +
                        "*****************************////////////////======********************\n" +
                        "***********************************************************************\n" +
                        "***********************************************************************\n" +
                        "***********************************************************************\n");

        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        //we can not get right class name from the applicationContext.getBean("robotQuoter").getClass(),
        // because it is going to give us a proxy name
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
            String originalClassName = "";
            if(beanDefinition instanceof AnnotatedBeanDefinition) { // definition with @Bean Annotation cause this issue
                MethodMetadata factoryMethodMetadata = ((AnnotatedBeanDefinition) beanDefinition).getFactoryMethodMetadata();
                if (factoryMethodMetadata != null) {
                    originalClassName = factoryMethodMetadata.getReturnTypeName();

                    try {
                        Class<?> beanClass = Class.forName(originalClassName);
                        Method[] methods = beanClass.getMethods();
                        for (Method method : methods) {
                            if (method.isAnnotationPresent(PostProxy.class)) {
                                Object bean = applicationContext.getBean(name);
                                Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                                currentMethod.invoke(bean);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

    }

}
