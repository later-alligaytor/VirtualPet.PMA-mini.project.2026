package dk.aau.med2.aag.prog.miniver2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class OverviewFrame extends JFrame {
	private String shunger, sclean;
	
	public void openOverviewFrame(Pet p1, Pet p2) {
		this.setDefaultCloseOperation(OverviewFrame.DISPOSE_ON_CLOSE); // Close operation when the window is closed, disposes only this frame
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocation(400, 100);

		
		JPanel centerPan = new JPanel();
		JTabbedPane tabPanel = new JTabbedPane();

		MotionPanel page1 = new MotionPanel(this);
		page1.setLayout(new FlowLayout(FlowLayout.LEFT));
		page1.setPreferredSize(new Dimension(480,260));// 
		page1.add(pageLayoutPan(p1));
		MotionPanel page2 = new MotionPanel(this);
		page2.setLayout(new FlowLayout(FlowLayout.LEFT));
		page2.setPreferredSize(new Dimension(480,260));// 
		page2.add(pageLayoutPan(p2));
		
		tabPanel.addTab(p1.name, page1);
		tabPanel.addTab(p2.name, page2);

		this.getContentPane().add(centerPan);
		centerPan.add(tabPanel);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel pageLayoutPan(Pet p) {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		JLabel img = new JLabel();
		img.setIcon(p.imgIdol);
		panel1.add(img);
		
		JPanel info = new JPanel();
		info.setLayout(new GridLayout(0,2));
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		
//		updateStats(p);
//		Integer age = p.age;
		LocalDate dateBefore = LocalDate.parse(p.bday);
		LocalDate dateAfter = LocalDate.now();;
		Long days = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		long days2;
		Long years = ChronoUnit.YEARS.between(dateBefore, dateAfter);
		String dateText1;
		
//		sage = age.toString();
		
		Integer hunger = p.hunger;
		shunger = hunger.toString();
		JLabel hun = new JLabel();
		hun.setText("Hunger: " + shunger);
		
		Integer clean = p.clean;
		sclean = clean.toString();
		JLabel cle = new JLabel();
		cle.setText("Hygiene: " + sclean);
		
		
		if (days >= 365 && years == 1) {
			days2 = days - 365;
			dateText1 = years + " year " + days2 + " days";
			}
		else if (years > 1) {
			days2 = days - (365 * 2);
			dateText1 = years + " years " + days2 + " days";
			}
		else {
			dateText1 = days.toString() + " days";
		}
		
		JButton butt1 = new JButton("Feed");
		JButton butt2 = new JButton("Clean");
		Dimension bd = new Dimension(80,30);
		butt1.setPreferredSize(bd);
		butt2.setPreferredSize(bd);
		
		butt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        p.hunger = 5;
		        shunger = "5";
		        hun.setText("Hunger: " + shunger);
				}
		});
		butt2.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        p.clean = 5;
		        sclean = "5";
		        cle.setText("Hygiene: " + sclean);
		    }
		});
		
		JLabel emo = new JLabel();
		emo.setPreferredSize(new Dimension(110,50));
		emo.setText("Name: " + p.name);
		
		JPanel b1 = new JPanel();
		b1.add(butt1);
		JPanel b2 = new JPanel();
		b2.add(butt2);
		
		info.add(emo);
		info.add(new JLabel());
		info.add(new JLabel("Age: " + dateText1));
		info.add(new JLabel());
		info.add(new JLabel("Feeling: " + p.emotion));
		info.add(new JLabel());
		info.add(hun);
		info.add(b1);
		info.add(cle);
		info.add(b2);

		panel1.add(info);
		return panel1;
	}
}
