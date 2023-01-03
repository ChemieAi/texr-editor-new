
package Command;

public class Write_Command implements Command {
    Receiver text;
    String newString;
    
    public Write_Command(Receiver text, String newString){
        this.text = text;
        this.newString = newString;
    }
    
    @Override
    public void execute() {
        text.write(newString);
    }

    @Override
    public void unexecute() {
        text.delete(newString);
    }

    @Override
    public void redo() {
        text.write(newString);
    }
}
