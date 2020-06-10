package io.wangxin.elasticjob.spring.boot.autoconfigure;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import io.wangxin.elasticjob.spring.boot.autoconfigure.properties.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;
import java.util.List;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.ELASTIC_JOB_PREFIX;

/**
 * @author Wang Xin
 */
@Configuration
@ConditionalOnProperty(prefix = ELASTIC_JOB_PREFIX, name = "enabled", matchIfMissing = true)
@ConditionalOnClass(name = "org.springframework.boot.context.properties.bind.Binder")
@EnableConfigurationProperties({ElasticJobProperties.class, RegistryZooKeeperProperties.class, JobProperties.class})
public class ElasticJobAutoConfiguration implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticJobAutoConfiguration.class);
    @Resource
    private RegistryZooKeeperProperties registryZooKeeperProperties;
    @Autowired
    private JobProperties jobProperties;

    @Bean
    @ConditionalOnMissingBean
    public CoordinatorRegistryCenter setUpRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(registryZooKeeperProperties.getServerLists(), registryZooKeeperProperties.getNamespace());
        CoordinatorRegistryCenter result = new ZookeeperRegistryCenter(zkConfig);
        result.init();
        LOGGER.info("注册中心启动成功！");
        return result;
    }

    public void setUpSimpleJob() {
        if (jobProperties != null) {
            List<SimpleJobProperties> simple = jobProperties.getSimple();
            if (simple != null) {
                simple.forEach(simpleJob -> {
                    JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(simpleJob.getId(), simpleJob.getClazz(), simpleJob.getShardingTotalCount()).shardingItemParameters(simpleJob.getShardingItemParameters()).build();
                    SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, simpleJob.getClazz());
                    new JobScheduler(setUpRegistryCenter(), LiteJobConfiguration.newBuilder(simpleJobConfig).build(), null).init();
                });
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        setUpSimpleJob();
    }




/*    @Bean
    public void setUpDataflowJob() {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("javaDataflowElasticJob", "0/5 * * * * ?", 3).shardingItemParameters("0=Beijing,1=Shanghai,2=Guangzhou").build();
        DataflowJobConfiguration dataflowJobConfig = new DataflowJobConfiguration(coreConfig, JavaDataflowJob.class.getCanonicalName(), true);
        new JobScheduler(setUpRegistryCenter(), LiteJobConfiguration.newBuilder(dataflowJobConfig).build(), null).init();
    }

    @Bean
    public void setUpScriptJob() {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("scriptElasticJob", "0/5 * * * * ?", 3).build();
        ScriptJobConfiguration scriptJobConfig = new ScriptJobConfiguration(coreConfig, buildScriptCommandLine());
        new JobScheduler(setUpRegistryCenter(), LiteJobConfiguration.newBuilder(scriptJobConfig).build(), null).init();
    }*/

}
