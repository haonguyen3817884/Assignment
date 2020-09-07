package com.company;



import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Lead {

    private String codeString;
    private String name;
    private Date birthdate;
    private boolean gender;
    private String phone;
    private String email;
    private String address;

    public Lead(String codeStringinput, String nameInput, Date birthdateInput, boolean genderInput, String phoneInput, String emailInput, String addressInput){
        this.codeString = codeStringinput;
        this.name = nameInput;
        this.birthdate = birthdateInput;
        this.gender = genderInput;
        this.phone = phoneInput;
        this.email = emailInput;
        this.address = addressInput;

    }


    public void setCodeString(String codeString) {
        this.codeString = codeString;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCodeString() {
        return codeString;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public boolean getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }




    public void fillList() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.birthdate);

        try{
            Scanner fileReading = new Scanner(new File("lead.csv"));
            fileReading.close();

            try{
                FileWriter writing = new FileWriter("lead.csv",true);
                writing.write(this.codeString);
                writing.write(',');
                writing.write(this.name);
                writing.write(',');
                writing.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                writing.write(',');
                writing.write(String.valueOf(this.gender));
                writing.write(',');
                writing.write(this.phone);
                writing.write(',');
                writing.write(this.email);
                writing.write(',');
                writing.write(this.address);
                writing.write('\n');
                writing.close();
            }
            catch(IOException ioException){
                throw new Exception();
            }

        }
        catch (FileNotFoundException fileNotfoundException){
            try{
                FileWriter fileWriter = new FileWriter("lead.csv");
                fileWriter.write(this.codeString);
                fileWriter.write(',');
                fileWriter.write(this.name);
                fileWriter.write(',');
                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                fileWriter.write(',');
                fileWriter.write(String.valueOf(this.gender));
                fileWriter.write(',');
                fileWriter.write(this.phone);
                fileWriter.write(',');
                fileWriter.write(this.email);
                fileWriter.write(',');
                fileWriter.write(this.address);
                fileWriter.write('\n');
                fileWriter.close();
            }
            catch (IOException ioException){
                throw new Exception();
            }
        }
    }
}
