package class06;

import java.util.HashMap;

//lc105
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal {
	
	// 不要提交这个类
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public TreeNode buildTree(int[] pre, int[] in) {
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			indexMap.put(in[i], i);// 用表记录in[]的每个值的index
		}

		return f(pre, 0, pre.length - 1, in, 0, in.length - 1, indexMap);
	}

	public static TreeNode f(int[] pre, int l1, int r1, int[] in, int l2, int r2, HashMap<Integer, Integer> indexMap) {
		if (l1 > r1) {
			return null;
		}
		TreeNode head = new TreeNode(pre[l1]);
		if (l1 == r1) {// 只有一个节点
			return head;
		}
		int find = indexMap.get(pre[l1]);// 查出头结点在in[]中的位置
		int p = find - l2 + l1;// in[]中l2到find - 1构成左树 同样个数的节点在pre[]中为l1 + 1到p
		head.left = f(pre, l1 + 1, p, in, l2, find - 1, indexMap);
		head.right = f(pre, p + 1, r1, in, find + 1, r2, indexMap);
		return head;
	}
}
