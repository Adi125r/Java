package Bean;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.util.Random;

public class GuiBean extends JPanel implements ActionListener, Serializable {
    Counter counter;
   private JButton binc = new JButton("Start");
   private JTextField txt = new JTextField(10);


   public GuiBean( )  {

       counter = new Counter();
        CounterView counterView = new CounterView(""+counter.getCount());
       counter.addPropertyChangeListener(counterView);
        CounterLimitator clim = new CounterLimitator();
       counter.addVetoableChangeListener(clim);
        //Container cp = getContentPane();
        this.setLayout(new FlowLayout());
        binc.addActionListener(this);
        this.add(binc);
        this.add(counterView);
        txt.addActionListener(this);
        this.add(txt);

    }


    // Obsługa akcji
    public void actionPerformed(ActionEvent e)  {


        try  {

                if (e.getSource() == txt)  {
                int n = 0;
                try  {
                    n = Integer.parseInt(txt.getText());
                } catch (NumberFormatException exc)  { return; }
                counter.setCount(n);
                counter.setBcount(n);
                return;
            }
            String cmd = e.getActionCommand();

            if (cmd.equals("Start")) counter.increment();
            else System.out.println("Unrecognized command");
        } catch (PropertyVetoException exc)  { // obługa wyjatku:
            System.out.println(""+ exc);       // podanie informacji
            //o niedopuszczalnej zmianie wartości
        }
    }

    public void setBinc(JButton binc) {
        this.binc = binc;
    }

    public void setTxt(JTextField txt) {
        this.txt = txt;
    }

    public JButton getBinc() {
        return binc;
    }

    public JTextField getTxt() {
        return txt;
    }
}