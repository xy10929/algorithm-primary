package class06;

//lc101
public class Code03_SymmetricTree {

	// 不要提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	public boolean isMirror(TreeNode n1, TreeNode n2) {
		if (n1 == null ^ n2 == null) {
			return false;
		}
		if (n1 == null && n2 == null) {
			return true;
		}
		return n1.val == n2.val && isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);
	}

}
