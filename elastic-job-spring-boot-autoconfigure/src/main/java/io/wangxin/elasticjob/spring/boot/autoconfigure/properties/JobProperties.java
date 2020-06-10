package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.JOB_PREFIX;
import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.SIMPLE_JOB_PREFIX;

/**
 * @author wx
 */
@Component
@ConfigurationProperties(prefix = JOB_PREFIX)
public class JobProperties {
    private List<SimpleJobProperties> simple;
    private List<DataflowJobProperties> dataflow;
    private List<ScriptJobProperties> script;

    public List<SimpleJobProperties> getSimple() {
        return simple;
    }

    public void setSimple(List<SimpleJobProperties> simple) {
        this.simple = simple;
    }

    public List<DataflowJobProperties> getDataflow() {
        return dataflow;
    }

    public void setDataflow(List<DataflowJobProperties> dataflow) {
        this.dataflow = dataflow;
    }

    public List<ScriptJobProperties> getScript() {
        return script;
    }

    public void setScript(List<ScriptJobProperties> script) {
        this.script = script;
    }
}
