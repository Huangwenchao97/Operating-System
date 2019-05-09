package Pri_Scheduling;

public class PCB {
	
	String name;
	int time;
	PCB next = null;
	int pri;
	char status = 'R';
	
	public PCB(String name, int time, int pri){
		this.name = name;
		this.time = time;
		this.pri = pri;
	}

	public void Display() {
		System.out.println(this.name + '\t' + this.time + '\t' + '\t' + this.pri + '\t' + this.status);
	}
}
