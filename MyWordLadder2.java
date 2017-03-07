import java.util.*;

public class MyWordLadder2 {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        List<List<String>> res = new LinkedList<>();
        if(wordList == null || wordList.size() == 0) return res;
        
        int minStep = Integer.MAX_VALUE;
        HashMap<String, List<String>> adjacentGraph = new HashMap<>();
        Queue<String> q = new LinkedList<>(); // q for BFS(Dijkstra)
        q.offer(beginWord);
        HashMap<String, Integer> minPaths = new HashMap<>();
        for(String w: wordList) minPaths.put(w, Integer.MAX_VALUE);
        minPaths.put(beginWord, 0);
        
        // Dijkstra Search
        while(!q.isEmpty()){
            String w = q.poll();
            int stepForNext = minPaths.get(w) + 1;
            if(stepForNext > minStep) break;
            
            char[] cs = w.toCharArray();
            for(int i = 0; i < w.length(); i++){
                char old = cs[i];
                for(char c = 'a'; c <= 'z'; c++){
                    cs[i] = c;
                    String target = String.valueOf(cs);
                    if(minPaths.containsKey(target)){//meaning that this is a word exists in dict
                        if(stepForNext > minPaths.get(target)) continue;
                        else if(stepForNext < minPaths.get(target)){
                            minPaths.put(target, stepForNext);
                            q.offer(target);
                        }
                        
                        // build adjacent graph
                        if(adjacentGraph.containsKey(target)) adjacentGraph.get(target).add(w);
                        else{
                            List<String> list = new LinkedList<>();
                            list.add(w);
                            adjacentGraph.put(target, list);
                        }
                        
                        if(target.equals(endWord)) minStep = stepForNext;
                    }
                }
                cs[i] = old;
            }
        }
        // end Dijstra
        // DFS from end's adjacent graph, because only valid paths have been added to adjacentGraph
        DFSHelper(endWord, beginWord, res, new LinkedList<String>(), adjacentGraph);
        return res;
    }
    
    public static void DFSHelper(String start, String end, List<List<String>> res, List<String> cur, HashMap<String, List<String>> adjacentGraph)
    {
        if(start.equals(end)){
            cur.add(0, start);
            res.add(new LinkedList<String>(cur));
            cur.remove(0);
            return;
        }
        
        cur.add(0, start);
        List<String> tem = adjacentGraph.get(start);
        if(tem != null){
            for(String s: tem) DFSHelper(s, end, res, cur, adjacentGraph);
        }
        cur.remove(0);
    }
    
    public static void main(String[] args){
    	String start = "hit", end = "cog";
    	String[] dict = {"hot", "dot", "dog", "lot", "log", "cog"};
    	LinkedList<String> list = new LinkedList<>(Arrays.asList(dict));
    	
    	MyWordLadder2.findLadders(start, end, list);
    }
}