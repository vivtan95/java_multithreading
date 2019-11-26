import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//The wrapper class can be changed to '?' and the other Integer classes can be changed to Void if a return result is not needed.
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				if(duration > 2000) {
					throw new IOException("Sleeping for too long.");
				}
				
				System.out.println("Starting ....");
				
				Thread.sleep(duration);
				
				System.out.println("Finished.");
				
				return duration;
			}
		});
		
		executor.shutdown();
		
		
		
		try {
			System.out.println("Result is: " + future.get()); //get is blocking. Will wait for thread associate with this future to be terminated.
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ex = (IOException) e.getCause();
			
			System.out.println(ex.getMessage());
		}
	}
		
	
}
