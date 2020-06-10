package io.wangxin.elasticjob.spring.boot.autoconfigure;

import io.wangxin.elasticjob.spring.boot.autoconfigure.properties.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wang Xin
 */
public class StarterConstants {
    /**
     * 属性前缀
     */
    public static final String ELASTIC_JOB_PREFIX = "elastic-job";
    public static final String REGISTRY_PREFIX = ELASTIC_JOB_PREFIX + ".reg";
    public static final String REGISTRY_ZOOKEEPER_PREFIX = REGISTRY_PREFIX + ".zookeeper";

    public static final String JOB_PREFIX = ELASTIC_JOB_PREFIX + ".job";
    public static final String JOB_LISTENER_PREFIX = JOB_PREFIX + ".listener";
    public static final String JOB_DISTRIBUTED_LISTENER_PREFIX = JOB_PREFIX + ".distributed-listener";

    public static final String SIMPLE_JOB_PREFIX = JOB_PREFIX + ".simple";
    public static final String DATAFLOW_JOB_PREFIX = JOB_PREFIX + ".dataflow";
    public static final String SCRIPT_JOB_PREFIX = JOB_PREFIX + ".script";
    private static final int MAP_CAPACITY = 64;

    public static final Map<String, Class> PROPERTY_MAP = new HashMap<String, Class>(MAP_CAPACITY) {
        {
            put(ELASTIC_JOB_PREFIX, ElasticJobProperties.class);
            put(REGISTRY_PREFIX, RegistryProperties.class);
            put(REGISTRY_ZOOKEEPER_PREFIX, RegistryZooKeeperProperties.class);

            put(JOB_LISTENER_PREFIX, JobListenerProperties.class);
            put(JOB_DISTRIBUTED_LISTENER_PREFIX, JobDistributedListenerProperties.class);

            put(SIMPLE_JOB_PREFIX, SimpleJobProperties.class);
            put(DATAFLOW_JOB_PREFIX, DataflowJobProperties.class);
            put(SCRIPT_JOB_PREFIX, ScriptJobProperties.class);
        }

    };

}
