import knapsackProblem.Instance;
import knapsackProblem.Item;
import knapsackProblem.Solution;

import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class Client implements IClient{
    private IServer server;
    private IRegister registers;
    private int id;
    ArrayList<Item> products=createRandomProducts();
    public Client(String name, int port) throws RemoteException {

        try {
            System.out.println("Klient port: "+ port);
            IClient client = (IClient) UnicastRemoteObject.exportObject(this, port);
            Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
            server = (IServer) registry.lookup(name);
            registers = (IRegister) registry.lookup("Spis");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Administrator nie jest uruchomiony");
            System.exit(0);
        } catch (NotBoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Błąd nawiązania połaczenia");
            System.exit(0);
        }

    }

    @Override
    public void getAllServers() throws RemoteException {
        for(int i=0;i<registers.getServers().size();i++)
        {
            System.out.println(registers.getServers().get(i).name);
        }

    }
    public void solveInstance() throws RemoteException {
        System.out.println("Rozwiązuje: "+server.getName());
        Instance instance=createRandomInstance();
        Solution result=server.solve(instance,0,0,new ArrayList<Integer>());

        for (int i=0;i<result.getBestItemID().size();i++)
        {
            System.out.println(result.getBestItemID().get(i));
        }
        System.out.println("Final Value: "+result.getBestValue());
    }
    private Instance createRandomInstance(){
        Random random=new Random();

        int size=random.nextInt(85)+5;
        Instance instance=new Instance();
        instance.setItemsArray(products);
        instance.setKnapsackCapacity(size);

        return instance;
    }
    private ArrayList<Item> createRandomProducts(){
        Random random=new Random();
        int numberOfItems=random.nextInt(12)+2;
        ArrayList<Item> products=new ArrayList<>();
        for(int i=0;i<numberOfItems;i++)
        {
            float value=random.nextFloat()+2;
            int weight=random.nextInt(20)+2;
            Item item=new Item(value,weight);
            System.out.println("Weight: "+item.getItemWeight());
            System.out.println("Value: "+item.getItemValue());
            products.add(item);

        }
        return products;
    }
    public void changeServer(String name) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
        server = (IServer) registry.lookup(name);
    }
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client client=new Client("SERVER_2",1950);
        client.solveInstance();
        client.changeServer("SERVER_1");
        client.solveInstance();
        Client client2=new Client("SERVER_2",1960);
        client2.solveInstance();
        client.changeServer("SERVER_1");
        client.solveInstance();
        client.getAllServers();


    }
}
