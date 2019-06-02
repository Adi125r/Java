package Bean;

import java.beans.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiBeanInfo extends SimpleBeanInfo {

    private static final int PROPERTY_font = 0;
    private static final int PROPERTY_lineborder= 1;
    private static final int PROPERTY_PreferredSize = 2;



    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor beanDescriptor = new BeanDescriptor( CounterView.class);
        return beanDescriptor;
    }
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor[] properties = new PropertyDescriptor[3];
        try {
            properties[PROPERTY_font] = new PropertyDescriptor("style", CounterView.class);
            properties[PROPERTY_lineborder] = new PropertyDescriptor("linecolor", CounterView.class);
            properties[PROPERTY_PreferredSize] = new PropertyDescriptor("size",CounterView.class);
        } catch (IntrospectionException ex) {
            Logger.getLogger(GuiBeanInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
       return properties;
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];
        return eventSets;
    }
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        MethodDescriptor[] methods = new MethodDescriptor[0];
        return methods;
    }

}
