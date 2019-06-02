package javaK;

import  java.util.List;

/**
 * Interfejs ktory nadaje wymogi jakie musza spelnic klasy {@link Bf } {@link Greedy} {@link Rs}
 * @author Adrian Wojcieszek
 */
public interface Algorithm {

    /**
     * Publiczna abstrakcyjna metoda narzucajaca implementacje(algorytmow) klasie {@link Bf } {@link Greedy }{@link Rs}
     *@param instance
     * Obiekt klasy Instance zawierajcy pojemnosc plecaka oraz aktualna liste przedmiotow znajdujacych sie w plecaku
     * @param currWeight
     * Aktualna waga wszystkich przedmiotow znajdujacych sie w plecaku
     * @param currVal
     * Aktualna wartosc wszystkich przedmiotow znajdujacych sie w plecaku
     * @param ite
     * Lista  przedmiotow ktore znajduja sie w plecaku
     * @return zwraca obkiet klasy Solution ktory zawiera rozwiazanie problemu plecakowego
     */
    public Solution solve(Instance instance, int currWeight, double currVal, List<Item> ite);

    /**
     *
     * @return Zwraca opis danego algorytmu
     */
    public String description();
}
