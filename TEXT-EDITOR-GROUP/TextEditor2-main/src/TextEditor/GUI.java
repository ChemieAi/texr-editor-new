
package TextEditor;

import Factory.Blue_Factory;
import Factory.Pink_Factory;
import Factory.White_Factory;
import Factory.Dark_Factory;
import Decorator.Arial_ConcreteClass;
import Decorator.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;

//Arayüzü oluşturmak için.
public final class GUI implements ActionListener {

    public JFrame window;
    //TEXT AREA
    public JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    //TOP MENU BAR
    JMenuBar menuBar;
    JMenu menuFile, menuFind, menuEdit, menuUndoRedo, 
            menuControl, menuFormat, menuTheme, menuFont;
    //FILE MENU
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    //OTHER MENUS
    JMenuItem iFind, iEdit, iUndo, iRedo, iControl, iWrap;
    //COLOR MENU
    JMenuItem iColor0, iColor1, iColor2, iColor3;
    //FONT MENU
    JMenu iFont0, iFont1, iFont2;
    JMenuItem iNormal0, iBold0, iItalic0, iBoldItalic0;
    JMenuItem iNormal1, iBold1, iItalic1, iBoldItalic1;
    JMenuItem iNormal2, iBold2, iItalic2, iBoldItalic2;

    Functions_GUI functionGUI = new Functions_GUI(this);
    Functions_other functionOther = new Functions_other(this);
    Blue_Factory blueTheme = new Blue_Factory(this);
    Dark_Factory darkTheme = new Dark_Factory(this);
    Pink_Factory pinkTheme = new Pink_Factory(this);
    White_Factory whiteTheme = new White_Factory(this);
    
    Text arial = new Arial_ConcreteClass(this);
    Text timesNewRoman = new TimesNewRoman_ConcreteClass(this);
    Text comicSansMS = new ComicSansMS_ConcreteClass(this);
    Text boldArial = new Bold_ConcreteDecorator(this, arial);
    Text boldTimesNewRoman = new Bold_ConcreteDecorator(this, timesNewRoman);
    Text boldComicSansMS = new Bold_ConcreteDecorator(this, comicSansMS);
    Text italicArial = new Italic_ConcreteDecorator(this, arial);
    Text italicTimesNewRoman = new Italic_ConcreteDecorator(this, timesNewRoman);
    Text italicComicSansMS = new Italic_ConcreteDecorator(this, comicSansMS);
    Text boldItalicArial = new Italic_ConcreteDecorator(this, boldArial);
    Text boldItalicTimesNewRoman = new Italic_ConcreteDecorator(this, boldTimesNewRoman);
    Text boldItalicComicSansMS = new Italic_ConcreteDecorator(this, boldComicSansMS);
    
    UndoManager um = new UndoManager();

    //Text Editor arayüzü için tüm fonksiyonların bir arada çağırıldığı tek bir fonksiyon.
    public GUI() {

        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createFindMenu();
        createEditMenu();
        createUndoRedoMenu();
        createControlMenu();
        createFormatMenu();
        createColorMenu();
        createFontMenu();

        //arialFont.changeFont();
        whiteTheme.changeTheme();
        window.setVisible(true);
    }

