package com.marshall.sky.zredis.command.impl;

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
    InitRegistryProcessor.registry = registry;
    if (!enableZRedis()) {
      return;
    }
    RedisPoolFactory.init();
    //registry bean
    registryBean(StringRedisCommand.class);
    registryBean(HashRedisCommand.class);
    registryBean(ListRedisCommand.class);
    registryBean(SetRedisCommand.class);
    registryBean(SortedSetRedisCommand.class);
    registryBean(KeyRedisCommand.class);
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
      throws BeansException {
  }


  private static void registryBean(Class clazz) {
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
        .genericBeanDefinition(clazz);
    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
    beanDefinition.setAutowireCandidate(true);
    //todo: getName()
    registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
  }

  private static boolean enableZRedis() {
    //todo: 预留位置
    return true;
  }

}
