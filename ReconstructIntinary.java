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
        return false;
    }
    
}