    //Büyük çerçeveyi oluşturur.
    public void createWindow() {

        window = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Yazının yazılacağı bölümü oluşturur.
    public void createTextArea() {

        textArea = new JTextArea();
        //Undo-Redo işlemleri için.

        textArea.getDocument().addUndoableEditListener((UndoableEditEvent e) -> {
            um.addEdit(e.getEdit());
        });
        //Sayfa dolduğunda sağda ve aşağıda scrollbar oluşması için.
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    //Üstte işlemlerin yapılacağı menüyü oluşturur.
    public void createMenuBar() {

        menuBar = new JMenuBar();           //Menu bar'ı açar
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");       //Dosya seçeneği eklenir
        menuBar.add(menuFile);

        menuFind = new JMenu("Find");       //Bir kelimeyi bulma seçeneği eklenir
        menuBar.add(menuFind);

        menuEdit = new JMenu("Edit");       //Bir kelimeyi değiştirme seçeneği eklenir
        menuBar.add(menuEdit);

        menuUndoRedo = new JMenu("Undo/Redo");   //Yazılan harfleri geri-ileri alma seçeneği eklenir
        menuBar.add(menuUndoRedo);

        menuControl = new JMenu("Control"); //Yazım hatası kontrolü seçeneği eklenir
        menuBar.add(menuControl);

        menuFormat = new JMenu("Format");   //Word wrap seçeneği eklenir
        menuBar.add(menuFormat);
        
        menuTheme = new JMenu("Theme");   //Tema değiştirme seçeneği eklenir
        menuBar.add(menuTheme);
        
        menuFont = new JMenu("Font");   //Font deeğiştirme seçeneği eklenir
        menuBar.add(menuFont);
    }

    //Dosya bölümünde bulunacak seçenekleri ekler.
    public void createFileMenu() {

        iNew = new JMenuItem("New");        //Yeni dosya oluşturma işlemi
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");      //Var olan dosyayı açma işlemi
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");      //Yazılan dosyayı (üstüne) kaydetme işlemi
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save as"); //Yazılan dosyayı farklı kaydetme işlemi
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand(("Save as"));
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");      //Çıkış işlemi
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }

    //Aranan bir kelimenin indexini bulma butonunu ekler.
    public void createFindMenu() {

        iFind = new JMenuItem("Find a word");
        iFind.addActionListener(this);
        iFind.setActionCommand("Find a word");
        menuFind.add(iFind);
    }

    //Seçilen bir kelimeyi yeni bir kelimeyle değiştirme butonunu ekler.
    public void createEditMenu() {

        iEdit = new JMenuItem("Replace a word");
        iEdit.addActionListener(this);
        iEdit.setActionCommand("Replace a word");
        menuEdit.add(iEdit);
    }

    //Yazılan harfleri tek tek ileri-geri alma butonunu ekler.
    public void createUndoRedoMenu() {

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuUndoRedo.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuUndoRedo.add(iRedo);
    }

    //Yazım yanlışlarını düzeltme butonunu ekler.
    public void createControlMenu() {

        iControl = new JMenuItem("Fix the typos");
        iControl.addActionListener(this);
        iControl.setActionCommand("Fix the typos");
        menuControl.add(iControl);
    }

    //Kelimelerin sayfa düzeni (Word wrap) butonunu ekler.
    public void createFormatMenu() {

        iWrap = new JMenuItem("Word wrap: OFF");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word wrap");
        menuFormat.add(iWrap);
    }
    
    //Renk menüsünü yaratır
    public void createColorMenu() {
        
        iColor0 = new JMenuItem("White");
        iColor0.addActionListener(this);
        iColor0.setActionCommand("White");
        menuTheme.add(iColor0);
        iColor1 = new JMenuItem("Blue");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("Blue");
        menuTheme.add(iColor1);
        iColor2 = new JMenuItem("Dark");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Dark");
        menuTheme.add(iColor2);
        iColor3 = new JMenuItem("Pink");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Pink");
        menuTheme.add(iColor3);
    }
    
    //Font menüsünü yaratır
    public void createFontMenu() {
        
        iFont0 = new JMenu("Arial");
        //iFont0.addActionListener(this);
        //iFont0.setActionCommand("Arial");
        menuFont.add(iFont0);
        iNormal0 = new JMenuItem("Normal Arial");
        iNormal0.addActionListener(this);
        iNormal0.setActionCommand("Normal Arial");
        iFont0.add(iNormal0);
        iBold0 = new JMenuItem("Bold Arial");
        iBold0.addActionListener(this);
        iBold0.setActionCommand("Bold Arial");
        iFont0.add(iBold0); 
        iItalic0 = new JMenuItem("Italic Arial");
        iItalic0.addActionListener(this);
        iItalic0.setActionCommand("Italic Arial");
        iFont0.add(iItalic0);
        iBoldItalic0 = new JMenuItem("Bold & Italic Arial");
        iBoldItalic0.addActionListener(this);
        iBoldItalic0.setActionCommand("Bold & Italic Arial");
        iFont0.add(iBoldItalic0);
                
        iFont1 = new JMenu("Comic Sans MS");
        //iFont1.addActionListener(this);
        //iFont1.setActionCommand("Comic Sans MS");
        menuFont.add(iFont1);
        iNormal1 = new JMenuItem("Normal Comic Sans MS");
        iNormal1.addActionListener(this);
        iNormal1.setActionCommand("Normal Comic Sans MS");
        iFont1.add(iNormal1);
        iBold1 = new JMenuItem("Bold Comic Sans MS");
        iBold1.addActionListener(this);
        iBold1.setActionCommand("Bold Comic Sans MS");
        iFont1.add(iBold1); 
        iItalic1 = new JMenuItem("Italic Comic Sans MS");
        iItalic1.addActionListener(this);
        iItalic1.setActionCommand("Italic Comic Sans MS");
        iFont1.add(iItalic1);
        iBoldItalic1 = new JMenuItem("Bold & Italic Comic Sans MS");
        iBoldItalic1.addActionListener(this);
        iBoldItalic1.setActionCommand("Bold & Italic Comic Sans MS");
        iFont1.add(iBoldItalic1);
        
        iFont2 = new JMenu("Times New Roman");
        //iFont2.addActionListener(this);
        //iFont2.setActionCommand("Times New Roman");
        menuFont.add(iFont2);
        iNormal2 = new JMenuItem("Normal Times New Roman");
        iNormal2.addActionListener(this);
        iNormal2.setActionCommand("Normal Times New Roman");
        iFont2.add(iNormal2);
        iBold2 = new JMenuItem("Bold Times New Roman");
        iBold2.addActionListener(this);
        iBold2.setActionCommand("Bold Times New Roman");
        iFont2.add(iBold2); 
        iItalic2 = new JMenuItem("Italic Times New Roman");
        iItalic2.addActionListener(this);
        iItalic2.setActionCommand("Italic Times New Roman");
        iFont2.add(iItalic2);
        iBoldItalic2 = new JMenuItem("Bold & Italic Times New Roman");
        iBoldItalic2.addActionListener(this);
        iBoldItalic2.setActionCommand("Bold & Italic Times New Roman");
        iFont2.add(iBoldItalic2);
    }

    //Seçilen butonlara fonksiyonları atar.
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        switch (command) {
            case "New":
                functionGUI.newFile();
                break;
            case "Open":
                functionGUI.openFile();
                break;
            case "Save":
                functionGUI.saveFile();
                break;
            case "Save as":
                functionGUI.saveAsFile();
                break;
            case "Exit":
                functionGUI.exitFile();
                break;
            case "Find a word":
                functionGUI.windowFind();
                break;
            case "Replace a word":
                functionGUI.windowReplace();
                break;
            case "Undo":
                functionGUI.undoTxt();
                break;
            case "Redo":
                functionGUI.redoTxt();
                break;
            case "Fix the typos":
                functionGUI.fixTypos();
                break;
            case "Word wrap":
                functionGUI.wordWrap();
                break;
            case "White":
                whiteTheme.changeTheme();
                break;
            case "Blue":
                blueTheme.changeTheme();
                break; 
            case "Dark":
                darkTheme.changeTheme();
                break;
            case "Pink":
                pinkTheme.changeTheme();
                break;
            case "Normal Arial":
                arial.decorate();
                break;
            case "Normal Comic Sans MS":
                comicSansMS.decorate();
                break;
            case "Normal Times New Roman":
                timesNewRoman.decorate();
                break;
            case "Bold Arial":
                boldArial.decorate();
                break;
            case "Bold Comic Sans MS":
                boldComicSansMS.decorate();
                break;
            case "Bold Times New Roman":
                boldTimesNewRoman.decorate();
                break;
            case "Italic Arial":
                italicArial.decorate();
                break;
            case "Italic Comic Sans MS":
                italicComicSansMS.decorate();
                break;
            case "Italic Times New Roman":
                italicTimesNewRoman.decorate();
                break;
            case "Bold & Italic Arial":
                boldItalicArial.decorate();
                break;
            case "Bold & Italic Comic Sans MS":
                boldItalicComicSansMS.decorate();
                break;
            case "Bold & Italic Times New Roman":
                boldItalicTimesNewRoman.decorate();
                break;
            default:
                break;
        }
    }

}
