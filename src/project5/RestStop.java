package project5;

/**
 * This class should represent rest stop class. 
 * It should be capable of storing the label of the rest stop along
 *  with a list of the supplies that a hiker can collect at this
 *  rest-stop and a list of obstacles that a hiker may encounter at this rest-stop.
 *  It implements the Comparable interface to compare the label of each reststop in
 *  alphanumeric order.
 * @author Yiang Li (Leo)
 * @version 2021/05/01
 */

public class RestStop implements Comparable<RestStop> {


	private  String label;
	private int food;   
	private int raft;
	private int axe;
	private int river;
	private int fallentree;

	/*
	 * helper variable to identify which way (left or right) does the hiker go
	 * when printing the path of a hiker's sucessful path
	 */ 
	private String direction;


	/**
	 * create a RestStop object by label, food,raft,axe,river,and fallentree
	 * @param label
	 * @param food
	 * @param raft
	 * @param axe
	 * @param river
	 * @param fallentree
	 */
	public RestStop(String label,int food, int raft, int axe, int river, int fallentree) {
		this.label = label;
		this.food = food;
		this.raft = raft;
		this.axe = axe;
		this.river = river;
		this.fallentree = fallentree;
	}

	/**
	 * From Line 53 to 94
	 * These are all getters and setters for each parameter for the RestStop object
	 */
	public RestStop (String label) {
		this.label = label;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
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


	/**
	 * compareTo method that compare two RestStops by their labels in alphanumeric order
	 * negative if this<other, positive if this>other, 0 if they are equal
	 * @return either postive, 0, or negative integers
	 */
	public int compareTo(RestStop other) {
		return this.label.compareTo(other.label);
	}

}
