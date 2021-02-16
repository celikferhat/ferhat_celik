import java.util.Observable;

public class WebUp extends Observable {
    private String audio;
    private String photograph;
    private String text;



    public void contentChanged(){
        setChanged();
        notifyObservers();
    }

    public void setContent(String audio , String photograph , String text){
        this.audio = audio;
        this.photograph = photograph;
        this.text = text;
        contentChanged();
    }



    public String getAudio() {
        return audio;
    }

    public String getPhotograph() {
        return photograph;
    }

    public String getText() {
        return text;
    }

}
