package MainMemory;

// ��ҵ��

public class Job {

	private long size;
	private long startAddr;
	
	public Job(long size) {
		this.size = size;		// ��Ҫ��������
	}
	
	public long getSize() {
		return this.size;
	}
	
	public long getStart() {
		return this.startAddr;
	}
	
	public void setStart(long l) {
		this.startAddr = l;
	}
}
