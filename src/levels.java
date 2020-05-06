
import javax.microedition.lcdui.*;

public class levels extends Canvas implements Runnable {

    private int width; // screen width
    private int height; // screen height
// To hold the current highlighted menu option
    private int levelIdx;
    private int levelItems;
    private int startX;
    private int startY;
    private int itemWidth;
    private int itemHeight;
//menu item
    private int level1 = 0;
    private int level2 = 1;
    private int level3 = 2;
    Thread menuThread;
// Constructor

    public levels() {
// Get Width and Height of Canvas
        width = getWidth();
        //System.out.println(width);
        height = getHeight();
        //System.out.println(height);
// Set Selected Menu Item to the first menu item
        levelIdx = 0;
//number of menu items
        levelItems = 3;
//set the start position of menu
        startX = 25;
        startY = 25;
//item width and height
        itemHeight = 240;
        itemWidth = 64;



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
//g.drawImage(Assets.backImage, 0, 0, 0);
        for (int i = 0; i < levelItems; i++) {
            if (i == levelIdx) {

                g.drawRegion(Assets.levelImage, itemWidth * i, 0, itemWidth, itemHeight, 0, startX + itemWidth * i, startY, 0);
            } else {

                g.drawRegion(Assets.level2Image, itemWidth * i, 0, itemWidth, itemHeight, 0, startX + itemWidth * i, startY, 0);

            }
        }



    }
// Capture Keypresses for Menu Selection

    protected void keyPressed(int code) {
        if (getGameAction(code) == Canvas.LEFT) {
            levelIdx--;
            if (levelIdx == -1) {
                levelIdx = levelItems - 1;
            }


        } else if (getGameAction(code) == Canvas.RIGHT) {
            levelIdx++;
        } else if (getGameAction(code) == Canvas.FIRE) {
            try {
                if (levelIdx == level1) {
                    TimerScreen time = new TimerScreen(TwinsGame.display, 1, Assets.twinsLogo, 5000);

                } else if (levelIdx == level2) {
                    TimerScreen time = new TimerScreen(TwinsGame.display, 2, Assets.twinsLogo, 5000);

                } else if (levelIdx == level3) {
                    TimerScreen time = new TimerScreen(TwinsGame.display, 3, Assets.twinsLogo, 5000);

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        levelIdx = levelIdx % 3;
    }
}
