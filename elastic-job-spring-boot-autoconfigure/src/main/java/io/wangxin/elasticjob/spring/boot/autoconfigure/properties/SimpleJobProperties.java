package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.SIMPLE_JOB_PREFIX;

/**
 * @author wx
 */
@Component
@ConfigurationProperties(prefix = SIMPLE_JOB_PREFIX)
public class SimpleJobProperties extends BaseJobProperties {

}
