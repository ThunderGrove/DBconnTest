import java.sql.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");//NOTE: The driver has to be added as a lib.
        Scanner input = new Scanner(System.in);

        System.out.println("Enter MariaDB username:");
        String uname=input.next();
        System.out.println("Enter MariaDB password:");
        String pw=input.next();

        Connection conn=null;
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/world",
                uname,
                pw
        );

        System.out.println("Enter a part of country name:");
        String countryName=input.next();

        String q="SELECT * FROM world.country WHERE name LIKE '%"+countryName+"%'";
        Statement statement = conn.createStatement();
        ResultSet r = statement.executeQuery(q);

        while(r.next()){
            System.out.println(
                    r.getString(1)+", "+r.getString(2)+", "+r.getString(3)+", Population: "+r.getString("Population")
            );
        }
        conn.close();
    }
}