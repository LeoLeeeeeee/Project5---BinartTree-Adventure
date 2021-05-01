package project5;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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


		BSTMountain tree = new BSTMountain();

		while (input.hasNextLine()) {
			String line = ""; 
			Scanner parseLine = null; 
			RestStop current = null;
			String label = "";
			int food = 0;
			int raft = 0; 
			int axe = 0;
			int fallentree = 0;
			int river = 0;
			try { 
				line = input.nextLine(); 
				line = line.trim();
				parseLine = new Scanner(line);
				parseLine.useDelimiter(" "); 
				label = parseLine.next();

				//OList to store supplies and obstacles
				ArrayList<String> OList = new ArrayList<String>();
				while (parseLine.hasNext()) {
					OList.add(parseLine.next());
				}

				//deal with fallen tree, make fallen and tree into one place
				int indexFallen = OList.indexOf("fallen");
				while (indexFallen != -1) {
					if (OList.get(indexFallen+1).equals("tree")) {
						OList.set(indexFallen, "fallen tree");
						OList.remove(indexFallen+1);
					}
					else {
						OList.remove(indexFallen);
					}
					indexFallen = OList.indexOf("fallen");
				}

				int indexF = OList.indexOf("fallen tree");
				int indexR = OList.indexOf("river");
				int indexObstacle =-1;
				if (indexF == -1 && indexR == -1) {
					indexObstacle = OList.size();
				}

				else if (indexF != -1 && indexR != -1) {
					indexObstacle = Math.min(indexF, indexR);

				}

				else if (indexF==-1) {
					indexObstacle = indexR;
				}

				else {
					indexObstacle = indexF;
				}

				for (int i = 0; i<indexObstacle;i++) {
					if (OList.get(i).equals("food")) {
						food++;
					}
					else if (OList.get(i).equals("raft")) {
						raft++;
					}
					else if (OList.get(i).equals("axe")) {
						axe++;
					}
					else {
						//we don't care the other irrelevant strings
					}
				}

				for (int i = indexObstacle;i<OList.size();i++) {
					if (OList.get(i).equals("fallen tree")) {
						fallentree++;
					}
					else if (OList.get(i).equals("river")) {
						river++;
					}
					else {
						//we don't care the other irrelevant strings
					}
				}
			}
			catch (NoSuchElementException ex ) {
				//caused by an incomplete or miss-formatted line in the input file
				System.err.println(line);
				continue; 	
			}
			try {
				current = new RestStop(label, food, raft, axe, river, fallentree);
				tree.add(  current  ); 
			}
			catch (IllegalArgumentException ex ) {
				//ignore this exception and skip to the next line 
			}
		}

		tree.updateLevel();
		int maxLevel = tree.maxLevel();
		Hiker hiker = new Hiker(maxLevel);
		tree.explore(hiker);
				
		// print the tree
		//System.out.print(tree.toStringTree());

	} // end of main methor
	


















}
