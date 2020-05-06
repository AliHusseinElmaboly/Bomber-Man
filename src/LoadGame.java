
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class LoadGame extends GameCanvas implements Runnable {

    private long delay; // To give thread consistency
    public static MainMenu menu;
    private int width;
    private int height;
    private int progress;
    private final String assets = "Assets";

// Constructor and initialization
    public LoadGame() throws Exception {
        super(true);
       // System.out.println(getWidth());
        //System.out.println(getHeight());
        delay = 100;
        width = 16;
        height = 32;
        progress = 0;
        Assets.loadImage = Image.createImage(assets + "/Load.png");
        Assets.progressBarImage = Image.createImage(assets + "/progress.png");
        Assets.StartImage = Image.createImage(assets + "/start.png");

        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        Graphics g = getGraphics();

        try {


            drawLoading(g);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.bombImage = Image.createImage(assets + "/bomb.png");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.bombImage = Image.createImage(assets + "/bomb.png");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);

            Assets.MenuImage = Image.createImage(assets + "/menu.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.MenuSelectedImage = Image.createImage(assets + "/menuSelected.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.equationImage = Image.createImage(assets + "/equation.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.twinsLogo = Image.createImage(assets + "/logo.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.backgroundImage = Image.createImage(assets + "/tiles.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.enemyImage = Image.createImage(assets + "/enemy.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.playerImage = Image.createImage(assets + "/person.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.fireImage = Image.createImage(assets + "/fire.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.floorImage = Image.createImage(assets + "/floor.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }

            drawLoading(g);
            Assets.backImage = Image.createImage(assets + "/background.png");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }
            progress = 14;
            drawLoading(g);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }
            progress = 14;
            drawLoading(g);
            Assets.winImage = Image.createImage(assets + "/win.png");
            Assets.GameOverImage = Image.createImage(assets + "/gameover.png");
            Assets.levelImage = Image.createImage(assets + "/levels.png");
            Assets.level2Image = Image.createImage(assets + "/levels2.png");

            menu = new MainMenu();

            TwinsGame.display.setCurrent(menu);
            menu = null;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void drawLoading(Graphics g) {
        progress++;
        g.setColor(0xFF0000);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(Assets.StartImage, 0, 0, 0);
        g.drawRegion(Assets.progressBarImage, 0, 0, (width * progress), height, 0, 0, 0, 0);
        g.drawImage(Assets.loadImage, 0, 0, 0);

        flushGraphics();
    }
}
