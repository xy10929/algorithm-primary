package class07;

//lc113
import java.util.ArrayList;
import java.util.List;

public class Code04_PathSumII {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		List<Integer> path = new ArrayList<>();
		process(root, path, 0, targetSum, ans);
		return ans;
	}

	// 当前到达的节点 之前的路径 之前路径的和 目标和 有效路径的list
	public static void process(TreeNode n, List<Integer> path, int preSum, int sum, List<List<Integer>> ans) {
		if (n.left == null && n.right == null) {// base case
			if (preSum + n.val == sum) {
				path.add(n.val);
				ans.add(copy(path));// path按引用传递 所以需要复制一份相同的path传入
				path.remove(path.size() - 1);// 即将返回给父节点 所以需要恢复现场
			}
			return;
		}
		path.add(n.val);
		preSum += n.val;// 将该节点的信息加入path和preSum
		if (n.left != null) {
			process(n.left, path, preSum, sum, ans);
		}
		if (n.right != null) {
			process(n.right, path, preSum, sum, ans);
		}
		path.remove(path.size() - 1);// 恢复现场
	}

	public static List<Integer> copy(List<Integer> path) {
		List<Integer> ans = new ArrayList<>();
		for (Integer num : path) {
			ans.add(num);
		}
		return ans;
	}
}
