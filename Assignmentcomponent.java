package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Assignmentcomponent {

    private String stringCode;
    private Date date;
    private Assignmentredo assignmentRedo;
    private String mean;
    private String status;



    public Assignmentcomponent(String stringCodeinput, Date dateInput, Assignmentredo assignmentRedoinput, String meanInput, String statusInput){
        this.stringCode = stringCodeinput;
        this.date = dateInput;
        this.assignmentRedo = assignmentRedoinput;
        this.mean = meanInput;
        this.status = statusInput;


    }

    public void setStringCode(String stringCode) {
        this.stringCode = stringCode;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAssignmentRedo(Assignmentredo assignmentRedo) {
        this.assignmentRedo = assignmentRedo;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStringCode() {
        return stringCode;
    }

    public Date getDate() {
        return date;
    }

    public Assignmentredo getAssignmentRedo() {
        return assignmentRedo;
    }

    public String getMean() {
        return mean;
    }

    public String getStatus() {
        return status;
    }

    public void fillList() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);



        try{

            Scanner fileReading = new Scanner(new File("interactionInformation.csv"));
            fileReading.close();

            try{
                FileWriter writing = new FileWriter("interactionInformation.csv", true);
                writing.write(this.stringCode);
                writing.write(',');
                writing.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                writing.write(',');
                writing.write(assignmentRedo.getCodeString());
                writing.write(',');
                writing.write(mean);
                writing.write(',');
                writing.write(status);
                writing.write('\n');
                writing.close();
                
            }
            catch (IOException ioException){
                throw new Exception();
            }
        }
        catch (FileNotFoundException fileNotfoundException){
            try {
                FileWriter fileWriter = new FileWriter("interactionInformation.csv");
                fileWriter.write(this.stringCode);
                fileWriter.write(',');
                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                fileWriter.write(',');
                fileWriter.write(assignmentRedo.getCodeString());
                fileWriter.write(',');
                fileWriter.write(mean);
                fileWriter.write(',');
                fileWriter.write(status);
                fileWriter.write('\n');
                fileWriter.close();
            }
            catch(IOException ioException){
                throw new Exception();
            }
        }
    }





}
