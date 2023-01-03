
package Command;

import java.util.ArrayList;

public class Receiver {
    String textFile;

    public Receiver(String textFile) {
        this.textFile = textFile;
    }
    
    
    /*
    public String newString;
    public String old;
    */
    
    /*
    write 
    delete 
    findAndReplace
    */
    /*
    public Receiver(String textFile, String newString, String old){
        this.textFile = textFile;
        this.newString = newString;
        this.old = old;
    }
    
    public Receiver(String textFile, String newString){
        this.textFile = textFile;
        this.newString = newString;
    }
    */
    public void write(String newString){
        textFile = textFile + newString;
    }
    
    public void delete(String newString){
        this.textFile = this.textFile.substring(0, (this.textFile.length() - newString.length()));
    }
    
    public void findAndReplace(String newString, String old){
        String txtAsArray[] = textFile.split("(?<=\\.)|(?=\\.)|(?<=;)|(?<=,)|"
                + "(?=,)|(?=;)|(?<=\\n)|(?=\\n)|(?<=\\s)|(?=\\s)|(?<=!)|(?=!)|"
                + "(?<=\\?)|(?=\\?)|(?<=-)|(?<=_)|(?=-)|(?=_)");
        //Aranan kelime i'ninci indexte bulunduğunda i değeri array liste atılır.
        ArrayList<Integer> locations = new ArrayList<>();
        for (int i = 0; i < txtAsArray.length; i++) {
            if (txtAsArray[i].equals(old)) {
                txtAsArray[i] = newString;
                locations.add(i);
            }
        }
        System.out.println("Degistirilen Lokasyonlar: ");
        locations.forEach((location) -> {
            System.out.print((location) + " ");
        });
        System.out.println("");
        //Kelime değiştirilir.
        textFile = String.join("", txtAsArray);
        System.out.print(textFile);
    }
    
    public void reverseFaR(String newString, String old){
        String txtAsArray[] = textFile.split("(?<=\\.)|(?=\\.)|(?<=;)|(?<=,)|"
                + "(?=,)|(?=;)|(?<=\\n)|(?=\\n)|(?<=\\s)|(?=\\s)|(?<=!)|(?=!)|"
                + "(?<=\\?)|(?=\\?)|(?<=-)|(?<=_)|(?=-)|(?=_)");
        //Aranan kelime i'ninci indexte bulunduğunda i değeri array liste atılır.
        ArrayList<Integer> locations = new ArrayList<>();
        for (int i = 0; i < txtAsArray.length; i++) {
            if (txtAsArray[i].equals(newString)) {
                txtAsArray[i] = old;
                locations.add(i);
            }
        }
        System.out.println("Degistirilen Lokasyonlar: ");
        locations.forEach((location) -> {
            System.out.print((location) + " ");
        });
        System.out.println("");
        //Kelime değiştirilir.
        textFile = String.join("", txtAsArray);
        System.out.print(textFile);
    }
}
