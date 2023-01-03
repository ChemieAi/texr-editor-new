/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import TextEditor.*;
import java.awt.Color;


public class Dark_Factory implements Fac_Theme_Interface{
    GUI gui;
    
    public Dark_Factory(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void changeTheme() {
        gui.window.getContentPane().setBackground(Color.black);
        gui.textArea.setBackground(Color.black);
        gui.textArea.setForeground(Color.white);
        System.out.println("<< Dark Theme is Activated >>");
}
}
