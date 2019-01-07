    class SegmentTreeNode {
        int start, end, count;
        SegmentTreeNode left, right;

        SegmentTreeNode(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
            left = right = null;
        }
    }
    
    // Build the Segment Tree
    private SegmentTreeNode buildHelper(int start, int end) {
        if (start > end) {
            return null;
        }

        SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
        if (start == end) {
            return root;
        }

        int mid = root.start + (root.end - root.start) / 2;
        root.left = buildHelper(start, mid);
        root.right = buildHelper(mid + 1, end);
        root.count = 0;

        return root;
    }

    // Query the count of numbers
    private int query(SegmentTreeNode root, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (start <= root.start && end >= root.end) {
            return root.count;
        }

        int mid = root.start + (root.end - root.start) / 2;
        int leftCount = 0, rightCount = 0;

        if (start <= mid) {
            leftCount = query(root.left, start, end);
        }

        if (end > mid) {
            rightCount = query(root.right, start, end);
        }

        return leftCount + rightCount;
    }

    private void modify(SegmentTreeNode root, int index, int value) {
        if (root.start > index || root.end < index) {
            return;
        }

        if (root.start == index && root.end == index) {
            root.count += value;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }

        root.count = root.left.count + root.right.count;
    }