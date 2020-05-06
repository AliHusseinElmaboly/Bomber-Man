
import java.util.TimerTask;

public class Time extends TimerTask {

    bomb Bomb;

    Time(bomb Bomb) {
        this.Bomb = Bomb;

    }

    public void run() {

        Bomb.getBombSprite().setFrameSequence(Bomb.seq_bomb[1]);
        Bomb.setBombstate(Bomb.end);
        //Bomb.getBombSprite().setPosition(64, 64);
        Bomb.getTimer().cancel();

    }
}
