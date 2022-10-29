import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Employee extends Staff implements ICalculator {
    // Field Variables Declare
    private final int EMPLOYEE_BASE_SALARY = 3000000;
    private final int OVERTIME_BASE_SALARY = 200000;
    private double overTimeHours;
    private static int numberOfEmployee;

    // Constructors
    Employee() {
        super();
        numberOfEmployee++;
    }

    // Accessor

    double getOverTimeHours() {
        return overTimeHours;
    }

    public static int getNumberOfEmployee() {
        return numberOfEmployee;
    }

    // Mutator

    void setOverTimeHours(String overTimeHours) {
        if (Double.parseDouble(overTimeHours) <= 0) { // Check negative input
            throw new NumberFormatException();
        } else {
            // Convert string -> double 1 decimal place WITH round
            this.overTimeHours = Math.round(Double.parseDouble(overTimeHours) * 10) / 10;
        }
    }

    // Override ICalculator interface
    @Override
    public void calculateSalary() {
        double salary = getSalaryRate() * EMPLOYEE_BASE_SALARY + getOverTimeHours() * OVERTIME_BASE_SALARY;
        // Double 2 decimal place WITH round
        salary = Math.round(salary * 100) / 100;
        setSalary(salary);
    }

    // Override Staff abstract class
    @Override
    String displayInformation() {
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        String space = " ";
        String idCellBlank = space.repeat(getIdHeaderMaxLength() - getID().length());
        String nameCellBlank = space.repeat(getNameHeaderMaxLength() - getName().length());
        String ageCellBlank = space.repeat(AGE_HEADER.length() - Integer.toString(getAge()).length());
        String salaryRateCellBlank = space
                .repeat(SALARY_RATE_HEADER.length() - String.format("%.1f", getSalaryRate()).length());
        String joinedDateCellBlank = space
                .repeat(JOINED_DATE_HEADER.length() - dateformat.format(getJoinedDate()).length());
        String absenceCellBlank = space.repeat(ABSENCE_HEADER.length() - Integer.toString(getAbsence()).length());
        String deptCellBlank = space.repeat(getDepartmentHeaderMaxLength() - getDepartment().length());
        String otCellBlank = space
                .repeat(OVERTIME_TITLE_HEADER.length() - String.format("%.1f", getOverTimeHours()).length());
        String SalaryCellBlank = space.repeat(getSalaryHeaderMaxLength() - String.format("%.2f", getSalary()).length());
        String staffRow = "| " + getID() + idCellBlank + " | " + getName() + nameCellBlank + " | " + getAge()
                + ageCellBlank + " | " + getSalaryRate() + salaryRateCellBlank + " | "
                + dateformat.format(getJoinedDate())
                + joinedDateCellBlank + " | " + getAbsence() + absenceCellBlank + " | "
                + getDepartment() + deptCellBlank + " | " + getOverTimeHours() + otCellBlank + " | "
                + String.format("%.2f", getSalary()) + SalaryCellBlank + " |";
        return staffRow;
    }
}
