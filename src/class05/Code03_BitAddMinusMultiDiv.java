package class05;
//lc29
public class Code03_BitAddMinusMultiDiv {
	public static int multi(int a, int b) {
		int res = 0;
		while (b != 0) {//第二个乘数为0时 表示其每一位都与第一个乘数相乘过了
			if ((b & 1) != 0) {//确认第二个乘数当前为是否为1
				res += a;//为1 则把左移的b的1与a相乘的结果(转换为左移的a乘以b的1)累加进res
			}
			a <<= 1;
			b >>>= 1;//第二个乘数每次右移 以把下一位次低位的结果移到最低位
		}
		return res;
	}

	public static int div(int a, int b) {
		int x = Math.abs(a);
		int y = Math.abs(b);//转为绝对值
		int ans = 0;
		for (int i = 30; i >= 0; i--) {//i表示移动的位数 从最大可能向下尝试 找到符合条件的极限位置 x确保非负 最高位为0 所以不需要把它移到最低位去尝试
			if ((x >> i) >= y) {//被除数x右移来接进除数y 等价于 y左移接近x 防止y左移至符号位
				ans |= (1 << i);//移动的位数对应的位置商1
				x -= (y << i);//从被除数减去
			}
		}
		return (a < 0) ^ (b < 0) ? -ans : ans;
	}

	public int divide(int a, int b) {
		if (a == b) {
			return 1;
		} else if (b == Integer.MIN_VALUE) {//肯定不够除
			return 0;
		} else if (a == Integer.MIN_VALUE) {
			if (b == -1) {
				return Integer.MAX_VALUE;//题目要求
			} else {
				int c = div(a + 1, b);//作为被除数的系统最小+1 令其取绝对值为系统最大 先用其做被除数
				int d = div(a - multi(b, c), b);// 计算得到的商*除数和实际被除数的差 检查其是否能被除数整除
				return c + d;//两步的商相加得到真正的商
			}
		} else {
			return div(a, b);
		}
	}
}
