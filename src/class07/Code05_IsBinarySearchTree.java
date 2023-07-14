package class07;

//lc98
public class Code05_IsBinarySearchTree {

	// 不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public boolean isValidBST(TreeNode root) {
		return process(root).isBST;
	}

	public class info {
		public boolean isBST;
		public int max;
		public int min;

		public info(boolean isBST, int max, int min) {
			this.isBST = isBST;
			this.max = max;
			this.min = min;
		}
	}

	public info process(TreeNode n) {
		if (n == null) {
			return null;// base case难以直接设置 则先返回null 在它的父节点处对子节点为null的情况进行判断和处理
		}
		info leftinfo = process(n.left);
		info rightinfo = process(n.right);
		int max = n.val;
		int min = n.val;
		if (leftinfo != null) {
			max = Math.max(max, leftinfo.max);
			min = Math.min(min, leftinfo.min);
		}
		if (rightinfo != null) {
			max = Math.max(max, rightinfo.max);
			min = Math.min(min, rightinfo.min);
		}
		boolean isBST = true;// 先设为true 任何不满足搜索的条件都会把isBST更新为false
		if (leftinfo != null && leftinfo.isBST == false) {
			isBST = false;
		}
		if (rightinfo != null && rightinfo.isBST == false) {
			isBST = false;
		}
		if (leftinfo != null && leftinfo.max >= n.val) {
			isBST = false;
		}
		if (rightinfo != null && rightinfo.min <= n.val) {
			isBST = false;
		}
		return new info(isBST, max, min);
	}
}
