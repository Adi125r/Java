package javaK;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Solution przechowuje najlepsze znane rozwiazanie
 * @author Adrian Wojcieszek
 */
public class Solution
{
    /**
     * Zmiena przechowuje liczbe itemow w plecaku
     */
    public int size;
    /** Zmiena przechowujaca najlepsza wartosc plecaka      */
    public double bestValue=0;
    /**
     * Zmiena przechowujaca najlepsza wage plecaka
     */
    public int bestWeight=0;

    /**
     * Przechowuje elementy plecaka
     */
    public List<Item> best = new ArrayList<>();

    /**
     *
     * @return zwraca najlepsza wartosc plecaka
     */
    public double getBestValue(){
    return bestValue;
}

    /**
     *
      * @param bestValue
     * Parametr ustawiajacy najlepsza wartosc plecaka
     */
    public void setBestValue(double bestValue) {
        this.bestValue = bestValue;
    }

    /**
     *
     * @param bestWeight
     * Parametr ustawiajacy najlepsza wage plecaka
     */
    public void setBestWeight(int bestWeight) {
        this.bestWeight = bestWeight;
    }

    /**
     *
     * @param best
     * parametr ustawiajacy liste przedmioty w plecaku tworzacych  najlepszego rozwiazanie
     */
    public void setBest(List<Item> best) {
        this.best = best;
    }


    /**
     *
     * @return zwraca najlepsza wage plecaka
     */
    public int getBestWeight() {
        return bestWeight;
    }

    /**
     *
      * @return zwraca liste przedmiotow z plecaka tworzacych najlepsze rozwiazanie
     */
    public List<Item> getBest() {
        return best;
    }

    /**
     *
     * @param size
     * parametr ustawiajacy liczbe elementow w plecaku
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     *
     * @return zwraca liczbe elementow w plecaku
     */
    public int getSize() {
        return size;
    }
}
