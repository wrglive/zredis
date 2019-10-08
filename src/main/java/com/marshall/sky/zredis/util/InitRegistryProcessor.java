package com.marshall.sky.zredis.util;

import java.io.InputStream;
import java.util.Properties;
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
    try {
      InputStream inputStream = InitRegistryProcessor.class.getClassLoader()
          .getResourceAsStream("sky-zredis.properties");
      Properties properties = new Properties();
      properties.load(inputStream);
      BaseRedis.init(properties);
      //registry bean
      registryBean(StringRedis.class);
    } catch (Exception e) {
      throw new RuntimeException("load sky-zredis.properties error!");
    }
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
      throws BeansException {
  }


  private static void registryBean(Class clazz) {
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
        .genericBeanDefinition(clazz);
    beanDefinitionBuilder.addPropertyValue("jedisPool", BaseRedis.getJedisPool());
    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    //todo: getname
    registry.registerBeanDefinition(clazz.getName(),
        beanDefinition);
  }

  private static boolean enableZRedis() {
    //todo: 预留位置
    return true;
  }

}
