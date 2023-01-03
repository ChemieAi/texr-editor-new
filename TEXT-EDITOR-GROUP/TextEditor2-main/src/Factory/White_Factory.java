/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import TextEditor.*;
import java.awt.Color;


public class White_Factory implements Fac_Theme_Interface{
    GUI gui;
    
    public White_Factory(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void changeTheme() {
        gui.window.getContentPane().setBackground(Color.white);
        gui.textArea.setBackground(Color.white);
        gui.textArea.setForeground(Color.black);
        System.out.println("<< White Theme is Activated >>");
    }

}
