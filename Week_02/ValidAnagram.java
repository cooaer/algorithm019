import java.util.HashMap;
import java.util.Map;

//有效的异位词
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] times = new int[26];
        for (int i = 0; i < s.length(); i++) {
            times[s.charAt(i) - 'a']++;
            times[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < times.length; i++) {
            if (times[i] != 0) {
                return false;
            }
        }
        return true;
    }


    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] times = new int[26];
        for (int i = 0; i < s.length(); i++) {
            times[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--times[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> times = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            times.put(s.charAt(i), times.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            int n = times.getOrDefault(t.charAt(i), 0);
            if (n == 0) {
                return false;
            }
            times.put(t.charAt(i), n - 1);
        }
        return true;
    }

}
