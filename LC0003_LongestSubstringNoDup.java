import java.io.*;
import java.util.*;

@TagAnnotation(tags = { "Two_Pointers" })
public class LC0003_LongestSubstringNoDup {
    public static void main(String[] args) {
        String s = "tmmzuxt";
        LC0003_LongestSubstringNoDup sol = new LC0003_LongestSubstringNoDup();
        System.out.println(sol.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        int start = 0, end = 0, maxLength = 0, currentLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char current = s.charAt(end);
            if (map.containsKey(current)) {
                start = Math.max(start, map.get(current) + 1);
                map.put(current, end);
                currentLength = end - start + 1;
                maxLength = Math.max(maxLength, currentLength);
                System.out.println(current + "-currentLength:" + currentLength);
            } else {
                map.put(current, end);
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
                System.out.println(current + "-maxLength:" + currentLength);
            }
            end++;
        }
        return maxLength;
    }
}
