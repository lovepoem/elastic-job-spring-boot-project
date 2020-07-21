package io.wangxin.elasticjob.spring.boot.autoconfigure;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.JobEventListener;
import com.dangdang.ddframe.job.event.JobEventListenerConfigurationException;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import io.wangxin.elasticjob.spring.boot.autoconfigure.properties.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.ELASTIC_JOB_PREFIX;

/**
 * @author Wang Xin
 */
@Configuration
@ConditionalOnProperty(prefix = ELASTIC_JOB_PREFIX, name = "enabled", matchIfMissing = true)
@ConditionalOnClass(name = "org.springframework.boot.context.properties.bind.Binder")
@EnableConfigurationProperties({ElasticJobProperties.class, RegistryZooKeeperProperties.class, JobProperties.class, JobDistributedListenerProperties.class, JobListenerProperties.class})
public class ElasticJobAutoConfiguration implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticJobAutoConfiguration.class);
    @Autowired
    private RegistryZooKeeperProperties registryZooKeeperProperties;
    @Autowired
    private JobProperties jobProperties;
    @Autowired
    private JobDistributedListenerProperties jobDistributedListenerProperties;
    @Autowired
    private JobListenerProperties jobListenerProperties;
    @Autowired
    private ApplicationContext ctx;
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
        // 构建SpringJobScheduler对象来初始化任务
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(SpringJobScheduler.class);
        factory.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        if (jobProperties != null) {
            List<SimpleJobProperties> simple = jobProperties.getSimple();
            if (simple != null) {
                simple.forEach(simpleJob -> {

                    // 构建SpringJobScheduler对象来初始化任务
                    factory.setScope(BeanDefinition.SCOPE_PROTOTYPE);
                    JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(simpleJob.getId(), simpleJob.getCron(), simpleJob.getShardingTotalCount()).shardingItemParameters(simpleJob.getShardingItemParameters()).build();
                    BeanDefinitionBuilder rdbFactory = BeanDefinitionBuilder.rootBeanDefinition(simpleJob.getClazz());
                    factory.addConstructorArgValue(rdbFactory.getBeanDefinition());
                    factory.addConstructorArgValue(setUpRegistryCenter());
                    factory.addConstructorArgValue(coreConfig);

                    // 任务执行日志数据源，以名称获取
              /*      if (StringUtils.hasText(simpleJob.getEventTraceRdbDataSource())) {
                        BeanDefinitionBuilder rdbFactory = BeanDefinitionBuilder.rootBeanDefinition(JobEventRdbConfiguration.class);
                        rdbFactory.addConstructorArgReference(simpleJob.getEventTraceRdbDataSource());
                        factory.addConstructorArgValue(rdbFactory.getBeanDefinition());
                    }*/

                    factory.addConstructorArgValue(setUpRegistryCenter());
                    DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
                    defaultListableBeanFactory.registerBeanDefinition("SpringJobScheduler" + simpleJob.getId(), factory.getBeanDefinition());
                    SpringJobScheduler springJobScheduler = (SpringJobScheduler) ctx.getBean("SpringJobScheduler" + simpleJob.getId());
                    springJobScheduler.init();

           /*         JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(simpleJob.getId(), simpleJob.getCron(), simpleJob.getShardingTotalCount()).shardingItemParameters(simpleJob.getShardingItemParameters()).build();
                    SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, simpleJob.getClazz());
                    new JobScheduler(setUpRegistryCenter(), LiteJobConfiguration.newBuilder(simpleJobConfig).build()).init();*/
                });
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
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
