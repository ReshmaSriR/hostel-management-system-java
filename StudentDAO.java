import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentDAO {

    static Scanner sc = new Scanner(System.in);

    // ================= ADD STUDENT =================
    public static void addStudent() {

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter College: ");
            String college = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            String query = "INSERT INTO students (student_id, name, college, phone) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, college);
            pst.setString(4, phone);

            pst.executeUpdate();

            System.out.println("Student Added Successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= VIEW STUDENTS =================
    public static void viewStudents() {

        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT * FROM students";

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== STUDENT LIST =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("college") + " | " +
                                rs.getString("phone")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= DELETE STUDENT =================
    public static void deleteStudent() {

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Student ID to Delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM students WHERE student_id=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            int rows = pst.executeUpdate();

            if (rows > 0)
                System.out.println("Student Deleted Successfully!");
            else
                System.out.println("Student Not Found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= SEARCH STUDENT =================
    public static void searchStudent() {

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Student ID to Search: ");
            int id = sc.nextInt();

            String query = "SELECT * FROM students WHERE student_id=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("\nStudent Found");
                System.out.println("ID: " + rs.getInt("student_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("College: " + rs.getString("college"));
                System.out.println("Phone: " + rs.getString("phone"));

            } else {

                System.out.println("Student Not Found!");

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= VIEW ROOMS =================
    public static void viewRooms() {

        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT * FROM rooms";

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== ROOM LIST =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("room_id") + " | " +
                                rs.getString("room_type") + " | " +
                                rs.getString("availability")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= REQUEST ROOM =================
    public static void requestRoom() {

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Your Student ID: ");
            int studentId = sc.nextInt();

            System.out.print("Enter Room ID: ");
            int roomId = sc.nextInt();

            String query = "INSERT INTO room_requests (student_id, room_id, status) VALUES (?, ?, 'PENDING')";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, studentId);
            pst.setInt(2, roomId);

            pst.executeUpdate();

            System.out.println("Room Request Sent Successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= VIEW ROOM REQUESTS =================
    public static void viewRoomRequests() {

        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT * FROM room_requests";

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== ROOM REQUESTS =====");

            while (rs.next()) {

                System.out.println(
                        "Request ID: " + rs.getInt("request_id") +
                                " | Student ID: " + rs.getInt("student_id") +
                                " | Room ID: " + rs.getInt("room_id") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= APPROVE REQUEST =================
    public static void approveRequest() {

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Request ID: ");
            int requestId = sc.nextInt();

            String query = "UPDATE room_requests SET status='APPROVED' WHERE request_id=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, requestId);

            int rows = pst.executeUpdate();

            if (rows > 0)
                System.out.println("Request Approved!");
            else
                System.out.println("Request Not Found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= REJECT REQUEST =================
    public static void rejectRequest() {

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Request ID: ");
            int requestId = sc.nextInt();

            String query = "UPDATE room_requests SET status='REJECTED' WHERE request_id=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, requestId);

            int rows = pst.executeUpdate();

            if (rows > 0)
                System.out.println("Request Rejected!");
            else
                System.out.println("Request Not Found!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================= CHECK REQUEST STATUS =================
    public static void checkRequestStatus() {

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Your Student ID: ");
            int studentId = sc.nextInt();

            String query = "SELECT * FROM room_requests WHERE student_id=?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, studentId);

            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== YOUR REQUEST STATUS =====");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Request ID: " + rs.getInt("request_id") +
                                " | Room ID: " + rs.getInt("room_id") +
                                " | Status: " + rs.getString("status")
                );
            }

            if (!found) {
                System.out.println("No Request Found.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}