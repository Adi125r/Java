package app;

import knapsackProblem.Instance;
import knapsackProblem.Item;
import knapsackProblem.Solution;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ThreadRun implements Runnable {
    public ThreadRun() { }

    @Override
    public void run() {

        while(SystemConfigManagement.ifNumOfThreadIsOk()) {
            try {
                int r= SystemConfigManagement.generateRandomNumber(1,15);
                Thread.sleep(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int seed = SystemConfigManagement.generateRandomNumber(1, SystemConfigManagement.getSeedsNumber());
            String sol = checkIfCacheContainsSeed(seed);
            Solution solution = new Solution();

            if(SystemConfigManagement.getClasses().size()!=0) {
                if (sol == null) {
                    try {
                        solution = solveProblem(seed);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    SystemConfigManagement.getCache().setReferenceCounter(SystemConfigManagement.getCache().getReferenceCounter()+1);
                    SystemConfigManagement.getCache().setFailedReferenceCounter(SystemConfigManagement.getCache().getFailedReferenceCounter()+1);
                    putIntoMap(seed,solution);

                } else {
                    SystemConfigManagement.getCache().setReferenceCounter(SystemConfigManagement.getCache().getReferenceCounter()+1);
                    synchronized (System.out)
                    {
                        System.out.println("Wynik pobrano z pamieci");
                    }
                }
            }
            else
            {
//                synchronized (System.out)
//                {
//                    System.out.println("Klasa nie zaladowana");
//                }
            }

            try {
                int r= SystemConfigManagement.generateRandomNumber(1,15);
                Thread.sleep(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        SystemConfigManagement.setActualThreadCount(SystemConfigManagement.getActualThreadCount()-1);
    }

    public synchronized String checkIfCacheContainsSeed(int seed) {

            return SystemConfigManagement.getCache().getMapElement((long)seed);

    }

    public synchronized void putIntoMap(int seed, Solution solution)
    {
//        String val = "Taken items: " + solution.getBestItemID() + "Value: " + solution.getBestValue();
        String val = SystemConfigManagement.createDataSize(2030);  //100000

        SystemConfigManagement.cache.putMapElement((long)seed, val);
    }

    public Solution solveProblem(int seed) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException
    {
        Instance instance;
        instance=SystemConfigManagement.generateNewInstance(seed);
        int drawAMethod = SystemConfigManagement.generateRandomNumber(0,SystemConfigManagement.getClasses().size()-1);
        Class cl=SystemConfigManagement.getClasses().get(drawAMethod);
        Method method = cl.getDeclaredMethod("solve", Instance.class, int.class, double.class, List.class);
        synchronized (System.out)
        {
            System.out.println("Thread: "+ Thread.currentThread().getName()+" is solving instance no.: "+seed+" " +
                    "by: " + SystemConfigManagement.getClasses().get(drawAMethod).getName());
        }
        return (Solution) method.invoke(cl.getConstructor().newInstance(), instance, 0, 0, new ArrayList<Item>());
    }
}
