

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TwinsGame extends MIDlet {

    public static Display display;
    public static LoadGame LoadingGame;

    public void startApp() {
        try {
            display = Display.getDisplay(this);

//display = Display.getDisplay(this);
            LoadingGame = new LoadGame();
            display.setCurrent(LoadingGame);

//Image splashLogo = Image.createImage("/background.png");
//display.setCurrent(gameCanvas);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Display getDisplay() {
        return display;
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        exit();
    }

    public void exit() {
        System.gc();
        destroyApp(false);
        notifyDestroyed();
    }
}
