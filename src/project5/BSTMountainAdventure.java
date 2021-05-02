package project5;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This application finds all paths through a BST Mountain 
 * This is the class that has the main method. This class is responsible for parsing and validating the command line arguments,
 * reading and parsing the input file, producing any error messages, handling any exceptions thrown by other classes,
 * and producing output.
 * @author Yiang Li (Leo)
 * @version 2021/05/01
 *
 */
public class BSTMountainAdventure {


	/**
	 * The main() method of this program. This method is helping the hiker to find each possible way
	 * to go from the top of the mountain to the bottom if there are ways
	 * @param args array of Strings on command line is read when the program is started; 
	 * the first string should be the name of the input file. 
	 */

	public static void main(String[] args) {

		/** line 36 to line 63 are my paraphrasing, it is from @author Joanna Klukowska
		 * I cited this part on May 1th, 2021
		 * This code is paraphrased from Professor Klulkowska's ColorConverter from ED Workspace
		 * You could find similar codes from 
		 * https://edstem.org/us/courses/3906/workspaces/pBSLx4PAZFAusHxD2yDEPMV9zXksyGgt
		 */

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

				//deal with fallen tree, merge fallen and tree into one index to make it easier when creating RestStop object
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

				// to find whether does the obstacle items start, everything including supplies are not
				// saved after the index of obstacle
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

				//record the number of supplies: foods, rafts and axes
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

				//record the number of obstacle: fallen tree, river
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

			//try to add the RestStop
			try {
				current = new RestStop(label, food, raft, axe, river, fallentree);
				tree.add(  current  ); 
			}
			catch (IllegalArgumentException ex ) {
				//ignore this exception and skip to the next line 
			}
		}

		//after creating the BSTMountain, we update all the levels of each RestStop
		tree.updateLevel();

		//create a hiker object with the maxlevel of the mountain
		int maxLevel = tree.maxLevel();
		Hiker hiker = new Hiker(maxLevel);

		//explore the mountain and print the path
		tree.explore(hiker);


	} 
}
