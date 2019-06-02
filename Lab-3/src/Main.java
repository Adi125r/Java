import Bean.GuiBean;

import javax.swing.*;

public class Main {
    public static void main(String[] args)  {

        GuiBean gui = new GuiBean();
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(gui);
        jFrame.pack();
        jFrame.show();
    }
}
