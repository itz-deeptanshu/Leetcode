class Solution {
    public int[] twoSum(int[] nums, int target) {
        int a =  nums.length;
        for(int i = 0; i < a; i++) {
            int ch = target - nums[i];
            for(int j = i+1; j < a; j++){
                if( ch == nums[j]){
                    return new int []{i,j};
                }
            }
        }
        return null;
    }
}