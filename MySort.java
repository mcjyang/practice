
public class MySort{

	// mergesot: divide and conquer
	public static int[] mergesort(int[] nums, int start, int end){
		if(nums == null || nums.length == 0){return new int[0];}
		if(start == end){return new int[]{nums[start]};}
		int mid = start + (end - start)/2;
		
		int[] left = mergesort(nums, start, mid);
		int[] right = mergesort(nums, mid+1, end);
		return merge(left, right);
	}
	
	public static int[] merge(int[] left, int[] right){
		int[] res = new int[left.length + right.length];
		int i = 0, j = 0, k = 0;
		while(i < left.length || j < right.length){
			if(i >= left.length){res[k++] = right[j++];}
			else if(j >= right.length){res[k++] = left[i++];}
			else{res[k++] = (left[i] <= right[j])? left[i++]: right[j++];}
		}
		return res;
	}
	
	// quicksort: top-down
	public static void quicksort(int[] nums, int start, int end){
		if(start >= end){return;}
		int idx = partition(nums, start, end, nums[end]);
		quicksort(nums, start, idx-1);
		quicksort(nums, idx+1, end);
		return;
	}
	
	public static int partition(int[] nums, int start, int end, int pivot){
		int idxP = end;
		while(start < end){
			while(nums[start] < pivot && start < end){start++;}
			while(start < end && nums[end] >= pivot){end--;}
			swap(nums, start, end);
		}
		swap(nums, start, idxP);
		return start;
	}
	
	public static void swap(int[] nums, int left, int right){
		int tem = nums[left];
		nums[left] = nums[right];
		nums[right] = tem;
		return;
	}
	
	// test
	public static void main(String[] args) {
		int[] nums = {2,4,5,1,3};
		
		int[] res = mergesort(nums, 0, 4);
		for(int n: res){System.out.println(n);}
		
		quicksort(nums, 0, 4);
		for(int n: nums){System.out.println(n);}
	}

}
