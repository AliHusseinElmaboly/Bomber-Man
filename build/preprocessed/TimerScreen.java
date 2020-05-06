
import java.util.Timer;
import javax.microedition.lcdui.*;

public class TimerScreen extends Canvas {

    private Display display;
    private TwinsGameCanvas next;
    private Timer timer;
    private Image image;
    private int dismissTime;
    private equation Equ;
    int level;
// Set Fonts
    static final Font lowFont = Font.getFont(Font.FACE_MONOSPACE,
            Font.STYLE_PLAIN, Font.SIZE_SMALL);
    static final Font highFont = Font.getFont(Font.FACE_MONOSPACE,
            Font.STYLE_BOLD, Font.SIZE_MEDIUM);
// Set Color
    static final int lowColor = 0x000000FF; // Not Highlighted
    static final int highColor = 0x00FF0000; // Highlighted
    static final int highBGColor = 0x00CCCCCC; //
    public TimerScreen(Display display, int level, Image image, int dismissTime) throws Exception {
        timer = new Timer();
        this.display = display;
        this.level = level;
        this.image = image;
        this.dismissTime = dismissTime;
        Equ = new equation();
        Equ.generateEqu();

        display.setCurrent(this);

    }

    static void access(TimerScreen splashScreen) {
        splashScreen.dismiss();
    }

    private void dismiss() {
        timer.cancel();
        try {
            next = new TwinsGameCanvas(level, Equ.equ, Equ.DigitValue);
            next.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        display.setCurrent(next);
    }

    protected void keyPressed(int keyCode) {
        dismiss();
    }

    protected void paint(Graphics g) {
        g.setColor(0x00FFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0x00000000);
        g.drawImage(image, getWidth() / 2, getHeight() / 2 - 5, 3);
//Equ.paint(g);

        g.setFont(highFont);

        g.setColor(highColor);
        g.drawString(Equ.equ,
                60, 270, Graphics.TOP | Graphics.LEFT);
        // g.drawString(, 30, 260,Graphics.TOP|Graphics.LEFT);
    }

    protected void pointerPressed(int x, int y) {
        dismiss();
    }

    protected void showNotify() {
        if (dismissTime > 0) {
            timer.schedule(new CountDown(this), dismissTime);
        }
    }
}
