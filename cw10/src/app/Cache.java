package app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Cache {

    private Map<Long,String> map = new HashMap<>();
    private int numOfElements=0;
    private int referenceCounter=0;
    private int failedReferenceCounter=0;

    public synchronized Map<Long, String> getMap() {
        return map;
    }

    public synchronized void setMap(Map<Long, String> map) {
        this.map = map;
    }

    public synchronized String getMapElement(long key){
        referenceCounter++;
        if (getMap().get(key) == null)
            failedReferenceCounter++;
        return getMap().get(key);
    }

    public synchronized void putMapElement(long key, String val){

        while((getMap().size())>=SystemConfigManagement.getCacheSize())
        {
            while(getMap().remove((long)SystemConfigManagement.generateRandomNumber(1,SystemConfigManagement.getSeedsNumber()))==null);
            setNumOfElements(getNumOfElements()+1);
        }

        getMap().put(key, val);
//        System.out.println("lalal: "+getNumOfElements());
        //setNumOfElements(getNumOfElements()+1);
        //System.out.println("lalalal: " + SystemConfigManagement.getCacheSize());
    }

    public int getReferenceCounter() {
        return referenceCounter;
    }

    public void setReferenceCounter(int referenceCounter) {
        this.referenceCounter = referenceCounter;
    }

    public int getFailedReferenceCounter() {
        return failedReferenceCounter;
    }

    public void setFailedReferenceCounter(int failedReferenceCounter) {
        this.failedReferenceCounter = failedReferenceCounter;
    }

    public synchronized int getNumOfElements()
    {
        return numOfElements;
    }

    public synchronized void setNumOfElements(int numOfElements)
    {
        this.numOfElements=numOfElements;
    }
}
