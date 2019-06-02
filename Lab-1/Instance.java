package javaK;

import java.util.ArrayList;
import java.util.List;

/**
 * klasa przechowujaca instancje problemu  pojemnosc plecaka oraz liste przedmiotow
 */
public class Instance
{
    /**
     * zmiena pojemnaosci plecaka
     */
    int backpack;
    /**
     * lista przedmiotow
     */
    List <Item>items = new ArrayList();

    /**
     * Parametr ustawiajacy pojemnosc plecaka
     * @param backpack
     */
    public void setBackpack(int backpack) {
        this.backpack = backpack;
    }

    /**
     *
      * @return zwraca pojemnosc plecaka
     */
    public int getBackpack() {
        return backpack;
    }


    /**
     * Metoda dodawania przedmiotow do listy
     * Parametr obiektow przedmiotu
     * @param item
     */
    public void addItem(Item item)
{
    items.add(item);
}

    /**
     * Metoda do zwracania przedmiotow z listy
     * @param value
     * parametr wartosci przedmiotu
     * @param weight
     * parametr wagi przedmiotu
     * @return zwraca przedmiot
     */
    public Item getItem(float value, int weight){
    for(Item i :items){
    if (i.getWeight() == weight && i.getValue() == value){
        return  i;
    }
    }
    return  null;
}

}
