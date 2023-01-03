package TextEditor;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Functions_GUI implements ActionListener{

    GUI gui;
    String fileName;
    String fileAddress;

    public Functions_GUI(GUI gui) {
        this.gui = gui;
    }

    //Yeni dosya oluşturma fonksyionu.
    /* yazılan her şeyi sıfırlar */
    public void newFile() {

        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }

    //Yeni dosya açma fonksiyonu.
    public void openFile() {

        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        System.out.println("-> A file has opened: " + fileName + " | Address: " + fileAddress);

        try { //Dosyayı okumak için adresine ihtiyacımız var
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText("");
            String line = null;

            while ((line = br.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }
            br.close();

        } catch (Exception e) {
            System.out.println("FİLE COULDN'T OPENED");
        }
    }

    //Yazılan dosyayı (open ile açıldıysa üzerine) kaydetme fonskiyonu.
    public void saveFile() {
        if (fileName == null) {
            saveAsFile();
        } else {
            try {
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            } catch (Exception e) {
                System.out.println("SOMETHING IS WRONG, COULDNT'T SAVED");
            }
        }
    }

    //Yazılan dosyayı farklı kaydetme fonksiyonu.
    public void saveAsFile() {

        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();

        } catch (Exception e) {
            System.out.println("FİLE COULDN'T SAVED");
        }

    }

    //Programdan çıkış fonksiyonu
    public void exitFile() {
        System.exit(0);
    }
    
    //Undo fonksiyonu
    public void undoTxt() {
        gui.um.undo();
    }

    //Redo fonksiyonu
    public void redoTxt() {
        gui.um.redo();
    }

    //Word wrap (kelime kaydırma) fonksiyonu
    public void wordWrap() {

        if (gui.wordWrapOn == false) {
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.iWrap.setText("Word wrap: ON");
        } else if (gui.wordWrapOn == true) {
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.iWrap.setText("Word wrap: OFF");
        }
    }

    //Yazım hatalarını düzeltme fonksiyonu (GUI için typoCheck çağrılır.)
    public void fixTypos() {
        Function_Dictionary dict = new Function_Dictionary("words.txt");
        String temp = gui.textArea.getText();
        gui.textArea.setText(Functions_other.typoCheck(temp, dict.getDict()));
    }

    JButton buttonFind;
    JButton buttonReplace;
    JTextField textFieldFind;
    JTextField textFieldReplaceOld;
    JTextField textFieldReplaceNew;
    JFrame windowFind;
    JFrame windowReplace;
    JLabel labelFindIndex;

    //İstenen bir kelimeyi aramak için yeni pencere açılır.
    public void windowFind() {

        buttonFind = new JButton("Find");
        buttonFind.setBounds(100, 100, 80, 30);
        buttonFind.addActionListener(this);

        JLabel label1 = new JLabel();
        label1.setText("Find");
        label1.setBounds(100, 50, 80, 30);

        JLabel label2 = new JLabel();
        label2.setText("Aradığınız kelimelerin indexleri:");
        label2.setBounds(40, 130, 220, 30);

        labelFindIndex = new JLabel();
        labelFindIndex.setBounds(40, 150, 800, 30);

        textFieldFind = new JTextField();
        textFieldFind.setBounds(100, 70, 80, 30);

        windowFind = new JFrame("Find");
        windowFind.setSize(300, 300);
        windowFind.setLocationRelativeTo(null);
        windowFind.setLayout(null);
        windowFind.add(buttonFind);
        windowFind.add(label1);
        windowFind.add(label2);
        windowFind.add(labelFindIndex);
        windowFind.add(textFieldFind);
        windowFind.setVisible(true);

    }

    //İstenen bir kelimeyi değiştirmek için yeni pencere açılır.
    public void windowReplace() {

        buttonReplace = new JButton("Replace");
        buttonReplace.setBounds(100, 100, 80, 30);
        buttonReplace.addActionListener(this);

        JLabel label1 = new JLabel();
        label1.setText("Old word");
        label1.setBounds(40, 50, 80, 30);

        JLabel label2 = new JLabel();
        label2.setText("New word");
        label2.setBounds(160, 50, 80, 30);

        textFieldReplaceOld = new JTextField();
        textFieldReplaceOld.setBounds(40, 70, 80, 30);

        textFieldReplaceNew = new JTextField(20);
        textFieldReplaceNew.setBounds(160, 70, 80, 30);

        windowReplace = new JFrame("Replace");
        windowReplace.setSize(300, 200);
        windowReplace.setLocationRelativeTo(null);
        windowReplace.setLayout(null);
        windowReplace.add(buttonReplace);
        windowReplace.add(label1);
        windowReplace.add(label2);
        windowReplace.add(textFieldReplaceOld);
        windowReplace.add(textFieldReplaceNew);
        windowReplace.setVisible(true);

    }

    //Find ve Replace pencerelerindeki butonların ne iş yapacağıyla ilgili.
    @Override
    public void actionPerformed(ActionEvent e) {
        //Find'e basıldığında yazılan kelime için find() fonksiyonu çağrılır.
        if (e.getSource() == buttonFind) {
            System.out.println("-> buttonFind clicked");
            System.out.println("Find: " + textFieldFind.getText());

            String word = textFieldFind.getText();
            String oldTxt = gui.textArea.getText();
            labelFindIndex.setText(Functions_other.find(word, oldTxt));
            
        //Replace'e basıldığında girilen kelimeler için findAndReplace() fonksiyonu çağrılır.
        } else if (e.getSource() == buttonReplace) {
            System.out.println("-> buttonReplace clicked");
            System.out.println("Old word: " + textFieldReplaceOld.getText());
            System.out.println("New word: " + textFieldReplaceNew.getText());

            String old_word = textFieldReplaceOld.getText();
            String new_word = textFieldReplaceNew.getText();
            String oldTxt = gui.textArea.getText();
            String newTxt = Functions_other.findAndReplace(old_word, new_word, oldTxt);
            gui.textArea.setText(newTxt);

            windowReplace.setVisible(false);
        }
    }
}
