package bomberman.Levels;

public class NextLevel {
    public static boolean wait;
    public static long waiting_time;
    public static void waitToLevelUp() {
        long now = System.currentTimeMillis();
        if (now - waiting_time > 3000) {
            new Level1();
            wait = false;
        }
    }
}
