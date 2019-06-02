package javaLab2;

import knapsackProblem.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static Cache cache = new Cache();
    private static List<Class> classes = new ArrayList<>();

    public static void main(String args[]) throws ClassNotFoundException, MalformedURLException, InterruptedException {
        int nThreads=5;
        List<ThreadRun> threadList = new ArrayList<>();

        for(int i=0; i<nThreads;i++)
        {
            threadList.add(new ThreadRun(i+""));
            threadList.get(i).start();
        }

        classes = loadClasses(new File("C:\\Users\\adimo\\OneDrive\\Desktop\\java-lab2\\algorithm"), "knapsackProblem");

        writeLoadedClasses();

        while(true)
        {
            Thread.sleep(10000);
            writeReferenceAmount();
        }
    }

    private static synchronized void writeLoadedClasses() {
        for(int i=1; i<classes.size();i++)
            System.out.println(classes.get(i).getName());
    }

    public static synchronized Cache getCache()
    {
        return cache;
    }

    public static synchronized List<Class> getClassesList()
    {
        return classes;
    }

    public static List<Class> loadClasses(File directory, String packageName) throws ClassNotFoundException, MalformedURLException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        URL url = directory.toURI().toURL();
        URL[] urls = new URL[]{url};

        ClassLoader c = new URLClassLoader(urls);

        File[] files = new File("C:\\Users\\adimo\\OneDrive\\Desktop\\java-lab2\\algorithm\\" + packageName).listFiles();
        for (File file : files) {

            Class cla= c.loadClass(packageName + "." + file.getName().substring(0, file.getName().length() - 6));

            if( Algorithms.class.isAssignableFrom(cla))
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
                    generateRandomNumber(seed * i * 10000, instance.getKnapsackCapacity() / amountOfItems, (int) (instance.getKnapsackCapacity() / amountOfItems * 1.6))));
        }
        instance.setItemsArray(items);

        return instance;
    }

    public static int generateRandomNumber(int from, int to) {
        Random generator = new Random();
        int num = generator.nextInt(to - from + 1) + from;

        return num;
    }

    public static int generateRandomNumber(int seed, int from, int to) {
        Random generator = new Random();
        generator.setSeed(seed);
        int num = generator.nextInt(to - from + 1) + from;

        return num;
    }

    public static synchronized void writeReferenceAmount()
    {

        System.out.println("Chybienia: "+ cache.getFailedReferenceCounter());
        System.out.println("Ogolna liczba: " +cache.getReferenceCounter());
        System.out.println("Procent chybien : " + ((double)cache.getFailedReferenceCounter()/(double) cache.getReferenceCounter()));
        System.out.println("Liczba elementow w mapie: "+getCache().getMap().size());
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
