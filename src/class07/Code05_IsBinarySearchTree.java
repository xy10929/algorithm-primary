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

	public info f(TreeNode n) {
		if (n == null) {
			return null;// base case难以直接设置 则先返回null 在它的父节点处对子节点为null的情况进行判断和处理
		}
		info leftInfo = f(n.left);
		info rightInfo = f(n.right);
		boolean isBST = true;
		int max = n.val;
		int min = n.val;
		if (leftInfo != null) {// 更新max和min 破坏BST条件时把isBST设为false
			max = Math.max(max, leftInfo.max);
			min = Math.min(min, leftInfo.min);
			if (leftInfo.isBST == false || leftInfo.max >= n.val) {
				isBST = false;
			}
		}
		if (rightInfo != null) {
			max = Math.max(max, rightInfo.max);
			min = Math.min(min, rightInfo.min);
			if (rightInfo.isBST == false || rightInfo.min <= n.val) {
				isBST = false;
			}
		}
		return new info(isBST, max, min);
	}

}
