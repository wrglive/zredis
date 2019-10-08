package com.marshall.sky.zredis.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitRegistryProcessor implements BeanDefinitionRegistryPostProcessor {

  private static BeanDefinitionRegistry registry;

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    this.registry = registry;
    if (!enableZRedis()) {
      return;
    }
    BaseZRedis.init();
    //registry bean
    registryBean(StringZRedis.class);
    registryBean(HashZRedis.class);
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
      throws BeansException {
  }


  private static void registryBean(Class clazz) {
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
        .genericBeanDefinition(clazz);
    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    //todo: getname
    registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
  }

  private static boolean enableZRedis() {
    //todo: 预留位置
    return true;
  }

}
