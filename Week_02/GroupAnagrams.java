import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] times = new int[26];
        for(int i = 0; i < strs.length; i ++) {
            Arrays.fill(times, 0);
            String str = strs[i];
            for(int j = 0; j < str.length(); j ++){
                times[str.charAt(j) - 'a'] ++;
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < times.length; j ++){
                sb.append('#');
                sb.append(times[j]);
            }
            String key = sb.toString();
            List<String> ss;
            if(map.containsKey(key)){
                ss = map.get(key);
            }else{
                ss = new ArrayList<>();
                map.put(key, ss);
            }
            ss.add(str);
        }
        return new ArrayList<>(map.values());
    }

}
