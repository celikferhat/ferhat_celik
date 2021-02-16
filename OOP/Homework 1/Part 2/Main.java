public class Main {


    public static void main(String [] args){
        WebUp webUp = new WebUp();
        TextSubscriber s = new TextSubscriber();
        AudioSubscriber a = new AudioSubscriber();
        s.setObservable(webUp);
        a.setObservable(webUp);
        webUp.setContent("Song","Photo","Text");
        webUp.setContent("AnotherSong","AnotherPhoto","AnotherText");

    }

}
