
package Decorator;

import TextEditor.*;
import java.awt.*;

public class ComicSansMS_ConcreteClass implements Text{
    GUI gui;

    public ComicSansMS_ConcreteClass(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void decorate() {
        System.out.println("<< Font: ComicSansMS >>");
        gui.textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
    }
    
}