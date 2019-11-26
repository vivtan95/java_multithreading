import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("Starting id: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed id: " + id);
	}
}

public class App {
	
	public static void main(String[] args) {
		// ExecutorService is a framework provided by the JDK which simplifies the execution of tasks in asynchronous mode
		// Set fixed number of threads n. Recycling these threads is more efficient as there are a lot of overheads in starting threads.
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i=0; i<10; i++) {
			executor.submit(new Processor(i));
		}
		
		executor.shutdown();
		
		System.out.println("All tasks submitted.");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed!");
		
	}
}
