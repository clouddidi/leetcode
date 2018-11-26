class Solution {
    private boolean isLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            return true;
        return false;
    }

    public String reverseOnlyLetters(String S) {
        if (S == null || S.length() == 0) return "";
        StringBuilder sb = new StringBuilder(S);
        int l = 0, r = sb.length() - 1;
        while (l < r) {
            while (!isLetter(sb.charAt(l)) && l < r) l++;
            while (!isLetter(sb.charAt(r)) && l < r) r--;
            if (l < r) {
                char lchar = sb.charAt(l);
                char rchar = sb.charAt(r);
                sb.replace(l, l + 1, String.valueOf(rchar));
                sb.replace(r, r + 1, String.valueOf(lchar));
                l++;
                r--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String result = sol.reverseOnlyLetters("7_28]");
        System.out.println(result);
    }
}