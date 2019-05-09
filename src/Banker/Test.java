package Banker;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		
		System.out.print("请输入进程个数:");
		int N_row = input.nextInt();
		
		System.out.print("请输入资源个数:");
		int N_col = input.nextInt();
		
		int[] Available = new int[N_col];	
		int[][] Max = new int[N_row][N_col];
		int[][] Allocation = new int[N_row][N_col];
		
		System.out.println("请输入可利用资源向量Available:");
		for (int i=0; i<N_col; i++) {
			System.out.print("可利用资源" + (i+1) + "的个数:");
			Available[i] = input.nextInt();
		}

		for (int i=0; i<N_row; i++) {
			System.out.println("请输入进程" + (i+1) + "的最大需求矩阵Max:");
			for (int j=0; j<N_col; j++) {
				System.out.print("请输入资源" + (j+1) + "的最大需求:");
				Max[i][j] = input.nextInt();
			}
		}
		
		for (int i=0; i<N_row; i++) {
			System.out.println("请输入进程" + (i+1) + "的分配矩阵Allocation:");
			for (int j=0; j<N_col; j++) {
				System.out.print("请输入已分配资源" + (j+1) + "的个数:");
				Allocation[i][j] = input.nextInt();
			}
		}
		
		int[][] Need = new int[N_row][N_col];
		for (int i=0; i<N_row; i++) {
			for (int j=0; j<N_col; j++) {
				Need[i][j] = Max[i][j] - Allocation[i][j];
			}
		}
		
		
		int[] request = new int[N_col];
		
		banker bk = new banker(Available, Allocation, Need);
		
		while(true) {
			bk.show();
			System.out.print("请输入要申请资源的进程:");
			int index = input.nextInt();
			for (int i=0; i<N_col; i++) {
				System.out.print("请输入要申请的资源" + (i+1) + "的个数:");
				request[i] = input.nextInt();
			}
			bk.bank(index-1, request);
			System.out.println("------------------------------");
		}
		
	}

}
