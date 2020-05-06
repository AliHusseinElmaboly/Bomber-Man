
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

public class Player {

    public int x = 0;
    public int y = 0;
    public int centerX;
    public int centerY;
    public String name;
    public int Spirits;
    public String preDirect;
    public String currentDirect;
    public String futureDirect;
    public int width;
    public int height;
    public boolean boolRight;
    public boolean boolleft;
    public boolean boolUp;
    public boolean boolDown;
    public boolean animate;
    public int jewels;
    public boolean shoot;
    public int startX = 48;
    public int startY = 48;
    public int Score;
    public int wrongSelect;
    public static int intsprites = 0;
    public Sprite playerSprite;
    int[][] sequence = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, {20, 21, 22, 23, 24, 25, 26, 27, 28, 29}, {30, 31, 32, 33, 34, 35, 36, 37, 38, 39}};
    point position;
    boolean gameover;
    boolean finish;

    public Player() throws Exception {
        gameover = false;
        finish = false;
        boolRight = boolleft = boolUp = boolDown = animate = true;
        shoot = false;
        name = "twins";
        jewels = 0;
        Spirits = 5;
        Score = 0;
        wrongSelect = 0;
        preDirect = "";
        currentDirect = "";
        futureDirect = "";
        width = 48;
        height = 48;
        centerX = x + width / 2;
        centerY = y + height / 2;
        position = new point(centerX / width, centerY / height);
        // for image

        playerSprite = new Sprite(Assets.playerImage, 48, 48);
        playerSprite.defineCollisionRectangle(18, 18, 12, 12);

        playerSprite.setFrameSequence(sequence[0]);

    }

    public void Direction(TiledLayer tiledBackground) {

        checkCollidebetween_person_blocks(tiledBackground);
        if (animate) {
            if (currentDirect.equals("up") && boolUp) {
                up();
            }
            if (currentDirect.equals("down") && boolDown) {
                down();

            }

            if (currentDirect.equals("left") && boolleft) {
                left();


            }

            if (currentDirect.equals("right") && boolRight) {
                right();


            }

        }


        if (x % 48 == 0 && y % 48 == 0) {
            checkFuture(tiledBackground);
        }



    }

    private void checkFuture(TiledLayer tiledBackground) {

        if (futureDirect.equals(currentDirect)) {
            FramePlayer();
            animate = false;

        } else {
            currentDirect = futureDirect;
            preDirect = futureDirect;
            animate = true;
            Direction(tiledBackground);

        }
    }

    private void right() {
        if (currentDirect.equals(preDirect)) {
            this.x += 4;
        } else {
            if (preDirect.equals("down")) {
                if (this.y % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.y += 4;
                }
            } else if (preDirect.equals("left")) {
                preDirect = currentDirect;

            } else if (preDirect.equals("up")) {
                if (this.y % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.y -= 4;
                }
            } else {
                preDirect = currentDirect;
                this.x += 4;
            }
        }

    }

    private void left() {
        if (currentDirect.equals(preDirect)) {
            this.x -= 4;
        } else {
            if (preDirect.equals("down")) {
                if (this.y % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.y += 4;
                }
            } else if (preDirect.equals("right")) {
                preDirect = currentDirect;

            } else if (preDirect.equals("up")) {
                if (this.y % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.y -= 4;
                }
            } else {
                preDirect = currentDirect;
                this.x -= 4;
            }
        }

    }

    private void down() {
        if (currentDirect.equals(preDirect)) {
            this.y += 4;
        } else {
            if (preDirect.equals("up")) {
                preDirect = currentDirect;
            } else if (preDirect.equals("right")) {
                if (this.x % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.x += 4;
                }

            } else if (preDirect.equals("left")) {
                if (x % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.x -= 4;
                }
            } else {
                preDirect = currentDirect;
                this.y += 4;
            }
        }

    }

    private void up() {
        if (currentDirect.equals(preDirect)) {
            this.y -= 4;
        } else {
            if (preDirect.equals("down")) {
                preDirect = currentDirect;
                ;
            } else if (preDirect.equals("right")) {
                if (this.x % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.x += 4;
                }

            } else if (preDirect.equals("left")) {
                if (this.x % 48 == 0) {
                    preDirect = currentDirect;
                } else {
                    this.x -= 4;
                }
            } else {
                preDirect = currentDirect;
                this.y -= 4;
            }
        }

    }

    public int getSpirits() {
        return Spirits;
    }

    public point getPosition() {
        centerX = x + width / 2;
        centerY = y + height / 2;
        position.cellX = centerX / width;
        position.cellY = centerY / height;

        return position;
    }

    private void checkCollidebetween_person_blocks(TiledLayer tiledBackground) {
        int cellx = getPosition().cellX;
        int celly = getPosition().cellY;
        int localX = 0, localY = 0;
        //if(playerSprite.collidesWith(tiledBackground, false)){
        if (currentDirect.equals("right")) {
            localX = cellx + 1;
            localY = celly;

        } else if (currentDirect.equals("left")) {
            localX = cellx - 1;
            localY = celly;

        } else if (currentDirect.equals("up")) {

            localX = cellx;
            localY = celly - 1;

        } else if (currentDirect.equals("down")) {
            localX = cellx;
            localY = celly + 1;

        }

        if (localX <= tiledBackground.getRows() && localX >= 0 && localY <= tiledBackground.getColumns() && localY >= 0) {
            if (tiledBackground.getCell(localX, localY) == 2 || tiledBackground.getCell(localX, localY) == 4 || tiledBackground.getCell(localX, localY) == 6 || tiledBackground.getCell(localX, localY) == 3) {
                if (x % 48 == 0 && y % 48 == 0) {
                    animate = false;
                    FramePlayer();
                }

            }
            if (tiledBackground.getCell(cellx, celly) >= 17 && tiledBackground.getCell(cellx, celly) <= 21) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
                Spirits -= 1;
                if (Spirits == 0) {
                    gameover = true;
                }

                x = startX;
                y = startY;
                TwinsGameCanvas.MoveCamera = true;


            }
            if (tiledBackground.getCell(cellx, celly) >= 7 && tiledBackground.getCell(cellx, celly) <= 16) {

                if ((tiledBackground.getCell(cellx, celly) - 7) == TwinsGameCanvas.RequiredValue) {
                    tiledBackground.setCell(cellx, celly, 0);
                    finish = true;

                } else {
                    tiledBackground.setCell(cellx, celly, 0);
                    wrongSelect++;
                    if (wrongSelect == 3) {
                        gameover = true;
                    }
                }

            }

        }


    }

    private void FramePlayer() {
        if (currentDirect.equals("right")) {
            playerSprite.setFrame(sequence[0][7]);
        } else if (currentDirect.equals("left")) {
            playerSprite.setFrame(sequence[0][7]);
        } else if (currentDirect.equals("up")) {
            playerSprite.setFrame(sequence[0][7]);

        } else if (currentDirect.equals("down")) {
            playerSprite.setFrame(sequence[0][7]);

        }
    }
}
