package Pri_Scheduling;

public class Pcb_Queue {
	
	private PCB head;		//队首指针
	private PCB p;			//用来遍历的指针
	
	public Pcb_Queue() {
		this.head = null;
		this.p = null;
	}
	
	// 将pn插入队列
	public void Insert(PCB pn) {
		// 若队列中没有元素 或者 队列中进程都已运行完毕
		if(head == null || head.status == 'E') {
			head = pn;
			return;
		}
		
		// 若pn状态为E， 则放在队尾
		if(pn.status == 'E') {
			p = head;
			while(p.next != null) {
				p = p.next;
			}
			p.next = pn;
			pn.next = null;
			return;
		}
		
		// 若只有一个元素
		if(head.next == null) {
			if(head.pri > pn.pri && head.status == 'R') {
				head.next = pn;
				pn.next = null;
				return;
			}
			else {
				pn.next = head;
				head.next = null;
				head = pn;
				return;
			}
		}
		
		p = head;
		
		// 遍历队列， 插入pn
		while(p.next != null) {
			if(p.next.status == 'E') {
				pn.next = p.next;
				p.next = pn;
				return;
			}
			if(p.next.pri < pn.pri) {
				pn.next = p.next;
				p.next = pn;
				return;
			}
			p = p.next;
		}
		p.next = pn;
		pn.next = null;
		return;
	}
	
	// 运行一次
	public void Run(PCB pn) {
		System.out.println( "运行的进程为：" + pn.name); 
		pn.pri--;
		pn.time--;
		if(pn.time == 0) {
			pn.status = 'E';
		}
		show();
	}
	
	// 进程调度
	void Scheduling() {
		while(head.status != 'E') {
			Run(head);
			PCB tmp = head;
			head = head.next;
			Insert(tmp);
		}
		System.out.println( "End"); 
	}
	
	// 显示当前所有进程
	public void show() {
		PCB tmp;
		tmp = head;
		if (tmp != null){
			System.out.println( "进程名" +'\t' + "所需运行时间" + '\t' + "优先级 " + '\t' + "状态"); 
		}
		while (tmp != null){
			tmp.Display();
			tmp = tmp.next;
		}
		System.out.println( "--------------------------------------"); 
	}
}
