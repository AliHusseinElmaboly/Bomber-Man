
import javax.microedition.lcdui.*;

public class MainMenu extends Canvas implements Runnable {

    private int width; // screen width
    private int height; // screen height
// To hold the current highlighted menu option
    private int menuIdx;
    private int menuItems;
    private int startX;
    private int startY;
    private int itemWidth;
    private int itemHeight;
//menu item
    private int newGame = 0;
    private int highScore = 1;
    private int option = 2;
    private int Sound = 3;
    private int exit = 4;
//for sound
    private boolean soundOn;
// Menu Thread
    Thread menuThread;
// Constructor
    public MainMenu() {
// Get Width and Height of Canvas
        width = getWidth();
       // System.out.println(width);
        height = getHeight();
       // System.out.println(height);
// Set Selected Menu Item to the first menu item
        menuIdx = 0;
//number of menu items
        menuItems = 5;
//set the start position of menu
        startX = 10;
        startY = 120;
//item width and height
        itemHeight = 32;
        itemWidth = 160;

        soundOn = true;

// Create Thread and Start
        menuThread = new Thread(this);
        menuThread.start();
    }
// Simple Run -- Should be modified for better performance/efficiency
    public void run() {
        while (true) {
            repaint();
        }
    }
// Paint Main Menu
    public void paint(Graphics g) {
        g.setColor(0x00FFFFFF);
        g.fillRect(0, 0, width, height);
        g.drawImage(Assets.backImage, 0, 0, 0);
        for (int i = 0; i < menuItems; i++) {
            if (i == menuIdx) {

                if (i == 3) {
                    if (soundOn) {
                        g.drawRegion(Assets.MenuSelectedImage, 0, itemHeight * i, itemWidth, itemHeight, 0, startX, startY + itemHeight * i, 0);

                    } else {
                        g.drawRegion(Assets.MenuSelectedImage, 0, itemHeight * 5, itemWidth, itemHeight, 0, startX, startY + itemHeight * i, 0);

                    }

                } else {
                    g.drawRegion(Assets.MenuSelectedImage, 0, itemHeight * i, itemWidth, itemHeight, 0, startX, startY + itemHeight * i, 0);
                }
            } else {
                if (i == 3) {
                    if (soundOn) {
                        g.drawRegion(Assets.MenuImage, 0, itemHeight * i, itemWidth, itemHeight, 0, startX, startY + itemHeight * i, 0);

                    } else {
                        g.drawRegion(Assets.MenuImage, 0, itemHeight * 5, itemWidth, itemHeight, 0, startX, startY + itemHeight * i, 0);

                    }

                } else {
                    g.drawRegion(Assets.MenuImage, 0, itemHeight * i, itemWidth, itemHeight, 0, startX, startY + itemHeight * i, 0);
                }

            }
        }



    }
// Capture Keypresses for Menu Selection
    protected void keyPressed(int code) {
        if (getGameAction(code) == Canvas.UP) {
            menuIdx--;
            if (menuIdx == -1) {
                menuIdx = menuItems - 1;
            }


        } else if (getGameAction(code) == Canvas.DOWN) {
            menuIdx++;
        } else if (getGameAction(code) == Canvas.FIRE) {
            try {
                System.out.println(menuIdx+"-----");
                if (menuIdx == newGame) {
                    levels l = new levels();
                    TwinsGame.display.setCurrent(l);

                    //TwinsGameCanvas   gameCanvas = new TwinsGameCanvas(2);
                    // gameCanvas.start();

                    //TwinsGame.display.setCurrent(gameCanvas);
                    // gameCanvas=null;
                    //new TimerScreen(TwinsGame.display, gameCanvas, Assets.twinsLogo,10000);

                } else if (menuIdx == highScore) {
                } else if (menuIdx == option) {
                } else if (menuIdx == Sound) {
                    soundOn = !soundOn;

                } else if (menuIdx == exit) {
                    TwinsGame game = new TwinsGame();
                    game.destroyApp(false);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        menuIdx = menuIdx % 5;
    }
}
