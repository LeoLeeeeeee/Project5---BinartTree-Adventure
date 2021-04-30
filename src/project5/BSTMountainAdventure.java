package project5;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BSTMountainAdventure {


		/**
		 * The main() method of this program. 
		 * @param args array of Strings provided on the command line when the program is started; 
		 * the first string should be the name of the input file containing the list of named colors. 
		 */
		public static void main(String[] args) {
			
			//verify the command line argument exists 
			if (args.length == 0 ) {
				System.err.println("The program needs a file as an argument.\n");
				System.exit(1);
			}
			
			//verify that reststop file exists
			File RSFile = new File(args[0]); 
			if (!RSFile.exists()){
				System.err.println("Error: the file does not exist");
				System.exit(1);
			}
			if (!RSFile.canRead()){
				System.err.println("Error: the file cannot be read.");
				System.exit(1);
			}
			
			//open the file for reading 
			Scanner input = null; 
			
			
			try {
				input = new Scanner (RSFile);
			} 
			catch (FileNotFoundException e) {
				System.err.println("Error: the file is not readable.");
				System.exit(1);
			}
			
			//read the file and save the data in an array of Reststops
			ArrayList<RestStop> list = new ArrayList<RestStop> (); 
			String line = ""; 
			Scanner parseLine = null; 
			String label = "";
			int food = 0;
			int raft = 0; 
			int axe = 0;
			int fallentree = 0;
			int river = 0;
			RestStop current = null;
			
			while (input.hasNextLine()) {
				try { 
					line = input.nextLine(); 
					parseLine = new Scanner(line);
					parseLine.useDelimiter(" "); 
					colorName = parseLine.next();
					hexValue = parseLine.next();
				}
				catch (NoSuchElementException ex ) {
					//caused by an incomplete or miss-formatted line in the input file
					System.err.println(line);
					continue; 	
				}
				try {
					current = new Color (hexValue.trim(), colorName.trim());
					list.add(  current  ); 
				}
				catch (IllegalArgumentException ex ) {
					//ignore this exception and skip to the next line 
				}
			}


	}

}
