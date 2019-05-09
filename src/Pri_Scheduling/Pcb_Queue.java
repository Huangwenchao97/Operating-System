package Pri_Scheduling;

public class Pcb_Queue {
	
	private PCB head;		//����ָ��
	private PCB p;			//����������ָ��
	
	public Pcb_Queue() {
		this.head = null;
		this.p = null;
	}
	
	// ��pn�������
	public void Insert(PCB pn) {
		// ��������û��Ԫ�� ���� �����н��̶����������
		if(head == null || head.status == 'E') {
			head = pn;
			return;
		}
		
		// ��pn״̬ΪE�� ����ڶ�β
		if(pn.status == 'E') {
			p = head;
			while(p.next != null) {
				p = p.next;
			}
			p.next = pn;
			pn.next = null;
			return;
		}
		
		// ��ֻ��һ��Ԫ��
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
		
		// �������У� ����pn
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
	
	// ����һ��
	public void Run(PCB pn) {
		System.out.println( "���еĽ���Ϊ��" + pn.name); 
		pn.pri--;
		pn.time--;
		if(pn.time == 0) {
			pn.status = 'E';
		}
		show();
	}
	
	// ���̵���
	void Scheduling() {
		while(head.status != 'E') {
			Run(head);
			PCB tmp = head;
			head = head.next;
			Insert(tmp);
		}
		System.out.println( "End"); 
	}
	
	// ��ʾ��ǰ���н���
	public void show() {
		PCB tmp;
		tmp = head;
		if (tmp != null){
			System.out.println( "������" +'\t' + "��������ʱ��" + '\t' + "���ȼ� " + '\t' + "״̬"); 
		}
		while (tmp != null){
			tmp.Display();
			tmp = tmp.next;
		}
		System.out.println( "--------------------------------------"); 
	}
}
