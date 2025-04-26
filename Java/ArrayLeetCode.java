import java.util.*;
import java.util.stream.Collectors;

public class ArrayLeetCode {

    public static int maxProfit(int[] prices) {
        /*
        1.Initialize variables buy with the first element of the prices numsay and profit as 0.
        2.Iterate through the prices starting from the second element.
        3.Update the buy variable if the current price is lower than the current buying price.
        4.Update the profit if the difference between the current price and the buying price is greater than the current profit.
        5.Return the final profit.
                 */
        int n= prices.length;
        int maxProfit=0;
        int buyPrice=prices[0];
        for(int i=1;i<n;i++){
            if (buyPrice>prices[i]){
                buyPrice=prices[i];

            }
            else if (maxProfit < prices[i]-buyPrice){
                maxProfit=prices[i]-buyPrice;
                System.out.println("maxprofit = sellrice - buyprice");
                System.out.println(maxProfit +"="+prices[i]+"-"+buyPrice);
            }
        }

        return maxProfit;
    }

    public static int majorityElement(int[] nums) {
        //brute force
        Map<Integer,Integer> freq = new HashMap<>();
        int countingType=nums.length/2;

        for(int i=0;i<nums.length;i++){
            if (freq.containsKey(nums[i])) {
                freq.put(nums[i],freq.get(nums[i]) + 1);
            }
            else{
                freq.put(nums[i],1);
            }
        }

        int maxCountKey=0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet())
        {
             //&& entry.getValue()> maxCount
            if(entry.getValue()>countingType){
                maxCountKey=entry.getKey();
            }
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        return maxCountKey;
    }


