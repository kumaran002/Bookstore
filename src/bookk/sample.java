package bookk;
public class sample {
	private class a{
		void print() {
			System.out.print("**");
		}
	}
	public static void main(String args[]) {
		sample s=new sample();
		a a1=s.new a();
		a1.print();
		
	}
}
