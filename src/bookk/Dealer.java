package bookk;

import java.sql.ResultSet;
import java.sql.Statement;
public class Dealer extends Book_Main {
	public void doptions() throws Exception
	{

		System.out.println("1.Login\n2.SignUp");
		int dn=sc.nextInt();
		if(dn==1)
			dbuyer();
		else
			dsignup();
	}
	
	public void dbuyer() throws Exception {
		System.out.print("Username: ");
		String dbu=sc.next();
		System.out.print("Password: ");
		String dbp=sc.next();
		sc.nextLine();
		if(check(dbu,dbp)) {
			System.out.println("Login Successful\n");
			System.out.println("1.Sell book\n2.Exit");
			int s=sc.nextInt();
			sc.nextLine();
			if(s==1) {
				sellbook();
			}
			else if(s==2) {
				doptions();
			}
		}
		else {
			System.out.println("Invalid login");
			login();
		}
	}
	public void sellbook() throws Exception {
		System.out.print("Enter Book name: ");
		String dbn=sc.nextLine();
		getConection();
		String query="select book_name from book where book_name='"+dbn+"'";
		Statement sat=con.createStatement();
		ResultSet res=sat.executeQuery(query);
		if(!res.next()) {
		System.out.print("Enter Author name: ");
		String an=sc.nextLine();
		System.out.print("Enter book edition: ");
		int de=sc.nextInt();
		System.out.print("Enter book price: ");
		int dp=sc.nextInt();
		System.out.print("Enter the quantity of book: ");
		int dq=sc.nextInt();
		if(dq<20) {
			System.out.println();
			System.out.println("Quantity of the book must atleast 20");
			System.out.println("Quantity of the book is atleast 20");
			System.out.println("1.If you want to sell book\n2.Exit");
			int se=sc.nextInt();
			sc.nextLine();
			if(se==1) {
				sellbook();
			}
			else {
				home();
			}
		}
		else {
		System.out.println();
		insert(dbn, an, de, dp,dq);
		System.out.println("Data has entered successfully!!!\n1.If you wnat to sell another book\n2.Exit");
		int ae=sc.nextInt();
		sc.nextLine();
		if(ae==1) {
			sellbook();
		}
		else if(ae==2) {
			home();
		}
		else {
			System.out.println("Your data is already exists\n1.Re-sell book\n2.Exit");
			int sb=sc.nextInt();
			sc.nextLine();
			if(sb==1) {
				sellbook();
			}
			else if(sb==2) {
				home();
			}
		}
		}
		}
}
	public void dsignup() throws Exception {
		System.out.print("Set Username: ");
		String dpu=sc.next();
		System.out.print("Set Password: ");
		String dpp=sc.next();
		System.out.print("Enter Gmail: ");
		String dpg=sc.next();
		if(!dpg.contains("@gmail.com")) {
			System.out.println("Invalid Gmail");
		}
		else {
		System.out.println("SignUp Successful.");
		dinsert(dpu,dpp,dpg);
		System.out.println();
		Options();
		}
	}
	
	public boolean check(String dbu,String dbp)throws Exception {
		getConection();
		
		String query="select * from dealer where username='"+dbu+"' AND password='"+dbp+"'";
		Statement sat=con.createStatement();
		ResultSet res=sat.executeQuery(query);
		if(res.next())
			return true;
		
		return false;
	}
	
	public void insert(String dbn,String an,int de,int dp,int dq) throws Exception{
		getConection();
		String query="INSERT INTO book values('"+dbn+"','"+an+"','"+de+"','"+dp+"','"+dq+"')";
	  	Statement sat=(Statement)con.createStatement();
	  	int resultset=sat.executeUpdate(query);
	}
	public void dinsert(String dpu,String dpp,String dpg) throws Exception{
		getConection();
		String query="INSERT INTO dealer values('"+dpu+"','"+dpp+"','"+dpg+"')";
	  	Statement sat=(Statement)con.createStatement();
	  	int resultset=sat.executeUpdate(query);
	}
}

