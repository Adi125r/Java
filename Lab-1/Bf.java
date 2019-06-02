package javaK;

import java.util.List;
import java.util.Random;

/**
 * Klasa rozwiazujaca problem plecakowy za pomoca metody Brute Force, implementujaca interfejs {@link Algorithm}.
 * @author Adrian Wojcieszek
 */
public class Bf {
    /**
     * Metoda rozwiazujaca problem plecakowy. Sprawdza ona wszystkie przypadki i zapisuje najlepsze rozwiazanie obiekcie klasy Solution
     * @param instance
     * Obiekt klasy Instance zawierajcy pojemnosc plecaka oraz aktualna liste przedmiotow znajdujacych sie w plecaku
     * @param currWeight
     * Aktualna waga wszystkich przedmiotow znajdujacych sie w plecaku
     * @param currVal
     * Aktualna wartosc wszystkich przedmiotow znajdujacych sie w plecaku
     * @param ite
     * Lista przedmiotow ktore znajduja sie w plecaku
     * @return zwraca obkiet klasy Solution ktory zawiera rozwiazanie problemu plecakowego
     * @see Instance
     */
    public Solution solve(Instance instance, int currWeight,
                          double currVal, List<Item> ite){
        Solution solution = new Solution();

        Item item;

        for (int i=0;i<instance.items.size();i++){

            item = instance.items.get(i);
            if (!ite.contains(item) )
            if ((currWeight + item.getWeight()) < instance.getBackpack()) {
                ite.add(item);
                currVal = currVal + item.getValue();
                currWeight = currWeight + item.getWeight();
                Solution sol2 = solve(instance,currWeight,currVal,ite);
                if (sol2.getBestValue()>solution.getBestValue())
                    solution=sol2;
                ite.remove(item);
                currVal = currVal - item.getValue();
                currWeight = currWeight - item.getWeight();
            }

        }

            if (currVal > solution.getBestValue()) {
                solution.setBestValue(currVal);
                solution.setBestWeight(currWeight);
                solution.setBest(ite);
                solution.setSize(ite.size());
            }

        return solution;
    };

    /**
     *
     * @return Zwraca opis algorytmu
     */
    public String description(){
        return "Przegląd zupełny (bruteforce) – metoda nieefektywna obliczeniowo ale optymalna, gdyż znajduje rozwiązanie najlepsze";
    };
}
