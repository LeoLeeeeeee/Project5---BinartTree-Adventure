package project5;
import java.util.ArrayList;
import java.util.Scanner;

//delete afterward

public class testtext {
	public static void main (String[] args) {
		
		int food = 0;
		int raft = 0; 
		int axe = 0;
		int fallentree = 0;
		int river = 0;
		
		String a = "food axe";
		Scanner parseLine = new Scanner(a);
		
		parseLine.useDelimiter(" ");
		ArrayList<String> OList = new ArrayList<String>();
		while (parseLine.hasNext()) {
			OList.add(parseLine.next());
		}
	
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
				//we don't care the other irrelevant string
			}
		}
		
		for (int i = indexObstacle;i<OList.size();i++) {
			if (OList.get(i).equals("fallen tree")) {
				fallentree++;
			}
			else if (OList.get(i).equals("river")) {
				river++;
			}
		}
		
		System.out.println(OList.toString());
		System.out.println("food" + food);
		System.out.println("raft" + raft);
		System.out.println("axe" + axe);
		System.out.println("fallen tree" + fallentree);
		System.out.println("river" + river);
		
	}
}
