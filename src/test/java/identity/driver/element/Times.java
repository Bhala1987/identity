package identity.driver.element;


public class Times {

    public static final long SECOND_PAUSE = 1000;

    public static void waitForMilliSeconds(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
