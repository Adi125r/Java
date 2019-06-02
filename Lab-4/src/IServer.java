import knapsackProblem.Instance;
import knapsackProblem.Solution;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IServer extends Remote {

    Solution solve(Instance instance, int currWeight, double currVal, List<Integer> itemId) throws RemoteException;

    String getName() throws RemoteException;

}
