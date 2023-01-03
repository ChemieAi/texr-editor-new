package Decorator;

import TextEditor.*;
import java.awt.*;

public class TimesNewRoman_ConcreteClass implements Text{
    GUI gui;

    public TimesNewRoman_ConcreteClass(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void decorate() {
        System.out.println("<< Font: Times New Roman >>");
        gui.textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    }
    
}
