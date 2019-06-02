package Bean;

import java.beans.*;
import java.util.Random;

public class Counter {

    private int count = 60;    // właściwość count

    private int bcount = 0;

    // Pomocniczy obiekt do prowadzenia listy słuchaczy zmian właściwości oraz
    // propagowania zmian  wśród zarejestrowanych złuchaczy
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);

    // Metody przyłączania i odłączania słuchaczy zmian właściwości

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(l);
    }

    // Proste metody zwiększania i zmniejszania licznika

    public void increment() {
        try {
            Random generator = new Random();
            setCount( generator.nextInt(bcount));

        } catch (PropertyVetoException e) {
            System.out.println("Wartosc byla juz wylosowana ");
        }
    }




    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }

    // Getter właściwości "count"
    public int getCount() {
        return count;
    }

    public int getBcount() {
        return bcount;
    }

    // Setter właściowści "count"

    public void setBcount(int bcount) {
        this.bcount = bcount;
    }

    public synchronized void setCount(int aCount)
            throws PropertyVetoException {

        int oldValue = count;


        vetos.fireVetoableChange("count", new Integer(oldValue), new Integer(aCount));

        // tylko jeśli nikt nie zawetował

        count = aCount;  // ustalamy nową wartość licznika

        // ... powiadamiamy nasłuchujących zmiany właściwości count

        propertyChange.firePropertyChange("count", new Integer(oldValue),
                new Integer(aCount));
    }


    // Konstruktor otrzymuje jako argumenty obiekty typu Counter i Bean.CounterView
    // Pierwszy jest nam potrzebny do komunikacji z licznikiem, drugi - widok
    // wbudujemy w to GUI.


}
