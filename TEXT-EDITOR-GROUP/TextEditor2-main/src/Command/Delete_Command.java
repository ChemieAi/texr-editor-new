
package Command;

public class Delete_Command implements Command{
    Receiver text;
    String toDelete;
    
    public Delete_Command(Receiver text, String toDelete){
        this.text = text;
        this.toDelete = toDelete;
    }
    
    @Override
    public void execute() {
        text.delete(toDelete);
    }

    @Override
    public void unexecute() {
        text.write(toDelete);
    }

    @Override
    public void redo() {
        System.out.println("You cant redo delete.");
    }
}
