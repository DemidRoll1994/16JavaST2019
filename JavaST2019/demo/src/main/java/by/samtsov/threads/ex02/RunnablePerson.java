package by.samtsov.threads.ex02;

public class RunnablePerson extends Person implements Runnable{
    public RunnablePerson(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getPersonName()+" Hello World!!! "+i);
        }
    }
}
