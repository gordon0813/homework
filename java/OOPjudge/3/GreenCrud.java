
public class GreenCrud {
	public static int calPopulation(int init_v,int days) {
		int n1=init_v;
		int n2=init_v;
		int temp=0;
		for (int i =0;i<days/5-1;i++) {
			temp=n2;
			n2=n1+n2;
			n1=temp;
		}
		return n2;
	}
	
}
