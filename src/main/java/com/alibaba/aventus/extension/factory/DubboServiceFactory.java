package com.alibaba.aventus.extension.factory;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.model.ApplicationModel;

/**
 * @author jifang.zjf@alibaba-inc.com (FeiQing)
 * @version 1.0
 * @since 2024/5/30 13:13.
 */
public class DubboServiceFactory {

    public static void main(String[] args) throws ClassNotFoundException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-consumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://10.20.130.230:2181");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        ReferenceConfig<Object> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);

        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(Class.forName("xxx"));
        reference.setVersion("1.0.0");

        // 和本地bean一样使用demoService
        // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        Object service = reference.get();

    }
}
