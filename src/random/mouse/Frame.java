package random.mouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mousePanel;
	private static String out = "";
	private static PrintWriter pw;
	
	public Frame() throws IOException {
		setTitle("Mouse Movement Random Bit Generator");
		mousePanel = new JPanel();
		mousePanel.setBackground(Color.WHITE);
		add(mousePanel, BorderLayout.CENTER);
		
		pw = new PrintWriter(new File("res/random.txt"));
		EventHandler handler = new EventHandler();
		mousePanel.addMouseMotionListener(handler);
	}
	
	private class EventHandler implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			long time = System.currentTimeMillis();

			int lsb4X = x &= 0x0f;
			int lsb4Y = y &= 0x0f;
			
			String convertX = Integer.toBinaryString(lsb4X);
			String realX = "";
			
			for(int i = convertX.length() - 1; i >= 0; i--) {
				realX += convertX.charAt(i);
			}
			
			realX += Integer.toBinaryString(lsb4Y);
			int realXOR = Integer.parseInt(realX, 2);	
			
			long xOred = realXOR ^ time;
			long low8Bits = xOred & 0xFF;
			
			out = Long.toBinaryString(low8Bits);
			System.out.print(out);
		}
		
	}
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		Frame frame = new Frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		
		pw.println(out);
		pw.close();
	}

}