    public static int morreVotingAlgo(int[] nums){
        int freq=0,ans=0;
        for (int i=0;i< nums.length;i++){
            if(freq==0){
                ans=nums[i];
            }
            if(ans==nums[i]){
                freq++;
            }
            else{
                freq--;
            }
        }
        int count=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==ans){
                count++;
            }
        }
        if(count > nums.length/2) return ans;
        else return -1;

    }
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> mySet=new HashSet<>();

        for(int n:nums){
            if(mySet.contains(n)){
                return true;
            }
            else{
                mySet.add(n);
            }
        }
        return false;
    }

    public static void moveZeroes(int[] nums) {
        int i=0;
       for(int j=0;j<nums.length;j++){
            if(nums[j]!=0){

                System.out.println("Found zero");
                int temp=nums[j];
                nums[j]=nums[i];
                nums[i]=temp;
                i++;
            }

        }

        for(int num:nums){
            System.out.print(num+", ");
        }
    }

    public static List<Integer> majorityElementII(int[] nums) {
        List<Integer> finalList =new ArrayList<>();
        //O(n) o(n)
        Map<Integer,Integer> freq = new HashMap<>();
        int countingType=nums.length/3;

        for(int i=0;i<nums.length;i++){
            if (freq.containsKey(nums[i])) {
                freq.put(nums[i],freq.get(nums[i]) + 1);
            }
            else{
                freq.put(nums[i],1);
            }
        }

        int maxCountKey=0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet())
        {
            if(entry.getValue()>countingType ){
                maxCountKey=entry.getKey();
                finalList.add(maxCountKey);
            }
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        return finalList;
    }
    public static int[] sortedSquares(int[] nums) {

        for(int i=0;i<nums.length;i++){
            nums[i]=nums[i]*nums[i];
        }

        Arrays.sort(nums);
        return nums;
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result =new ArrayList<>();
        //index
        int i=0;

        // Add all intervals that come before the new interval
        while(i<intervals.length && intervals[i][1] < newInterval[0]){
            result.add(intervals[i]);
            i++;
        }
        // Merge all overlapping intervals to the new interval
        while(i<intervals.length && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0],newInterval[0]);
            newInterval[1]=Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        result.add(newInterval);
        // Add all remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        // Convert the result list to an array
        return result.toArray(new int[result.size()][]);
    }

    public static boolean canAttendMeetings(List<Interval> intervals) {
        // Definition of Interval class mentioned below
        if(intervals.isEmpty()){
            return true;
        }
        intervals.sort(Comparator.comparingInt(i->i.start));
        int end = intervals.get(0).end;
        for (int i=0;i<intervals.size();i++){
            int start=intervals.get(i).start;
            if(start < end){
                return  false;
            }
            end=intervals.get(i).end;
        }
        return true;
    }

      public class Interval {
          int start, end;
          Interval(int start, int end) {
              this.start = start;
              this.end = end;
          }
      }

    public static int[][] mergeOverlappingInt(int [][] arr){
        if (arr.length == 0) {
            return new int[0][0];
        }
        // Sort the intervals based on their start value
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int[][] mergedIntervals = new int[arr.length][2];
        int index = 0;
        mergedIntervals[index] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int start = arr[i][0];
            int end = arr[i][1];

            // If the current interval does not overlap with the last merged interval, add it
            if (start > mergedIntervals[index][1]) {
                mergedIntervals[++index] = new int[]{start, end};
            } else {
                // Merge the current interval with the last merged interval if they overlap
                mergedIntervals[index][1] = Math.max(end, mergedIntervals[index][1]);

            }
        }
        // Trim the mergedIntervals array to the correct size
        int[][] result = new int[index + 1][2];
        System.arraycopy(mergedIntervals, 0, result, 0, index + 1);

        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
    /*   0 1 2 3 4 5 6 7
       0 =! 1
       0 =! 2
       1 =! 2
       */
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        // Not so optimize
//        Set<List<Integer>> listSet=new HashSet<>();
//        for (int i = 0; i < nums.length - 2; i++) {
//            int j = i + 1;
//            int k = nums.length - 1;
//
//            while (j < k) {
//                int first = nums[i];
//                int second = nums[j];
//                int third = nums[k];
//
//                if (first + second + third == 0) {
//                    List<Integer> innerList = Arrays.asList(first, second, third);
//                    Collections.sort(innerList);
//                    if (!listSet.contains(innerList)) {
//                        listSet.add(innerList);
//                        ans.add(innerList);
//                    }
//                    j++;
//                    k--;
//                } else if (first + second + third > 0) {
//                    k--;
//                } else {
//                    j++;
//                }
//            }
//        }

        // optimize approach
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for i
            if(i>0 && nums[i] == nums[i-1]) continue;
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // Skip duplicates for j and k
                    while(j<k && nums[j]==nums[j+1]) j++;
                    while(j<k && nums[k]==nums[k-1]) k--;
                    j++;
                    k--;
                } else if (sum> 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return  ans;
    }

    public static List<Integer> twoSum(int[] nums,int target){
        List<Integer> ans=new ArrayList<>();
        Arrays.sort(nums);
        int i=0;
        int j=nums.length-1;
        while(i<j){
            if(nums[i]+ nums[j]==target){
                ans.add(i);
                ans.add(j);
            break;
            } else if (nums[i]+ nums[j]>target) {
                j--;
            }
            else {
                i++;
            }
        }
        return ans;
    }

    public static int[] productExceptSelf(int[] nums) {
        /*
        Point to remember you need to know
        how to calculate suffix sum and prefix sum
        */
       int suffix=1;
       int[] ans=new int[nums.length];
       Arrays.fill(ans,1);

       //prefix prod
       for (int i=1;i<nums.length;i++){
           ans[i]=nums[i-1] * ans[i-1];
       }

       //suffix and multi
        for (int i= nums.length-2;i>0 ;i--){
            suffix *= nums[i+1];
            ans[i] *= suffix;
        }
        return  ans;

    }

    static Set<List<Integer>> uniqueSet=new HashSet<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> combination=new ArrayList<>();
        getAllComSum(candidates,0,target,combination,ans);

        return ans;
    }

    public static void getAllComSum(int[] arr,int index,int target,List<Integer> combination,List<List<Integer>> ans){
        //Base case
        if(index == arr.length || target<0){
            return ;
        }
        System.out.println(combination);
        if(target==0){
          if(!uniqueSet.contains(combination)){
                ans.add(new ArrayList<>(combination)); // Add a copy of the combination
               uniqueSet.add(combination);
           }
            return;
        }
        combination.add(arr[index]);
        //single choice
        getAllComSum(arr,index+1,target-arr[index],combination, ans);
        // multiple
        getAllComSum(arr,index,target-arr[index],combination,ans);
        combination.removeLast(); // Backtrack
        //exclude
        getAllComSum(arr,index+1,target,combination,ans);
    }

    public static void sortColors(int[] nums) {
        int mid=0;
        int low=0;
        int high=nums.length-1;
          //Dutch national flag algo(one pass algo)
        while(mid<=high){
            if(nums[mid]==0){
               // swap(nums[low],nums[mid])
               int temp=nums[mid];
               nums[mid]=nums[low];
               nums[low]=temp;
                mid++;
                low++;
            }
        else if(nums[mid]==1){
            mid++;
        }
          else{
                //swap(nums[high],nums[mid]
                int temp=nums[high];
                nums[high]=nums[mid];
                nums[mid]=temp;
                high--;
            }
        }
        System.out.println(Arrays.toString(Arrays.stream(nums).toArray()));
    }

    public static int maxArea(int[] height) {
        int ans=Integer.MIN_VALUE;
//        for(int i=0;i<height.length; i++){
//            for (int j = i+1; j < height.length; j++) {
//                int left_b=height[i];
//                int right_b=height[j];
//                int high=Math.min(left_b,right_b);
//                int width=j-i;
//                int maxWater=high * width;
//                if(ans<maxWater) ans=maxWater;
//            }
//        }
//        return ans;
        int left=0;
        int right=height.length-1;
        int max_area=0;
        while(left<right){
            int high=Math.min(height[left],height[right]);
            int wid=right-left;
            int currAre=high*wid;
            max_area=Math.max(max_area,currAre);
            if (height[left]<height[right]){
                left++;
            }
            else {
                right--;
            }
        }
        return max_area;
    }
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int start=0;
        int currFuel=0;
        int totalGas=0;
        int totalCost=0;
