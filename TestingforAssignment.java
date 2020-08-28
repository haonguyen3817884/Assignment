package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.ServiceConfigurationError;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestingforAssignment {


    public static void main(String[] args){

        Assignment[] listOfleads = new Assignment[0];
        try {


            Scanner fileReading = new Scanner(new File("leadInformation.csv"));
            while (fileReading.hasNext()) {
                String eachLine = fileReading.nextLine();
                String[] listOfLeadsInformation = eachLine.split(",");
                if (listOfLeadsInformation[3].equals("true")) {
                    Assignment assignment = Assignment.makeLead(listOfLeadsInformation[1], listOfLeadsInformation[2], true, listOfLeadsInformation[4], listOfLeadsInformation[5], listOfLeadsInformation[6]);
                    assignment.setCodeString(listOfLeadsInformation[0]);
                    listOfleads = Assignment.add(assignment, listOfleads);
                } else {
                    Assignment assignment = Assignment.makeLead(listOfLeadsInformation[1], listOfLeadsInformation[2], false, listOfLeadsInformation[4], listOfLeadsInformation[5], listOfLeadsInformation[6]);
                    assignment.setCodeString(listOfLeadsInformation[0]);
                    listOfleads = Assignment.add(assignment, listOfleads);

                }

            }
            fileReading.close();
        }
        catch(FileNotFoundException fileNotfoundException){
            System.out.print("no list in the system");
        }

        while (true){
            System.out.print("Welcome to CRM");
            System.out.print("\n");
            System.out.print("What would you like to do?");
            System.out.print("\n");
            System.out.print("- View leads in detail");
            System.out.print("\n");
            System.out.print("- Add a new lead");
            System.out.print("\n");
            System.out.print("- Delete a lead");
            System.out.print("\n");
            System.out.print("- Update for a lead");
            System.out.print("\n");
            System.out.print("Type 'view' for viewing, 'add' for adding, 'delete' for deleting, 'update' for updating and 'exit' for stopping");


            Scanner functionInput = new Scanner(System.in);
            String readingFunctionInput = functionInput.nextLine();
            String readingFunctionInputsanitized = readingFunctionInput.toLowerCase().trim();
            if (readingFunctionInputsanitized.equals("add")){

                while (true){




                        try{

                            Scanner input = new Scanner(System.in);

                            System.out.print("input your name");
                            String leadName = input.nextLine();

                            for (char eachCharacter: leadName.toLowerCase().trim().toCharArray()){
                                if (eachCharacter<97 || eachCharacter>97+26){
                                    throw new IllegalArgumentException();
                                }
                            }


                            System.out.print("input your birthdate");
                            String leadBirthdate = input.nextLine();
                            SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = getFormat.parse(leadBirthdate);



                            System.out.print("input your phone");
                            String leadPhone = input.nextLine();



                            System.out.print("input your email");
                            String leadEmail = input.nextLine();

                            Pattern patternOfEmail=  Pattern.compile("[a-zA-Z0-9]*[@][a-zA-z]*[.][a-zA-Z]*[.][a-zA-Z]*");
                            Matcher emailMatcher = patternOfEmail.matcher(leadEmail);
                            if (!emailMatcher.matches()){


                                throw new IllegalArgumentException();
                            }


                            System.out.print("input your address");
                            String leadAddress = input.nextLine();
                            Pattern patternOfAddress = Pattern.compile("[a-zA-Z0-9 ]*");
                            Matcher addressMatcher = patternOfAddress.matcher(leadAddress);
                            if (!addressMatcher.matches()){

                                throw new IllegalArgumentException();
                            }



                            System.out.print("input your gender");
                            boolean leadGender = input.nextBoolean();



                            Assignment assignment = Assignment.makeLead(leadName,leadBirthdate,leadGender,leadPhone,leadEmail,leadAddress);

                            assignment.fillList();
                            listOfleads = Assignment.add(assignment,listOfleads);

                        }
                        catch (Exception exception){
                            continue;
                        }
                        System.out.print("Successfully add a lead");
                        System.out.print(listOfleads.length);
                        break;







                }

            }
            else if (readingFunctionInputsanitized.equals("delete")){
                while (true){
                    Scanner codeStringinput = new Scanner(System.in);
                    System.out.print("input codeString");
                    String readingCodestringInput = codeStringinput.nextLine();
                    boolean isCodestringAdded = false;
                    for (Assignment assignment: listOfleads){
                        if(assignment.getCodeString().equals(readingCodestringInput)){
                            isCodestringAdded = true;
                        }
                    }
                    if(isCodestringAdded){
                        listOfleads = Assignment.delete(readingCodestringInput,listOfleads);
                        try{
                            FileWriter fileWriting = new FileWriter("leadInformation.csv");


                            for (Assignment assignment: listOfleads) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(assignment.getBirthdate());
                                fileWriting.write(assignment.getCodeString());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getName());
                                fileWriting.write(',');
                                fileWriting.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                fileWriting.write(',');
                                fileWriting.write(assignment.getGender().toString());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getPhone());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getEmail());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getAddress());
                                fileWriting.write('\n');

                            }
                            fileWriting.close();
                        }
                        catch (IOException ioException){

                        }

                    }
                    else {
                        System.out.print("Unsuccessful");
                    }
                    break;
                }
            }
            else if (readingFunctionInputsanitized.equals("exit")){

                break;

            }
            else if (readingFunctionInputsanitized.equals("view")){
                for (Assignment assignment: listOfleads){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(assignment.getBirthdate());
                    System.out.print(assignment.getCodeString());
                    System.out.print(',');
                    System.out.print(assignment.getName());
                    System.out.print(',');
                    System.out.print(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                    System.out.print(',');
                    System.out.print(assignment.getGender());
                    System.out.print(',');
                    System.out.print(assignment.getPhone());
                    System.out.print(',');
                    System.out.print(assignment.getEmail());
                    System.out.print(',');
                    System.out.print(assignment.getAddress());
                    System.out.print('\n');

                }
                continue;
            }

            else if (readingFunctionInputsanitized.equals("update")){

                while (true) {
                    Scanner codeStringinput = new Scanner(System.in);
                    System.out.print("input codeString");
                    String readingCodestringInput = codeStringinput.nextLine();
                    boolean isCodestringAdded = false;
                    for (Assignment assignment : listOfleads) {
                        if (readingCodestringInput.equals(assignment.getCodeString())) {
                            isCodestringAdded = true;
                        }
                    }
                    if (isCodestringAdded){
                        int indexOflead = Assignment.getIndex(readingCodestringInput, listOfleads);
                        Scanner parameterNameinput = new Scanner(System.in);
                        String readingParameternameInput = parameterNameinput.nextLine();
                        Scanner newUpdateinput = new Scanner(System.in);
                        if (readingParameternameInput.equals("name")){

                            while(true){

                                try{
                                    System.out.print("input your name");
                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                    for (char eachCharacter: readingNewupdateInput.toLowerCase().trim().toCharArray()){
                                        if (eachCharacter<97 || eachCharacter>97+26){
                                            throw new IllegalArgumentException();
                                        }
                                    }

                                    listOfleads[indexOflead].setName(readingNewupdateInput);



                                }
                                catch (Exception exception){
                                    continue;

                                }

                                break;

                            }



                        }
                        else if(readingParameternameInput.equals("birthdate")){
                            while (true){
                                try {
                                    System.out.print("input your birthdate");
                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = getFormat.parse(readingNewupdateInput);


                                    listOfleads[indexOflead].setBirthdate(date);

                                }
                                catch (Exception exception){
                                    continue;
                                }
                                break;
                            }
                        }
                        else if ( readingParameternameInput.equals("phone")){
                            while (true){
                                try {
                                    System.out.print("input your phone");
                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                    listOfleads[indexOflead].setPhone(readingNewupdateInput);
                                }
                                catch (Exception exception){
                                    continue;
                                }
                                break;
                            }
                        }
                        else if ( readingParameternameInput.equals("email")){


                            try {
                                System.out.print("input your email");
                                String readingNewupdateInput = newUpdateinput.nextLine();
                                Pattern patternOfEmail=  Pattern.compile("[a-zA-Z0-9]*[@][a-zA-z]*[.][a-zA-Z]*[.][a-zA-Z]*");
                                Matcher emailMatcher = patternOfEmail.matcher(readingNewupdateInput);
                                if (!emailMatcher.matches()){


                                    throw new IllegalArgumentException();
                                }

                                listOfleads[indexOflead].setEmail(readingNewupdateInput);
                            }
                            catch (Exception exception){
                                continue;
                            }
                            break;
                        }



                        else if ( readingParameternameInput.equals("address")){
                            while (true){
                                try {
                                    System.out.print("input your address");
                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                    Pattern patternOfAddress = Pattern.compile("[a-zA-Z0-9 ]*");
                                    Matcher addressMatcher = patternOfAddress.matcher(readingNewupdateInput);
                                    if (!addressMatcher.matches()){

                                        throw new IllegalArgumentException();
                                    }


                                    listOfleads[indexOflead].setAddress(readingNewupdateInput);

                                }
                                catch (Exception exception){
                                    continue;
                                }
                                break;
                            }
                        }





                        else if(readingParameternameInput.equals("gender")){
                            while (true){
                                try {
                                    System.out.print("input your gender");
                                    boolean readingNewupdateInput = newUpdateinput.nextBoolean();
                                    listOfleads[indexOflead].setGender(readingNewupdateInput);
                                }
                                catch (Exception exception){
                                    continue;
                                }
                                break;
                            }
                        }










                        else {
                            System.out.print("Unsuccessful");
                            break;
                        }
                        try {
                            FileWriter fileWriting = new FileWriter("leadInformation.csv");


                            for (Assignment assignment : listOfleads) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(assignment.getBirthdate());
                                fileWriting.write(assignment.getCodeString());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getName());
                                fileWriting.write(',');
                                fileWriting.write(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE));
                                fileWriting.write(',');
                                fileWriting.write(assignment.getGender().toString());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getPhone());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getEmail());
                                fileWriting.write(',');
                                fileWriting.write(assignment.getAddress());
                                fileWriting.write('\n');

                            }
                            fileWriting.close();
                        }
                        catch (IOException ioException){

                        }
                    }
                    else {
                        System.out.print("Unsuccessful");

                    }
                    break;

                }
            }
            else {
                continue;
            }
        }



    }
}
