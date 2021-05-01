package project5;
public class Hiker {

	private int food;   // when add BSTNode, if no food, enter 0
	private int raft;
	private int axe;
	private int mountainLevel; //help variable to check when
	private int totalLevel;
	

	public Hiker(int totalLevel) {
		this.totalLevel = totalLevel;
		this.mountainLevel = 0;
		this.food = 0;
		this.axe = 0;
		this.raft = 0;
	}

	public Hiker(int food, int raft, int axe, int mountainLevel, int totalLevel) {
		this.food = food;
		this.raft = raft;
		this.axe = axe;
		this.mountainLevel = mountainLevel;
		this.totalLevel = totalLevel;
	}

	
	public int getMountainLevel() {
		return mountainLevel;
	}


	public void setMountainLevel(int mountainLevel) {
		this.mountainLevel = mountainLevel;
	}


	public int getTotalLevel() {
		return totalLevel;
	}
	
	public void printHiker() {
		String s = String.format("Mountain is level %d, I am at level %d, I have %d food, %d raft, %d axe",
				this.totalLevel, this.mountainLevel,this.food,this.raft,this.axe);
		System.out.println(s);
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
