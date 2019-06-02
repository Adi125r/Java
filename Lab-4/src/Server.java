import knapsackProblem.*;

import javax.swing.*;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server implements  IServer {
    private BruteForce bf;
    private RandomSearch rs;
    private Greedy gs;
    private IRegister registers;
    private OS os;
    private  int ids;

    public Server(String name,String description, int id, int port ) throws RemoteException, AlreadyBoundException {
        System.out.println("Server is up");
        bf = new BruteForce();
        rs = new RandomSearch();
        gs = new Greedy();
        ids = id;
        try {
            System.out.println("Server port: "+port);
            IServer server = (IServer) UnicastRemoteObject.exportObject(this, port);
            Registry registry = LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
            registry.bind(name, this);
            registers =(IRegister) registry.lookup("Spis");
            os=new OS(name,description);
            registers.register(os);

        } catch (AlreadyBoundException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Administrator juz uruchomiony");
            System.exit(0);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Solution solve(Instance instance, int currWeight, double currVal, List<Integer> itemId) throws RemoteException {
        Solution result=new Solution();
        if (ids ==1)
        result=bf.solve(instance,0,0,new ArrayList<Integer>());
        if (ids ==2)
            result=rs.solve(instance,0,0,new ArrayList<Integer>());
        if (ids ==3)
            result=gs.solve(instance,0,0,new ArrayList<Integer>());
        return result;
    }

    @Override
    public String getName() throws RemoteException {
        return os.name;
    }
    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        Server server=new Server("SERVER_2","Random search Solution",2,2001);
        Server server1=new Server("SERVER_1","BruteForce Solution",1,2002);
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }
}
