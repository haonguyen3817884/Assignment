package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Interaction {

    private String stringCode;
    private Date date;
    private Lead lead;
    private String mean;
    private String status;



    public Interaction(String stringCodeinput, Date dateInput, Lead leadInput, String meanInput, String statusInput){
        this.stringCode = stringCodeinput;
        this.date = dateInput;
        this.lead = leadInput;
        this.mean = meanInput;
        this.status = statusInput;


    }

    public void setStringCode(String stringCode) {
        this.stringCode = stringCode;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
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

    public Lead getLead() {
        return lead;
    }

    public String getMean() {
        return mean;
    }

    public String getStatus() {
        return status;
    }

    public void fillList() throws Exception {

        String interactionTitle = "codeString,date,stringCodeofLead,mean,status";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);



        try{

            Scanner fileReading = new Scanner(new File("interaction.csv"));
            fileReading.close();

            try{
                FileWriter writing = new FileWriter("interaction.csv", true);
                writing.write(this.stringCode);
                writing.write(',');
                writing.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                writing.write(',');
                writing.write(lead.getCodeString());
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
                FileWriter fileWriter = new FileWriter("interaction.csv");
                fileWriter.write(interactionTitle);
                fileWriter.write('\n');
                fileWriter.write(this.stringCode);
                fileWriter.write(',');
                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                fileWriter.write(',');
                fileWriter.write(lead.getCodeString());
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
