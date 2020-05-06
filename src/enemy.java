
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;

/**
 * @author twins
 */
public class enemy {

    public int x;
    public int y;
    private int step = 2;
    public int RandomDirect;
    public int width;
    public int height;
    public String EnemyDirection;
    private Random ii;
    public Sprite enemySprite;
    int[][] sequence = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
        {13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25},
        {26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38},
        {39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51}};
    private Image enemyImage;
    private Vector directions;
    int left, right, up, down;
    int w = 4;
    point position;

    public enemy() throws Exception {
        ii = new Random();
        x = 100;
        y = 100;
        width = 48;
        height = 48;

        EnemyDirection = "down";
        directions = new Vector();
        AddDirections();

        enemySprite = new Sprite(Assets.enemyImage, 48, 48);
        enemySprite.setFrameSequence(sequence[0]);

    }

    public void AddDirections() {
        directions.removeAllElements();
        directions.addElement("up");
        directions.addElement("down");
        directions.addElement("right");
        directions.addElement("left");


    }

    public String GetRandomDirection() {

        String result = "";
        Random i = new Random();

        int target = 0, t = 0;
        t = directions.size();
        if (FoundDirect(EnemyDirection)) {
            result += EnemyDirection;
        } else {
            if (t == 4) {
                target = Math.abs(i.nextInt() & 0x3);
            } else if (t == 3) {
                target = Math.abs(i.nextInt() & 0x2);
            } else if (t == 2) {
                target = Math.abs(i.nextInt() & 0x1);
            } else if (t == 1) {
                target = Math.abs(i.nextInt() & 0);
            }
            EnemyDirection = (String) directions.elementAt(target);
            result += (String) directions.elementAt(target);
        }
        if (result.equals("down")) {
            enemySprite.setFrameSequence(sequence[0]);
        } else if (result.equals("left")) {
            enemySprite.setFrameSequence(sequence[1]);
        } else if (result.equals("right")) {
            enemySprite.setFrameSequence(sequence[2]);
        } else if (result.equals("up")) {
            enemySprite.setFrameSequence(sequence[3]);
        }

        return result;
    }

    public boolean FoundDirect(String direct) {

        boolean cond = false;

        for (int i = 0; i < directions.size(); i++) {
            if (directions.elementAt(i).equals(direct)) {
                cond = true;
            }
        }

        return cond;

    }

    public enemy(int cellx, int celly, String Direction) throws IOException {
        ii = new Random();
        width = 48;
        height = 48;
        this.x = cellx * width;
        this.y = celly * height;
        left = 3;
        up = 0;
        down = 1;
        right = 2;
        position = new point(x / width, y / height);

        directions = new Vector();
        Random s = new Random();



        RandomDirect = Math.abs(s.nextInt() & 0xf);
        AddDirections();
        height = 48;
        enemyImage = Assets.enemyImage;
        enemySprite = new Sprite(enemyImage, 48, 48);
        enemySprite.defineCollisionRectangle(18, 18, 12, 12);
        enemySprite.setPosition(x, y);
        EnemyDirection = Direction;
    }

    public void EnemyDirection() {

        if (EnemyDirection.equals("up")) {
            up();
        } else if (EnemyDirection.equals("down")) {
            down();
        } else if (EnemyDirection.equals("left")) {
            left();
        } else if (EnemyDirection.equals("right")) {
            right();
        }



    }

    public void up() {

        y -= step;


    }

    public void down() {
        y += step;


    }

    public void right() {

        x += step;

    }

    public void left() {

        x -= step;

    }

    public void CheckLimits(TiledLayer Background) {

        if (x / width - 1 <= 0 || (x / width - 1 > 0 && Background.getCell(x / width - 1, y / height) == 2 || Background.getCell(x / width - 1, y / height) == 6 || Background.getCell(x / width - 1, y / height) == 3 || Background.getCell(x / width - 1, y / height) == 1 || Background.getCell(x / width - 1, y / height) == 4)) {
            directions.removeElementAt(left);
        }
        if (x / width + 2 >= Background.getRows() || (x / width + 1 < Background.getRows() && Background.getCell(x / width + 1, y / height) == 2 || Background.getCell(x / width + 1, y / height) == 6 || Background.getCell(x / width + 1, y / height) == 3 || Background.getCell(x / width + 1, y / height) == 1 || Background.getCell(x / width + 1, y / height) == 4)) {
            directions.removeElementAt(right);
        }
        if (y / height + 2 >= Background.getRows() || (y / height + 1 < Background.getRows() && Background.getCell(x / width, y / height + 1) == 2 || Background.getCell(x / width, y / height + 1) == 6 || Background.getCell(x / width, y / height + 1) == 3 || Background.getCell(x / width, y / height + 1) == 1 || Background.getCell(x / width, y / height + 1) == 4)) {
            directions.removeElementAt(down);
        }
        if (y / width - 1 <= 0 || (y / height - 1 > 0 && Background.getCell(x / width, y / height - 1) == 2 || Background.getCell(x / width, y / height - 1) == 6 || Background.getCell(x / width, y / height - 1) == 3 || Background.getCell(x / width, y / height - 1) == 1 || Background.getCell(x / width, y / height - 1) == 4)) {
            directions.removeElementAt(up);
        }
        // check right

    }

    public point getPosition() {
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        position.cellX = centerX / width;
        position.cellY = centerY / height;

        return position;
    }

    public void DetermineDirection(TiledLayer Background, int algorithm) {
        CheckLimits(Background);
        if (algorithm == 1) {
            EnemyDirection = GetRandomDirection();
        } else {
            EnemyDirection = GetRandomDirection_2();
        }
        /** for(int i=0;i<directions.size();i++){
        System.out.print(directions.elementAt(i)+" ");
        }**/
        EnemyDirection();
        AddDirections();
    }

    public void StillMove(TiledLayer Background) {
        if (x % width == 0 && y % height == 0) {

            step = Math.abs(ii.nextInt() & 0x2);
            if (step == 0) {
                step = 2;
            } else if (step == 1) {
                step = 1;
            } else {
                step = 4;
            }
            DetermineDirection(Background, 1);


        } else {
            EnemyDirection();
        }
    }

    public void StillMove_2(TiledLayer Background) {
        if (x % width == 0 && y % height == 0) {

            step = Math.abs(ii.nextInt() & 0x2);
            if (step == 0) {
                step = 2;
            } else if (step == 1) {
                step = 1;
            } else {
                step = 4;
            }

            DetermineDirection(Background, 2);


        } else {
            EnemyDirection();
        }
    }

    private void removeOpposite(String EnemyDirection) {
        int i = 0;
        if (EnemyDirection.equals("down")) {

            for (i = 0; i < directions.size(); i++) {
                if (directions.elementAt(i).equals("up")) {
                    directions.removeElementAt(i);
                }
            }
        } else if (EnemyDirection.equals("up")) {

            for (i = 0; i < directions.size(); i++) {
                if (directions.elementAt(i).equals("down")) {
                    directions.removeElementAt(i);
                }
            }

        } else if (EnemyDirection.equals("right")) {

            for (i = 0; i < directions.size(); i++) {
                if (directions.elementAt(i).equals("left")) {
                    directions.removeElementAt(i);
                }
            }

        } else if (EnemyDirection.equals("left")) {


            for (i = 0; i < directions.size(); i++) {
                if (directions.elementAt(i).equals("right")) {
                    directions.removeElementAt(i);
                }
            }

        }
    }

    private String GetRandomDirection_2() {



        String result = "";
        Random i = new Random();

        int target = 0, t = 0;
        if (directions.size() > 1) {
            removeOpposite(EnemyDirection);
        }
        t = directions.size();

        if (t == 4) {
            target = Math.abs(i.nextInt() & 0x3);
        } else if (t == 3) {
            target = Math.abs(i.nextInt() & 0x2);
        } else if (t == 2) {
            target = Math.abs(i.nextInt() & 0x1);
        } else if (t == 1) {
            target = Math.abs(i.nextInt() & 0);
        }

        EnemyDirection = (String) directions.elementAt(target);
        result += (String) directions.elementAt(target);
        // for preDirect so as no make acycle;
        if (result.equals("down")) {
            enemySprite.setFrameSequence(sequence[0]);
        } else if (result.equals("left")) {
            enemySprite.setFrameSequence(sequence[1]);
        } else if (result.equals("right")) {
            enemySprite.setFrameSequence(sequence[2]);
        } else if (result.equals("up")) {
            enemySprite.setFrameSequence(sequence[3]);
        }

        return result;
    }

    public String Opposite(String enemyDirect) {
        String result;
        if (enemyDirect.equals("up")) {
            result = "down";
        } else if (enemyDirect.equals("down")) {
            result = "up";
        } else if (enemyDirect.equals("right")) {
            result = "left";
        } else {
            result = "right";
        }
        return result;
    }
}
