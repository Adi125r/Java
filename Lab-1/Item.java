package javaK;

/** Klasa Item pozwala tworzyc przedmiot o podanych parametrach wadze i wartosci oraz oblicza wspolczynik
 * @author Adrian Wojcieszek
 */
public class Item {

    /** Zmienna przechowujaca wage przedmiotu     */
    int weight;
    /** Zmienna przechowujaca wartosc przedmiotu     */
    double value;
    /** Zmienna przechowujaca wspolczynik wartosci do wagi rzedmiotu     */
    double ratio;
    /** Zmiena przechowujaca id przedmiotu     */
    int id;

    /**
     *
     * @param id
     * parametr ustawiajacy id przedmiotu
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return zwraca id produktu
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param value
     * parametr ustawiajacy wartosc przedmiotu
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Oblicza wspolczynik wartosci do wagi
     * @param value
     * parametr do wyliczenia wspolczynika przdmiotu
     * @param weight
     * drugi parametr do obliczen
     */
    public void setRatio(double value,int weight) {
        this.ratio = value/weight;
    }

    /**
     *
     * @param weight
     * Parametr ustawiajacy wage przedmiotu
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     *
     * @return zwraca wartosc wspolczynika
     */
    public double getRatio() {
        return ratio;
    }

    /**
     *
     * @return Zwraca wartosc przedmiotu
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @return zwraca wage przedmiotu
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Tworzy item o podanych parametrach
     * @param i
     *id przedmiotu
     * @param w
     * waga przedmiotu
     * @param v
     * wartosc przedmiotu
     */
    public Item(int i,int w,double v)
    {
       setId(i);
        setRatio(v,w);
        setValue(v);
        setWeight(w);
    }
}
