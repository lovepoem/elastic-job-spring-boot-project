package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.JOB_LISTENER_PREFIX;

/**
 * @author wx
 */
@Component
@ConfigurationProperties(prefix =  JOB_LISTENER_PREFIX)
public class JobListenerProperties {
    private String clazz;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
