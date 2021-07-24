import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("создание потоков");
        List<Callable<Integer>> listThread = new ArrayList<>();
        listThread.add(new MyThread("Поток 1", 1));
        listThread.add(new MyThread("Поток 2", 3));
        listThread.add(new MyThread("Поток 3", 2));
        listThread.add(new MyThread("Поток 4", 1));

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("\nЗапустим все\n");
        invokeAllThreads(threadPool, listThread);
        System.out.println("\nЗапукс потоков и результат самого быстрого\n");
        int i = threadPool.invokeAny(listThread);
        System.out.println("результат " + i);


        threadPool.shutdown();
    }

    private static void invokeAllThreads(ExecutorService threadPool, List<Callable<Integer>> listThread)
            throws InterruptedException, ExecutionException {
        List<Future<Integer>> futureList = threadPool.invokeAll(listThread);
        for (int i = 0; i < futureList.size(); i++) {
            System.out.println("резултат потока " + listThread.get(i) + " = " + futureList.get(i).get());
        }
    }

}
