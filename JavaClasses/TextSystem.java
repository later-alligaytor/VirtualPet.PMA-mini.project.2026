package dk.aau.med2.aag.prog.miniver2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class TextSystem {
	
	public static void readSaveFile(Pet creature, String s) {
		try {
//			BufferedReader br = new BufferedReader(new FileReader("src/assets/"+ s +".txt"));
			BufferedReader br = new BufferedReader(new FileReader("saves/"+ s +".txt"));
//			BufferedReader br = new BufferedReader(getClass().getResource("/assets/" + s + ".txt"));
			creature.name = br.readLine();
//			creature.age = Integer.parseInt(br.readLine());
			creature.emotion = br.readLine();
			creature.hunger = Integer.parseInt(br.readLine());
			creature.clean = Integer.parseInt(br.readLine());
			
			String lineTjeck = br.readLine();
			if (lineTjeck != null && !lineTjeck.trim().isEmpty() && !"null".equals(lineTjeck)) { //if the bday line is not empty, contains only spaces, or has "null" written in it
				creature.bday = lineTjeck; //sets bday to line read.
				}
			else {
				LocalDate date = LocalDate.now(); 
				creature.bday = date.toString(); //sets bday to cur date
				}
			br.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public static void saveSaveFile(Pet creature, String s) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("saves/"+ s +".txt"));
			bw.write(""+creature.name);
			bw.newLine();
//			bw.write(""+creature.age);
//			bw.newLine();
			bw.write(""+creature.emotion);
			bw.newLine();
			bw.write(""+creature.hunger);
			bw.newLine();
			bw.write(""+creature.clean);
			bw.newLine();
			bw.write(""+creature.bday);
			bw.close();
		}
	catch(Exception e) {
	}
	}
}
