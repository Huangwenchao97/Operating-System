package Disk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import MainMemory.Partition;

public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> pl = new ArrayList<>();
		Random ra =new Random();
		for (int i=0; i<15; i++) {
			pl.add(ra.nextInt(200)+1);
			System.out.print(pl.get(i) + "  ");
		}
		System.out.println("");
		Head.Scan(pl);
		Head.preLength();
	}

}
