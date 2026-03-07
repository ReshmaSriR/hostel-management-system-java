import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UserDAO {

    public static String login() {

        try {

            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            String query = "SELECT role FROM users WHERE username=? AND password=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            } else {
                System.out.println("Invalid Username or Password!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}