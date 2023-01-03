
package Decorator;

import TextEditor.*;
import java.awt.*;

public class Arial_ConcreteClass implements Text{
    GUI gui;

    public Arial_ConcreteClass(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void decorate() {
        System.out.println("<< Font: Ariiiiiial >>");
        gui.textArea.setFont(new Font("Arial", Font.PLAIN, 13));
    }
    
}
