package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.wangxin.elasticjob.spring.boot.autoconfigure.StarterConstants.SCRIPT_JOB_PREFIX;

/**
 * @author wx
 */
@Component
@ConfigurationProperties(prefix = SCRIPT_JOB_PREFIX)
public class ScriptJobProperties extends BaseJobProperties {
    private String scriptCommandLine;

    public String getScriptCommandLine() {
        return scriptCommandLine;
    }

    public void setScriptCommandLine(String scriptCommandLine) {
        this.scriptCommandLine = scriptCommandLine;
    }
}