//        if(Arrays.stream(gas).sum()>=Arrays.stream(cost).sum()) {
            for (int i = 0; i < gas.length; i++) {
                totalGas+=gas[i];
                totalCost+=cost[i];
                        currFuel=currFuel+gas[i]-cost[i];
                        if(currFuel<0){
                            currFuel=0;
                            start=i+1;
                       }
                System.out.println("Value of start "+start);
                }
//        }
//        else {
//            return -1;
//        }
        if(totalGas< totalCost){
            return -1;
        }

        return start;
    }

    public static int longestConsecutive(int[] nums) {
   //     int count=1;
   //     int result=1;
//        Arrays.sort(nums);
//        for(int i=1;i<nums.length;i++){
//           if(nums[i] == nums[i-1]){
//               continue;
//           }
//           if(nums[i] == nums[i-1]+1) {
//               count++;
//           }else {
//               count=1;
//           }
//           result =Math.max(result,count);
//
//        }

        Set<Integer> set= Arrays.stream(nums).boxed().collect(Collectors.toSet());
       int longestStreak=0;
        for(int i:set){
            if(!set.contains(i-1)){
                int currentNum=i;
                int currentStreak=1;
                while(set.contains(currentNum +1 )){
                    currentStreak ++;
                    currentNum++;
                }
                longestStreak=Math.max(longestStreak,currentStreak);
            }
        }
        return longestStreak;
    }
    public static void rotate(int[] nums, int k) {
     int left=0,right=nums.length-1;
     k=k%nums.length;

         while(left<right){
             int temp=nums[left];
             nums[left]=nums[right];
             nums[right]=temp;
             left++;
             right--;
         }

         int rleft=k, rright=nums.length-1;
         while (rleft<rright){
             int temp=nums[rleft];
             nums[rleft]=nums[rright];
             nums[rright]=temp;
             rleft++;
             rright--;
         }
         int lleft=0, lright=k-1;
         while (lleft<lright){
             int temp=nums[lleft];
             nums[lleft]=nums[lright];
             nums[lright]=temp;
             lleft++;
             lright--;
         }

        for(int val:nums){
            System.out.print(val+" ");
        }
    }
    public static int findMaxLength(int[] nums) {
        //we have to find sum of sub-array =0 for that we are replacing 0 with -1
        int sum=0;
        int longestStreak = 0;
        HashMap<Integer,Integer> myMap=new HashMap<>();
        // if we have 0,1 array it will result in 0 length to avoid this we're putting 0 in -1 index
        myMap.put(0,-1);
        //converting all zeros to -1
        for(int i=0;i<nums.length; i++){
            if(nums[i]==0){
                nums[i]=-1;
            }
        }

        for(int i=0;i<nums.length;i++){
            sum +=nums[i];
            if(myMap.containsKey(sum)){
                longestStreak = Math.max(longestStreak,i - myMap.get(sum));
            }
            else {
                myMap.put(sum,i);
            }
        }
        System.out.println(myMap);

        return longestStreak;
    }
    public static int subarraySum(int[] nums, int k) {
        int ans=0;
        List<Integer> prefixSum=new ArrayList<>();
        prefixSum.addFirst(nums[0]);

       //prefix sum
        for(int i=1;i<nums.length;i++){
            prefixSum.add(i,prefixSum.get(i-1) + nums[i]);
        }

        Map<Integer,Integer> freq=new HashMap<>();
        freq.put(0, 1); //When we calculate val = prefixSum.get(j) - k;, if prefixSum.get(j) equals k, then val will be 0.

        //check weather prefix sum contains or not
        for(int j=0;j<nums.length;j++){
           int val=prefixSum.get(j)-k;
            if(freq.containsKey(val)){
                ans+=freq.get(val);
            }
           freq.put(prefixSum.get(j),freq.getOrDefault(prefixSum.get(j),0) +1);
}
        return ans;
    }


    public static void main(String[] args) {
        int [] prices = {7,1,5,3,6,4};
        int [] nums = {1,2,3};
        int [] duplicate ={1,2,3,1};
        int [] moveZero = {0,1,5,6,0,6,3,0};
        int [] majELemII = {3,2,3};
        int [] majElemI ={3,2,3};
        int [] sumofsortedar= {-4,-1,0,3,10};
        int [][] meetingInvervals ={{0,30},{5,10},{15,20}};
        int [][]intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int []newInterval = {4,8};
        int [] prodc={1,2,3,0};
        int [] twoS={2,7,11,15};
        int [] threeS={-1,0,1,2,-1,-4};
        int [] candidates = {2,3,6,7};int target = 7;
        int [] sortColorArr = {2,0,2,1,1,0};
        int [] height = {1,8,6,2,5,4,8,3,7};
        int [] gas = {1,2,3,4,5}; int [] cost = {3,4,5,1,2};
        int [] conseNums = {0,3,7,2,5,8,4,6,0,1};
        int [] contArray = {0,1};
        int [] rotateArr = {-1}; int k = 3;
        int [] subar = {1,2,3} ; int sumSub = 3;
        System.out.println("Ans "+subarraySum(subar,sumSub));


        //insert(intervals,newInterval);

//        int[][]mergedIntervals= insert(intervals,newInterval);
//
//        // Print the merged intervals
//        for (int[] interval : mergedIntervals) {
//            System.out.println(Arrays.toString(interval));
//        }
      //  System.out.println(Arrays.toString(productExceptSelf(prodc)));
    }
}
