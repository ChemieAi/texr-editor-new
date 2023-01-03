
package Factory;

import TextEditor.*;
import java.awt.*;


public class Blue_Factory implements Fac_Theme_Interface{
    GUI gui;
    
    public Blue_Factory(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void changeTheme() {
        gui.window.getContentPane().setBackground(Color.blue);
        gui.textArea.setBackground(Color.blue);
        gui.textArea.setForeground(Color.white);
        System.out.println("<< Blue Theme is Activated >>");
    }
}