package random.keyboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFiles {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\Neil\\Downloads\\random\\random18.txt"));
		
		String value = bf.readLine();
		int zeroCounter = 0;
		int oneCounter = 0;
		
		while(value != null) {
			for(int i = 0; i < value.length(); i++) {
				if(value.charAt(i) == '0') {
					zeroCounter++;
				} else if(value.charAt(i) == '1') {
					oneCounter++;
				}
			}
			value = bf.readLine();
		}
		
		double average = (double) (zeroCounter + oneCounter) / 2;
		double percentError = 0;
		int sum = zeroCounter + oneCounter;
		
		if(oneCounter > zeroCounter) {
			percentError = (oneCounter - average) / average;
		} else {
			percentError = (zeroCounter - average) / average;
		}
		
		System.out.println(zeroCounter + " " + oneCounter);
		System.out.println(percentError);
		System.out.println(sum);
		bf.close();
	}
}
