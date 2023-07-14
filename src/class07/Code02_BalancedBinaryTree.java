package class07;

//lc110
public class Code02_BalancedBinaryTree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}
//  ⑴假设以入参节点为头 可以向其左树和右树要任何信息 分析获得题目结果需要的信息的全集
//	⑵定义info类 将相关信息封装在内
//	⑶定义递归函数 参数为树的节点 返回info类型的对象
//	⑷base case若难以确定则设为null  在上游分析
//	⑸用递归函数获得左树和右树的info
//	⑹整合求出入参的info信息并返回

	public boolean isBalanced(TreeNode root) {
		return process(root).isb;
	}

	public static class info {
		public boolean isb;
		public int height;

		public info(boolean isb, int height) {
			this.isb = isb;
			this.height = height;
		}
	}

	public static info process(TreeNode root) {
		if (root == null) {
			return new info(true, 0);
		}
		info leftinfo = process(root.left);
		info rightinfo = process(root.right);
		int height = Math.max(leftinfo.height, rightinfo.height) + 1;
		boolean isb = leftinfo.isb && rightinfo.isb && Math.abs(leftinfo.height - rightinfo.height) <= 1;
		return new info(isb, height);
	}
}
