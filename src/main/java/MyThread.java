import java.util.concurrent.Callable;

public class MyThread extends Thread implements Callable<Integer> {

    final int QUANTITY_OF_MESSAGES = 4;
    int time = 2;

    public MyThread(String name) {
        super(name);
    }

    public MyThread(String name, int time) {
        super(name);
        this.time = time;
    }

    public MyThread() {
        super();
    }

    @Override
    public Integer call() {
        int quantity = 0;

        try {
            while (quantity < QUANTITY_OF_MESSAGES) {
                if (isInterrupted()) {
                    break;
                }
                Thread.sleep(time * 1000);
                quantity++;
                System.out.println("Я поток " + super.getName() + ". Всем привет!");
            }

        } catch (InterruptedException err) {

        } finally {
            System.out.printf("%s - %s завершен\n", Thread.currentThread().getName(), super.getName());
            return quantity;
        }
    }

    @Override
    public String toString() {
        return super.getName();
    }

}
