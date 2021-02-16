import java.util.Observable;
import java.util.Observer;

public class AudioSubscriber  implements Observer {
    Observable observable;
    String audioContent;


    public void setObservable(Observable ob){
        this.observable = ob;
        ob.addObserver(this);
    }

    public void ListenAudioContent(){
        System.out.println("AudioSubscriber : " + audioContent);
    }

    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof WebUp){
            WebUp web = (WebUp) o;
            this.audioContent = web.getAudio();
           ListenAudioContent(); // For test
        }
    }
}
