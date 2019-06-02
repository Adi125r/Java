import knapsackProblem.BruteForce;
import knapsackProblem.Instance;
import knapsackProblem.Item;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws AlreadyBoundException, RemoteException {



                Client client2=new Client("SERVER_2",2545);
        client2.solveInstance();

        Client client=new Client("SERVER_2",2556);
        client.solveInstance();



        //System.out.println("PO2: "+ spis.getRegisteredServers().size());



    }

}
