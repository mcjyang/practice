import java.util.*;

public class MyLFU {

    // two hashmap + Double LinkedList
	// one is for key and node
	// another is for freqHead and node
    
    // private nested class DLNode
    private static class DLNode{
        private int val, key, freq;
        private DLNode next, pre;
    }
    
    // fields
    private HashMap<Integer, DLNode> keyCache, freqCache;
    private DLNode head, tail;
    private int capacity;
    
    public MyLFU(int capacity) {
        this.capacity = capacity;
        keyCache = new HashMap<>();
        freqCache = new HashMap<>();
        head = new DLNode();
        tail = new DLNode();
        
        head.pre = null;
        tail.next = null;
        head.next = tail;
        tail.pre = head;
    }
    
    public void addPreNode(DLNode pre, DLNode cur){
        pre.pre = cur.pre;
        pre.next = cur;
        
        cur.pre.next = pre;
        cur.pre = pre;
    }
    
    public void addNodeToTail(DLNode node){
        if(freqCache.containsKey(1)){
            DLNode freqHead = freqCache.get(1);
            addPreNode(node, freqHead);
        }else{
            addPreNode(node, tail);
        }
        freqCache.put(1, node);
        keyCache.put(node.key, node);
    }
    
    public void removeNode(DLNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void increaseFreq(DLNode node){
        int curFreq = node.freq;
        DLNode freqHead;
        node.freq = curFreq+1;
        if(freqCache.containsKey(node.freq)){
            freqHead = freqCache.get(node.freq);
            if(freqCache.get(curFreq) == node && curFreq == node.next.freq) 
            	freqCache.put(curFreq,node.next);
            else if(freqCache.get(curFreq) == node) 
            	freqCache.remove(curFreq);
            removeNode(node);
            freqCache.put(node.freq, node);
            addPreNode(node, freqHead);
        }else{ // does not contain curFreq in freqCache
            freqHead = freqCache.get(curFreq);
            if(freqHead == node && curFreq == node.next.freq) 
            	freqCache.put(curFreq,node.next);
            else if(freqHead == node)
            	freqCache.remove(curFreq);
            else{
            	removeNode(node);
            	addPreNode(node, freqHead);
            }
            freqCache.put(node.freq, node);
        }
    }
    
    public void removeTail(){
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
    }
    
    public int getLastKey(){
        return tail.pre.key;
    }
    
    public int get(int key) {
        DLNode node = keyCache.get(key);
        if(node == null) return -1;
        increaseFreq(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        DLNode node = keyCache.get(key);
        if(node != null){
            node.val = value;
            increaseFreq(node);
        }else{
        	if(keyCache.size() == capacity){
        		int lastKey = getLastKey();
                DLNode last = keyCache.get(lastKey);
                int freq = last.freq;
                if(freqCache.get(freq) == last){
                	freqCache.remove(freq);
                }
                keyCache.remove(lastKey);
                removeTail();
            }
        	node = new DLNode();
            node.freq = 1;
            node.val = value;
            node.key = key;
            addNodeToTail(node);
        }
    }
    
    public static void main(String[] args){
    	MyLFU obj = new MyLFU(2);
    	System.out.println(obj.get(2));
    	obj.put(2, 6);
    	System.out.println(obj.get(1));
    	obj.put(1, 5);
    	obj.put(1, 2);
    	System.out.println(obj.get(1));
    	System.out.println(obj.get(2));
    	
    	
    	
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */