package bookk;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
public class Book_Main {
	static private int price;
	Connection con=null;
	void getConection()
    {
        try {
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "#321@toorymlqs");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
	
	static Scanner sc=new Scanner(System.in);
	public void home()throws Exception {
		System.out.println("\t\t********************************************\n");
		System.out.println("\t\t\tBookstore Management System\n");
		System.out.println("\t\t********************************************\n");
		System.out.println("1.Admin\n2.User");
		int a=sc.nextInt();
		if(a==2) {
			login();
		}
		else{
			Admin aa=new Admin();
			aa.ahome();
		}
	}
	public void login() throws Exception {
		System.out.println("1.Buyer \n2.Dealer");
		int n1=sc.nextInt();
		if(n1==1) {
			Options();
		}
		else {Dealer d=new Dealer();
			d.doptions();
		}
	}
	public void Options() throws Exception
	{

		System.out.println("1.Login\n2.SignUp");
		int n=sc.nextInt();
		if(n==1)
			buyer();
		else
			signup();
	}
	public void buyer() throws Exception {
		System.out.print("Username: ");
		String bu=sc.next();
		System.out.print("Password: ");
		String bp=sc.next();
		if(bcheck(bu,bp)) {
			System.out.println("Login Successful\n");
			System.out.println("1.View books \n2.Exit");
			int n2=sc.nextInt();
			sc.nextLine();
			if(n2==1) {
				viewbook();
			}
			if(n2==2) {
				home();
			}
		}
		else {
			System.out.println("Invalid login");
			login();
		}
	}
	public boolean bcheck(String bu,String bp)throws Exception {
		getConection();
		String query="select * from buyer where username='"+bu+"' AND password='"+bp+"'";
		Statement sat=con.createStatement();
		ResultSet res=sat.executeQuery(query);
		if(res.next())
			return true;
		
		return false;
	}
	public void viewbook() throws Exception {
		Books bk=new Books();
		bk.displaybooks();
		System.out.println("\n1.Buy now");
		System.out.println("2.Exit");
		int n3=sc.nextInt();
		sc.nextLine();
		if(n3==1) {
			buynow();
		}
		else {
			home();
		}	
	}
	public void buynow() throws Exception {
		System.out.print("Enter your book: ");
		String str=sc.nextLine();
		getConection();
		String query="select book_name from book where book_name='"+str+"'";
		Statement sat=con.createStatement();
		ResultSet res=sat.executeQuery(query);
		if(res.next()) {
			
		System.out.print("Quantity: ");
		int q=sc.nextInt();
		getConection();
		//String query="select * from book where book_name='"++"';";
		String query2="select book_name,author_name,edition,price*"+q+",quantity from book where book_name='"+str+"'";
		Statement sat2=con.createStatement();
		ResultSet res2=sat2.executeQuery(query2);
		
		while(res2.next())
		{
			System.out.println("Book Name: "+res2.getString(1)+"\n"+"Author Name: "+res2.getString(2)+"\n"+"Edition: "+res2.getString(3)+"\n"+"Book Price: "+res2.getInt(4));
			 price=res2.getInt(4);
			System.out.println();
		}
		System.out.println("1.Place order");
		System.out.println("2.exit");
		System.out.println("3.Do you want to continue");
		int e=sc.nextInt();
		sc.nextLine();
		if(e==1) {
		System.out.println("Your order has placed!!!\n");
		System.out.print("Enter Amount to buy book: ");
		int p=sc.nextInt();
		sc.nextLine();
		if(p==price) {
			cinsert(str, q);
			System.out.println("Your purchase Successful!!!\n1.Buy again\n2.Exit");
			int ba=sc.nextInt();
			if(ba==1) {
				buynow();
			}
			else if(ba==2) {
				home();
			}
		}
		else {
			System.out.println("Invalid Amount");
			System.out.println("1.Try again\n2.Exit");
			int t=sc.nextInt();
			sc.nextLine();
			if(t==1) {
			buynow();
			}
			else {
				home();
			}
		}
		}
		else if(e==3) {
			buynow();
		}
		else {
			System.out.println("Thank you for visiting us");
		}
		}
		else {
			System.out.println("Invalid book name");
			buynow();
		}
		
	}	
	public void dealer() {
        System.out.print("Username: ");
        String su=sc.next();
        System.out.print("Password: ");
        String sp=sc.next();
        System.out.print("Dealer id: ");
        String si=sc.next();
	}	
	static String pass="";
	public static String isValidPassword()
    {
		System.out.print("Set Password: ");
		String password=sc.next();
         String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
         Pattern p = Pattern.compile(regex);
         Matcher m = p.matcher(password);
         boolean check=m.find();
         if(!check) {
        	System.out.println("Invalid password");
        	isValidPassword();
         }
         else
         {
        	 pass=password;
         }
        return pass;
    }
	public void signup() throws Exception {
		System.out.print("Set Username: ");
		String spu=sc.next();
		String pass1=isValidPassword();
		System.out.print("Enter Gmail: ");
		String spg=sc.next();
		if(!spg.contains("@gmail.com")) {
			System.out.println("Invalid Gmail");
			Options();
		}
		else {
		System.out.println("SignUp Successful.");
		binsert(spu,pass1,spg);
		System.out.println();
		Options();
		}
	}	
	public void binsert(String spu,String spp,String spg) throws Exception{
		getConection();
		String query="INSERT INTO buyer values('"+spu+"','"+spp+"','"+spg+"')";
		Statement sat=con.createStatement();
		int resultset=sat.executeUpdate(query);
	}
	public void cinsert(String str,int q) throws Exception{
		getConection();
		String query="INSERT INTO purchase values('"+str+"','"+q+"')";
	  	Statement sat=con.createStatement();
	  	int resultset=sat.executeUpdate(query);
	  	
		query="UPDATE BOOK set quantity=quantity-"+q+" where book_name='"+str+"';";
	  	sat=con.createStatement();
	  	resultset =sat.executeUpdate(query);
	}
	public static void main(String[] args) throws Exception {
		Book_Main bk=new Book_Main();
		// TODO Auto-generated method stub
		bk.home();
	}
}