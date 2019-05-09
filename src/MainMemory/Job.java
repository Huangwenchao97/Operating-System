package MainMemory;

// 作业类

public class Job {

	private long size;
	private long startAddr;
	
	public Job(long size) {
		this.size = size;		// 需要的主存量
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
