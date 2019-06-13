package app;

import knapsackProblem.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class SystemConfigManagement {

    private static final int SEEDS_NUM = 500;
    private static final int DEFAULT_NO_THREADS=4;
    private static final int DEFAULT_CACHE_SIZE =100;
    private static int actualThreadCount =4;
    private static SystemConfig mBean = new SystemConfig(DEFAULT_NO_THREADS, DEFAULT_CACHE_SIZE);
    public static Cache cache = new Cache();
    public static List<Class> classes = new ArrayList<>();

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedURLException, ClassNotFoundException {
        //Get the MBean server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //register the MBean

        ObjectName name = new ObjectName("com.journaldev.jmx:type=app.SystemConfig");
        mbs.registerMBean(mBean, name);

        List<Thread> threads = new ArrayList<>();

        ClassLoader c = SystemConfigManagement.class.getClassLoader();
        classes = loadClasses(new File("C:\\Users\\adimo\\Desktop\\cw10\\algorithm\\"), "knapsackProblem", c);

        synchronized (System.out) {
            for (int i = 0; i < classes.size(); i++)
                System.out.println(classes.get(i).getName());
        }
        for (int i=0;i<DEFAULT_NO_THREADS;i++){
            threads.add(new Thread(new ThreadRun()));
            threads.get(i).start();

        }


        int i=0;
        while(true)
        {
            while(getActualThreadCount() <mBean.getThreadCount()) {
            threads.add(new Thread(new ThreadRun()));
            threads.get(threads.size()-1).start();
            setActualThreadCount(getActualThreadCount()+1);
        }

            System.out.println(cache.getMap().size());
            Thread.sleep(500);
            if(i>=8)
            {
                getCache().setReferenceCounter(0);
                getCache().setFailedReferenceCounter(0);
            }
            i++;
        }
    }

    public synchronized static int getCacheSize()
    {
        return mBean.getCacheSize();
    }

    public synchronized static int getSeedsNumber()
    {
        return SEEDS_NUM;
    }

    public static boolean ifNumOfThreadIsOk()
    {
        return getActualThreadCount()<=mBean.getThreadCount();
    }

    public synchronized static int getActualThreadCount()
    {
        return actualThreadCount;
    }

    public synchronized static void setActualThreadCount(int num)
    {
        actualThreadCount = num;
    }

    public static List<Class> loadClasses(File directory, String packageName, ClassLoader c) throws ClassNotFoundException, MalformedURLException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        URL url = directory.toURI().toURL();
        URL[] urls = new URL[]{url};

        c = new URLClassLoader(urls);

        File[] files = new File("C:\\Users\\adimo\\Desktop\\cw10\\algorithm\\" + packageName).listFiles();
        for (File file : files) {

            Class cla= c.loadClass(packageName + "." + file.getName().substring(0, file.getName().length() - 6));


            if(Algorithms.class.isAssignableFrom(cla) )
                classes.add(cla);
        }
        return classes;
    }

    public static synchronized Instance generateNewInstance(int seed) {
        Item.setCounter(1);
        Instance instance = new Instance();
        List<Item> items = new ArrayList<>();
        int amountOfItems = generateRandomNumber(seed * 10000, 4, 11);

        instance.setKnapsackCapacity(generateRandomNumber(seed * 10000, 20, 100));

        for (int i = 0; i < amountOfItems; i++) {
            items.add(new Item((double) (generateRandomNumber(seed * i * 10000, 2, 15)),
                    generateRandomNumber(seed * i * 10000, instance.getKnapsackCapacity() / amountOfItems,
                            (int) (instance.getKnapsackCapacity() / amountOfItems * 1.6))));
        }
        instance.setItemsArray(items);

        return instance;
    }

    public synchronized static List<Class> getClasses ()
    {
        return  classes;
    }
    public synchronized static Cache getCache ()
    {
        return  cache;
    }


    public synchronized static int generateRandomNumber(int from, int to) {
        Random generator = new Random();
        int num = generator.nextInt(to - from + 1) + from;

        return num;
    }

    public synchronized static int generateRandomNumber(int seed, int from, int to) {
        Random generator = new Random();
        generator.setSeed(seed);
        int num = generator.nextInt(to - from + 1) + from;

        return num;
    }

    public static String createDataSize(int msgSize){
        msgSize=msgSize/2;
        msgSize=msgSize*1024;
        StringBuilder sb=new StringBuilder(msgSize);
        for(int i=0;i<msgSize;i++){
            sb.append('x');
        }
        return sb.toString();
    }


}
