package project5;

/**
 * This class represents the Hiker class which is created by food, raft, axe,
 * mountainLevel and total Level
 * Hiker object will be used to represent the hiker that travels from 
 * mountain peak to the bottom
 * @author Yiang Li (Leo)
 * @version 2021/05/01
 *
 */

public class Hiker {

	private int food;   
	private int raft;
	private int axe;

	//help to check which level hiker is at
	private int mountainLevel;
	//help variable to do the comparison between the hiker level and total mountain level
	private int totalLevel;

	/**
	 * create an initial hiker object that is at the peak
	 * @param totalLevel
	 */

	public Hiker(int totalLevel) {
		this.totalLevel = totalLevel;
		this.mountainLevel = 0;
		this.food = 0;
		this.axe = 0;
		this.raft = 0;
	}

	/**
	 * create a hiker object that can be seen as hiker‘s shadow copy.
	 * It will be used in the recursive explore method in BSTMountain class, 
	 * hiker's shadow copy will explore every possible path and 
	 * either die or find the way to the bottom
	 * @param food
	 * @param raft
	 * @param axe
	 * @param mountainLevel
	 * @param totalLevel
	 */
	public Hiker(int food, int raft, int axe, int mountainLevel, int totalLevel) {
		this.food = food;
		this.raft = raft;
		this.axe = axe;
		this.mountainLevel = mountainLevel;
		this.totalLevel = totalLevel;
	}

	/**
	 * getter of mountainLevel
	 * @return mountainLevel
	 */

	public int getMountainLevel() {
		return mountainLevel;
	}

	/**
	 * setter of mountainLevel
	 * change the current mountainLevel
	 * @param mountainLevel
	 */
	public void setMountainLevel(int mountainLevel) {
		this.mountainLevel = mountainLevel;
	}

	/**
	 * getter of totalLevel
	 * @return total mountain level
	 */
	public int getTotalLevel() {
		return totalLevel;
	}

	/**
	 * getter of food
	 * @return food
	 */

	public int getFood() {
		return food;
	}

	/**
	 * setter of food
	 * change the food of a hiker has
	 * @param food
	 */

	public void setFood(int food) {
		this.food = food;
	}

	/**
	 * getter of raft
	 * @return raft
	 */

	public int getRaft() {
		return raft;
	}

	/**
	 * setter of raft
	 * @param raft
	 */
	public void setRaft(int raft) {
		this.raft = raft;
	}

	/** 
	 * getter of axe
	 * @return axe
	 */
	public int getAxe() {
		return axe;
	}

	/** 
	 * setter of axe
	 * @param axe
	 */
	public void setAxe(int axe) {
		this.axe = axe;
	}



}
