# algorithm-primary

Leetcode problems in data stucture & algorithm course by [Chengyun Zuo](https://github.com/algorithmzuo/algorithm-primary)

- [class01](#class01)
- [class02](#class02)
- [class03](#class03)
- [class04](#class04)
  - [lc206 反转单链表](#lc206)
  - [lc25 k 个节点一组 逆序单链表](#lc25)
  - [lc2 两个链表代表的数相加](#lc2)
  - [lc21 合并两个有序链表](#lc21)
- [class05](#class05)
  - [lc29 不用乘除模 实现除法](#lc29)
- [class06](#class06)
  - [lc23 合并 n 个有序链表](#lc23)
  - [lc100 判断两棵二叉树是否完全相同](#lc100)
  - [lc101 判断二叉树是否镜像](#lc101)
  - [lc104 求二叉树的最大深度](#lc104)
  - [lc105 根据先序 中序结果建立二叉树](#lc105)
- [class07](#class07)
  - [lc107 从下向上按层返回二叉树的遍历结果](#lc107)
  - [lc110 判断是否为平衡二叉树](#lc110)
  - [lc112 判断二叉树从头到底部是否存在路径 和为目标值](#lc112)
  - [lc113 返回二叉树从头到底部和为目标值的所有路径](#lc113)
  - [lc98 判断是否为搜索二叉树](#lc98)
- [class08](#class08)

## class01

## class02

## class03

## class04

### lc206

@反转单链表

下头<u>下前</u>头

```java
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
```

### lc25

@k 个节点一组 逆序单链表

```java
public ListNode reverseKGroup(ListNode head, int k) {
  ListNode start = head;
  ListNode end = getGroupEnd(start, k);
  if (end == null) {// 一组也凑不齐
    return head;
  }
  head = end;// 第一组逆序后的头为整个链最后的头 不变
  reverse(start, end);// 完成组内反转 但start和end指向的节点不变
  ListNode lastEnd = start;// 上一组的结尾节点即start
  while (lastEnd.next != null) {
    start = lastEnd.next;// 找到下一组的start
    end = getGroupEnd(start, k);
    if (end == null) {
      return head;
    }
    reverse(start, end);
    lastEnd.next = end;// 反转后end为当前组的开头 被上一组的结尾指向
    lastEnd = start;// start反转后为当前组的结尾 也即此后的上一组的结尾
  }
  return head;
}

public static ListNode getGroupEnd(ListNode start, int k) {// start开始数k个 返回组内最后一个 不够k个会返回null
  while (--k != 0 && start != null) {
    start = start.next;
  }
  return start;
}

public static void reverse(ListNode start, ListNode end) {// start到end逆序
  end = end.next;// 记录逆序后要连向的位置
  ListNode pre = null;
  ListNode next = null;
  ListNode head = start;
  while (head != end) {
    next = head.next;
    head.next = pre;
    pre = head;
    head = next;
  }
  start.next = end;// 原头为逆序后的尾 连向记录好的位置
}
```

### lc2

@两个链表从左到右分别表示一个数 把它们相加 返回代表结果的新链表

求出两链表的长度 根据<u>长短链表中有无遍历到的节点</u>分 3 部分计算 结果放入长链表

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
  int len1 = listLength(l1);
  int len2 = listLength(l2);
  ListNode l = len1 > len2 ? l1 : l2;
  ListNode s = l == l1 ? l2 : l1;
  ListNode curL = l;
  ListNode curS = s;
  int carry = 0;// 进位
  int num = 0;// 和
  ListNode last = curL;// 跟踪最后一个节点 便于往后加节点
  while (curS != null) {
    num = curS.val + curL.val + carry;
    curL.val = num % 10;
    carry = num / 10;
    last = curL;
    curS = curS.next;
    curL = curL.next;
  }
  while (curL != null) {
    num = curL.val + carry;
    curL.val = num % 10;
    carry = num / 10;
    last = curL;
    curL = curL.next;
  }
  if (carry != 0) {
    last.next = new ListNode(1);
  }
  return l;
}

public int listLength(ListNode head) {
  int ans = 0;
  while (head != null) {
    head = head.next;
    ans++;
  }
  return ans;
}
```

### lc21

@合并两个有序链表 使返回的链表仍有序

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
  if (l1 == null || l2 == null) {
    return l1 == null ? l2 : l1;
  }
  ListNode head = l1.val < l2.val ? l1 : l2;// 较小的为head
  ListNode cur1 = head.next;
  ListNode cur2 = head == l1 ? l2 : l1;
  ListNode pre = head;// 已连好的串的结尾
  while (cur1 != null && cur2 != null) {
    if (cur1.val < cur2.val) {
      pre.next = cur1;
      cur1 = cur1.next;
    } else {
      pre.next = cur2;
      cur2 = cur2.next;
    }
    pre = pre.next;
  }
  pre.next = cur1 != null ? cur1 : cur2;// 一个链已连完 另一个链的剩余部分直接接上
  return head;
}

```

## class05

### lc29

@不用乘 除 模 实现除法

乘法  
根据乘法算式 第二个乘数当前位如果为 1 则照抄第一个乘数到应放的位置(位移后累加进结果) 如果为 0 则跳过

除法  
把除数左移 直到不超过被除数的极限位置 被除数减去该结果 当前位商 1

```java
public static int multi(int a, int b) {
  int res = 0;
  while (b != 0) {// 第二个乘数为0时 表示其每一位都与第一个乘数相乘过了
    if ((b & 1) != 0) {// 确认第二个乘数当前为是否为1
      res += a;// 为1 则把左移的b的1与a相乘的结果(转换为左移的a乘以b的1)累加进res
    }
    a <<= 1;
    b >>>= 1;// 第二个乘数每次右移 以把下一位次低位的结果移到最低位
  }
  return res;
}

public static int div(int a, int b) {
  int x = Math.abs(a);
  int y = Math.abs(b);// 转为绝对值
  int ans = 0;
  for (int i = 30; i >= 0; i--) {// i表示移动的位数 从最大可能向下尝试 找到符合条件的极限位置 x确保非负 最高位为0 所以不需要把它移到最低位去尝试
    if ((x >> i) >= y) {// 被除数x右移来接进除数y 等价于 y左移接近x 防止y左移至符号位
      ans |= (1 << i);// 移动的位数对应的位置商1
      x -= (y << i);// 从被除数减去
    }
  }
  return (a < 0) ^ (b < 0) ? -ans : ans;
}

public int divide(int a, int b) {
  if (a == b) {
    return 1;
  } else if (b == Integer.MIN_VALUE) {// 肯定不够除
    return 0;
  } else if (a == Integer.MIN_VALUE) {
    if (b == -1) {
      return Integer.MAX_VALUE;// 题目要求
    } else {
      int c = div(a + 1, b);// 作为被除数的系统最小+1 令其取绝对值为系统最大 先用其做被除数
      int d = div(a - multi(b, c), b);// 计算得到的商*除数和实际被除数的差 检查其是否能被除数整除
      return c + d;// 两步的商相加得到真正的商
    }
  } else {
    return div(a, b);
  }
}
```

## class06

### lc23

@合并 n 个有序链表

将每一个头节点放入小根堆 每次谈出值最小的节点 放入其 next 节点  
定义比较器来对节点排序

```java
public static class ListNode {
  public int val;
  public ListNode next;
}

public ListNode mergeKLists(ListNode[] lists) {
  if (lists == null) {
    return null;
  }
  PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
  for (int i = 0; i < lists.length; i++) {
    if (lists[i] != null) {
      heap.add(lists[i]);//抓取每个链表的头结点
    }
  }
  if (heap.isEmpty()) {//所以链表都为空
    return null;
  }
  ListNode head = heap.poll();//从小根堆弹出 作为返回链表的头
  ListNode pre = head;//已穿好的部分的结尾
  if (pre.next != null) {
    heap.add(pre.next);//弹出节点后将其next节点加入小根堆
  }
  while (!heap.isEmpty()) {
    ListNode cur = heap.poll();
    pre.next = cur;//更新pre
    pre = cur;
    if (cur.next != null) {
      heap.add(cur.next);
    }
  }
  return head;
}

public static class ListNodeComparator implements Comparator<ListNode> {

  @Override
  public int compare(ListNode L1, ListNode L2) {
    return L1.val - L2.val;//如果第一个参数较小 且要把它排在前 则需返回负数
  }
}
```

### lc100

@判断两棵二叉树是否完全相同

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
  // 先检查头结点 再递归地检查其左树和右树
  if (p == null ^ q == null) {// 一空一非空
    return false;
  }
  if (p == null && q == null) {// 都空
    return true;
  }
  return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```

### lc101

@判断二叉树是否镜像

把该树看作相同头节点的两颗树 判断它们是否镜像

```java
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
```

### lc104

@求二叉树的最大深度

```java
public int maxDepth(TreeNode root) {
  if (root == null) {
    return 0;
  }
  return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```

### lc105

@根据先序 中序结果建立二叉树

在函数中  
整个树的头为先序第一个数 在中序中找到这个数 左树即由这个数之前的数组成 确定数字个数后可知在先序中的 index 范围 左树可由递归调用该函数生成  
可以用表记录 in[]每个值的位置 找 find 时直接查表

```java
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
```

## class07

### lc107

@从下向上按层返回二叉树的遍历结果

用 LinkedList 收集代表每一层的 list 新生成的 list 每次都放在最前
头节点放入队列  
队列非空时 循环进行下列操作  
⑴ 求队列的 size 进行 size 次的 ⑵-size 会变化 先抓取当前层的节点个数  
⑵ 弹出节点 把它的 val 加入当前层的队列 然后将其左右节点(如果有)加入队列  
⑶ 把当前层队列放入结果队列的 0 位置

```java
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
```

### lc110

@判断是否为平衡二叉树

```java
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
```

### lc112

@判断二叉树从头到底部是否存在路径 和为目标值

⑴ 节点已无左右节点-节点的值是否为目标值  
⑵ 返回值先设为 false  
❶ 有左节点 则递归调用 f(左节点,目标值-当前节点值) 将结果或进 ans  
❷ 有右节点 则递归调用 f(右节点,目标值-当前节点值) 将结果或进 ans

```java
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
```

### lc113

@返回二叉树从头到底部和为目标值的所有路径

```java
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
```

### lc98

@判断是否为搜索二叉树

```java
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
```

## class08
