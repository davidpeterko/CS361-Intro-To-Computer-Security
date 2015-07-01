import static java.lang.System.*;
import java.util.*;

public class HuffHelp{
	private QueuePriority<Integer> sortFreq; //sorted frequencies 
	private ArrayList<String> alphabet;
	private TreeNode root;
	
	public HuffHelp(ArrayList<Integer> regFreq, ArrayList<String> incomingAlpha){
		sortFreq = new QueuePriority<Integer>();
		alphabet = incomingAlpha;
		sortAdd(regFreq);
		root = makeTree();
		//checkTree(root);

	}
	
	public TreeNode getTreeRoot(){
		return root;
	}	
		
	public void checkTree(TreeNode node){
		if(node.getLeft() != null)
			checkTree(node.getLeft());
		if(node.getLeft() == null && node.getRight() == null){
			out.println(node.getWeight()+ " " + node.getValue());
			return;
		}
		checkTree(node.getRight());
	}
	
	public void sortAdd(ArrayList<Integer> regFreq){
		for(int x = 0; x < regFreq.size(); x++)
			sortFreq.add(regFreq.get(x), alphabet.get(x));
	}
	
	public TreeNode makeTree(){//makes the tree and return the root.
		QueuePriority<TreeNode> list = new QueuePriority<TreeNode>();
        for(int x = 0; x < sortFreq.size(); x++)
        	list.add(new TreeNode(sortFreq.getString(x), sortFreq.get(x)));
        while(list.size() != 1){
        	TreeNode left = list.remove();
        	TreeNode right = list.remove();
        	TreeNode temp = new TreeNode("-1", right.getWeight() + left.getWeight(), left, right);
        	list.add(temp);
        }
        return list.remove();
	}
}
