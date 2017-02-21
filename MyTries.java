import java.util.List;

class MyTries {

	private static class Node{
		private boolean isWord;
		private Node[] children;
		
		public Node(){
			children = new Node[26];
		}
	}
	
	private Node root;
	
	public MyTries() {
		root = new Node();
	}
	
	/* insert word */
	public void insert(String s){
		Node cur = root;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(cur.children[c-'a'] == null) cur.children[c-'a'] = new Node();
			cur = cur.children[c-'a'];
		}
		cur.isWord = true;
	}
	
	/* search if s in tries */
	public boolean search(String s){
		Node cur = root;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(cur.children[c-'a'] == null) return false;
			cur = cur.children[c-'a'];
		}
		return cur.isWord;
	}
	
	/* if there is any word start with prefix*/
	public boolean startsWith(String prefix){
		Node cur = root;
		for(int i = 0; i < prefix.length(); i++){
			char c = prefix.charAt(i);
			if(cur.children[c-'a']==null) return false;
			cur = cur.children[c-'a'];
		}
		return true;
	}
	
	/* return all words start with prefix*/
	public List<String> allWordsWith(String prefix){
		return null;
	}
	
	
	public static void main(String[] args){
		MyTries prefixTree = new MyTries();
		System.out.println(prefixTree.startsWith("hi")); // F
		System.out.println(prefixTree.search("hello")); // F
		prefixTree.insert("helloha");
		System.out.println(prefixTree.search("hello")); // F
		System.out.println(prefixTree.startsWith("hello")); // T
		System.out.println(prefixTree.search("helloha")); // T
		prefixTree.insert("hellohb");
		prefixTree.insert("cc");
		System.out.println(prefixTree.search("hellohaa")); // F
		System.out.println(prefixTree.startsWith("hellohb")); // T
		System.out.println(prefixTree.startsWith("c")); // T
	}
	

}
