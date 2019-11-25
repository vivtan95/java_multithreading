import java.util.Scanner;

class Processor extends Thread {
	
	// Volatile is used to prevent threads from caching.
	// Other systems might treat the running variable as fixed since the variable 
	// is never changed in the while loop within that thread.
	private volatile boolean running = true;
	
	public void run() {
		
		while(running) {
			System.out.println("Hello");
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void shutdown() {
		running = false;
	}
	
}

public class App {

	public static void main(String[] args) {
		
		Processor proc1 = new Processor();
		
		proc1.start();
		
		System.out.println("Press Enter to stop ...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();
		
	}
	
}
