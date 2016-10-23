package random.image;

public class Generation {

	public String getComponent() {
		long time = System.currentTimeMillis();
		String component = "";
		
		if(time % 3 == 0) {
			component = "Red";
		} else if(time % 3 == 1) {
			component = "Green";
		} else {
			component = "Blue";
		}
		
		return component;
	}
	
	public static void main(String[] args) {

	}

}
