
public class MyTreeTraversal {
	
	private static class TreeNode{
		
		public TreeNode left, right;
		public int val;
		
		public TreeNode(int val){
			this.val = val;
		}
	}

	private TreeNode root;
	
	public MyTreeTraversal(int[] list){
		root = buildTree(list, 0, list.length);
	}
	
	public TreeNode buildTree(int[] list, int start, int end){
		if(start >= end) return null;
		
		int mid = start + (end-start)/2;
		TreeNode root = new TreeNode(list[mid]);
//		System.out.println(list[mid]);
		root.left = buildTree(list, start, mid);
		root.right = buildTree(list, mid+1, end);
		return root;
	}
	
	// in-order Morris traversal with O(1) space (2 pointers)
	public void morrisTreaversal(){
		TreeNode node = root;
		TreeNode pre = null;
		while(node != null){
			if(node.left != null){
				//find the previous node of node in in-inder
				pre = node.left;
				while(pre.right != null && pre.right != node) pre = pre.right;
				
				if(pre.right != null){
					// if true then pre.right == node: recover the tree, output the node.val, go to node.right
					pre.right = null;
					System.out.println(node.val);
					node = node.right;
				}else{
					// if false then pre.right == null: pointer pre.right to node, go to node.left
					pre.right = node;
					node = node.left;
				}
			}else{
				System.out.println(node.val);
				node = node.right;
			}
		}
	}
	
	public static void main(String[] args){
		
		int[] list = new int[]{0,1,2,3,4,5,6};
		MyTreeTraversal t = new MyTreeTraversal(list);
		t.morrisTreaversal(); 
		
	}

}
