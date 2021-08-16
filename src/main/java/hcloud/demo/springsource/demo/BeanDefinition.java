package hcloud.demo.springsource.demo;

import java.util.Objects;

/**
 * @author dinghui
 * @Descrition 类描述信息
 * @date 2021/6/12 23:42
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Object getBeanClass() throws IllegalAccessException, InstantiationException {
        return beanClass.newInstance();
    }
}
