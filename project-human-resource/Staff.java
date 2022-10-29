import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

abstract class Staff {
    // Field Variables Declare
    private String ID;
    private String name;
    private int age;
    private double salaryRate;
    private Date joinedDate;
    private int absence;
    private String department;
    private double salary;

    private static int numberOfStaff;
    private static ArrayList<String> uniqueIdList = new ArrayList<>();
    private static int nameHeaderMaxLength;
    private static int idHeaderMaxLength;
    private static int departmentHeaderMaxLength;
    private static int salaryHeaderMaxLength;

    protected static final String ID_HEADER = "ID";
    protected static final String NAME_HEADER = "Name";
    protected static final String AGE_HEADER = "Age";
    protected static final String SALARY_RATE_HEADER = "Salary Rate";
    protected static final String JOINED_DATE_HEADER = "Joined Date";
    protected static final String ABSENCE_HEADER = "Absence";
    protected static final String DEPARTMENT_HEADER = "Department";
    protected static final String OVERTIME_TITLE_HEADER = "Overtime/Title   "; // Add spaces for cheating fixed titles
    protected static final String SALARY_HEADER = "Salary";

    // Constructor
    public Staff() {
        numberOfStaff++;
    }

    // Methods
    abstract String displayInformation();

    // Accessor
    String getID() {
        return ID;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    double getSalaryRate() {
        return salaryRate;
    }

    Date getJoinedDate() {
        return joinedDate;
    }

    int getAbsence() {
        return absence;
    }

    String getDepartment() {
        return department;
    }

    double getSalary() {
        return salary;
    };

    static int getNumberOfStaff() {
        return numberOfStaff;
    }

    static String getHeaderRow() {
        String space = " ";
        String idSpace = "";
        String nameSpace = "";
        String departmentSpace = "";
        String salarySpace = "";
        if (idHeaderMaxLength > ID_HEADER.length()) {
            idSpace = space.repeat(idHeaderMaxLength - ID_HEADER.length());
        }
        if (nameHeaderMaxLength > NAME_HEADER.length()) {
            nameSpace = space.repeat(nameHeaderMaxLength - NAME_HEADER.length());
        }
        if (departmentHeaderMaxLength > DEPARTMENT_HEADER.length()) {
            departmentSpace = space.repeat(departmentHeaderMaxLength - DEPARTMENT_HEADER.length());
        }
        if (salaryHeaderMaxLength > SALARY_HEADER.length()) {
            salarySpace = space.repeat(salaryHeaderMaxLength - SALARY_HEADER.length());
        }
        final String HEADER_ROW = "| " + ID_HEADER + idSpace + " | " + NAME_HEADER + nameSpace + " | " + AGE_HEADER
                + " | " + SALARY_RATE_HEADER + " | " + JOINED_DATE_HEADER + " | " + ABSENCE_HEADER + " | "
                + DEPARTMENT_HEADER + departmentSpace + " | " + OVERTIME_TITLE_HEADER + " | " + SALARY_HEADER
                + salarySpace + " |";

        return HEADER_ROW;
    }

    static int getIdHeaderMaxLength() {
        return idHeaderMaxLength;
    }

    static int getNameHeaderMaxLength() {
        return nameHeaderMaxLength;
    }

    static int getDepartmentHeaderMaxLength() {
        return departmentHeaderMaxLength;
    }

    static int getSalaryHeaderMaxLength() {
        return salaryHeaderMaxLength;
    }

    // Mutator
    void setID(String ID) throws Exception {
        if (ID.length() > getIdHeaderMaxLength()) {
            setIdHeaderMaxLength(ID.length());
        }
        // ID must be unique
        if (uniqueIdList.contains(ID.toUpperCase())) {
            throw new IOException("Dupiticate ID!");
        } else {
            uniqueIdList.add(ID.toUpperCase());
            this.ID = ID;
        }
    }

    void setName(String name) {
        if (name.length() > getNameHeaderMaxLength()) {
            setNameHeaderMaxLength(name.length());
        }
        this.name = name;
    }

    void setDepartment(String department) {
        if (department.length() > getDepartmentHeaderMaxLength()) {
            setDepartmentHeaderMaxLength(department.length());
        }
        this.department = department;
    }

    void setJoinedDate(String joinedDate) throws Exception {
        // throws to prevent wrong input
        this.joinedDate = new SimpleDateFormat("dd/MM/yyyy").parse(joinedDate);
    }

    void setSalaryRate(String salaryRate) {
        if (Double.parseDouble(salaryRate) <= 0) { // Check negative input
            throw new NumberFormatException();
        } else {
            // Convert string -> double 1 decimal place WITHOUT round
            this.salaryRate = Math.floor(Double.parseDouble(salaryRate) * 10) / 10;
        }
    }

    void setAge(String age) {
        if (Double.parseDouble(age) <= 0) { // Check negative input
            throw new NumberFormatException();
        } else {
            this.age = (int) Double.parseDouble(age); // Convert string -> double -> int
        }
    }

    void setAbsence(String absence) {
        if (Double.parseDouble(absence) < 0) { // Check negative input (absence could be 0)
            throw new NumberFormatException();
        } else {
            this.absence = (int) Double.parseDouble(absence);// Convert string -> double -> int
        }
    }

    void setSalary(double salary) {
        if (String.format("%.2f", getSalary()).length() > getSalaryHeaderMaxLength()) {
            setSalaryHeaderMaxLength(String.format("%.2f", salary).length());
        }
        this.salary = salary;
    }

    static void setNameHeaderMaxLength(int nameHeaderMaxLength) {
        Staff.nameHeaderMaxLength = nameHeaderMaxLength;
    }

    static void setIdHeaderMaxLength(int idHeaderMaxLength) {
        Staff.idHeaderMaxLength = idHeaderMaxLength;
    }

    static void setDepartmentHeaderMaxLength(int departmentHeaderMaxLength) {
        Staff.departmentHeaderMaxLength = departmentHeaderMaxLength;
    }

    static void setSalaryHeaderMaxLength(int salaryHeaderMaxLength) {
        Staff.salaryHeaderMaxLength = salaryHeaderMaxLength;
    }

}
