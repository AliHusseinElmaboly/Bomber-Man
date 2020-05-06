
import java.util.Timer;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class TwinsGameCanvas extends GameCanvas implements Runnable {

    private boolean isPlay; // Game Loop runs when isPlay is true
    public static long delay; // To give thread consistency
// for player
    private Player player;
    private int width; // To hold screen width
    private int height; // To hold screen height
    private int offset;
    private int level1 = 1;
    private int level2 = 2;
    private int level3 = 3;
    private bomb[] bomb1;
    private bomb[] bomb2;
    private bomb[] bomb3;
    private int currentLevel;
    private Timer timer;
    private Num number;
    private enemy[] enemys;
    private int enemiesNum;
    private int scnX, scnY; // To hold screen starting viewpoint
// Sprites to be used
//game Assets
    bomb Bomb;
    int BombX;
    int BombY;
    int prevX = 1;
    int prevY = 1;
    Sprite floorSprite;
    Thread t;
    String equation;
    static int RequiredValue;
// Layer Manager
    private LayerManager layerManager;
// TiledLayer
    private TiledLayer tiledBackground;
    static boolean MoveCamera;
    boolean restore;
    private int rPointX;
    private int rPointY;
    private Vector locations;

// Constructor and initialization
    public TwinsGameCanvas(int level, String equ, int value) throws Exception {

        super(true);
        currentLevel = level;
        equation = equ;
        RequiredValue = value;
        width = getWidth();
        height = getHeight();
        floorSprite = new Sprite(Assets.floorImage);
        floorSprite.setPosition(0, 48);
        restore = false;
        offset = 48;
        locations = new Vector();
        timer = new Timer();
        delay = 50;
        MoveCamera = false;
        scnX = 0;
        scnY = 0;
        number = new Num();

// Load Images to Sprites




        if (level == level1) {
            enemiesNum = 5;
            enemys = new enemy[enemiesNum];
            enemys[0] = new enemy(4, 1, "down");
            enemys[1] = new enemy(3, 9, "right");
            enemys[2] = new enemy(1, 7, "right");
            enemys[3] = new enemy(8, 1, "down");
            enemys[4] = new enemy(5, 3, "down");

            int[][] map = {
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 1, 1, 0, 0, 0, 3, 3, 0, 3, 2},
                {2, 1, 1, 0, 0, 4, 3, 3, 0, 3, 2},
                {2, 0, 0, 0, 4, 0, 4, 0, 0, 0, 2},
                {2, 0, 0, 4, 0, 0, 0, 4, 0, 0, 2},
                {2, 0, 4, 0, 0, 4, 0, 0, 4, 0, 2},
                {2, 3, 0, 4, 3, 4, 3, 4, 0, 3, 2},
                {2, 0, 3, 0, 0, 3, 0, 0, 0, 0, 2},
                {2, 0, 3, 4, 0, 4, 0, 4, 0, 0, 2},
                {2, 0, 3, 0, 0, 3, 0, 0, 0, 3, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            tiledBackground = initBackground(map);
            bomb1 = new bomb[1];

        } else if (level == level2) {

            enemiesNum = 7;
            enemys = new enemy[enemiesNum];
            enemys[0] = new enemy(9, 1, "down");
            enemys[1] = new enemy(7, 1, "right");
            enemys[2] = new enemy(1, 5, "right");
            enemys[3] = new enemy(1, 8, "down");
            enemys[4] = new enemy(6, 4, "down");
            enemys[5] = new enemy(8, 8, "down");
            enemys[6] = new enemy(5, 3, "down");

            int[][] map = {
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 1, 1, 3, 0, 0, 0, 0, 3, 0, 2},
                {2, 1, 1, 4, 0, 4, 0, 4, 0, 0, 2},
                {2, 0, 0, 0, 3, 0, 0, 0, 0, 3, 2},
                {2, 0, 0, 4, 0, 4, 0, 4, 0, 0, 2},
                {2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 2},
                {2, 0, 0, 4, 0, 4, 0, 4, 3, 0, 2},
                {2, 3, 3, 0, 3, 3, 0, 3, 3, 0, 2},
                {2, 0, 0, 4, 0, 4, 0, 4, 0, 0, 2},
                {2, 3, 0, 0, 0, 0, 0, 0, 3, 3, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            tiledBackground = initBackground(map);
            bomb2 = new bomb[2];

        } else if (level == level3) {
            enemiesNum = 10;
            enemys = new enemy[enemiesNum];
            enemys[0] = new enemy(1, 5, "down");
            enemys[1] = new enemy(4, 4, "right");
            enemys[2] = new enemy(4, 7, "right");
            enemys[3] = new enemy(6, 7, "down");
            enemys[4] = new enemy(1, 8, "down");
            enemys[5] = new enemy(1, 7, "down");
            enemys[6] = new enemy(9, 3, "down");
            enemys[7] = new enemy(8, 3, "down");
            enemys[8] = new enemy(8, 1, "down");
            enemys[9] = new enemy(1, 9, "down");
            int[][] map = {
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 1, 1, 0, 0, 0, 3, 0, 0, 3, 2},
                {2, 1, 1, 0, 0, 4, 3, 3, 0, 3, 2},
                {2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 4, 0, 3, 0, 4, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 3, 0, 4, 3, 0, 3, 4, 0, 0, 2},
                {2, 0, 3, 0, 0, 3, 0, 0, 0, 0, 2},
                {2, 0, 3, 4, 0, 4, 0, 4, 0, 0, 2},
                {2, 0, 0, 3, 0, 3, 0, 0, 0, 3, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            tiledBackground = initBackground(map);
            bomb3 = new bomb[3];

        }

        player = new Player();
        player.x = player.startX;
        player.y = player.startY;
//backgroundImage = Image.createImage("/background.png");
//backgroundSprite = new Sprite(backgroundImage);

        layerManager = new LayerManager();

        layerManager.append(player.playerSprite);


        for (int i = 0; i < enemys.length; i++) {
            if (enemys[i] != null) {
                layerManager.append(enemys[i].enemySprite);
            }
        }

//layerManager.append(backgroundSprite);
        layerManager.append(tiledBackground);
        layerManager.append(floorSprite);

    }
// Automatically start thread for game loop
    public void start() {
        isPlay = true;
        t = new Thread(this);
        t.start();
    }

    public void stop() {
        isPlay = false;
    }
// Main Game Loop
    public void run() {
        Graphics g = getGraphics();
        while (isPlay == true) {
            update();

            input();
            drawScreen(g);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ie) {
            }
        }

        while (player.gameover) {
            drawGameOver(g);
            inputGameOver();
        }
        while (player.finish) {
            drawWinner(g);
            inputWinner();
        }
    }
// Method to Handle User Inputs
    private void input() {
        int keyStates = getKeyStates();
//playerSprite.setFrame(0);
// Left

        if ((keyStates & LEFT_PRESSED) != 0) {

            player.playerSprite.setFrameSequence(player.sequence[1]);

            playerDirection("left");



        }
// Right
        if ((keyStates & RIGHT_PRESSED) != 0) {

            player.playerSprite.setFrameSequence(player.sequence[2]);
            playerDirection("right");


        }
// Up
        if ((keyStates & UP_PRESSED) != 0) {

            player.playerSprite.setFrameSequence(player.sequence[3]);
            playerDirection("up");


        }
// Down
        if ((keyStates & DOWN_PRESSED) != 0) {

            player.playerSprite.setFrameSequence(player.sequence[0]);
            playerDirection("down");



        }
        if ((keyStates & FIRE_PRESSED) != 0) {
            if (player.x % player.width == 0 && player.y % player.height == 0) {
                BombX = player.getPosition().cellX * 48;
                BombY = player.getPosition().cellY * 48;
                // BombX=(player.x/)*;
                //BombY=(player.y/)*;
                if (!(prevX == BombX && prevY == BombY)) {
                    prevX = BombX;
                    prevY = BombY;
                    Bomb = new bomb(BombX, BombY);
                    switch (currentLevel) {
                        case 1:
                            fire(bomb1, player.getPosition().cellX, player.getPosition().cellY);
                            break;
                        case 2:
                            fire(bomb2, player.getPosition().cellX, player.getPosition().cellY);
                            break;
                        case 3:
                            fire(bomb3, player.getPosition().cellX, player.getPosition().cellY);
                            break;

                    }

                }

            }


        }

    }

    private void drawWinner(Graphics g) {
        g.setColor(0xFF0000);
        g.fillRect(0, 0, getWidth(), getHeight());
        layerManager.paint(g, 0, 0);
        g.drawImage(Assets.winImage, 20, 90, 0);

        flushGraphics();
    }

    private void drawGameOver(Graphics g) {
        g.setColor(0xFF0000);
        g.fillRect(0, 0, getWidth(), getHeight());
        layerManager.paint(g, 0, 0);
        g.drawImage(Assets.GameOverImage, 20, 90, 0);


        flushGraphics();
    }
// Method to Display Graphics
    private void drawScreen(Graphics g) {
        g.setColor(0xFF0000);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(0x0000ff);
        DrawTitle(g);
// updating player sprite position
        player.playerSprite.setPosition(player.x, player.y);
// updating enemies sprite position
        for (int i = 0; i < enemys.length; i++) {
            if (enemys[i] != null) {
                enemys[i].enemySprite.setPosition(enemys[i].x, enemys[i].y);
            }
        }
// display all layers

        layerManager.setViewWindow(scnX, scnY, width, height - offset);
        layerManager.paint(g, 0, offset);



        flushGraphics();
    }

    private TiledLayer initBackground(int[][] map) throws Exception {

        TiledLayer tiledLayer = new TiledLayer(11, 12, Assets.backgroundImage, 48, 48);


        int column = 0, row = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                column = j;
                row = i;
                tiledLayer.setCell(column, row, map[i][j]);
            }
        }
//tiledLayer.setCell(1,3,map[1]);
//tiledLayer.setCell(2,2,map[1]);

        return tiledLayer;
    }

    private void update() {
        player.playerSprite.nextFrame();
        //update player
        if (!MoveCamera) {
            updatePlayer();
        }
        updateEnemy();

//update TiledBackground
        if (restore) {

            restore = false;
            restoreTiledBackground();
        }

// update directions to be available to use

// check collision between person And enemy
        for (int u = 0; u < enemys.length; u++) {
            if (enemys[u] != null) {
                enemys[u].enemySprite.nextFrame();

                if (player.playerSprite.collidesWith(enemys[u].enemySprite, false)) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ie) {
                    }
                    player.Spirits -= 1;
                    if (player.Spirits == 0) {
                        player.gameover = true;
                    }
                    player.x = player.startX;
                    player.y = player.startY;

                    MoveCamera = true;
                    //  playerSprite.setPosition(0, 0);

                }

                int xx = enemys[u].getPosition().cellX;
                int yy = enemys[u].getPosition().cellY;
                if (tiledBackground.getCell(xx, yy) >= 17 && tiledBackground.getCell(xx, yy) <= 21) {
                    player.Score += 100;
                    layerManager.remove(enemys[u].enemySprite);
                    enemys[u] = null;
                }
            }
        }
        // update Camera
        if (MoveCamera) {
            if (scnX > 0) {
                scnX -= 4;
            }
            if (scnY > 0) {
                scnY -= 4;
            }
            if (scnX == 0 && scnY == 0) {
                MoveCamera = false;
            }
        }



        switch (currentLevel) {
            case 1:
                updateBomb(bomb1);
                break;
            case 2:
                updateBomb(bomb2);
                break;
            case 3:
                updateBomb(bomb3);
                break;
        }


        if (player.gameover || player.finish) {
            isPlay = false;
        }



    }

    private void updatePlayer() {

        if (player.currentDirect.equals("left")) {
            if (player.x - 1 >= 0) {
                player.Direction(tiledBackground);
            }

            if (scnX - 1 > 0 && player.x < (tiledBackground.getWidth() - width / 2) && player.animate) {
                scnX -= 4;
            }


        }
// Right
        if (player.currentDirect.equals("right")) {

            if (player.x + 48 < tiledBackground.getWidth()) {
                player.Direction(tiledBackground);
            }



            if (scnX + 1 + width < tiledBackground.getWidth() && player.x > (width / 2) && player.animate) {
                scnX += 4;
            }


        }
// Up
        if (player.currentDirect.equals("up")) {
            if (player.y - 1 >= 0) {
                player.Direction(tiledBackground);
            }


            if (scnY - 1 > 0 && player.y < (tiledBackground.getHeight() - height / 2) && player.animate) {
                scnY -= 4;
            }

        }
// Down
        if (player.currentDirect.equals("down")) {

            if (player.y + 48 < tiledBackground.getHeight()) {
                player.Direction(tiledBackground);
            }



            if (scnY + 1 + height < tiledBackground.getHeight() && player.y > (height / 2) && player.animate) {
                scnY += 4;
            }


        }
    }

    private void fire(bomb[] bomb2, int cellX, int cellY) {
        int i;
        for (i = 0; i < bomb2.length; i++) {
            if (bomb2[i] == null || !bomb2[i].isAlive) {
                bomb2[i] = Bomb;
                tiledBackground.setCell(cellX, cellY, 6);
                break;
            }

        }
        if (i != bomb2.length) {
            bomb2[i].showNotify();

            layerManager.insert(bomb2[i].getBombSprite(), 1);
        }

    }

    /**
    private void updateBomb(bomb[] bomb) {
    for(int i=0;i<bomb.length;i++){

    if(bomb[i]!=null&&bomb[i].isAlive){


    bomb[i].getBombSprite().nextFrame();


    if(bomb[i].getState()==bomb[i].end){

    if(bomb[i].getBombSprite().getFrame()==bomb[i].Bomb_frameNum)

    {
    bomb[i].isAlive = false;
    layerManager.remove(bomb[i].getBombSprite());


    prevX=1;
    prevY=1;
    }

    }

    }

    }
    for(int q=0;q<bomb.length;q++){
    if(bomb[q]!=null&&!bomb[q].isAlive){
    firePressed=true;
    break;

    }
    }

    }

     * */
    private void updateBomb(bomb[] bomb) {
        for (int i = 0; i < bomb.length; i++) {

            if (bomb[i] != null && bomb[i].isAlive) {


                bomb[i].getBombSprite().nextFrame();


                if (bomb[i].getState() == bomb[i].end) {




                    if (bomb[i].getBombSprite().getFrame() == bomb[i].Bomb_frameNum) {

                        bomb[i].isAlive = false;
                        layerManager.remove(bomb[i].getBombSprite());
                        rPointX = bomb[i].getX() / 48;
                        rPointY = bomb[i].getY() / 48;
                        tiledBackground.setCell(rPointX, rPointY, 0);

                        fire(rPointX, rPointY);
                        //System.out.println("restore1");
                        timer.schedule(new fireTime(this), 500);
                        prevX = 1;
                        prevY = 1;
                    }

                }

            }

        }


    }

    private void playerDirection(String direct) {
        player.futureDirect = direct;
        // if(player.currentDirect.equals("right")||(player.x%==0&&player.y%==0))
        if ((player.x % 48 == 0 && player.y % 48 == 0)) {
            player.currentDirect = direct;
        }

        player.animate = true;

    }

    private void updateEnemy() {
        for (int i = 0; i < enemys.length; i++) {
            if (enemys[i] != null) {
                enemys[i].StillMove_2(tiledBackground);
            }
        }
    }

    private void fire(int cellx, int celly) {
        check(cellx, celly, 21);

        //check(cellx-1,celly,3,22);

        // check(cellx+1,celly,3,22);
        check(cellx + 1, celly, 18);
        check(cellx - 1, celly, 17);
        // check(cellx,celly+1,3,23);
        // check(cellx,celly-1,3,23);
        check(cellx, celly + 1, 20);
        check(cellx, celly - 1, 19);

    }

    private void restoreTiledBackground() {

        for (int i = 0; i < locations.size(); i++) {
            point p = (point) locations.elementAt(i);
            if ((p.cellX == 1 || p.cellX == 2) && (p.cellY == 1 || p.cellY == 2)) {
                tiledBackground.setCell(p.cellX, p.cellY, 1);
            } else {
                tiledBackground.setCell(p.cellX, p.cellY, 0);
            }

        }

        locations.removeAllElements();
    }

    private void check(int x, int y, int tiledFire) {
        int cellType;
        if (x >= 0 && y >= 0 && x < 10 && y < 10) {

            cellType = tiledBackground.getCell(x, y);
            switch (cellType) {
                case 2: {
                    //do nothing
                }
                break;
                case 4: {
                    //do nothing
                }
                break;
                case 7: {
                }
                break;
                case 8: {
                }
                break;
                case 9: {
                }
                break;
                case 10: {
                }
                break;
                case 11: {
                }
                break;
                case 12: {
                }
                break;
                case 13: {
                }
                break;
                case 14: {
                }
                break;
                case 15: {
                }
                break;
                case 16: {
                }
                break;
                case 3: {
                    int num = number.getrandomNum();
                    if (num != 10) {
                        tiledBackground.setCell(x, y, 7 + num);
                    } else {
                        tiledBackground.setCell(x, y, 0);
                    }
                }
                break;
                default: {
                    tiledBackground.setCell(x, y, tiledFire);
                    point p = new point(x, y);
                    locations.addElement(p);

                }
            }

        }

    }

    public Timer getTimer() {
        return timer;
    }

    private void DrawTitle(Graphics g) {


        g.drawString("Score: " + player.Score, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.drawString("Spirits: " + player.getSpirits(), getWidth() - 100, 0, Graphics.TOP | Graphics.LEFT);
        g.drawString(equation, 0, 20, Graphics.TOP | Graphics.LEFT);
    }

    private void inputGameOver() {
        int keyStates = getKeyStates();

        if ((keyStates & FIRE_PRESSED) != 0) {
            MainMenu menu = new MainMenu();

            TwinsGame.display.setCurrent(menu);
            menu = null;

            try {
                t.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }


        }

    }

    private void inputWinner() {
        int keyStates = getKeyStates();

        if ((keyStates & FIRE_PRESSED) != 0) {
            levels l = new levels();

            TwinsGame.display.setCurrent(l);
            l = null;

            try {
                t.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }


        }

    }
}
