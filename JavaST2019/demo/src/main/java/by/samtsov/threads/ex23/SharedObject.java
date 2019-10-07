package by.samtsov.threads.ex23;

public class SharedObject {

    private volatile int count = 0;

    public void increamentCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}