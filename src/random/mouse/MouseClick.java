package random.mouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseClick extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel clickPanel;
	private long previous;
	private PrintWriter pw;
	
	public MouseClick() throws IOException {
		setTitle("Mouse Click Timing Random Bit Generator");
		clickPanel = new JPanel();
		clickPanel.setBackground(Color.WHITE);
		add(clickPanel, BorderLayout.CENTER);
		
		pw = new PrintWriter(new File("res/random2.txt"));
		EventHandler handler = new EventHandler();
		clickPanel.addMouseListener(handler);
		
	}
	
	private class EventHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			long elapsedTime = System.nanoTime() - previous;
			long time = System.currentTimeMillis();
			
			previous = elapsedTime;
			long lsb4 = elapsedTime &= 0x0f;
			
			long xOr = lsb4 ^ time;
			long low8Bits = xOr & 0xFF;
			
			String output = Long.toBinaryString(low8Bits);
			
			System.out.print(output);
			pw.println(output);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		MouseClick click = new MouseClick();
		click.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		click.setSize(800, 600);
		click.setVisible(true);
	}
}
