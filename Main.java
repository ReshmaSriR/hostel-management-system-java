import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== HOSTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    adminMenu();
                    break;

                case 2:
                    studentMenu();
                    break;

                case 3:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // ================= ADMIN MENU =================

    public static void adminMenu() {

        while (true) {

            System.out.println("\n===== ADMIN MENU =====");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. View Rooms");
            System.out.println("6. View Room Requests");
            System.out.println("7. Approve Request");
            System.out.println("8. Reject Request");
            System.out.println("9. Back");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    StudentDAO.addStudent();
                    break;

                case 2:
                    StudentDAO.viewStudents();
                    break;

                case 3:
                    StudentDAO.deleteStudent();
                    break;

                case 4:
                    StudentDAO.searchStudent();
                    break;

                case 5:
                    StudentDAO.viewRooms();
                    break;

                case 6:
                    StudentDAO.viewRoomRequests();
                    break;

                case 7:
                    StudentDAO.approveRequest();
                    break;

                case 8:
                    StudentDAO.rejectRequest();
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // ================= STUDENT MENU =================

    public static void studentMenu() {

        while (true) {

            System.out.println("\n===== STUDENT MENU =====");

            System.out.println("1. View Rooms");
            System.out.println("2. Request Room");
            System.out.println("3. Check Request Status");
            System.out.println("4. Back");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    StudentDAO.viewRooms();
                    break;

                case 2:
                    StudentDAO.requestRoom();
                    break;

                case 3:
                    StudentDAO.checkRequestStatus();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
