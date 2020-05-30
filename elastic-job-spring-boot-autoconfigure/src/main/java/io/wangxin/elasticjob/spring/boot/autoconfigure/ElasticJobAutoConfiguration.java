package io.wangxin.elasticjob.spring.boot.autoconfigure;

import io.wangxin.elasticjob.spring.boot.autoconfigure.properties.ElasticJobProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.ELASTIC_JOB_PREFIX;

/**
 * @author Wang Xin
 */
@Configuration
@ConditionalOnProperty(prefix = ELASTIC_JOB_PREFIX, name = "enabled", matchIfMissing = true)
@ConditionalOnClass(name = "org.springframework.boot.context.properties.bind.Binder")
@EnableConfigurationProperties({ElasticJobProperties.class})
public class ElasticJobAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticJobAutoConfiguration.class);



}
