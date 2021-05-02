package project5;

/**
 * This is the class that represents the mountain itself.
 * This is a BST structure that its nodes store data items of type RestStop
 * The recursive method of explore path of BSTMountain adventure is also in this class.
 * @author Yiang Li (Leo)
 * @version 2021/05/01
 */
public class BSTMountain {
	//peak of mountain is like a root of BST
	private BSTNode peak;

	/**
	 * BSTMountain object is created
	 */
	public BSTMountain() {
		peak = null;
	}

	/**
	 * explore method uses a binary search tree to model a mountain hiking expedition:
	 * A hiker starts at the top of the mountain. They need to find their way down the mountain
	 * with limited resources to survive and overcome obstacles.
	 * 
	 * @param hiker
	 */
	public void explore(Hiker hiker) {
		if (hiker == null) {
			System.err.print("this is not a valid hiker"); //should happen
		}
		explore (hiker, peak);
	}

	/**
	 * actual recursive explore method
	 * @param hiker
	 * @param peak
	 */
	private void explore(Hiker hiker, BSTNode peak) {	
		//update hiker's current mountainlevel
		hiker.setMountainLevel(peak.level);

		//hiker will fall from a cliff because it cannot go down when the current level is not the bottom mountain level
		if (peak.left == null && peak.right == null  && hiker.getMountainLevel() < hiker.getTotalLevel()) {
			return;
		}

		//check whether hiker can cut the fallen tree with axe, if not, this path is not passable
		if ((hiker.getAxe()+peak.data.getAxe())<peak.data.getFallentree()) {
			return;
		}

		//check whether hiker can cross the river by a raft, if not, this path is not passable
		if ((hiker.getRaft()+peak.data.getRaft())<peak.data.getRiver()) {
			return;
		}

		//check whether hiker has enough food to go to the next stop, if not, this path is not passable
		//unless the hiker is already at the bottom of the mountain
		if ((!(peak.left== null &peak.right == null)&&(hiker.getFood()+peak.data.getFood())<1)) {
			return;
		}

		//base case: reach the bottom and pass all obstacles
		if (hiker.getMountainLevel() == hiker.getTotalLevel()) {
			//print the path, the method is below
			System.out.println(getPath().trim());
			//go back to check whether there is other path
			return;
		}

		//create a hiker's shadow copy, he will use the supply to get through the obstacle and explore the next stop
		Hiker hikerShadow = new Hiker(hiker.getFood()+peak.data.getFood()-1, hiker.getRaft()
				+peak.data.getRaft()-peak.data.getRiver(),hiker.getAxe()+peak.data.getAxe()
				-peak.data.getFallentree(), peak.level, hiker.getTotalLevel() );

		//if hiker can go to the left bottom, hiker's shadow copy is sent there
		if (peak.left!= null) {
			//used to print the path in the getPath() method
			peak.data.setDirection("L");
			explore(hikerShadow,peak.left);
		}

		//if hiker can go to the right bottom, hiker's shadow copy is sent there
		if (peak.right!= null) {
			//used to print the path in the getPath() method
			peak.data.setDirection("R");
			explore(hikerShadow,peak.right);
		}
	}

	/**
	 * get the String of the path from the peak of the mountain
	 * @return the string that shows the path of a hiker that can get to the bottom successfully
	 */
	public String getPath() {
		return getPath(peak);
	}

	/**
	 * actual getPath() method that return all the labels that a hiker passes
	 * @param peak
	 * @return all the labels of a hiker pass
	 */

	private String getPath(BSTNode peak) {
		String label = peak.data.getLabel()+ " ";
		String direction = peak.data.getDirection();
		if (direction == "R") {
			return label + getPath(peak.right);
		}
		else if (direction == "L") {
			return label + getPath(peak.left);
		}
		else {
			return label;
		}
	}

	/**
	 * Adds the specified RestStop object to this BST 
	 * @param rs
	 */
	public void add(RestStop rs) {
		if (rs== null) {
			return;
		}
		peak = add(rs,peak);
	}

	/**
	 * actual adding recursive implementation of add. 
	 * This function returns a reference to the subtree in which 
	 * the new value was added. 
	 * @param rs
	 * @param peak
	 * @return the updated peak node of the mountain
	 */

	private BSTNode add(RestStop rs, BSTNode peak) {
		if (peak == null) {
			return new BSTNode(rs);
		}

		int comp = rs.compareTo(peak.data);
		if (comp<0) {
			peak.left = add (rs,peak.left);
		}
		else if (comp>0) {
			peak.right = add (rs, peak.right);
		}
		else {
			return peak;
		}
		return peak;
	}

	/**
	 * to assign the level of each RestStop object of the mountain
	 */
	public void updateLevel() {
		updateLevel(peak,0);
	}

	/**
	 * actual recursive implementation of assigning the level to each Reststop
	 * @param peak
	 * @param level
	 */
	private void updateLevel (BSTNode peak, int level ) {
		if (peak == null) {
			return;
		}
		peak.level = level;
		level++;
		updateLevel(peak.left, level);
		updateLevel(peak.right, level);
	}

	/**
	 * get the max level of the mountain, when the hiker's current level is
	 * equal to the mountain's max level, it means he has reached to the bottom
	 * @return maxLevel
	 */
	public int maxLevel() {
		return maxLevel(peak, 0 );
	}

	/**
	 * actual recursive implementation of finding the maximum of the mountain
	 * @param peek
	 * @param max
	 * @return maxLevel of the mountain
	 */
	private int maxLevel(BSTNode peek, int max) {
		if (peek == null) {
			return max;
		}

		if (peek.level>max) {
			max = peek.level;
		}
		int max1 = maxLevel(peek.left,max);
		int max2 = maxLevel(peek.right,max);
		max = Math.max(max1, max2);
		return max;
	}

	/**
	 * node class for the BSTMountain that implements comparable to compare
	 * two BSTNodes by their reststop's labels
	 */
	private class BSTNode implements Comparable<BSTNode>{
		RestStop data;
		BSTNode left;
		BSTNode right;
		int level;

		/**
		 * create an BSTNode object by the data
		 * @param data
		 */
		public BSTNode (RestStop data) {
			this.data = data;
		}

		/**
		 * create an bSTNode object by its data, right reference, left reference
		 * @param data
		 * @param left
		 * @param right
		 */
		public BSTNode(RestStop data, BSTNode left, BSTNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		/**
		 * compare method to compare two BSTnode by the reststop's label
		 * return positive if this > other, negative if this < other, 0 if equal
		 * @return integers
		 */
		public int compareTo(BSTNode other) {
			return this.data.compareTo(other.data);
		}

	}



}
