package dk.aau.med2.aag.prog.miniver2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class PetBehav {
	private int xDis, yDis;
	private int i = 0;
	
	void move(JFrame f, Pet pet) {
		Random r= new Random();
		int delay = r.nextInt(10000 - 4000) + 4000;
        int twens = 90; // milliseconds
        Timer timerMove;
        Timer timerMove2;
        int moveDis = 50;
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets screen dimension. 
    	int maxX = screenSize.width - f.getWidth(); //variables that are useful tools for later.
    	int maxY = screenSize.height - f.getHeight();

        timerMove = new Timer(delay, null);
        timerMove2 = new Timer(twens, null);
        
        timerMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eMove) {
            	xDis = r.nextInt(4 + 4) - 4;
            	yDis = r.nextInt(4 + 4) - 4;
            	timerMove2.start();
            	timerMove.stop();
            	if (xDis < 0) {
                	pet.changeImg(2, pet);
            	}
            	else {
                	pet.changeImg(1, pet);
            	}
            }
        });
        timerMove2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eMent) {
            	
                if (i < moveDis) {
                	int xp = f.getX();
                	int yp = f.getY();
            		xp = xp + xDis; //x location + movement speed.
            		yp = yp + yDis;

                	xp = Math.max(0, Math.min(xp, maxX)); //prevents frame from moving outside screen
                	yp = Math.max(0, Math.min(yp, maxY)); //prevents frame from moving outside screen

                	f.setLocation(xp, yp);
                	
        			xp = f.getX();
        			yp = f.getY();
                	i++;
                } else {
                	timerMove2.stop();
                	int newDelay = r.nextInt(5000 - 2000) + 2000;
                	timerMove.setDelay(newDelay);
                	timerMove.start();
                	i = 0;
                	pet.changeImg(0, pet);
                }
            }
        });
                
        timerMove.start();
	}
	void statTimer(Pet pet) {
		int delay = 360000; //360000 = 6 min
		Random r= new Random();
        Timer statTimer; 

        statTimer = new Timer(delay, null);
        
        statTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eMove) {
            	String nextEmotion;
            	int i = r.nextInt(5 - 1) + 1;
            	if (pet.hunger == 0 && pet.clean == 0) {
            		nextEmotion = "Not good";
            		pet.emotion = nextEmotion;
            		return;
            	}
            	if (i == 1) {
            		int e = r.nextInt(3 - 1) + 1;
            		if (e == 1) {
            			nextEmotion = "Happy";
            		}
            		else if (e == 2) {
            			nextEmotion = "Calm";
            		}
            		else if (e == 3) {
            			nextEmotion = "Sleepy";
            		}
            		else if (e == 4) {
            			nextEmotion = "Smad";
            		}
            		else {
            			nextEmotion = pet.emotion;
            		}
            		pet.emotion = nextEmotion;
            	}
            	else if (i == 2 && pet.hunger > 0) {
            		pet.hunger--;
            	}
            	else if (i == 3 && pet.clean > 0) {
            		pet.clean--;
            	}
            	else {
            	}
            }
        });
                
        statTimer.start();
	}
}
