import java.util.Arrays;

public class MyDijkstra {

	public int findMinDistance(int[] distances, boolean[] visited){
		
		int res = Integer.MAX_VALUE;
		int idx = -1;
		for(int i = 0; i < distances.length; i++){
			if(!visited[i] && distances[i] < res){
				res = distances[i];
				idx = i;
			}
		}
		return idx;
	}
	
	public void dijkstra(int[][] graph, int start){
		
		//initialize array
		int len = graph.length;
		boolean[] visited = new boolean[len];
		int[] distances = new int[len];
		Arrays.fill(distances, Integer.MAX_VALUE);
		
		distances[start] = 0;
		
		//start running algorithms
		for(int i = 0; i < len-1; i++){ 
			// len -1 because we don't need to calculate last point (double directions) 
			
			int s = findMinDistance(distances, visited);
			visited[s] = true;
			// update
			for(int v = 0; v < len; v++){
				if(!visited[v] && graph[s][v]!=0) 
					distances[v] = Math.min(distances[v], distances[s]+graph[s][v]);
			}
		}
		
		System.out.println("Vertex   Distance from Source");
		for(int v = 0; v < len; v++){
			System.out.println(v+"\t\t "+distances[v]);
		}
	}

	public static void main(String[] args){
		int graph[][] = new int[][]{
			{0, 4, 0, 0, 0, 0, 0, 8, 0},
			{4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}};
            
       MyDijkstra d = new MyDijkstra();
       d.dijkstra(graph, 0);
	}
}
