import java.io.*;
import java.util.*;

@TagAnnotation(tags = { "Stack" })
public class LC0084_LargestRectangle {
    public static void main(String[] args) {
        // int[] heights = new int[] {2, 1, 5, 6, 2, 3};
        int[] heights = new int[] {2, 1, 2};
        LC0084_LargestRectangle sol = new LC0084_LargestRectangle();
        System.out.println(sol.largestRectangleArea(heights));
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        
        int[] newHeights = new int[heights.length + 1];
        for (int i = 0; i < heights.length; i++) {
            newHeights[i] = heights[i];
        }
        newHeights[heights.length] = 0;

        int index = 0, max = 0;
        Stack<Integer> stack = new Stack<>();
        while (index < newHeights.length) {
            if (!stack.isEmpty()) {
                Integer last = stack.peek();
                if (newHeights[index] >= newHeights[last])
                    stack.push(index);
                else {
                    // pop all the popables in stack
                    while (!stack.isEmpty()) {
                        last = stack.peek();
                        if (newHeights[index] < newHeights[last]) {
                            last = stack.pop();
                            int width = 0;
                            if (stack.isEmpty()) {
                                width = index;
                                System.out.println("width=" + width + ",last-" + last + ",index-" + index);
                            }
                            else {
                                width = index - stack.peek() - 1;
                                System.out.println("width=" + width + ",prelast-" + stack.peek() + ",index-" + index);
                            }
                            max = Math.max(max, newHeights[last] * width);
                            System.out.println("max=" + max + ",h*w=" + newHeights[last] + "*" + width);
                        } else {
                            break;
                        }
                    }
                    stack.push(index);
                }
            } else {
                stack.push(index);
            }
            index++;
        }

        return max;
    }
}
