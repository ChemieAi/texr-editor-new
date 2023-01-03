
package Decorator;

public abstract class Text_Decorator implements Text {
    protected Text decoratedText;
    
    public Text_Decorator(Text text) {
        this.decoratedText = text;
    }
    
    @Override
    public void decorate(){
        decoratedText.decorate();
    }
}
