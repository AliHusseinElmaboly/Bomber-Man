
import java.util.Random;

public class Num {

    /**
     * @param args the command line arguments
     */
    String Num = "0123456789      ";
    int rand = 0xf;
    Random r;
    private int randomLocation;

    public Num() {
        r = new Random();

    }

    public int getrandomNum() {

        int result;
        int myNum;
        myNum = ((r.nextInt() & rand));
        rand--;

        if (Num.charAt(myNum) == ' ') {

            String first = Num.substring(0, myNum);
            String Second = Num.substring(myNum + 1, Num.length());
            Num = first + Second;
            return 10;
        }
        result = Integer.parseInt(Num.charAt(myNum) + "");

        String first = Num.substring(0, myNum);
        String Second = Num.substring(myNum + 1, Num.length());
        Num = first + Second;
        return result;
    }
}
