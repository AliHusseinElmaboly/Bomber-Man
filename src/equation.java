
import java.util.*;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Sprite;

public class equation {

    private String Firststr;
    private String Secondstr;
    private String Opstr;
    private String Resultstr;
    private int FirstNum;
    private int SecondNum;
    private int Operator;
    private int Result;
    private int equal = 3;
    String equ;
    private int randomDigit;
    private int randomLocation;
    int DigitValue;
    Random r;
    private Sprite equationSprite;
    private int offset = 0;

    public equation() throws Exception {
        r = new Random();

        equationSprite = new Sprite(Assets.equationImage, 16, 16);


    }

    public int getrandomNum() {

        int myNum;
        myNum = ((r.nextInt() & 0x63));
        if (myNum < 10) {
            myNum += 10;
        }

        return myNum;
    }

    public int getrandomOperator() {

        r = new Random();
        int myNum;
        myNum = ((r.nextInt() & 0x2));

        return myNum;
    }

    public void generateEqu() {
        FirstNum = getrandomNum();

        Operator = getrandomOperator();
        SecondNum = getrandomNum();

        if (Operator == 0) {
            Result = FirstNum + SecondNum;

            Opstr = "+";

        } else if (Operator == 1) {
            if (FirstNum > SecondNum) {
                swap();
            }
            Result = FirstNum - SecondNum;
            Opstr = "-";
        } else if (Operator == 2) {
            Result = FirstNum * SecondNum;
            Opstr = "*";
        }
        Firststr = FirstNum + "";
        Secondstr = SecondNum + "";
        Resultstr = Result + "";
        chooseRandomDigit();

    }

    private void swap() {
        int temp = FirstNum;
        FirstNum = SecondNum;
        SecondNum = temp;

    }

    private void updateOffset() {
        offset += 16;
    }

    protected void paint(Graphics g) {

        drawNum(g, FirstNum, 0);
        updateOffset();
        equationSprite.setPosition(offset, 0);
        drawOperator(g, Operator);
        updateOffset();
        equationSprite.setPosition(offset, 0);
        drawNum(g, SecondNum, 1);
        updateOffset();
        equationSprite.setPosition(offset, 0);
        drawOperator(g, equal);
        updateOffset();
        equationSprite.setPosition(offset, 0);
        drawNum(g, Result, 2);


    }

    private void drawNum(Graphics g, int Num, int pos) {
        String number = Num + "";
        int Digit = 0;
        if (pos == randomLocation) {
            for (int i = 0; i < number.length(); i++) {


                if (randomDigit == i) {

                    equationSprite.setFrame(14);
                } else {
                    Digit = Integer.parseInt(number.charAt(i) + "");

                    equationSprite.setFrame(Digit);
                }
                equationSprite.paint(g);
                if (number.length() - i != 1) {
                    updateOffset();
                    equationSprite.setPosition(offset, 0);
                }
            }

        } else {



            for (int i = 0; i < number.length(); i++) {
                Digit = Integer.parseInt(number.charAt(i) + "");
                equationSprite.setFrame(Digit);
                equationSprite.paint(g);
                if (number.length() - i != 1) {
                    updateOffset();
                    equationSprite.setPosition(offset, 0);
                }
            }
        }
    }

    private void chooseRandomDigit() {
        randomLocation = getrandom3();
        int len;
        String num = "";
        if (randomLocation == 0) {

            num = FirstNum + "";

            randomDigit = ((r.nextInt() & 0x1));

        } else if (randomLocation == 1) {
            num = SecondNum + "";
            randomDigit = ((r.nextInt() & 0x1));

        } else if (randomLocation == 2) {
            num += Result + "";
            len = num.length();
            if (len == 1) {
                randomDigit = ((r.nextInt() & 0x0));
            } else if (len == 2) {
                randomDigit = ((r.nextInt() & 0x1));
            } else if (len == 3) {
                randomDigit = ((r.nextInt() & 0x2));
            } else if (len == 4) {
                randomDigit = ((r.nextInt() & 0x3));
            }

        }

        DigitValue = Integer.parseInt(num.charAt(randomDigit) + "");

        generateEquStr(randomLocation, randomDigit);



    }

    public int getDigitValue() {
        return DigitValue;
    }

    private void drawOperator(Graphics g, int operator) {
        if (operator == 0) {
            equationSprite.setFrame(10);

        } else if (operator == 1) {
            equationSprite.setFrame(11);

        } else if (operator == 2) {
            equationSprite.setFrame(12);

        } else if (operator == 3) {
            equationSprite.setFrame(13);

        }
        equationSprite.paint(g);
    }

    private int getrandom3() {

        int myNum;
        myNum = ((r.nextInt() & 0x2));

        return myNum;
    }

    private void generateEquStr(int randomLocation, int randomDigit) {
        switch (randomLocation) {
            case 0: {
                Firststr = Firststr.substring(0, randomDigit) + '?' + Firststr.substring(randomDigit + 1, Firststr.length());

            }
            break;
            case 1: {
                Secondstr = Secondstr.substring(0, randomDigit) + '?' + Secondstr.substring(randomDigit + 1, Secondstr.length());


            }
            break;
            case 2: {

                Resultstr = Resultstr.substring(0, randomDigit) + '?' + Resultstr.substring(randomDigit + 1, Resultstr.length());

            }
            break;


        }

        equ = Firststr + Opstr + Secondstr + "=" + Resultstr;
    }
}
