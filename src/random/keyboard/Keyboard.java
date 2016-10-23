package random.keyboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Keyboard extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel keyPanel;
	private static String out = "";
	private long previous = 0;
	private static PrintWriter pw;
	
	public Keyboard() throws IOException {
		setTitle("Keyboard timings random bit generator");
		keyPanel = new JPanel();
		keyPanel.setBackground(Color.WHITE);
		add(keyPanel, BorderLayout.CENTER);
		
		pw = new PrintWriter(new File("res/random1.txt"));
		EventHandler handler = new EventHandler();
		keyPanel.addKeyListener(handler);
		keyPanel.setFocusable(true);
	}
	
	private class EventHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			long elapsedTime = System.nanoTime() - previous;
			long time = System.currentTimeMillis();
			
			previous = elapsedTime;
			long lsb4 = elapsedTime &= 0x0f;
			
			long xOr = lsb4 ^ time;
			long low8Bits = xOr & 0xFF;
			
			out = Long.toBinaryString(low8Bits);
			System.out.print(out);
		}
		
	}
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		Keyboard keyboard = new Keyboard();
		keyboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		keyboard.setSize(800, 600);
		keyboard.setVisible(true);
		
		//System.out.print(out);
		pw.println(out);
		pw.close();
	}

}
