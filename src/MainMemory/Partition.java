package MainMemory;

// 定义分区类

public class Partition {

	private long startAddr;
	private String status;
	private long length;
	
	public Partition(long startAddr, long length, String status) {
		this.startAddr = startAddr;
		this.length = length;
		this.status = status;
	}
	
	public long getAddr() {
		return this.startAddr;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public long getLength() {
		return this.length;
	}

	public void setAddr(long s) {
		this.startAddr = s;
	}
	
}
