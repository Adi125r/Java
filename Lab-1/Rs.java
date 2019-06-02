package javaK;

import java.util.ArrayList;
import java.util.List;
import  java.util.Random;

/**
 * Klasa rozwiazujaca problem plecakowy za pomoca przegladania losowego , implementujaca {@link Algorithm}
 * @author Adrian Wojceszek
 */
public class Rs implements  Algorithm{


    /**
     * Metoda rozwizujaca problem plecakowy za pomoca algorytmu losowego
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

for(int i =0;i<1;i++) {

    Item item;

    while (currWeight<=instance.getBackpack()) {
        Random generator = new Random();
    List <Integer> IdL = new ArrayList<>();
       int l =generator.nextInt(instance.items.size());
        item = instance.items.get(l);
        int idI =item.getId();
//System.out.println(l);
//System.out.println(item.getValue());
        if (!ite.contains(item) )
            if((currWeight + item.getWeight()) < instance.getBackpack()) {
            //System.out.println(currWeight);
            ite.add(item);

            currVal = currVal + item.getValue();
            currWeight = currWeight + item.getWeight();
            }
            else
                break;
    }
        if (currVal > solution.getBestValue()) {
        solution.setBestValue(currVal);
        solution.setBestWeight(currWeight);
        solution.setBest(ite);
         }


}
            return solution;
        };

    /**
     *
     * @return zwraca opis algorytmu
     */
    public String description(){
            return "Przegląd losowy (wybieranie losowych przedmiotów do momentu aż nie zabraknie miejsca w plecaku," +
                    " powtórzenie tego algorytmu np. 100 razy i wybranie najlepszego wyniku";
        };

}
