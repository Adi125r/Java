package javaLab2;

import knapsackProblem.Instance;
import knapsackProblem.Item;
import knapsackProblem.Solution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ThreadRun implements Runnable {
    private Thread t;
    private String threadName;

    public ThreadRun(String threadName)
    {
        this.threadName=threadName;
    }

    @Override
    public void run() {
        while (true) {

//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            int seed = Main.generateRandomNumber(1, 1000000);
            String sol = checkIfCacheContainsSeed(seed);
            Solution solution = new Solution();

            if(Main.getClassesList().size()!=0)
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

                    //System.out.println(solution.getBestItemID());
                    String val = "Taken items: " + solution.getBestItemID() + "Value: " + solution.getBestValue();
                    Main.getCache().putMapElement(new Long(seed), val);
                }
                else {
                    synchronized (this) {
                        System.out.println("znalazl roz "); }
                }

//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void start () throws InterruptedException {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start();
        }
    }

    public synchronized String checkIfCacheContainsSeed(int seed) {
        return Main.getCache().getMapElement(new Long(seed));
    }

    public Solution solveProblem(int seed) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException
    {
        Instance instance;
        instance=Main.generateNewInstance(seed);
        int drawAMethod = Main.generateRandomNumber(1,Main.getClassesList().size()-1);
        Class cl=Main.getClassesList().get(drawAMethod);
        Method method = cl.getDeclaredMethod("solve", Instance.class, int.class, double.class, List.class);
        synchronized (this)
        {
            System.out.println("Thread: "+this.threadName+" is solving instance no.: "+seed+" " +
                    "by: " + Main.getClassesList().get(drawAMethod).getName());
        }
        return (Solution) method.invoke(cl.getConstructor().newInstance(), instance, 0, 0, new ArrayList<Item>());
    }
}
