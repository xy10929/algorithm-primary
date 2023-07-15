package class07;

//lc112
public class Code03_PathSum {

	// 不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return false;
		}
		return process(root, targetSum);
	}

	public boolean process(TreeNode n, int rest) {
		if (n.left == null && n.right == null) {// 无左右节点 直接对比目标值和当前节点值
			return n.val == rest;
		}
		boolean ans = false;
		// 对于左/右节点 递归调用f(子节点,目标值-当前节点值) 将结果或进ans
		ans |= n.left != null ? process(n.left, rest - n.val) : false;
		ans |= n.right != null ? process(n.right, rest - n.val) : false;
		return ans;
	}
}
