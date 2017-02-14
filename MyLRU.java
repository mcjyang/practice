import java.util.*;

// HashMap + DoubleLinkedList
public class MyLRU {

	// private nested class
	private static class DLNode{
		
		int key, val;
		DLNode pre, next;
		
	}
	
	// fields
	private int capacity;
	private HashMap<Integer, DLNode> map;
	private DLNode head, tail;
	
	public MyLRU(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		head = new DLNode();
		head.pre = null;
		
		tail = new DLNode();
		tail.next = null;
		
		head.next = tail;
		tail.pre = head;
	}
	
	public void addNode(DLNode node){
		DLNode next = head.next;
		head.next = node;
		next.pre = node;
		
		node.pre = head;
		node.next = next;
	}
	
	public void removeNode(DLNode node){
		DLNode pre = node.pre;
		DLNode next =node.next;
		
		pre.next = next;
		next.pre = pre;
	}
	
	public void toHead(DLNode node){
		removeNode(node);
		addNode(node);
	}
	
	public int removeLast(){
		DLNode pre = tail.pre.pre;
		int res = tail.pre.key;
		pre.next = tail;
		tail.pre = pre;
		return res;
	}
	
	public int get(int key){
		
		DLNode node = map.get(key);
		if(node == null) return -1;
		toHead(node);
		return node.val;
	}
	
	public void put(int key, int value){
		DLNode node = map.get(key);
		if(node == null){
			node = new DLNode();
			node.key = key;
			node.val = value;
			addNode(node);
			map.put(key, node);
			if(map.size() > capacity){
				int lastKey = removeLast();
				map.remove(lastKey);
			}
			
		}else{
			node.val = value;
			toHead(node);
		}
		
		
	}
	
	public static void main(String[] args){
		MyLRU cache = new MyLRU(3);
		System.out.println(cache.get(1));
		cache.put(1, 3);
		System.out.println(cache.get(1));
		cache.put(2, 8);
		cache.put(1, 8);
		System.out.println(cache.get(1));
		cache.put(3, 10);
		cache.put(3, 11);
		cache.put(6,123);
		System.out.println(cache.get(2));
		cache.put(10, 10);
		System.out.println(cache.get(10));
		System.out.println(cache.get(1));
	}

}
