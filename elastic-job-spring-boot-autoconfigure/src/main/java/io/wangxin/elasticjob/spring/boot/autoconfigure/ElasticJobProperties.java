package io.wangxin.elasticjob.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.ELASTIC_JOB_PREFIX;

/**
 * @author Wang Xin
 */
@ConfigurationProperties(prefix = ELASTIC_JOB_PREFIX)
public class ElasticJobProperties {

}
