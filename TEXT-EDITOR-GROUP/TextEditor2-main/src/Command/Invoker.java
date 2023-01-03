
package Command;

import java.util.Stack;

public class Invoker {
    private Receiver receiver;
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    public Invoker(){
        //this.receiver = receiver;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }
    
    public void execute(Command cmd){
        undoStack.push(cmd);
        redoStack.clear();
        cmd.execute();
    }
    
    public void unexecute(){
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.unexecute();
            redoStack.push(cmd);
        }else{
            System.err.println("EXECUTION LOG NOT FOUND");
        }
    }
    
    public void redo() {
        Command cmd = redoStack.pop();
        cmd.execute();
        undoStack.push(cmd);
    }
}
