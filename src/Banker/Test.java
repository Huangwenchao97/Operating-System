package Banker;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
		
		System.out.print("��������̸���:");
		int N_row = input.nextInt();
		
		System.out.print("��������Դ����:");
		int N_col = input.nextInt();
		
		int[] Available = new int[N_col];	
		int[][] Max = new int[N_row][N_col];
		int[][] Allocation = new int[N_row][N_col];
		
		System.out.println("�������������Դ����Available:");
		for (int i=0; i<N_col; i++) {
			System.out.print("��������Դ" + (i+1) + "�ĸ���:");
			Available[i] = input.nextInt();
		}

		for (int i=0; i<N_row; i++) {
			System.out.println("���������" + (i+1) + "������������Max:");
			for (int j=0; j<N_col; j++) {
				System.out.print("��������Դ" + (j+1) + "���������:");
				Max[i][j] = input.nextInt();
			}
		}
		
		for (int i=0; i<N_row; i++) {
			System.out.println("���������" + (i+1) + "�ķ������Allocation:");
			for (int j=0; j<N_col; j++) {
				System.out.print("�������ѷ�����Դ" + (j+1) + "�ĸ���:");
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
			System.out.print("������Ҫ������Դ�Ľ���:");
			int index = input.nextInt();
			for (int i=0; i<N_col; i++) {
				System.out.print("������Ҫ�������Դ" + (i+1) + "�ĸ���:");
				request[i] = input.nextInt();
			}
			bk.bank(index-1, request);
			System.out.println("------------------------------");
		}
		
	}

}
