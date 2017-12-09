

public class MySegmentTree {
	
	private class Node{
		
		private int start, end, val;
		private Node left, right;
		
		public Node(int start, int end, int val){
			this.start = start;
			this.end = end;
			this.val = val;
		}
	}
	
	private Node root;
	
	public MySegmentTree(int[] nums){
		root = construct(nums, 0, nums.length-1);
	}
	
	public Node construct(int[] nums, int start, int end){
		
		if(start == end){
			return new Node(start, end, nums[start]);
		}
		int mid = start + (end - start)/2;
		Node left = construct(nums, start, mid);
		Node right = construct(nums, mid+1, end);
		Node cur = new Node(start, end, Math.min(left.val, right.val));
		cur.left = left;
		cur.right = right;
		return cur;
	}
	
	public Node getRoot(){return root;}
	
	public int query(Node root, int qStart, int qEnd){
		// assume that all input are valid
		if(qStart <= root.start && root.end <= qEnd){return root.val;}
		else if(qStart > root.end || qEnd < root.start){return Integer.MAX_VALUE;}
		else{return Math.min(query(root.left, qStart, qEnd), query(root.right, qStart, qEnd));}
	}
	
	public static void main(String[] args){
		int[] nums = {10, 20, 30, 40, 11, 22, 33, 44, 15, 5};
		MySegmentTree st = new MySegmentTree(nums);
		int[][] ranges = {{0, 5},{1, 3},{2, 6},{3, 9}}; // 10, 20, 11, 5 
		for(int [] range: ranges){
			System.out.println(st.query(st.getRoot(), range[0], range[1]));
		}
	}
}
