package class07;

//lc107
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code01_BinaryTreeLevelOrderTraversalII {

	// 不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> ans = new LinkedList<>();// LinkedList收集代表每一层节点的list 新生成的list每次都放在最前
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();// 获取队列的size 即当前层的节点个数
			List<Integer> curList = new LinkedList<>();
			for (int i = 0; i < size; i++) {// 对应每个当前层的节点 弹出并记录val 把它的左右节点加入队列
				TreeNode cur = queue.poll();
				curList.add(cur.val);
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			ans.add(0, curList);// 把当前层的list加入ans的0位置 实现从上到下的逆序
		}
		return ans;
	}
}
