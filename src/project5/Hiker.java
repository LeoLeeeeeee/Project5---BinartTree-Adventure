package project5;
public class Hiker {

	private int food;   // when add BSTNode, if no food, enter 0
	private int raft;
	private int axe;
	
	public Hiker(int food, int raft, int axe) {
		this.food = food;
		this.raft = raft;
		this.axe = axe;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getRaft() {
		return raft;
	}

	public void setRaft(int raft) {
		this.raft = raft;
	}

	public int getAxe() {
		return axe;
	}

	public void setAxe(int axe) {
		this.axe = axe;
	}
	
	
	
}
