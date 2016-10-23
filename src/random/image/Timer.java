package random.image;

public class Timer {

	private long start;
	private long now;
	
	public void start() {
		start = System.nanoTime();
	}
	
	public void stop() {
		now = System.nanoTime();
	}
	
	public long elapsedTime() {
		long first = (now - start);
		long result = first / 1000;
		return result;
	}
	
	public static void main(String[] args) {
		Timer timer1 = new Timer();
		Timer timer = new Timer();
		timer1.start();
		timer.start();
		timer1.stop();
		timer.stop();
		
		System.out.println(timer1.elapsedTime());
		System.out.println(timer.elapsedTime());
	}

}
