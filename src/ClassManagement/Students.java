package ClassManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Students
{
    private int id_student;
    private String Name;
    private String PhoneNr;
    private String BirthDate;
    private float FinalGrade;

    public int getId_Student() { return id_student; }
    public String getName() { return Name; }
    public String getPhoneNr() { return PhoneNr; }
    public String getBirthDate() { return BirthDate; }

    public float getFinalGrade() { return FinalGrade; }

    public void setId_Student(int id_student) { this.id_student = id_student; }
    public void setName(String Name) { this.Name = Name; }
    public void setPhoneNr(String PhoneNr) { this.PhoneNr = PhoneNr; }
    public void setBirthDate(String BirthDate) { this.BirthDate = BirthDate; }
    public void setFinalGrade(float FinalGrade) { this.FinalGrade = FinalGrade; }
}
