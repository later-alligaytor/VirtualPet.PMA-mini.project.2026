package dk.aau.med2.aag.prog.miniver2;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Pet extends PetBehav{
	String name;
	String emotion;
	int hunger;
	int clean;
	String bday;

	ImageIcon imgIdol;
	private ImageIcon imgWalkR;
	private ImageIcon imgWalkL;
	
	private ImageIcon imgIcon;
	private JLabel img;
		
	int i = 0;

	public void changeImg(int i, Pet pet) {
		JLabel imgG = pet.getLabel(pet);
	    Icon oldIcon = imgG.getIcon();
	    if (oldIcon instanceof ImageIcon) {
	        ((ImageIcon) oldIcon).getImage().flush(); //cleans previous icon
	    }

		if (i == 1) {
			imgIcon = imgWalkR;
			pet.changeIcon(imgG,imgIcon);
			}
		else if (i == 2) {
			imgIcon = imgWalkL;
			pet.changeIcon(imgG,imgIcon);
		}
		else {
			imgIcon = imgIdol;
			pet.changeIcon(imgG,imgIcon);
		}
	}
	
	public void changeIcon(JLabel img, ImageIcon imgI) {
		img.setIcon(imgI);
	}
	
	public JLabel getLabel (Pet pet) {
		return img;
	}
	
	public void petFrame(Pet creature, String s, int x, int y) {
//		imgIdol = new ImageIcon("src/"+ s +"/bab.png");
		imgIdol = new ImageIcon(getClass().getResource("/" + s + "/bab.png"));
		imgWalkR = new ImageIcon(getClass().getResource("/" + s + "/babWalk1.gif"));
		imgWalkL = new ImageIcon(getClass().getResource("/" + s + "/babWalk2.gif"));
		imgIcon = imgIdol;
		
	    TextSystem.readSaveFile(this, s);
		
		// create a internal frame
		JFrame petF = new JFrame("Pet");
		petF.setUndecorated(true);
		petF.setBackground(new Color(0, 0, 0, 0));
		petF.setAlwaysOnTop(true);
		petF.setLocation(x, y);
	    MotionPanel inCenterPan = new MotionPanel(petF)
	    {
	        @Override
	        protected void paintComponent(Graphics g) {
	            Graphics2D g2 = (Graphics2D) g.create();

	            // Clear everything (important for transparent windows)
	            g2.setComposite(AlphaComposite.Clear);
	            g2.fillRect(0, 0, getWidth(), getHeight());

	            g2.setComposite(AlphaComposite.SrcOver);
	            super.paintComponent(g2);

	            g2.dispose();
	        }
	    }
	    ;

	    inCenterPan.setOpaque(false); // keep transparency
	    	
	    petF.setContentPane(inCenterPan); //??
	    inCenterPan.setLayout(new BoxLayout(inCenterPan, BoxLayout.Y_AXIS));
	    inCenterPan.setBackground(new Color(0, 0, 0, 0)); //mb same as set op

	    img = new JLabel();
	    changeIcon(img, imgIcon);
	    inCenterPan.add(img);
        img.setAlignmentX(JLabel.CENTER_ALIGNMENT); //makes the image actually centered. 

        petF.pack();
		petF.setVisible(true);
        move(petF, creature);
        statTimer(creature);
	}
}
