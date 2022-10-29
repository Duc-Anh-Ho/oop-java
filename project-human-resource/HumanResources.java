import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HumanResources {
    private static ArrayList<Department> deptsList = new ArrayList<Department>();
    private static ArrayList<Staff> staffList = new ArrayList<Staff>();
    private static Scanner input = new Scanner(System.in);
    private final static String TITTLE_ARRAY[] = { "Business Leader", "Project Leader", "Technical Leader" };

    // Display main
    private static void printMainMenu() {
        System.out.println("~~~~~~~~~~~~~~~~~~~MAIN MENU~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Display all staff information.");
        System.out.println("2. Display all departments.");
        System.out.println("3. Display staffs according to the department.");
        System.out.println("4. Add a new staff.");
        System.out.println("5. Search staff by name or ID.");
        System.out.println("6. Display all staff paystub.");
        System.out.println("7. Display all staff paystub ascendingly.");
        System.out.println("0. EXIT.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // Display Add Staff Menu
    private static void printAddStaffMenu() {
        System.out.println("~~~~ADD STAFF MENU~~~~");
        System.out.println("1. Add a new employee");
        System.out.println("2. Add a new manager");
        System.out.println("0. BACK.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
    }

    // Display Search Staff Menu
    private static void printSearchStaffMenu() {
        System.out.println("~~~SEARCH STAFF MENU~~~");
        System.out.println("1. Search staff as name");
        System.out.println("2. Search staff as ID");
        System.out.println("0. BACK.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // Display Select Department Menu
    private static String getDeptMenu() {
        String text = ("~~~~~Pick Departmemt~~~~~\n");
        for (Department dept : deptsList) {
            text += deptsList.indexOf(dept) + 1 + ". " + dept.getID() + " - " + dept.getName() + "\n";
        }
        text += "~~~~~~~~~~~~~~~~~~~~~~~~~";
        text += "\n" + "Your selection: ";
        return text;
    }

    // Display Select Title Menu
    private static String getTitleMenu() {
        String text = ("~~~~~~Pick Title~~~~~~\n");
        for (int i = 0; i < TITTLE_ARRAY.length; i++) {
            text += i + 1 + ". " + TITTLE_ARRAY[i] + "\n";
        }
        text += "~~~~~~~~~~~~~~~~~~~~~~~~~";
        text += "\n" + "Your selection: ";
        return text;
    }

    // Display error message
    private static void printErrorMenu() {
        final String ERROR_MESSAGE = "PLEASE SELECT AS THE MENU!!!";
        printBoxDash('=', ERROR_MESSAGE.length(), ERROR_MESSAGE);
    }

    // Display staff information
    private static void printStaffInformation() {
        final String HEADER_ROW = Staff.getHeaderRow();
        printBoxDash('-', HEADER_ROW.length(), HEADER_ROW);
        for (Staff staff : staffList) {
            System.out.println(staff.displayInformation()); // Print rows
        }
        printBoxDash('-', HEADER_ROW.length(), "");
    }

    // Display departments
    private static void printAllDepartments() {
        final String HEADER_ROW = Department.getHeaderRow();
        printBoxDash('-', HEADER_ROW.length(), HEADER_ROW); // Print title
        for (Department dept : deptsList) { // Advance for loop
            System.out.println(dept.toString()); // Print rows
        }
        printBoxDash('-', HEADER_ROW.length(), "");
    }

    // Display staff information according to the department
    private static void printStaffInformationAsDept() {
        final String HEADER_ROW = Staff.getHeaderRow();
        printBoxDash('-', HEADER_ROW.length(), "");
        for (Department dept : deptsList) {
            System.out.println(dept.getName().toUpperCase());
            printBoxDash('-', HEADER_ROW.length(), HEADER_ROW);
            for (Staff staff : staffList) {
                if (staff.getDepartment().equals(dept.getName())) {
                    System.out.println(staff.displayInformation()); // Print rows
                }
            }
            printBoxDash('-', HEADER_ROW.length(), "");
        }
    }

    // Display paystub of staffs
    private static void printPaystubOfStaffs() {
        printStaffInformation();
    }

    // Display paystub of staff accendingly
    private static void printPaystubOfStaffsAccendingly() {
        final String HEADER_ROW = Staff.getHeaderRow();
        ArrayList<Staff> staffListAccendPaystub = new ArrayList<Staff>(staffList);
        // Overide sort arraylist
        Collections.sort(staffListAccendPaystub, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                return Double.compare(s1.getSalary(), s2.getSalary());
            }
        });
        printBoxDash('-', HEADER_ROW.length(), HEADER_ROW);
        for (Staff staff : staffListAccendPaystub) {
            System.out.println(staff.displayInformation()); // Print rows
        }
        printBoxDash('-', HEADER_ROW.length(), "");
    }

    // Display paystub of staff descendingly
    private static void printPaystubOfStaffsDescendingly() {
        final String HEADER_ROW = Staff.getHeaderRow();
        ArrayList<Staff> staffListAccendPaystub = new ArrayList<Staff>(staffList);
        // Overide sort arraylist
        Collections.sort(staffListAccendPaystub, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                return Double.compare(s2.getSalary(), s1.getSalary());
            }
        });
        Collections.reverse(staffListAccendPaystub);

        printBoxDash('-', HEADER_ROW.length(), HEADER_ROW);
        for (Staff staff : staffListAccendPaystub) {
            System.out.println(staff.displayInformation()); // Print rows
        }
        printBoxDash('-', HEADER_ROW.length(), "");
    }

    // Display Dash
    private static void printBoxDash(char dashType, int amount, String message) {
        for (int i = 0; i < amount; i++) {
            System.out.print(dashType);
        }
        System.out.println();
        if (!message.isEmpty()) {
            System.out.println(message);
            for (int i = 0; i < amount; i++) {
                System.out.print(dashType);
            }
            System.out.println();
        }
    }

    // Create departments
    private final static void addDeptsInfo() {
        Department humanResource = new Department("HR", "Human Resource");
        deptsList.add(humanResource);
        Department informationTechnology = new Department("IT", "Information Technology");
        deptsList.add(informationTechnology);
        Department marketing = new Department("MKT", "Marketing");
        deptsList.add(marketing);
        Department accounting = new Department("ACT", "Accounting");
        deptsList.add(accounting);
    }

    // Get input from user
    private static String getUserInput(String instructionMessage) {
        System.out.print(instructionMessage);
        return input.nextLine();
    }

    // Add new company's member
    private static void addNewStaff() {
        String userSelected;
        loopLabel: while (true) { // Label while with loopLabel
            printAddStaffMenu();
            userSelected = getUserInput("Your selection: ");
            switch (userSelected) {
                case "1":
                    addNewEmployee();
                    break;
                case "2":
                    addNewManager();
                    break;
                case "0":
                    break loopLabel;
                default:
                    printErrorMenu();
            }
        }
    }

    // Add new Manager
    private static void addNewManager() {
        Manager newManager = new Manager();
        System.out.println("~~~~~~ADD MANAGER~~~~~~");
        getInfomation(newManager);
        staffList.add(newManager);
        addOneToDepartment(newManager);
        newManager.calculateSalary();
    }

    // Add new Employee
    private static void addNewEmployee() {
        Employee newEmployee = new Employee();
        System.out.println("~~~~~~ADD EMPLOYEE~~~~~~");
        getInfomation(newEmployee);
        staffList.add(newEmployee);
        addOneToDepartment(newEmployee);
        newEmployee.calculateSalary();
    }

    // Add staff information
    private static void getInfomation(Staff newStaff) {
        String errorMessage;
        int userSelected;
        String staffType = newStaff.getClass().getSimpleName();
        loop: while (true) {
            try {
                if (newStaff.getID() == null) // string
                    newStaff.setID(getUserInput("- Input staff's ID: "));
                if (newStaff.getName() == null) // string
                    newStaff.setName(getUserInput("- Input staff's name: "));
                if (newStaff.getAge() == 0) // int
                    newStaff.setAge(getUserInput("- Input staff's age: "));
                if (newStaff.getSalaryRate() == 0) // double
                    newStaff.setSalaryRate(getUserInput("- Input staff's salary rate: "));
                if (newStaff.getJoinedDate() == null) // string
                    newStaff.setJoinedDate(getUserInput("- Input hired date (dd/mm/yyyy): "));
                if (newStaff.getAbsence() == 0) // int
                    newStaff.setAbsence(getUserInput("- Input number of day(s) off: "));
                if (newStaff.getDepartment() == null) {
                    userSelected = Integer.parseInt(getUserInput(getDeptMenu())) - 1;
                    newStaff.setDepartment(deptsList.get(userSelected).getName());
                }
                if (staffType.equals("Employee")) {
                    Employee castedStaff = (Employee) newStaff;
                    if (castedStaff.getOverTimeHours() == 0) {
                        castedStaff.setOverTimeHours(getUserInput("- Input employee's overtime: "));
                    }
                }
                if (staffType.equals("Manager")) {
                    Manager castedStaff = (Manager) newStaff;
                    if (castedStaff.getTitle() == null) {
                        userSelected = Integer.parseInt(getUserInput(getTitleMenu())) - 1;
                        castedStaff.setTitle((TITTLE_ARRAY[userSelected]));
                    }
                }
                break loop;
            } catch (Exception e) {
                if (e instanceof ParseException) { // Error wrong date format
                    errorMessage = "You've input wrong date format. PLEASE TRY AGAIN!!";
                    printBoxDash('=', errorMessage.length(), errorMessage);
                } else if (e instanceof NumberFormatException) { // Error needed int/double but input string
                    errorMessage = "You have to input a positive number. PLEASE TRY AGAIN!!";
                    printBoxDash('=', errorMessage.length(), errorMessage);
                } else if (e instanceof IndexOutOfBoundsException) {
                    printErrorMenu();
                } else if (e instanceof IOException) {
                    errorMessage = "Your ID have already exist. PLEASE TRY AGAIN!!";
                    printBoxDash('=', errorMessage.length(), errorMessage);
                } else { // *TEST Error unknown
                    System.out.println("-UNHANDLED ERROR-");
                    System.out.println(e);
                }
            }
        }
    }

    // Add staff to department
    private static void addOneToDepartment(Staff newStaff) {
        for (Department dept : deptsList) {
            if (dept.getName().equals(newStaff.getDepartment())) {
                dept.addMember();
            }
        }
    }

    // Search staff
    private static void searchStaff() {
        String userSelected;
        loopLabel: while (true) { // Label while with loopLabel
            printSearchStaffMenu();
            userSelected = getUserInput("Your selection: ");
            switch (userSelected) {
                case "1":
                    searchStaffAsName();
                    break;
                case "2":
                    searchStaffAsID();
                    break;
                case "0":
                    break loopLabel;
                default:
                    printErrorMenu();
            }
        }
    }

    // Search As Name
    private static void searchStaffAsName() {
        String userSelected;
        final String HEADER_ROW = Staff.getHeaderRow();
        userSelected = getUserInput("Input staff's name: ");
        printBoxDash('-', HEADER_ROW.length(), HEADER_ROW);
        for (Staff staff : staffList) {
            if (staff.getName().toUpperCase().contains(userSelected.toUpperCase())) {
                System.out.println(staff.displayInformation()); // Print rows
            }
        }
        printBoxDash('-', HEADER_ROW.length(), "");
    }

    // Search As ID
    private static void searchStaffAsID() {
        String userSelected;
        final String HEADER_ROW = Staff.getHeaderRow();
        userSelected = getUserInput("Input staff's ID: ");
        printBoxDash('-', HEADER_ROW.length(), HEADER_ROW);
        for (Staff staff : staffList) {
            if (staff.getID().toUpperCase().contains(userSelected.toUpperCase())) {
                System.out.println(staff.displayInformation()); // Print rows
            }
        }
        printBoxDash('-', HEADER_ROW.length(), "");
    }

    // MAIN
    public static void main(String[] args) {
        String userSelected;
        // addDeptsInfo(); // Add all default departments into deptsList
        do {
            printMainMenu();
            userSelected = getUserInput("Your selection: ");
            switch (userSelected) {
                case "1":
                    printStaffInformation();
                    break;
                case "2":
                    printAllDepartments();
                    break;
                case "3":
                    printStaffInformationAsDept();
                    break;
                case "4":
                    addNewStaff();
                    break;
                case "5":
                    searchStaff();
                    break;
                case "6":
                    // printPaystubOfStaffs();
                    printPaystubOfStaffsDescendingly();
                    break;
                case "7":
                    printPaystubOfStaffsAccendingly();
                    break;
                case "0":
                    break;
                default:
                    printErrorMenu();
            }
        } while (!userSelected.equals("0"));

    }

    static {
        // Default Staff
        try {
            addDeptsInfo();
            Employee defaultEmp_1 = new Employee();
            Employee defaultEmp_2 = new Employee();
            Manager defaultMan_1 = new Manager();
            Manager defaultMan_2 = new Manager();
            defaultEmp_1.setID("E001");
            defaultEmp_2.setID("E002");
            defaultMan_1.setID("M001");
            defaultMan_2.setID("M002");
            defaultEmp_1.setName("Phong Tuyết Hoa");
            defaultEmp_2.setName("Trần Thị B");
            defaultMan_1.setName("Nguyễn Văn An");
            defaultMan_2.setName("Tạ Văn Lộc");
            defaultEmp_1.setAge("28");
            defaultEmp_2.setAge("24");
            defaultMan_1.setAge("25");
            defaultMan_2.setAge("32");
            defaultEmp_1.setSalaryRate("3.2");
            defaultEmp_2.setSalaryRate("2.2");
            defaultMan_1.setSalaryRate("2.5");
            defaultMan_2.setSalaryRate("4.5");
            defaultEmp_1.setJoinedDate("10/10/2010");
            defaultEmp_2.setJoinedDate("09/10/2019");
            defaultMan_1.setJoinedDate("11/11/2010");
            defaultMan_2.setJoinedDate("01/11/2010");
            defaultEmp_1.setAbsence("10");
            defaultEmp_2.setAbsence("7");
            defaultMan_1.setAbsence("5");
            defaultMan_2.setAbsence("1");
            defaultEmp_1.setDepartment(deptsList.get(0).getName());
            defaultEmp_2.setDepartment(deptsList.get(0).getName());
            defaultMan_1.setDepartment(deptsList.get(1).getName());
            defaultMan_2.setDepartment(deptsList.get(1).getName());
            defaultEmp_1.setOverTimeHours("4");
            defaultEmp_2.setOverTimeHours("3");
            defaultMan_1.setTitle(TITTLE_ARRAY[0]);
            defaultMan_2.setTitle(TITTLE_ARRAY[2]);
            staffList.add(defaultEmp_1);
            staffList.add(defaultEmp_2);
            staffList.add(defaultMan_1);
            staffList.add(defaultMan_2);
            addOneToDepartment(defaultEmp_1);
            addOneToDepartment(defaultEmp_2);
            addOneToDepartment(defaultMan_1);
            addOneToDepartment(defaultMan_2);
            defaultEmp_1.calculateSalary();
            defaultEmp_2.calculateSalary();
            defaultMan_1.calculateSalary();
            defaultMan_2.calculateSalary();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
