
import java.util.TimerTask;


public class fireTime extends TimerTask {

    TwinsGameCanvas canvas1;

    fireTime(TwinsGameCanvas canvas1) {
        this.canvas1 = canvas1;
    }

    public void run() {
       // System.out.println("restore");

        //Bomb.getBombSprite().setPosition(64, 64);
        canvas1.restore = true;

        //canvas1.getTimer().cancel();




    }
}
