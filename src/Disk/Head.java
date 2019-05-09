package Disk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Head {

	private static int headLocation = 100;	// 磁头所在磁道号
	private static char direction = 'U';	// 磁头移动方向, 'U'表示磁道号增加方向, 'D'表示磁道号减少方向
	private static List<Integer> order = new ArrayList<>();				// 存储运行顺序
	
	// 扫描算法电梯调度
	public static void Scan(List<Integer> pl) {
		List<Integer> temp = new ArrayList<>();			// 保存所有在磁头移动方向上的值
		while(!pl.isEmpty()) {

			for (int i=0; i<pl.size(); i++) {
				if (direction == 'U') {
					if (pl.get(i) >= headLocation) {
						temp.add(pl.get(i));
					}
				}
				else if (direction == 'D') {
					if (pl.get(i) <= headLocation) {
						temp.add(pl.get(i));
					}
				}
			}
			
			if (!temp.isEmpty()) {
				// 找出最优先进程并运行
				if (direction == 'U') {
					int minPro = Collections.min(temp);
					int index = pl.indexOf(minPro);
					System.out.println("Run Process:" + minPro);
					order.add(minPro);
					pl.remove(index);
				}
				else if (direction == 'D') {
					int maxPro = Collections.max(temp);
					int index = pl.indexOf(maxPro);
					System.out.println("Run Process:" + maxPro);
					order.add(maxPro);
					pl.remove(index);
				}
			}

			// 若该磁头移动方向再无可运行进程， 则改变运行方向
			else{
				direction = direction == 'U'?'D':'U';
			}

			while(!temp.isEmpty()) {
				temp.remove(0);
			}
		}
	}
	
	public static void preLength() {
		
		double length = 0;
		for (int i=0; i<order.size()-1; i++) {
			int firstNum = order.get(i);
			int secondNum = order.get(i+1);
			length += (firstNum>=secondNum)?(firstNum-secondNum):(secondNum-firstNum);
		}
		length /= order.size();
		System.out.println("average length:" + length);
	}
}
