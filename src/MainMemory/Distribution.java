package MainMemory;

import java.util.ArrayList;
import java.util.List;

// ����������Ӧ�㷨ʵ���������ͻ���

public class Distribution {
	// ��������
	private static List<Partition> free = new ArrayList<>();
	
	// ��ʼ��free��
	public static void initFree() {
		Partition p = new Partition(5000, 507000, "N");
		free.add(p);
		showFree();
	}
	
	// ��ӡfree��
	private static void showFree() {
		System.out.println("Addr" + "      " + "Length" + "  " + "status");
		for (int i=0; i<free.size(); i++) {
			System.out.println(free.get(i).getAddr()+ "     " + free.get(i).getLength() + "     " + free.get(i).getStatus());
		}
		System.out.println("--------------------------------------");
	}
	
	// ��ҵ��������ռ�
	public static void apply(Job j) {
		int index = 0;				// ��������װ��Ŀ�����������ֵ
		boolean can = false;		// �����ж���ҵ�Ƿ�װ��
		for(int i=0; i<free.size(); i++){
			// ���ÿ������ǳ��ȴ�����ҵ����ĳ��ȣ�����ҵװ�����棬�޸�free��
			if(free.get(i).getLength() > j.getSize()) {
				j.setStart(free.get(index).getAddr());
				long newStart = free.get(index).getAddr() + j.getSize();
				long newLength = free.get(index).getLength() - j.getSize();
				Partition p = new Partition(newStart, newLength, "N");
				free.remove(index);
				free.add(index, p);
				can = true;
				break;
			}
			else if(free.get(i).getLength() == j.getSize()) {
				free.remove(index);
				can = true;
				break;
			}
			index++;
		}
		if(!can) {
			System.out.println("�����㹻��Ŀ�������");
		}
		showFree();
	}
	
	// ��ҵ�ͷ�
	public static void release(Job j) {
		int index=0; 					// ������������ֵ
		for(int i=0; i<free.size(); i++) {
			if(free.get(i).getAddr() > j.getStart()) {
				break;
			}
			index++;
		}
		
		Partition p = new Partition(j.getStart(), j.getSize(), "N");
		free.add(index, p);
		// �������ڿ���������ϲ�
		if(index != 0 && index != free.size()-1) {
			long frontAddr = free.get(index-1).getAddr() + free.get(index-1).getLength();
			long behindAddr = free.get(index).getAddr() + free.get(index).getLength();
			if (frontAddr == j.getStart()) {
				long newStart = free.get(index-1).getAddr();
				long newLength = behindAddr - newStart;
				free.remove(index);
				free.remove(index-1);
				Partition pf = new Partition(newStart, newLength, "N");
				free.add(index-1, pf);
			}
			if (behindAddr == free.get(index+1).getAddr()) {
				long newStart = free.get(index).getAddr();
				long newLength = behindAddr + free.get(index+1).getLength() - newStart;
				Partition pb = new Partition(newStart, newLength, "N");
				free.remove(index+1);
				free.remove(index);
				free.add(index, pb);
			}
		}
		else if (index == 0) {
			long behindAddr = free.get(index).getAddr() + free.get(index).getLength();
			if (behindAddr == free.get(index+1).getAddr()) {
				long newStart = free.get(index).getAddr();
				long newLength = behindAddr + free.get(index+1).getLength() - newStart;
				Partition pb = new Partition(newStart, newLength, "N");
				free.remove(index+1);
				free.remove(index);
				free.add(index, pb);
			}
		}
		else {
			long frontAddr = free.get(index-1).getAddr() + free.get(index-1).getLength();
			if (frontAddr == j.getStart()) {
				long newStart = free.get(index-1).getAddr();
				long newLength = free.get(index).getAddr() + free.get(index).getLength() - newStart;
				free.remove(index);
				free.remove(index-1);
				Partition pf = new Partition(newStart, newLength, "N");
				free.add(index-1, pf);
			}
		}
		showFree();
	}

}
