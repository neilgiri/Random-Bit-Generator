package random.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;


public class ConcatImage {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		PrintWriter pw = new PrintWriter(new File("res/random.txt"));
		pw.println(getRandomness());
		pw.close();
	}
	
	public static String getRandomness() throws IOException, NoSuchAlgorithmException {
		BufferedImage image = ImageIO.read(new File("res/shortPhone.jpg"));
		String concat = "";
		
		for(int i = 0; i < image.getWidth(); i++) {
			for(int j = 0; j < image.getHeight(); j++) {
				concat += Integer.toBinaryString(image.getRGB(i, j));
			}
		}
		
		//MessageDigest md = MessageDigest.getInstance("SHA-1");
		
		return concat;
	}

}
