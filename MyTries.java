import java.util.*;

class MyTries {

	private static class Node{
		private boolean isWord;
		private Node[] children;
		
		public Node(){
			children = new Node[26];
		}
	}
	
	private Node root;
	private int len;
	
	public MyTries() {
		root = new Node();
		len = 0;
	}
	
	public MyTries(int len){
		root = new Node();
		this.len = len;
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
	public List<String> findByPrefix(String prefix){
		List<String> res = new LinkedList<>();
        Node cur = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.children[c-'a'] == null) return res;
            cur = cur.children[c-'a'];
        }
        DFSHelper(res, new StringBuilder(prefix), cur, len-prefix.length());
        return res;
	}
	
	public void DFSHelper(List<String> res, StringBuilder sb, Node cur, int k){
        if(k==0){
            res.add(sb.toString());
            return;
        }
        for(int i = 0; i < 26; i++){
            if(cur.children[i]!=null){
                char tem = (char) ('a'+i);
                sb.append(tem);
                DFSHelper(res, sb, cur.children[i], k-1);
                sb.deleteCharAt(sb.length()-1);
            }   
        }
        return;
    }
	
	// also a DFS: to search next words by changing prefix
	public void search(List<List<String>> ans, List<String> tem){
		if(tem.size() == this.len){
			ans.add(new LinkedList<>(tem));
			return;
		}
		
		int idx = tem.size();
		StringBuilder sb = new StringBuilder();
		for(String s: tem){
			sb.append(s.charAt(idx));
		}
		List<String> allWords = this.findByPrefix(sb.toString());
		for(String w: allWords){
			tem.add(w);
			search(ans, tem);
			tem.remove(tem.size()-1);
		}
		return;
	}
	
	
	public static void main(String[] args){
//		Example 1: Basic Tries
//		MyTries prefixTree = new MyTries();
//		System.out.println(prefixTree.startsWith("hi")); // F
//		System.out.println(prefixTree.search("hello")); // F
//		prefixTree.insert("helloha");
//		System.out.println(prefixTree.search("hello")); // F
//		System.out.println(prefixTree.startsWith("hello")); // T
//		System.out.println(prefixTree.search("helloha")); // T
//		prefixTree.insert("hellohb");
//		prefixTree.insert("cc");
//		System.out.println(prefixTree.search("hellohaa")); // F
//		System.out.println(prefixTree.startsWith("hellohb")); // T
//		System.out.println(prefixTree.startsWith("c")); // T
		
//		Example 2: Words Square
		MyTries pt = new MyTries(4);
		String[] words = {"abat","baba","atan","atal"};
		for(String s: words){
			pt.insert(s);
		}
		
		List<List<String>> res = new LinkedList<>();
		List<String> tem = new LinkedList<>();
		for(String s: words){
			tem.add(s);
			pt.search(res, tem);
			tem.remove(tem.size()-1);
		}
		
		System.out.println(res);
		
	}
	

}
