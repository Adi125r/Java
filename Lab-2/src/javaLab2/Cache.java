package javaLab2;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache {

    private Map<Long,String> map = new HashMap<>();
    private int referenceCounter=0;
    private int failedReferenceCounter=0;

    public Map<Long, String> getMap() {
        return map;
    }

    public void setMap(Map<Long, String> map) {
        this.map = map;
    }

    public String getMapElement(Long key){
        synchronized (this) {
            referenceCounter++;
            if (map.get(key) == null)
                failedReferenceCounter++;
            return map.get(key);
        }
    }

    public void putMapElement(Long key, String val){
        synchronized (this) {
            map.put(key, val);
        }
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
}
