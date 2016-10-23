package random.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class HardwareRNG {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		PrintWriter pw = new PrintWriter(new File("res/random2.txt"));
		
		int numOfTimes = 1000000 / 160;
		
		for(int i = 0; i < numOfTimes; i++) {
			pw.print(getRandomNumber());
		}
		
		pw.close();
	}

	public static String getRandomNumber() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// Getting Pixel Bits
		Timer timer = new Timer();
		Timer timer1 = new Timer();
		
		timer.start();
		timer1.start();
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/entropy.jpg"));
		} catch (IOException e) {
			System.out.println("An exception was thrown");
		}
		
		//Generation generation = new Generation();
		List<Integer> list = new ArrayList<Integer>();
		
		timer.stop();
		timer1.stop();
		
		int elapsed = (int) (timer.elapsedTime() % 10);
		int elapsed1 = (int) (timer1.elapsedTime() % 10);
		
		
		for(int i = elapsed; i < image.getWidth(); ) {
			for(int j = elapsed1; j < image.getHeight(); ) {
				
				if(i > image.getWidth() - 1 || j > image.getHeight() - 1) {
					break;
				}
				
				timer.start();
				timer1.start();
				
				//String color = generation.getComponent();
				//Color c = new Color(image.getRGB(i, j));
				
//				switch(color) {
//				case "Red":
//					list.add(c.getRed());
//					break;
//				case "Green":
//					list.add(c.getGreen());
//					break;
//				case "Blue":
//					list.add(c.getBlue());
//					break;
//				}
				
				timer.stop();
				timer1.stop();
				
				int elapse = (int) (timer.elapsedTime() % 10);
				int elapse1 = (int) (timer1.elapsedTime() % 10);
				
				i += elapse;
				j += elapse1;

			}
		}

		// Getting Least Significant Bit Stream

		String bits = "";
		List<String> theList = new ArrayList<String>();

		for(int i = 0; i < list.size(); i++) {
			String num = Integer.toBinaryString(list.get(i));
			String leastSignificantBit = Character.toString(num.charAt(num.length() - 1));
			
			bits += leastSignificantBit;
			
			if(bits.length() == 4) {
				theList.add(bits);
				bits = "";
			}
		}

		// Von Neumann Algorithm for Biased Coin

		String bits2 = "";
		
		for(int i = 0; i < theList.size(); i++) {
			String bit = "";
			//String fourDigitInput = theList.get(i);
//			
//			switch(fourDigitInput) {
//			case "0001":
//				bit = "00";
//				break;
//			case "0010":
//				bit = "10";
//				break;
//			case "0011":
//				bit = "0";
//				break;
//			case "0100":
//				bit = "01";
//				break;
//			case "0101":
//				bit = "00";
//				break;
//			case "0110":
//				bit = "01";
//				break;
//			case "0111":
//				bit = "01";
//				break;
//			case "1000":
//				bit = "11";
//				break;
//			case "1001":
//				bit = "10";
//				break;
//			case "1010":
//				bit = "11";
//				break;
//			case "1011":
//				bit = "11";
//				break;
//			case "1100":
//				bit = "1";
//				break;
//			case "1101":
//				bit = "00";
//				break;
//			case "1110":
//				bit = "10";
//			}

			bits2 += bit;
		}

		// Getting Von Neumann Bit Stream Output

		if(bits2.length() < 192) {
			return "";
		}
		
		String stream = (bits2.substring(0, 192));

		// Hashing the Von Neumann Bit Stream Output

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(stream.getBytes("UTF-8"));
		
		byte[] digest = md.digest();
		String result = "";
		
		BigInteger big = new BigInteger(1, digest);
		result = big.toString(2);
		
//		for(int i = 0; i < digest.length; i++) {
//			result += Integer.toBinaryString(digest[i]);
//		}
		
		return result;
	}
}
