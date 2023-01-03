package TextEditor;


import java.io.*;
import java.util.*;

public class Function_Dictionary {

    String dict[];

    public Function_Dictionary(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            //String dict[] = new String[87315];
            /*
            BURADA ONCE WORDS.TXT DOSYASINDAKI KELIMELERI SET'E AETIP SONRADAN
            ARRAYIN ICINE ATIYORIZ. BUNU YAPMAMIZIN SEBEBI WORDS.TXT DOSYASI ICINDE
            TEKRAR EDEN DEGER VARSA ONLARDAN KURTULMAK. ARRAYE CEVIRME SEBEBIMIZ DE 
            ICINDEKI DEGERLER ULUSMA HIZINI ARTTIRMAK
             */
            Set setA = new HashSet();
            while (myReader.hasNext()) {
                setA.add(myReader.nextLine());
            }
            myReader.close();

            Iterator<String> iterator = setA.iterator();
            dict = new String[setA.size()];
            int index = 0;
            while (iterator.hasNext()) {
                dict[index] = iterator.next();
                index++;

            }
            //System.out.println(setA.size());
            Arrays.sort(dict);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("Dosya bulunamadi.");
        }
    }

    public String[] getDict() {
        return dict;
    }

    public void setDict(String[] dict) {
        this.dict = dict;
    }
}
