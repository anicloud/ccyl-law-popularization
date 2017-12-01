package com.ani.ccyl.leg.service.infrastructure.spring;

import com.ani.ccyl.leg.commons.constants.Constants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * Created by lihui on 17-12-1.
 */
public class ExtendedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        Constants.PROPERTIES = props;
    }
}
