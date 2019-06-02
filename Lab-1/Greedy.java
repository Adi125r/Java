package javaK;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Klasa rozwiazujaca problem plecakowy za pomoca algorytmu zachlannego, implementujaca {@link Algorithm}
 * @author Adrian Wojceszek
 */
public class Greedy implements  Algorithm{

    /**
     * Metoda rozwizujaca problem plecakowy za pomoca algorytmu zachlannego.
     *@param instance
     * Obiekt klasy Instance zawierajcy pojemnosc plecaka oraz aktualna liste przedmiotow znajdujacych sie w plecaku
     * @param currWeight
     * Aktualna waga wszystkich przedmiotow znajdujacych sie w plecaku
     * @param currVal
     * Aktualna wartosc wszystkich przedmiotow znajdujacych sie w plecaku
     * @param ite
     * Lista  przedmiotow ktore znajduja sie w plecaku
     * @return zwraca obkiet klasy Solution ktory zawiera rozwiazanie problemu plecakowego
     * @see Instance
     */
    public Solution solve(Instance instance, int currWeight,
                          double currVal, List<Item> ite){
        Solution solution = new Solution();
        List<Item> sortIt = instance.items;
        Collections.sort(sortIt, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getRatio()<o2.getRatio()? 1 : -1;
            }
        });
        for (Item item :sortIt ){
           // System.out.println(item.getWeight());
            if (!ite.contains(item) )
            if ((currWeight+item.getWeight())<instance.getBackpack()){
             //  System.out.println(item.getWeight());
               ite.add(item);
               currVal = currVal+ item.getValue();
               currWeight = currWeight + item.getWeight();
           }
        }
        solution.setBestValue(currVal);
        solution.setBestWeight(currWeight);
        solution.setBest(ite);
        return solution;
    };

    /**
     *
     * @return zwraca opis algorytmu
     *
     */
    public String description(){
        return "Algorytm Zachlanny - algorytm, który w celu wyznaczenia rozwiązania w" +
                " każdym kroku dokonuje zachłannego, tj. najlepiej " +
                "rokującego w danym momencie wyboru rozwiązania" +
                " częściowego";
    };
}