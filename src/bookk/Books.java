package bookk;

import java.util.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class Books extends Book_Main{
	String bookname;
	String authorname;
	int edition;
	double price;
	
	public Books() {
		bookname="not initialized";
		authorname="not initialized";
		edition=0;
		price=0;
	}
	
	public Books(String bookname,String authorname,int edition,double price) throws Exception{
		this.bookname=bookname;
		this.authorname=authorname;
		this.edition=edition;
		this.price=price;
		this.displaybooks();
	}
	public void displaybooks()throws Exception{
//		getConection();
//		String query="select * from book";
//		Statement sat=con.createStatement();
//		ResultSet res=sat.executeQuery(query);
//		while(res.next())
//			System.out.println(res.getString(1)+" "+res.getString(2)+" "+res.getInt(3)+" "+res.getInt(4));
		new printTable().printResult("book");
	}
}