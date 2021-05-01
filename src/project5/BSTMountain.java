package project5;

public class BSTMountain {
	private BSTNode peek;
	
	public BSTMountain() {
		peek = null;
	}
	
	public void explore(Hiker hiker) {
		if (hiker == null) {
			System.err.print("this is not a valid hiker"); //should happen
		}
		explore (hiker, peek);
	}
	
	private void explore(Hiker hiker, BSTNode peek) {
		if (peek == null && hiker.getMountainLevel() == hiker.getTotalLevel()) {
			System.out.println("success!");
			return;
		}
		
		if (peek == null && hiker.getMountainLevel() < hiker.getTotalLevel()) {
			System.out.println("fall from cliff");
			return;
		}
		
		//add supply
		/*
		hiker.setAxe(hiker.getAxe()+peek.data.getAxe());
		hiker.setFood(hiker.getFood()+peek.data.getFood());
		hiker.setRaft(hiker.getRaft()+peek.data.getRaft());
		*/
		if ((hiker.getAxe()+peek.data.getAxe())<peek.data.getFallentree()) {
			System.out.println("tree blocked");
			return;
		}
		if ((hiker.getRaft()+peek.data.getRaft())<peek.data.getRiver()) {
			System.out.println("river blocked");
			return;
		}
		if ((hiker.getFood()+peek.data.getFood())<1) {
			System.out.println("running out of food");
			return;
		}
		//use supply
		hiker.setAxe(hiker.getAxe()+peek.data.getAxe()-peek.data.getFallentree());
		hiker.setRaft(hiker.getRaft()+peek.data.getRaft()-peek.data.getRiver());
		hiker.setFood(hiker.getFood()+peek.data.getFood()-1);
		
		//update height
		hiker.setMountainLevel(peek.level);
		
		explore(hiker,peek.left);
		explore(hiker,peek.right);
		
	}
	
	
	
	public void add(RestStop rs) {
		if (rs== null) {
			return;
		}
		peek = add(rs,peek);
	}
	
	private BSTNode add(RestStop rs, BSTNode peek) {
		if (peek == null) {
			return new BSTNode(rs);
		}
		
		int comp = rs.compareTo(peek.data);
		if (comp<0) {
			peek.left = add (rs,peek.left);
		}
		else if (comp>0) {
			peek.right = add (rs, peek.right);
		}
		else {
			return peek;
		}
		return peek;
	}
	
	public void updateLevel() {
		updateLevel(peek,0);
	}
	
	private void updateLevel (BSTNode peek, int level ) {
		if (peek == null) {
			return;
		}
		peek.level = level;
		level++;
		updateLevel(peek.left, level);
		updateLevel(peek.right, level);
	}
	
    public String toStringTree( ) {
        StringBuffer sb = new StringBuffer(); 
        toStringTree(sb, peek, 0);
        return sb.toString();
    }

    public int maxLevel() {
    	return maxLevel(peek, 0 );
    }
    public int maxLevel(BSTNode peek, int max) {
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
    
    
    //uses preorder traversal to display the tree 
    //WARNING: will not work if the data.toString returns more than one line 
    public void toStringTree( StringBuffer sb, BSTNode node, int level ) {
        //display the node 
        if (level > 0 ) {
            for (int i = 0; i < level-1; i++) {
                sb.append("   ");
            }
            sb.append("|--");
        }
        if (node == null) {
            sb.append( "->\n"); 
            return;
        }
        else {
            sb.append( node.data.getLabel()+node.level+ "\n"); 
        }

        //display the left subtree 
        toStringTree(sb, node.left, level+1); 
        //display the right subtree 
        toStringTree(sb, node.right, level+1); 
    }
    
    
	private class BSTNode implements Comparable<BSTNode>{
		RestStop data;
		BSTNode left;
		BSTNode right;
		int level;
		
		public BSTNode (RestStop data) {
			this.data = data;
		}
		
		public BSTNode(RestStop data, BSTNode left, BSTNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		
		public int compareTo(BSTNode other) {
			return this.data.compareTo(other.data);
		}
			
	}
	

	
}
