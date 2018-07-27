
import java.util.Collections;
import java.util.Random;

public class BTree {
	public Node root;
	public static int t = 3;
	public static int h = 0;

	BTree() {
		Node x = new Node(true);
		root = x;
		h++;
	}

	public boolean search(Node x, int value) {
		int i = 0;
		while (i < x.Entries.size()-1 && value > x.Entries.get(i))
			i++;
		if (i < x.Entries.size()-1 && value == x.Entries.get(i))
			return true;
		else {
			if (x.isnewLeaf)
				return false;
			else
				return this.search(x.Children.get(i), value);
		}
	}

	public void insert(int key) {
		Node r = root; 
		if (r.Entries.size() == 2*t-1) {
//			System.out.println("SPLITTING");
			Node s = new Node(false);
			root = s;
			s.Children.add(0, r);
			h++;
			splitChild(s, 0);
			insertNonfull(s, key);
		} else
			insertNonfull(r, key);
	}
	
	public void splitChild(Node x, int i){
		Node z = new Node(true);
		Node y = x.Children.get(i);
		z.isnewLeaf = y.isnewLeaf = true;
		for(int j = 0; j < t-1; j++)
			z.Entries.add(j, y.Entries.get(j+t));
		if(!y.isnewLeaf){
			for(int j = 0; j < t; j++)
				z.Children.add(j, y.Children.get(j+t));
		}
		for(int j = x.Entries.size()+1; j > i+1; j--)
			x.Children.add(j+1, x.Children.get(j));
		
		x.Children.add(i+1, z);
		
		x.Entries.add(y.Entries.get(t-1));	

		for(int j = y.Entries.size()-1; j >= t-1; j--)
			y.Entries.remove(j);

	}

	public void insertNonfull(Node x, int k) {
		int i = x.Entries.size()-1;
		if (x.isnewLeaf) {
			x.Entries.add(k);
			Collections.sort(x.Entries);
		} 
		else {
			while (i >= 0 && k < x.Entries.get(i))
				i--;
			i++;
			if (x.Children.get(i).Entries.size() == 2 * t - 1) {
				splitChild(x, i);
				if (k > x.Entries.get(i))
					i++;
			}
			insertNonfull(x.Children.get(i), k);
		}
	}

	public static void main(String[] args) {
		BTree btree = new BTree();

		for(int i = 0; i < 10; i++) {
			btree.insert(i);
		}
			
		System.out.println("Number of Entries: " + btree.root.Entries.size());
		System.out.println("Entries in root: ");
		for(int i = 0; i < btree.root.Entries.size(); i++)
			System.out.println(btree.root.Entries.get(i));
		System.out.println();
		System.out.println("Children size: " + btree.root.Children.size());
		System.out.println("1st Child size: " + btree.root.Children.get(0).Entries.size());
		System.out.println("Entries in child: ");
		for(int i = 0; i < btree.root.Children.get(0).Entries.size(); i++)
			System.out.println(btree.root.Children.get(0).Entries.get(i));
		System.out.println();
		System.out.println("2nd Child size: " + btree.root.Children.get(1).Entries.size());
		System.out.println("Entries in child:");
		for(int i = 0; i < btree.root.Children.get(1).Entries.size(); i++)
			System.out.println(btree.root.Children.get(1).Entries.get(i));
		
		System.out.println();
		System.out.println("3rd Child size: " + btree.root.Children.get(2).Entries.size());
		System.out.println("Entries in child: ");
		for(int i = 0; i < btree.root.Children.get(2).Entries.size(); i++)
			System.out.println(btree.root.Children.get(2).Entries.get(i));
		
		System.out.println();
		System.out.println("Total height: " + h);
		System.out.println(btree.search(btree.root, 2));

	}
}