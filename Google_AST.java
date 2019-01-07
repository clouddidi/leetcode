import java.io.*;
import java.util.*;

class TreeNode {
    String val;
    TreeNode left;
    TreeNode right;
    public TreeNode(String v) {
        this.val = v;
    }
}

// @TagAnnotation(tags={"DFS"})
public class Google_AST {
    static Stack<TreeNode> lastNodes = new Stack<>();
    String lastOp;

    public static void main(String[] args) {
        // List<String> input = new LinkedList<>(List.of("1","+","2","*","3","-","4","*","5","*","6","+","7"));
        // List<String> input = new LinkedList<>(List.of("1","*","2","-","3","-","4","*","5","+","6","*","7"));
        // List<String> input = new LinkedList<>(List.of("1","*","2","*","3","-","4","*","5","*","6","+","7"));
        List<String> input = new LinkedList<>(List.of("1","+","2","+","3","*","4","*","5","*","6","*","7"));
        Google_AST ast = new Google_AST();
        TreeNode lastNode = ast.init2Nodes(input, 0);
        lastNodes.push(lastNode);
        TreeNode root = ast.parse(input);
        ast.printTree(root);
    }

    private TreeNode init2Nodes(List<String> input, int index) {
        String v = input.get(index);
        String op = input.get(++index);
        TreeNode root = new TreeNode(op);
        TreeNode left = new TreeNode(v);
        root.left = left;
        return root;
    }

    public TreeNode parse(List<String> input) {
        int index = 2;
        while (index < input.size()) {
            TreeNode lastNode = lastNodes.peek();
            lastOp = lastNode.val;
            String v = input.get(index);
            TreeNode node = new TreeNode(v);
            if (index == input.size() - 1) {
                lastNode.right = node;
                lastNodes.pop();
                if (lastNodes.isEmpty())
                    return lastNode;
                TreeNode prevNode = lastNodes.pop();
                prevNode.right = lastNode;
                while (!lastNodes.isEmpty()) {
                    TreeNode curNode = lastNodes.pop();
                    curNode.right = prevNode;
                    prevNode = curNode;
                }
                return prevNode;
            } else {
                index++;
                String op = input.get(index);
                TreeNode root = new TreeNode(op);
                if (lastOp == "*" || lastOp == "/") {
                    lastNode.right = node;
                    lastNodes.pop();
                    if (lastNodes.isEmpty()) {
                        root.left = lastNode;
                    } else {
                        TreeNode temp = lastNodes.peek();
                        if (op == "*" || op == "/")
                            root.left = lastNode;
                        else {
                            temp.right = lastNode;
                            root.left = temp;
                            lastNodes.pop();
                        }
                    }
                    lastNodes.push(root);
                } else {
                    if (op == "+" || op == "-") {
                        lastNode.right = node;
                        root.left = lastNode;
                        lastNodes.pop();
                        lastNodes.push(root);
                    } else {
                        root.left = node;
                        lastNodes.push(root);
                    }
                }
                index++;
            }
        }
        return null;
    }

    /* Just to do some printing. */
    private List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for(String[] arr:res)
            Arrays.fill(arr,"");
        List<List<String>> ans = new ArrayList<>();
        fill(res, root, 0, 0, res[0].length);
        for(String[] arr:res) {
            ans.add(Arrays.asList(arr));
            for (String s : arr) {
                System.out.print(" " + s);
            }
            System.out.println();
        }
        return ans;
    }

    /* Just to do some printing. */
    private void fill(String[][] res, TreeNode root, int i, int l, int r) {
        if (root == null)
            return;
        res[i][(l + r) / 2] = "" + root.val;
        fill(res, root.left, i + 1, l, (l + r) / 2);
        fill(res, root.right, i + 1, (l + r + 1) / 2, r);
    }

    /* Just to do some printing. */
    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}

