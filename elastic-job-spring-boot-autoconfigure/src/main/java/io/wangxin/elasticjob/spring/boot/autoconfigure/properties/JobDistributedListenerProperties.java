package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.JOB_DISTRIBUTED_LISTENER_PREFIX;

/**
 * @author wx
 */
@Component
@ConfigurationProperties(prefix = JOB_DISTRIBUTED_LISTENER_PREFIX)
public class JobDistributedListenerProperties {
    private String clazz = "";
    private String startedTimeoutMilliseconds;
    private String completedTimeoutMilliseconds;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getStartedTimeoutMilliseconds() {
        return startedTimeoutMilliseconds;
    }

    public void setStartedTimeoutMilliseconds(String startedTimeoutMilliseconds) {
        this.startedTimeoutMilliseconds = startedTimeoutMilliseconds;
    }

    public String getCompletedTimeoutMilliseconds() {
        return completedTimeoutMilliseconds;
    }

    public void setCompletedTimeoutMilliseconds(String completedTimeoutMilliseconds) {
        this.completedTimeoutMilliseconds = completedTimeoutMilliseconds;
    }
}
