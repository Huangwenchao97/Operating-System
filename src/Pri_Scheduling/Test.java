package Pri_Scheduling;

import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in); 
		int[] runTime = new int[5];
		int[] pri = new int[5];
		for(int i=0; i<5; i++) {
			System.out.println("请输入进程" + (i+1) + "的运行时间：");
			runTime[i] = input.nextInt();
			System.out.println("请输入进程" + (i+1) + "的优先级：");
			pri[i] = input.nextInt();
		}
		
		
		PCB p1 = new PCB("no.1", runTime[0], pri[0]);
		PCB p2 = new PCB("no.2", runTime[1], pri[1]);
		PCB p3 = new PCB("no.3", runTime[2], pri[2]);
		PCB p4 = new PCB("no.4", runTime[3], pri[3]);
		PCB p5 = new PCB("no.5", runTime[4], pri[4]);
		
		Pcb_Queue pqueue = new Pcb_Queue();
		
		pqueue.Insert(p1);
		pqueue.Insert(p2);
		pqueue.Insert(p3);
		pqueue.Insert(p4);
		pqueue.Insert(p5);
		
		pqueue.show();
		pqueue.Scheduling();
		
	}

}
