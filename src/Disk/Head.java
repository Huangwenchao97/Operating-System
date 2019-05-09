package Disk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Head {

	private static int headLocation = 100;	// ��ͷ���ڴŵ���
	private static char direction = 'U';	// ��ͷ�ƶ�����, 'U'��ʾ�ŵ������ӷ���, 'D'��ʾ�ŵ��ż��ٷ���
	private static List<Integer> order = new ArrayList<>();				// �洢����˳��
	
	// ɨ���㷨���ݵ���
	public static void Scan(List<Integer> pl) {
		List<Integer> temp = new ArrayList<>();			// ���������ڴ�ͷ�ƶ������ϵ�ֵ
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
				// �ҳ������Ƚ��̲�����
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

			// ���ô�ͷ�ƶ��������޿����н��̣� ��ı����з���
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
