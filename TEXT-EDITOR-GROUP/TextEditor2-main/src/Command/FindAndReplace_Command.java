
package Command;

public class FindAndReplace_Command implements Command {
    Receiver text;
    String newString;
    String old;
    
    public FindAndReplace_Command(Receiver text){
        this.text = text;
    }
    
    @Override
    public void execute() {
        text.findAndReplace(newString, old);
    }

    @Override
    public void unexecute() {
        text.reverseFaR(old, newString);
    }

    @Override
    public void redo() {
        System.out.println("You cant redo findAndReplace");
    }
}
