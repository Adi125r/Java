package app;

import java.text.DecimalFormat;

public class SystemConfig implements SystemConfigMBean {
    private int threadCount;
    private int cacheSize;

    public SystemConfig(int numThreads, int schema){
        this.threadCount=numThreads;
        this.cacheSize =schema;
    }

    @Override
    public synchronized void setThreadCount(int noOfThreads) {
        this.threadCount=noOfThreads;
    }


    @Override
    public synchronized int getThreadCount() {
        return this.threadCount;
    }


    @Override
    public synchronized void setCacheSize(int cacheSize) {
        this.cacheSize =cacheSize;
    }

    @Override
    public synchronized int getCacheSize() {
        return this.cacheSize;
    }

    @Override
    public String doConfig(){
        return "Liczba wątków="+this.threadCount+"\nChybienia="+new DecimalFormat("##.##").format(SystemConfigManagement.getCache().getFailedReferenceCounter()
                /(double)SystemConfigManagement.getCache().getReferenceCounter()*100) +"\nLiczba zajętych wpisów: "+((SystemConfigManagement.getCacheSize()<=SystemConfigManagement.getSeedsNumber()) ? SystemConfigManagement.getCacheSize(): SystemConfigManagement.getSeedsNumber())+
                " Liczba wolnych wpisów: " + ((SystemConfigManagement.getCacheSize()<=SystemConfigManagement.getSeedsNumber()) ? 0:(SystemConfigManagement.getCacheSize()- SystemConfigManagement.getSeedsNumber()));
    }
}
