import java.util.ArrayList;

public class Node {
	public boolean isnewLeaf;
	ArrayList<Integer> Entries = new ArrayList<Integer>(); 
	ArrayList<Node> Children = new ArrayList<Node>();

	public Node(boolean isnewLeaf) {
		this.isnewLeaf = isnewLeaf;
	}
	public Node(int x) {
		Entries.add(x);
	}

	
	
}

