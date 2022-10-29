
public class Department {
    // Field Variables Declare
    private String ID;
    private String name;
    private int headcount;

    private static int nameTitleMaxLength;
    private static int numberOfdepartments;
    private static final String ID_HEADER = "Department ID";
    private static final String NAME_HEADER = "Department Name";
    private static final String HEADCOUNT_HEADER = "Staff Amount ";

    // Constructor
    public Department(String ID, String name) {
        this.ID = ID;
        this.name = name;
        this.headcount = 0;
        if (name.length() > nameTitleMaxLength) {
            nameTitleMaxLength = name.length();
        }
        numberOfdepartments++;
    }

    // // Destructor / Terminate
    // @Override
    // public void finalize() throws Throwable {
    // System.out.println("test");
    // numberOfdepartments--;
    // }

    // Mutator

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeadcount(int headcount) {
        this.headcount = headcount;
    }

    public void addMember() {
        headcount++;
    }

    // Overloading
    public void addMember(int numOfmems) {
        headcount += numOfmems;
    }

    // Accessor

    String getID() {
        return ID;
    }

    String getName() {
        return name;
    }

    int getHeadcount() {
        return headcount;
    }

    static int getNumberOfdepartments() {
        return numberOfdepartments;
    }

    // Override Methods
    @Override
    public String toString() {
        String space = " ";
        String IDCellBlank = space.repeat(ID_HEADER.length() - ID.length());
        String headcountCellBlank = space.repeat(HEADCOUNT_HEADER.length() - Integer.toString(headcount).length());
        space = space.repeat(nameTitleMaxLength - name.length());
        String deptRow = "| " + ID + IDCellBlank + " | " + name + space + " | " + headcount + headcountCellBlank
                + " | ";
        return deptRow;
    }

    static String getHeaderRow() {
        String space = " ";
        if (nameTitleMaxLength > NAME_HEADER.length()) {
            space = space.repeat(nameTitleMaxLength - NAME_HEADER.length());
        }
        final String HEADER_ROW = "| " + ID_HEADER + " | " + NAME_HEADER + space + " | " + HEADCOUNT_HEADER + " |";
        return HEADER_ROW;
    }

}
