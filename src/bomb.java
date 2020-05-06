
import javax.microedition.lcdui.game.Sprite;
import java.util.Timer;

public class bomb {

    private int x;
    private int y;
    private int width;
    private int height;
    private Sprite bombSprite;
    private Timer timer;
    boolean isAlive;
    int Bomb_frameNum = 3;
    int[][] seq_bomb = {{0, 1, 2, 3, 4, 5, 6, 7, 8}, {9, 10, 11, 12}};
    int state;
    int start = 1;
    int end = 2;

    public bomb(int x, int y) {
        this.x = x;
        this.y = y;
        timer = new Timer();

        width = 48;
        height = 48;

        bombSprite = new Sprite(Assets.bombImage, width, height);
        bombSprite.setPosition(x, y);
        setBombstate(start);



        isAlive = true;


    }

    public int getState() {
        return state;
    }

    public void setBombstate(int state) {

        this.state = state;
        if (state == start) {
            bombSprite.setFrameSequence(seq_bomb[0]);
        } else if (state == end) {
            bombSprite.setFrameSequence(seq_bomb[1]);
        }

    }

    public Sprite getBombSprite() {
        return bombSprite;
    }

    public Timer getTimer() {
        return timer;
    }

    public void showNotify() {
        timer.schedule(new Time(this), 3000);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
