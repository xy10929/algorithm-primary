package class04;

//lc206
public class Code01_ReverseList {

	// 不要提交这个类
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	// 下(头下)前头
	// 双-下头下(头上下)前头
	public ListNode reverseList(ListNode head) {
		// head记录尚未完成反转的部分的头
		ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;// 提前记录下一个要处理的节点
			head.next = pre;// 反转链
			pre = head;// 用pre记录已完成反转的部分的最新节点 它将作为下一次反转的目标
			head = next;// head到达之前记录的下一个要处理的节点
		}
		return pre;
	}

}