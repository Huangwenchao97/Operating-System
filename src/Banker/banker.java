package Banker;

import java.util.Arrays;

public class banker {
	
	int[] Available;
	int[][] Max;
	int[][] Allocation;
	int[][] Need;
	boolean[] Finish;
	int[] Work;
	int N_col;			// 资源种类个数		
	int N_row;			// 进程个数
	
	public banker(int[] Available, int[][] Allocation, int[][] Need) {
		
		this.Available = Available;
		this.Allocation = Allocation;
		this.Need = Need;
		this.N_col = Need[0].length;
		this.N_row = Need.length;
		this.Work = new int[N_col];	
		Finish = new boolean[N_row];
		for (int i=0; i<N_row; i++) {
			Finish[i] = false;
		}
	}

	// 银行家算法
	boolean bank(int row, int[] Request) {
		// 若Request大于Need, 则判定出错
		for (int i=0; i<N_col; i++) {
			if (Request[i] > Need[row][i]) {
				System.out.println("申请资源数不能大于所需资源数！");
				return false;
			}
		}
		// 若Request大于Available, 则等待
		for (int i=0; i<N_col; i++) {
			if (Request[i] > Available[i]) {
				System.out.println("申请失败！");
				return false;
			}
		}
		
		// 备份数据
		int[] backup_Available = new int[N_col];
		for (int i=0; i<N_col; i++) {
			backup_Available[i] = Available[i];
		}
		int[][] backup_Allocation = new int[N_row][N_col];
		for (int i=0; i<N_row; i++) {
			for (int j=0; j<N_col; j++) {
				backup_Allocation[i][j] = Allocation[i][j];
			}
		}
		int[][] backup_Need = new int[N_row][N_col];
		for (int i=0; i<N_row; i++) {
			for (int j=0; j<N_col; j++) {
				backup_Need[i][j] = Need[i][j];
			}
		}

		// 试探分配
		boolean isDone = true;
		for (int j=0; j<N_col; j++) {
			Available[j] = Available[j] - Request[j];
			Allocation[row][j] = Allocation[row][j] + Request[j];  
			Need[row][j] = Need[row][j] - Request[j];
			if(Need[row][j] != 0) {
				isDone = false;
			}
		}
		
		// 若Need为0, 则表示该进程已运行完, 将所有占有的资源释放
		if (isDone) {
			for (int i=0; i<N_col; i++) {
				Available[i] = Allocation[row][i] + Available[i];
				Allocation[row][i] = 0;
			}
		}
		
		// 若分配完系统还处于安全状态,则分配成功
		boolean s = isSafe();
		if (s) {
			System.out.println("申请成功！");
			return true;
		}
		// 否则, 恢复数据, 分配失败
		else {
			Available = backup_Available;
			Allocation = backup_Allocation;
			Need = backup_Need;
			System.out.println("申请失败！");
			return false;
		}
	}
	
	// 安全性算法
	boolean isSafe() {
		boolean Found;			// 判断是否找到可运行的进程
		boolean worked;			// 判断进程是否可运行
		// 初始化Finish和Work
		for (int i=0; i<N_row; i++) {
			Finish[i] = false;
		}
		for (int i=0; i<N_col; i++) {
			Work[i] = Available[i];
		}
		
		// 不断寻找可以运行的Finish=false的进程
		while(true) {
			Found = false;
			// 循环所有进程
			for(int i=0; i<N_row; i++) {
				worked = true;
				// 循环所有资源种类,判断 i 进程是否可运行
				for(int j=0; j<N_col; j++) {
					// 若所需资源大于当前空闲的资源
					if (Need[i][j] > Work[j]) {
						worked = false;
						break;
					}
				}
				// 若当前进程可以运行,则执行操作
				if(worked) {
					for ( int j=0; j<N_col; j++) {
						Work[j] = Work[j] + Allocation[i][j];
					}
					Finish[i] = true;
					Found = true;
				}
			}
			if(Found == false) {
				break;
			}
		}
		// 若finish不全为true，则表示系统处于不安全状态
		for(int i=0; i<N_row; i++) {
			if(Finish[i] == false) {
				return false;
			}
		}
		return true;
	}


	// display
	void show() {
		System.out.println("Process" + '\t' + '\t' + "Allocation" + '\t' + "Need" + '\t' + '\t' + "Available");
		for(int i=0; i<N_row; i++) {
			if(i == 0) {
				System.out.println("process" + (i+1) + '\t' + Arrays.toString(Allocation[i]) + '\t' + Arrays.toString(Need[i]) + '\t' + Arrays.toString(Available));
			}
			else {
				System.out.println("process" + (i+1) + '\t' + Arrays.toString(Allocation[i]) + '\t' + Arrays.toString(Need[i]));
			}
			
		}
	}
	
}
