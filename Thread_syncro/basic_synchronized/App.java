// This code is for learning usage of synchronized keyword.
// This is important for multiple threads trying to access a shared resource

public class App {

	private int count = 0;
	
	// synchronized ensures taking the Mutex from the method.
	// There is only 1 Mutex for each method. 
	public synchronized void increment() {
		count++;
	}
	
	public static void main(String[] args) {
		
		App app = new App();
		app.doWork();
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				for(int i=0; i<10000; i++) {
					increment(); //count = count + 1; (Taking the current count value then adding 1 abit later. Need use atomic variable)
				}
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				for(int i=0; i<10000; i++) {
					increment();
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("count is: " + count);
	}
}
