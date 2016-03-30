/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> result=new ArrayList<>();
        result.add(triangle.get(0).get(0));
        for(int i=1;i<triangle.size();i++){
            List<Integer> temp=triangle.get(i);
            List<Integer> t_re=new ArrayList<>();
            t_re.add(temp.get(0)+result.get(0));
            for(int j=1;j<temp.size()-1;j++){
                t_re.add(temp.get(j)+Math.min(result.get(j-1),result.get(j)));
            }
            t_re.add(temp.get(temp.size()-1)+result.get(result.size()-1));
            result=t_re;
        }
        int min=Integer.MAX_VALUE;
        for(int i:result){
            if(i<=min) min=i;
        }
        return min;
    }
}
