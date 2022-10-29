import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Manager extends Staff implements ICalculator {
    // Field Variables Declare
    private final int MANANGER_BASE_SALARY = 5000000;
    private final int BUSSINESS_RESPONSIBILITY_SALARY = 8000000;
    private final int PROJECT_RESPONSIBILITY_SALARY = 5000000;
    private final int TECHNICAL_RESPONSIBILITY_SALARY = 6000000;

    private String title;

    private static int numberOfManager;

    // Constructors
    Manager() {
        super();
        numberOfManager++;
    }

    // Mutator
    String getTitle() {
        return title;
    }

    public static int getNumberOfManager() {
        return numberOfManager;
    }

    // Accessor
    void setTitle(String title) {
        this.title = title;
    }

    // Override ICalculator interface
    @Override
    public void calculateSalary() {
        double responsibilitySalary;
        switch (getDepartment()) {
            case "Business Leader":
                responsibilitySalary = BUSSINESS_RESPONSIBILITY_SALARY;
                break;
            case "Project Leader":
                responsibilitySalary = PROJECT_RESPONSIBILITY_SALARY;
                break;
            case "Technical Leader":
                responsibilitySalary = TECHNICAL_RESPONSIBILITY_SALARY;
                break;
            default:
                responsibilitySalary = 0;
        }
        double salary = getSalaryRate() * MANANGER_BASE_SALARY + responsibilitySalary;
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
        String titleCellBlank = space
                .repeat(OVERTIME_TITLE_HEADER.length() - getTitle().length());
        String SalaryCellBlank = space.repeat(getSalaryHeaderMaxLength() - String.format("%.2f", getSalary()).length());
        String staffRow = "| " + getID() + idCellBlank + " | " + getName() + nameCellBlank + " | " + getAge()
                + ageCellBlank + " | " + getSalaryRate() + salaryRateCellBlank + " | "
                + dateformat.format(getJoinedDate())
                + joinedDateCellBlank + " | " + getAbsence() + absenceCellBlank + " | "
                + getDepartment() + deptCellBlank + " | " + getTitle() + titleCellBlank + " | "
                + String.format("%.2f", getSalary()) + SalaryCellBlank + " |";
        return staffRow;
    }

}
