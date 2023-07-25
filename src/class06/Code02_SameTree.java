package class06;

//lc100
public class Code02_SameTree {

	// 不要提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		// 先检查头结点 再递归地检查其左树和右树
		if (p == null && q == null) {// 都空
			return true;
		}
		if (p == null ^ q == null) {// 一空一非空
			return false;
		}
		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}
}
