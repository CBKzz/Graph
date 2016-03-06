/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
*/
//this is the method that I fixed all the bugs. 
public class Solution {
    private List<String> result=new ArrayList<>();
    private int result_length;
    public List<String> findItinerary(String[][] tickets) {
       this.result_length=tickets.length+1;
       Map<String,List<String>> map=new HashMap<>();
       for(int i=0;i<tickets.length;i++){
           if(map.containsKey(tickets[i][0])) map.get(tickets[i][0]).add(tickets[i][1]);
           else {
               List<String> temp=new ArrayList<>();
               temp.add(tickets[i][1]);
               map.put(tickets[i][0],temp);
           }
       }
       for(List<String> list:map.values()){
           Collections.sort(list);
       }
       List<String> list=new ArrayList<>();
       list.add("JFK");
       DFS("JFK",map,list);
       return result;
    }
    public boolean DFS(String headKey,Map<String,List<String>> map,List<String> list){
        if(!map.containsKey(headKey)||map.get(headKey).isEmpty()){
            if(list.size()!=result_length)  return false;
            result=list;
            return true;
        }
        for(int i=0;i<map.get(headKey).size();i++){
            List<String> temp=new ArrayList<>(map.get(headKey));//original
            String head=map.get(headKey).remove(i);
            List<String> temp1=new ArrayList<String>(list);
            temp1.add(head);
            if(DFS(head,map,temp1)) return true;
            map.put(headKey,temp);
        }
        /*
        modified for loop that runs faters
        int l=map.get(headKey).size();
        for(int i=0;i<l;i++){
            String head=map.get(headKey).remove(0);
            list.add(head);
            if(DFS(head,map,list)) return true;
            list.remove(list.size()-1);
            map.get(headKey).add(head);
        }
        */
        return false;
    }
    
}
//this is the original one
/*
Several reasons with time exceed:
1. The problem has told us he starts with JFK. So we don't have to test every head
2. JFK->ABC, DEF, HJK
    Why do we sort the list first?
    If ABC and DEF are both satisfied answers, then it's cool, when we finished with ABC, we don't have to go to DEF.
    The only case that we go through everything is that ABD and DEF are not a complete path
*/
public class Solution {
    private List<String> result=new ArrayList<>();
    private int result_length;
    public List<String> findItinerary(String[][] tickets) {
       this.result_length=tickets.length+1;
       Map<String,List<String>> map=new HashMap<>();
       for(int i=0;i<tickets.length;i++){
           if(map.containsKey(tickets[i][0])) map.get(tickets[i][0]).add(tickets[i][1]);
           else {
               List<String> temp=new ArrayList<>();
               temp.add(tickets[i][1]);
               map.put(tickets[i][0],temp);
           }
       }
       for(String head:map.keySet()){
           List<String> temp=new ArrayList<>();
           temp.add(head);
           DFS(head,map,temp);
            
       }
       return result;
       
    }
    public void DFS(String headKey,Map<String,List<String>> map,List<String> list){
        if(!map.containsKey(headKey)||map.get(headKey).isEmpty()){
            
            if(list.size()!=result_length)  return;
            result=compareList(list,result);
            return;
        }
        for(int i=0;i<map.get(headKey).size();i++){
            
            List<String> temp=new ArrayList<>(map.get(headKey));//original
            String head=map.get(headKey).remove(i);
            List<String> temp1=new ArrayList<String>(list);
            temp1.add(head);
            DFS(head,map,temp1);
            map.put(headKey,temp);
            
        }
    }
    public List<String> compareList(List<String> list1, List<String> list2){
        if(list2.size()==0) return list1;
        for(int i=0;i<list1.size();i++){
            String a=list1.get(i);
            String b=list2.get(i);
            if(a.compareTo(b)==0) continue;
            if(a.compareTo(b)==1) return list2;
            return list1;
        }
        return list1;
    }
}
