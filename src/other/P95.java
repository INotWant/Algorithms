package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P95 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n <= 0)
            return result;
        if (n == 1) {
            result.add(new TreeNode(1));
            return result;
        }
        int[] array = new int[(int) Math.pow(2, n)];
        Arrays.fill(array, -1);
        array[1] = 1;
        helper(1, 1, 1, n, array, result);
        return result;
    }

    private void helper(int pos, int endPos, int count, int n, int[] array, List<TreeNode> result) {
        for (; pos <= endPos; ++pos) {
            if (array[pos] != -1) {
                for (int j = 1; j <= 2; j++) {
                    if (j == 1) {
                        ++count;
                        if (count == n) {
                            TreeNode node = createTree(array, pos, endPos, 1, count);
                            Data.count = 0;
                            modifyTree(node);
                            result.add(node);
                            node = createTree(array, pos, endPos, 2, count);
                            Data.count = 0;
                            modifyTree(node);
                            result.add(node);
                            --count;
                            break;
                        } else if (count > n) {
                            return;
                        } else {
                            array[pos * 2] = count;
                            helper(pos + 1, endPos > pos * 2 ? endPos : (pos * 2), count, n, array, result);
                            array[pos * 2] = -1;
                            array[pos * 2 + 1] = count;
                            helper(pos + 1, endPos > pos * 2 + 1 ? endPos : (pos * 2 + 1), count, n, array, result);
                            array[pos * 2 + 1] = -1;
                            --count;
                        }
                    } else if (count > n) {
                        return;
                    } else {
                        count += 2;
                        if (count == n) {
                            TreeNode node = createTree(array, pos, endPos, 3, count);
                            Data.count = 0;
                            modifyTree(node);
                            result.add(node);
                            count -= 2;
                            break;
                        } else {
                            array[pos * 2] = count - 1;
                            array[pos * 2 + 1] = count;
                            helper(pos + 1, endPos > pos * 2 + 1 ? endPos : (pos * 2 + 1), count, n, array, result);
                            array[pos * 2] = -1;
                            array[pos * 2 + 1] = -1;
                            count -= 2;
                        }
                    }
                }
            }
        }
    }

    private static class Data {
        static int count = 0;
    }

    private void modifyTree(TreeNode node) {
        if (node != null) {
            if (node.left != null)
                modifyTree(node.left);
            node.val = ++Data.count;
            if (node.right != null)
                modifyTree(node.right);
        }
    }

    /**
     * @param flag 1 指 pos 添加左节点; 2 指 pos 添加右节点; 3 指 pos 添加左右节点
     */
    private TreeNode createTree(int[] array, int pos, int endPos, int flag, int count) {
        TreeNode[] nodes = new TreeNode[endPos + 1];
        TreeNode node;
        for (int i = 1; i <= endPos / 2 || i == 1; i++) {
            int num = array[i];
            if (num != -1) {
                if (i == 1) {
                    node = new TreeNode(num);
                    nodes[1] = node;
                } else
                    node = nodes[i];
                if (array[i * 2] != -1) {
                    nodes[i * 2] = new TreeNode(array[2 * i]);
                    node.left = nodes[i * 2];
                }
                if (array[i * 2 + 1] != -1) {
                    nodes[i * 2 + 1] = new TreeNode(array[2 * i + 1]);
                    node.right = nodes[i * 2 + 1];
                }
            }
        }
        if (flag == 1) {
            nodes[pos].left = new TreeNode(count);
        } else if (flag == 2) {
            nodes[pos].right = new TreeNode(count);
        } else {
            nodes[pos].left = new TreeNode(count - 1);
            nodes[pos].right = new TreeNode(count);
        }
        return nodes[1];
    }

    public static void main(String[] args) {
        P95 p95 = new P95();
        List<TreeNode> tree = p95.generateTrees(3);
        System.out.println("END");
    }

}
