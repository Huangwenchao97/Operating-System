package MainMemory;

import java.util.ArrayList;
import java.util.List;

// 采用最先适应算法实现主存分配和回收

public class Distribution {
	// 空闲区表
	private static List<Partition> free = new ArrayList<>();
	
	// 初始化free表
	public static void initFree() {
		Partition p = new Partition(5000, 507000, "N");
		free.add(p);
		showFree();
	}
	
	// 打印free表
	private static void showFree() {
		System.out.println("Addr" + "      " + "Length" + "  " + "status");
		for (int i=0; i<free.size(); i++) {
			System.out.println(free.get(i).getAddr()+ "     " + free.get(i).getLength() + "     " + free.get(i).getStatus());
		}
		System.out.println("--------------------------------------");
	}
	
	// 作业申请主存空间
	public static void apply(Job j) {
		int index = 0;				// 用来保存装入的空闲区的索引值
		boolean can = false;		// 用来判断作业是否装入
		for(int i=0; i<free.size(); i++){
			// 若该空闲区是长度大于作业所需的长度，则将作业装入主存，修改free表。
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
			System.out.println("暂无足够大的空闲区！");
		}
		showFree();
	}
	
	// 作业释放
	public static void release(Job j) {
		int index=0; 					// 用来保存索引值
		for(int i=0; i<free.size(); i++) {
			if(free.get(i).getAddr() > j.getStart()) {
				break;
			}
			index++;
		}
		
		Partition p = new Partition(j.getStart(), j.getSize(), "N");
		free.add(index, p);
		// 若有相邻空闲区，则合并
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
