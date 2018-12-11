//从右往左建立当前可替换最大值数组 比如原数组2736 新数组为7766 新数组表示在这个index上原数组可以被替换的最大值 然后从左到右比较原数组和最大值数组即可 只需要一个for循环
public class Solution {
    /**
     * @param num: a non-negative intege
     * @return: the maximum valued number
     */
    public int maximumSwap(int num) {
        // Write your code here
        String snum = String.valueOf(num);
        char[] order = new char[snum.length()];
        int curMax = 0;
        for (int i = snum.length() - 1; i >= 0; i--) {
            curMax = Math.max(curMax, snum.charAt(i) - '0'); 
            order[i] = String.valueOf(curMax).charAt(0);
        }
        // System.out.println(new String(order));
        int p1 = -1, p2 = -1;
        for (int i = 0; i < snum.length(); i++) {
            if (snum.charAt(i) < order[i]) {
                // we find the place to swap
                p1 = i;
                p2 = order[i] - '0';
                break;
            }
        }
        if (p1 == -1) // no swap
            return num;
            
        // System.out.println(p1 + "," + p2);
        for (int i = order.length - 1; i > 0; i--) {
            if (order[i] - '0' == p2) {
                p2 = i;
                break;
            }
        }
        // System.out.println(p1 + "," + p2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < snum.length(); i++) {
            if (i == p1)
                sb.append(snum.charAt(p2));
            else if (i == p2)
                sb.append(snum.charAt(p1));
            else
                sb.append(snum.charAt(i));
        }        
        
        return Integer.valueOf(sb.toString());
    }
}