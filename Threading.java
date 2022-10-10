package KeShe5;

public class Threading extends Thread {

    boolean flag = true;
    Fun bgm = new Fun();
    final Object obj = new Object();

    public Threading(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            bgm.music("KeShe/src/img/Collapsing World(Original Mix).mp3");
        }
    }

    public void pause() {
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
    }

    public void goOn() {
        synchronized (obj) {
            obj.notify();
        }
        flag = true;
    }

}
