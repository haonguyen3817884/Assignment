package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Assignment {

    private String codeString;
    private static int codeInteger;
    private String name;
    private Date birthdate;
    private Boolean gender;
    private String phone;
    private String email;
    private String address;


    private Assignment(){


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

    public void setGender(Boolean gender) {
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

    public Boolean getGender() {
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

    public static Assignment makeLead(String name, String birthdate, Boolean gender, String phone, String email, String address){
        Assignment newObject = new Assignment();
        int countLines =0 ;
        for (char eachCharacter: name.toLowerCase().toCharArray()){
            if (eachCharacter<97 || eachCharacter>97+26){
                throw new IllegalArgumentException();
            }
            else{
                newObject.name = name;
            }
        }

        SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = getFormat.parse(birthdate);
            newObject.birthdate = date;
        }
        catch (ParseException pE){
            throw new IllegalArgumentException();

        }

        newObject.gender = gender;


        for (char eachCharacter: phone.toLowerCase().toCharArray()){
            if (eachCharacter<(int) '0' || eachCharacter>(int) '9'){
                throw new IllegalArgumentException();
            }
            else{
                newObject.phone = phone;

            }
        }


        Pattern patternOfEmail=  Pattern.compile("[a-zA-Z0-9]*[@][a-zA-z]*[.][a-zA-Z]*[.][a-zA-Z]*");
        Matcher emailMatcher = patternOfEmail.matcher(email);
        if (emailMatcher.matches()){
            newObject.email = email;
        }
        else{
            throw new IllegalArgumentException();
        }

        Pattern patternOfAddress = Pattern.compile("[a-zA-Z0-9 ]*");
        Matcher addressMatcher = patternOfAddress.matcher(address);
        if (addressMatcher.matches()){
            newObject.address = address;
        }
        else{
            throw new IllegalArgumentException();
        }


        try {
            Scanner fileReading = new Scanner(new File("leadInformation.csv"));

            while (fileReading.hasNext()){
               String theLastline = fileReading.nextLine();
               countLines = countLines+1;
            }
            codeInteger = countLines;
            fileReading.close();
        }
        catch(FileNotFoundException fileNotfoundException){

        }


        newObject.codeString = "lead"+'_';

        for (int i = 0 ; i < 3 - String.valueOf(codeInteger).length(); ++i ){
            newObject.codeString = newObject.codeString + "0";

        }
        newObject.codeString = newObject.codeString +codeInteger;




        return newObject;

    }


    public void fillList() throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthdate);

        try{
            Scanner fileReading = new Scanner(new File("leadInformation.csv"));
            fileReading.close();

            try{


            FileWriter writing = new FileWriter("leadInformation.csv", true);

            writing.write(codeString);
            writing.write(',');
            writing.write(name);
            writing.write(',');
            writing.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
            writing.write(',');
            writing.write(gender.toString());
            writing.write(',');
            writing.write(phone);
            writing.write(',');
            writing.write(email);
            writing.write(',');
            writing.write(address);
            writing.write("\n");
            writing.close();}
            catch(IOException ioException){
                throw new Exception();
            }
        }
        catch(FileNotFoundException fileNotfoundException){
            try {
                FileWriter fileWriter = new FileWriter("leadInformation.csv");
                fileWriter.write(codeString);
                fileWriter.write(',');
                fileWriter.write(name);
                fileWriter.write(',');
                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                fileWriter.write(',');
                fileWriter.write(gender.toString());
                fileWriter.write(',');
                fileWriter.write(phone);
                fileWriter.write(',');
                fileWriter.write(email);
                fileWriter.write(',');
                fileWriter.write(address);
                fileWriter.write('\n');
                fileWriter.close();
            }
            catch (IOException ioException){
                throw  new Exception();
            }
        }
    }



    public static Assignment[] add(Assignment assignment, Assignment[] listOfleads){
        Assignment[] newListofLeads = new Assignment[listOfleads.length+1];
        int count = 0;
        for (Assignment assignment1 : listOfleads){
            newListofLeads[count] = assignment1;
            count = count + 1;
        }
        newListofLeads[listOfleads.length] = assignment;
        return  newListofLeads;
    }


    public static int getIndex(String codeString,Assignment[] listOfleads){
        int index = 0;
        for (Assignment assignment1: listOfleads){
            if(codeString.equals( assignment1.getCodeString())) {
                break;
            }
            index = index+1;

        }
        return index;
    }

    public static Assignment[] delete(String codeString, Assignment[] listOfleads){
        Assignment[] newListofLeads = new Assignment[listOfleads.length-1];
        int newIndex = 0;

        for(int i =0; i<newListofLeads.length;++i){

            if (i >=getIndex(codeString, listOfleads)){
             newIndex = i +1;
                newListofLeads[i] = listOfleads[newIndex];
                String testing = "lead"+'_';
                int intergerCode = Integer.parseInt(newListofLeads[i].getCodeString().split("_")[1]) -1;
                for (int index = 0; index< 3- String.valueOf(intergerCode).length();++index ){
                    testing = testing + "0";
                }
                testing = testing+intergerCode;
                newListofLeads[i].setCodeString(testing);
            }
            else {
                newIndex = i;
                newListofLeads[i] = listOfleads[newIndex];
            }



        }
        return newListofLeads;
    }




}
