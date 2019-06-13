package app;

public interface SystemConfigMBean {

    public void  setThreadCount(int noOfThreads);
    public int getThreadCount();

    public void setCacheSize(int schemaName);
    public int getCacheSize();

    // any method starting with get and set are considered
    // as attributes getter and setter methods, so I am
    // using do* for operation.
    public String doConfig();
}
