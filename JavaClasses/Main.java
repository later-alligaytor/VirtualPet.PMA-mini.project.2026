package dk.aau.med2.aag.prog.miniver2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	static Pet p1 = new Pet();
	static Pet p2 = new Pet();
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				start();
			}
		});
	}
	
	public static void start(){
		JButton butt1 = new JButton("X");
		JButton butt2 = new JButton("Menu");
		
		JFrame frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
			     close();
			   }
			  });
		frame.setPreferredSize(new Dimension(200,100));// 
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setLocation(100, 100);
				
		JPanel panel1 = new JPanel();
		MotionPanel centerPanel = new MotionPanel(frame);
		
		frame.getContentPane().add(centerPanel);
		centerPanel.add(panel1);
				
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		
		panel1.add(butt1);
		panel1.add(butt2);
		
		butt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				}
		});
		butt2.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        OverviewFrame m = new OverviewFrame();
		        m.openOverviewFrame(p1,p2);
		    }
		});
				
		frame.pack();
		frame.setVisible(true);
		

        p1.petFrame(p1, "pet1", 300, 500);
        p2.petFrame(p2, "pet2", 1000, 500);
	}
	
	public static void close() {
		TextSystem.saveSaveFile(p1, "pet1");
		TextSystem.saveSaveFile(p2, "pet2");
		System.exit(0);
	}
}
