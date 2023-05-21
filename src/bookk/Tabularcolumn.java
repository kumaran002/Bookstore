package bookk;
import java.sql.*;
import java.util.*;
public class Tabularcolumn {
	
}

class printTable {
	public int getMaxLength(ArrayList<String>arr) {
		int len=0;
		for(String i:arr)
			len=Math.max(len,i.length());
		return len;
	}
	void printResult(String tableName) {
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","#321@toorymlqs");
			ResultSet rs=con.createStatement().executeQuery("select * from "+tableName);
			ResultSetMetaData rsmd=rs.getMetaData();
			ArrayList<ArrayList<String>>data=new ArrayList<>();
			int columnCount=rsmd.getColumnCount();
			data.add(new ArrayList<>());
			for(int i=1;i<=columnCount;i++) {
				data.add(new ArrayList<>());
				data.get(i).add(rsmd.getColumnName(i));
			}
			while(rs.next()) {
				for(int i=1;i<=columnCount;i++) {
					data.get(i).add(rs.getString(i));
				}
			}
		//	System.out.println(data+"\n");
			
			for(int i=0;i<data.get(1).size();i++) {
				int totcnt=0;
				for(int j=1;j<=columnCount;j++) {
					int len=getMaxLength(data.get(j))+4;
					totcnt+=len;
					int cur=data.get(j).get(i).length();
					int diff=len-cur;
					for(int k=0;k<diff/2;k++)System.out.print(" ");
					System.out.print(data.get(j).get(i));
					for(int k=0;k<diff/2+diff%2;k++)
						System.out.print(" ");
					if(j!=columnCount)
						System.out.print("|");
				}
				System.out.println();
				if(i==0) {
					for(int j=0;j<totcnt;j++)
						System.out.print("-");
					System.out.println();
				}
			}
			
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println();
	}

}