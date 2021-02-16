import java.util.Observable;
import java.util.Observer;

public class TextSubscriber  implements Observer {
    Observable observable;
    String textContent;


    public void setObservable(Observable ob){
        this.observable = ob;
        ob.addObserver(this);
    }

    public void DisplayTextContent(){
        System.out.println("TextSubscriber : " + textContent);
    }

    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof WebUp){
            WebUp web = (WebUp) o;
            this.textContent = web.getText();
            DisplayTextContent();
        }
    }
}
