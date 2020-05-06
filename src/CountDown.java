
import java.util.TimerTask;

class CountDown extends TimerTask {

    private final TimerScreen timerScreen;

    CountDown(TimerScreen timerScreen) {
        this.timerScreen = timerScreen;
    }

    public void run() {
        TimerScreen.access(this.timerScreen);
    }
}
