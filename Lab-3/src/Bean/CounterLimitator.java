package Bean;

import java.beans.*;
import java.util.ArrayList;
import java.util.List;

public class CounterLimitator implements VetoableChangeListener {

    // minimalne i makszymalne dopuszczalne wartości licznika
    private List be = new ArrayList() ;


    // Obsługa zdarzenia vetoableChange
// metoda może sygnalizować PropertyVetoException
    public void vetoableChange(PropertyChangeEvent e)
            throws PropertyVetoException {
        Integer newVal = (Integer) e.getNewValue();
        int val = newVal.intValue();

        // Sprawdzamy, czy zmiana  licznika jest dopuszczalna,
        // jeśli nie – sygnalizujemy wyjatek  PropertyVetoException
        if (be.contains(val))
            throw new PropertyVetoException("Niedopuszczalna zmiana wartości", e);

        be.add(val);

    }

}
