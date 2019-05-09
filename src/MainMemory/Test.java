package MainMemory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Job j1 = new Job(300000);
		Job j2 = new Job(100000);
		Job j3 = new Job(150000);
		Job j4 = new Job(30000);
		Job j5 = new Job(40000);
		Job j6 = new Job(60000);
		Distribution.initFree();
		Distribution.apply(j1);
		Distribution.apply(j2);
		Distribution.release(j1);
		Distribution.apply(j3);
		Distribution.apply(j4);
		Distribution.apply(j5);
		Distribution.apply(j6);
		Distribution.release(j4);
	}

}
