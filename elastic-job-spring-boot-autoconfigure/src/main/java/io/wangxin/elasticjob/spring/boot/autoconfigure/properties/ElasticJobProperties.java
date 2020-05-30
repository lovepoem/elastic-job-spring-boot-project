package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.ELASTIC_JOB_PREFIX;

/**
 * @author Wang Xin
 */
@Component
@ConfigurationProperties(prefix = ELASTIC_JOB_PREFIX)
public class ElasticJobProperties {

}
