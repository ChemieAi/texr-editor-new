package TextEditor;


import java.util.*;

public class Functions_other {

    GUI gui;

    public Functions_other(GUI gui) {
        this.gui = gui;
    }

    /*  Aranan bir kelimenin indexini bulma fonksiyonu.
    
        "bir, iki" yazısında eğer "iki"yi ararsak indexi 3 olacak.
            0: "bir"
            1: ","
            2: " "
            3: "iki"
     */
    static String find(String kelime, String txtFile) {
        //Aşağıdaki noktalamalarla kelimeler ayrılır.
        String txtAsArray[] = txtFile.split("(?<=\\.)|(?=\\.)|(?<=;)|(?<=,)|"
                + "(?=,)|(?=;)|(?<=\\n)|(?=\\n)|(?<=\\s)|(?=\\s)|(?<=!)|(?=!)|"
                + "(?<=\\?)|(?=\\?)|(?<=-)|(?<=_)|(?=-)|(?=_)");
        //Aranan kelime i'ninci indexte bulunduğunda i değeri array liste atılır.
        ArrayList<Integer> locations = new ArrayList<>();
        for (int i = 0; i < txtAsArray.length; i++) {
            if (txtAsArray[i].equals(kelime)) {
                locations.add(i);
            }
        }
        System.out.println("Bulunan Lokasyonlar: ");

        String locationTxt = "";
        for (Integer location : locations) {
            locationTxt += (location.toString() + " ");
        }
        System.out.println(locationTxt + "\n");

        return locationTxt;
    }

    //Br kelimeyi başka bir kelimeyle değiştirme fonksiyonu.
    static String findAndReplace(String kelime, String yeniKelime, String txtFile) {
        //Aşağıdaki noktalamalarla kelimeler ayrılır.
        String txtAsArray[] = txtFile.split("(?<=\\.)|(?=\\.)|(?<=;)|(?<=,)|"
                + "(?=,)|(?=;)|(?<=\\n)|(?=\\n)|(?<=\\s)|(?=\\s)|(?<=!)|(?=!)|"
                + "(?<=\\?)|(?=\\?)|(?<=-)|(?<=_)|(?=-)|(?=_)");
        //Aranan kelime i'ninci indexte bulunduğunda i değeri array liste atılır.
        ArrayList<Integer> locations = new ArrayList<>();
        for (int i = 0; i < txtAsArray.length; i++) {
            if (txtAsArray[i].equals(kelime)) {
                txtAsArray[i] = yeniKelime;
                locations.add(i);
            }
        }
        System.out.println("Degistirilen Lokasyonlar: ");
        locations.forEach((location) -> {
            System.out.print((location) + " ");
        });
        System.out.println("");
        //Kelime değiştirilir.
        txtFile = String.join("", txtAsArray);
        System.out.print(txtFile);
        return txtFile;
    }

    //Yazım hatası kontrol fonksiyonu.
    static String typoCheck(String txtFile, String[] dict) {
        System.out.println("Typo Check:");
        //Aşağıdaki noktalamalarla kelimeler ayrılır.
        String txtAsArray[] = txtFile.split("(?<=\\.)|(?=\\.)|(?<=;)|(?<=,)|"
                + "(?=,)|(?=;)|(?<=\\n)|(?=\\n)|(?<=\\s)|(?=\\s)|(?<=!)|(?=!)|"
                + "(?<=\\?)|(?=\\?)|(?<=-)|(?<=_)|(?=-)|(?=_)");

        //Sözlükteki kelimelerle yazılan kelimeler kıyaslanır.
        for (int i = 0; i < txtAsArray.length; i++) {
            Boolean varMi = false;
            for (String string1 : dict) {
                if (txtAsArray[i].toLowerCase().equals(string1)) {
                    varMi = true;
                    break;
                }
            }
            if (!varMi) {

                for (int j = 0; j < txtAsArray[i].length() - 1; j++) {
                    StringBuilder temp = new StringBuilder(txtAsArray[i]);
                    char tempChar = temp.charAt(j + 1);
                    temp.setCharAt(j + 1, temp.charAt(j));
                    temp.setCharAt(j, tempChar);
                    String tempString = temp.toString();
                    for (String string1 : dict) {
                        if (tempString.equals(string1)) {
                            varMi = true;
                            txtAsArray[i] = tempString;
                            break;
                        }
                    }
                    if (varMi) {
                        break;
                    }
                }
            }
        }
        //Kelimeler değiştirilir.
        txtFile = String.join("", txtAsArray);

        System.out.println(txtFile + "\n");

        return txtFile;

    }

}
