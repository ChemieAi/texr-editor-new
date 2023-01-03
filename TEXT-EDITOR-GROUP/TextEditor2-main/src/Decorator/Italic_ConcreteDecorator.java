
package Decorator;

import TextEditor.*;
import java.awt.*;

public class Italic_ConcreteDecorator extends Text_Decorator{
    
    GUI gui; 

    public Italic_ConcreteDecorator(GUI gui, Text text) {
        super(text);
        this.gui = gui;
    }
    
    //public Italic_ConcreteDecorator(Text text) {    }
    
    private void setTextItalic(Text text){
        System.out.println("<< Font: Italic >>");
        if (gui.textArea.getFont().getStyle() == Font.PLAIN){
            Font italic  = new Font(gui.textArea.getFont().getName(), 
                    Font.ITALIC, gui.textArea.getFont().getSize());
            gui.textArea.setFont(italic);
        } else if (gui.textArea.getFont().getStyle() == Font.BOLD){
            Font boldItalic  = new Font(gui.textArea.getFont().getName(), 
                Font.BOLD + Font.ITALIC, gui.textArea.getFont().getSize());
                gui.textArea.setFont(boldItalic);
        }
    }
    
    @Override
    public void decorate() {
        decoratedText.decorate();
        setTextItalic(decoratedText);
    }
    
}
