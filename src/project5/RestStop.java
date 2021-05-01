/**
 * This class should represent a single rest stop. 
 * It should be capable of storing the label of the rest stop along
 *  with a list of the supplies that a hiker can collect at this
 *  rest-stop and a list of obstacles that a hiker may encounter at this rest-stop.
 *   It may be useful to implement the Comparable interface.
 * @author leo
 *
 */

package project5;
public class RestStop implements Comparable<RestStop> {

	
	private  String label;
	private int food;   // when add BSTNode, if no food, enter 0
	private int raft;
	private int axe;
	private int river;
	private int fallentree;
	private String direction;
	
	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public RestStop(String label,int food, int raft, int axe, int river, int fallentree) {
		this.label = label;
		this.food = food;
		this.raft = raft;
		this.axe = axe;
		this.river = river;
		this.fallentree = fallentree;
	}

	
	public RestStop (String label) {
		this.label = label;
	}
	
	
	public String getLabel() {
		return this.label;
	}
	
	public int getFood() {
		return food;
	}


	public int getRaft() {
		return raft;
	}


	public int getAxe() {
		return axe;
	}


	public int getRiver() {
		return river;
	}


	public int getFallentree() {
		return fallentree;
	}


	public int compareTo(RestStop other) {
		return this.label.compareTo(other.label);
		// if negative,  this <other
		// if positive, this> other
	}
	
	public void printRS() {
		System.out.println(String.format("In RS %s, Food: %d, Raft: "
				+ "%d, Axe: %d, River: %d, Fallentree: %d" , this.label,
				this.food,this.raft,this.axe,this.river,this.fallentree));
	}
	
	// delete after passing autograder
	public static void main (String[] args) {
		BSTMountain t = new BSTMountain();
		RestStop a = new RestStop("sk",1,3,2,1,3);
		RestStop b = new RestStop("a",2,3,2,1,3);

		a.printRS();
		t.add(a);
		t.add(b);
		System.out.println(t.toStringTree());
	}
}
