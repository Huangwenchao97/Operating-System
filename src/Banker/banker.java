package Banker;

import java.util.Arrays;

public class banker {
	
	int[] Available;
	int[][] Max;
	int[][] Allocation;
	int[][] Need;
	boolean[] Finish;
	int[] Work;
	int N_col;			// ��Դ�������		
	int N_row;			// ���̸���
	
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

	// ���м��㷨
	boolean bank(int row, int[] Request) {
		// ��Request����Need, ���ж�����
		for (int i=0; i<N_col; i++) {
			if (Request[i] > Need[row][i]) {
				System.out.println("������Դ�����ܴ���������Դ����");
				return false;
			}
		}
		// ��Request����Available, ��ȴ�
		for (int i=0; i<N_col; i++) {
			if (Request[i] > Available[i]) {
				System.out.println("����ʧ�ܣ�");
				return false;
			}
		}
		
		// ��������
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

		// ��̽����
		boolean isDone = true;
		for (int j=0; j<N_col; j++) {
			Available[j] = Available[j] - Request[j];
			Allocation[row][j] = Allocation[row][j] + Request[j];  
			Need[row][j] = Need[row][j] - Request[j];
			if(Need[row][j] != 0) {
				isDone = false;
			}
		}
		
		// ��NeedΪ0, ���ʾ�ý�����������, ������ռ�е���Դ�ͷ�
		if (isDone) {
			for (int i=0; i<N_col; i++) {
				Available[i] = Allocation[row][i] + Available[i];
				Allocation[row][i] = 0;
			}
		}
		
		// ��������ϵͳ�����ڰ�ȫ״̬,�����ɹ�
		boolean s = isSafe();
		if (s) {
			System.out.println("����ɹ���");
			return true;
		}
		// ����, �ָ�����, ����ʧ��
		else {
			Available = backup_Available;
			Allocation = backup_Allocation;
			Need = backup_Need;
			System.out.println("����ʧ�ܣ�");
			return false;
		}
	}
	
	// ��ȫ���㷨
	boolean isSafe() {
		boolean Found;			// �ж��Ƿ��ҵ������еĽ���
		boolean worked;			// �жϽ����Ƿ������
		// ��ʼ��Finish��Work
		for (int i=0; i<N_row; i++) {
			Finish[i] = false;
		}
		for (int i=0; i<N_col; i++) {
			Work[i] = Available[i];
		}
		
		// ����Ѱ�ҿ������е�Finish=false�Ľ���
		while(true) {
			Found = false;
			// ѭ�����н���
			for(int i=0; i<N_row; i++) {
				worked = true;
				// ѭ��������Դ����,�ж� i �����Ƿ������
				for(int j=0; j<N_col; j++) {
					// ��������Դ���ڵ�ǰ���е���Դ
					if (Need[i][j] > Work[j]) {
						worked = false;
						break;
					}
				}
				// ����ǰ���̿�������,��ִ�в���
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
		// ��finish��ȫΪtrue�����ʾϵͳ���ڲ���ȫ״̬
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
