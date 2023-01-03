
package Decorator;

import TextEditor.*;
import java.awt.*;

public class Bold_ConcreteDecorator extends Text_Decorator{
    
    GUI gui; 

    public Bold_ConcreteDecorator(GUI gui, Text text) {
        super(text);
        this.gui = gui;
    }
    
    private void setTextBold(){
        System.out.println("<< Font Changed to Bold >>");
        if (gui.textArea.getFont().getStyle() == Font.PLAIN){
            Font bold  = new Font(gui.textArea.getFont().getName(), 
                    Font.BOLD, gui.textArea.getFont().getSize());
            gui.textArea.setFont(bold);
        } else if (gui.textArea.getFont().getStyle() == Font.ITALIC){
            Font boldItalic  = new Font(gui.textArea.getFont().getName(), 
                Font.ITALIC + Font.BOLD, gui.textArea.getFont().getSize());
                gui.textArea.setFont(boldItalic);
        }
    }
    
    @Override
    public void decorate() {
        decoratedText.decorate();
        setTextBold();
    }
    
}
