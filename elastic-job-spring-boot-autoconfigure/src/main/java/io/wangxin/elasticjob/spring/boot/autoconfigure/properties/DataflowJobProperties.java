package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.DATAFLOW_JOB_PREFIX;

/**
 * @author wx
 */
@Component
@ConfigurationProperties(prefix = DATAFLOW_JOB_PREFIX)
public class DataflowJobProperties extends BaseJobProperties {
    private String  streamingProcess;

    public String getStreamingProcess() {
        return streamingProcess;
    }

    public void setStreamingProcess(String streamingProcess) {
        this.streamingProcess = streamingProcess;
    }
}
