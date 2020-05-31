package io.wangxin.elasticjob.spring.boot.autoconfigure.properties;

/**
 * @author wx
 */
public class BaseJobProperties {
    private String id;
    private String clazz;
    private String jobRef;
    private String registryCenterRef;
    private String cron;
    private Integer shardingTotalCount;
    private String shardingItemParameters;
    private String jobParameter;
    private Boolean monitorExecution = true;
    private Long maxTimeDiffSeconds = -1L;
    private Long reconcileIntervalMinutes = 10L;
    private Boolean misfire = true;
    private String description;
    private Boolean disabled = false;
    private Boolean overwrite = false;
    private String executorServiceHandler = "com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler";
    private String jobExceptionHandler = "com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler";
    private String jobShardingStrategyClass = "";
    /**
     * bean id
     */
    private String eventTraceRdbDataSource;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getJobRef() {
        return jobRef;
    }

    public void setJobRef(String jobRef) {
        this.jobRef = jobRef;
    }

    public String getRegistryCenterRef() {
        return registryCenterRef;
    }

    public void setRegistryCenterRef(String registryCenterRef) {
        this.registryCenterRef = registryCenterRef;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getShardingTotalCount() {
        return shardingTotalCount;
    }

    public void setShardingTotalCount(Integer shardingTotalCount) {
        this.shardingTotalCount = shardingTotalCount;
    }

    public String getShardingItemParameters() {
        return shardingItemParameters;
    }

    public void setShardingItemParameters(String shardingItemParameters) {
        this.shardingItemParameters = shardingItemParameters;
    }

    public String getJobParameter() {
        return jobParameter;
    }

    public void setJobParameter(String jobParameter) {
        this.jobParameter = jobParameter;
    }

    public Boolean getMonitorExecution() {
        return monitorExecution;
    }

    public void setMonitorExecution(Boolean monitorExecution) {
        this.monitorExecution = monitorExecution;
    }

    public Long getMaxTimeDiffSeconds() {
        return maxTimeDiffSeconds;
    }

    public void setMaxTimeDiffSeconds(Long maxTimeDiffSeconds) {
        this.maxTimeDiffSeconds = maxTimeDiffSeconds;
    }

    public Long getReconcileIntervalMinutes() {
        return reconcileIntervalMinutes;
    }

    public void setReconcileIntervalMinutes(Long reconcileIntervalMinutes) {
        this.reconcileIntervalMinutes = reconcileIntervalMinutes;
    }

    public Boolean getMisfire() {
        return misfire;
    }

    public void setMisfire(Boolean misfire) {
        this.misfire = misfire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getOverwrite() {
        return overwrite;
    }

    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }

    public String getExecutorServiceHandler() {
        return executorServiceHandler;
    }

    public void setExecutorServiceHandler(String executorServiceHandler) {
        this.executorServiceHandler = executorServiceHandler;
    }

    public String getJobExceptionHandler() {
        return jobExceptionHandler;
    }

    public void setJobExceptionHandler(String jobExceptionHandler) {
        this.jobExceptionHandler = jobExceptionHandler;
    }

    public String getJobShardingStrategyClass() {
        return jobShardingStrategyClass;
    }

    public void setJobShardingStrategyClass(String jobShardingStrategyClass) {
        this.jobShardingStrategyClass = jobShardingStrategyClass;
    }

    public String getEventTraceRdbDataSource() {
        return eventTraceRdbDataSource;
    }

    public void setEventTraceRdbDataSource(String eventTraceRdbDataSource) {
        this.eventTraceRdbDataSource = eventTraceRdbDataSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
