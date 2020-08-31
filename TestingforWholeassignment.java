package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestingforWholeassignment {
    public static void main(String[] args) {
        while (true){



            boolean isLeadinformationAdded = false;
            int numberOflines = 0;
            try{
                Scanner fileReading = new Scanner(new File("leadInformation.csv"));

                while (fileReading.hasNext()){
                    String eachLine = fileReading.nextLine();
                    numberOflines += 1;
                }

                if (numberOflines >0){
                    isLeadinformationAdded = true;
                }

                fileReading.close();

            }
            catch (FileNotFoundException fileNotfoundException){

            }

            Scanner functionInput1 = new Scanner(System.in);
            System.out.print("Welcome to CRM");
            System.out.print('\n');
            System.out.print("What would you like to do?");
            System.out.print('\n');
            System.out.print("- Manage lead information");
            System.out.print('\n');
            System.out.print("- Manage interaction information");
            System.out.print('\n');
            System.out.print("Type lead for managing lead information, interaction for managing interaction information, exit for stopping");

            String readingFunctioninput1 = functionInput1.nextLine();
            String readingFunctioninput1Sanitized = readingFunctioninput1.toLowerCase().trim();

            if (readingFunctioninput1Sanitized.equals("lead")){


                while (true){


                    int lengthOfarray = 0;
                    boolean isThereaList = false;
                    try{
                        Scanner fileReading = new Scanner(new File("leadInformation.csv"));
                        while (fileReading.hasNext()){
                            String eachLine = fileReading.nextLine();
                            lengthOfarray = lengthOfarray + 1;
                        }
                        if(lengthOfarray>0){
                            isThereaList = true;
                        }
                        fileReading.close();

                    }
                    catch (FileNotFoundException fileNotfoundException){

                    }
                    Assignmentredo[] listOfleads = new Assignmentredo[lengthOfarray];
                    int index = 0;

                    try{
                        Scanner fileReading = new Scanner(new File("leadInformation.csv"));
                        while (fileReading.hasNext()){
                            String eachLine = fileReading.nextLine();
                            String[] theArray = eachLine.split(",");
                            SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = simpleDateformat.parse(theArray[2]);
                            if (theArray[3].equals("true")){
                                Assignmentredo assignmentRedo = new Assignmentredo(theArray[0],theArray[1],date,true,theArray[4],theArray[5],theArray[6]);
                                listOfleads[index] = assignmentRedo;
                            }
                            else {
                                Assignmentredo assignmentRedo = new Assignmentredo(theArray[0],theArray[1],date,false,theArray[4],theArray[5],theArray[6]);
                                listOfleads[index] = assignmentRedo;
                            }

                            index += 1;
                        }
                        fileReading.close();


                    }catch (Exception exception){

                    }


                    int lengthOfarray1 = 0;
                    boolean isThereaList1 = false;
                    try{
                        Scanner fileReading = new Scanner(new File("interactionInformation.csv"));
                        while (fileReading.hasNext()){
                            String eachLine = fileReading.nextLine();
                            lengthOfarray1 = lengthOfarray1 + 1;
                        }
                        if(lengthOfarray1>0){
                            isThereaList1 = true;
                        }
                        fileReading.close();

                    }
                    catch (FileNotFoundException fileNotfoundException){

                    }
                    Assignmentcomponent[] listOfinteraction = new Assignmentcomponent[lengthOfarray1];
                    int index2 = 0;

                    try{
                        Scanner fileReading = new Scanner(new File("interactionInformation.csv"));
                        while (fileReading.hasNext()){
                            String eachLine = fileReading.nextLine();
                            String[] theArray = eachLine.split(",");
                            SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = simpleDateformat.parse(theArray[1]);
                            for (Assignmentredo assignmentRedo: listOfleads){
                                if (assignmentRedo.getCodeString().equals(theArray[2])){
                                    Assignmentcomponent assignmentComponent = new Assignmentcomponent(theArray[0],date,assignmentRedo,theArray[3],theArray[4]);
                                    listOfinteraction[index2] = assignmentComponent;
                                }
                            }

                            index2 += 1;
                        }
                        fileReading.close();
                    }catch (Exception exception){

                    }






















                    Scanner functionInput = new Scanner(System.in);
                    System.out.print("Welcome to CRM");
                    System.out.print('\n');
                    System.out.print("What would you like to do?");
                    System.out.print('\n');
                    System.out.print("- View leads in detail");
                    System.out.print('\n');
                    System.out.print("- Add lead detail");
                    System.out.print('\n');
                    System.out.print("- Delete lead detail");
                    System.out.print('\n');
                    System.out.print("- Update lead detail");
                    System.out.print("\n");
                    System.out.print("Type view for viewing, add for adding, delete for deleting, update for updating and exit for stopping");

                    String readingInput = functionInput.nextLine();
                    String readingInputsanitized = readingInput.toLowerCase().trim();
                    if (readingInputsanitized.equals("view")){
                        if (isThereaList){
                            for (Assignmentredo assignmentRedo: listOfleads){
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(assignmentRedo.getBirthdate());
                                System.out.print(assignmentRedo.getCodeString());
                                System.out.print(',');
                                System.out.print(assignmentRedo.getName());
                                System.out.print(',');
                                System.out.print(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                System.out.print(',');
                                System.out.print(assignmentRedo.getGender());
                                System.out.print(',');
                                System.out.print(assignmentRedo.getPhone());
                                System.out.print(',');
                                System.out.print(assignmentRedo.getEmail());
                                System.out.print(',');
                                System.out.print(assignmentRedo.getAddress());
                                System.out.print('\n');
                            }
                        }
                        else {
                            System.out.print("");
                            continue;
                        }
                    }
                    else if(readingInputsanitized.equals("add")){
                        while (true){
                            try{


                                String codeString = "lead"+'_';
                                int codeInteger = 0;
                                try{
                                    Scanner fileReading = new Scanner(new File("leadInformation.csv"));
                                    while (fileReading.hasNext()){
                                        String eachLine = fileReading.nextLine();
                                        codeInteger = Integer.parseInt(eachLine.split(",")[0].split("_")[1]);
                                    }

                                    fileReading.close();

                                }
                                catch (FileNotFoundException fileNotFoundexception){

                                }
                                codeInteger = codeInteger + 1;
                                for (int i = 0; i < 3 - String.valueOf(codeInteger).length();++i){
                                    codeString = codeString + '0';
                                }

                                codeString = codeString + codeInteger;






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




//                        System.out.print("input codeString of the lead");
//                        String interactionLead = input.nextLine();
//                        boolean isStringcodeOfleadNotadded = true;
//                        try{
//                            Scanner fileReading = new Scanner(new File("leadInformation.csv"));
//
//                            while (fileReading.hasNext()){
//                                String eachLine = fileReading.nextLine();
//                                if (eachLine.split(",")[0].equals(interactionLead)){
//                                    isStringcodeOfleadNotadded = false;
//                                }
//                                else {
//                                    isStringcodeOfleadNotadded = true;
//                                }
//                            }
//                            fileReading.close();
//                            if (isStringcodeOfleadNotadded){
//                                throw new IllegalArgumentException();
//                            }
//
//
//                        }
//                        catch (FileNotFoundException fileNotfoundException){
//                            break;
//                        }



                                Assignmentredo assignmentRedo = new Assignmentredo(codeString, leadName, date,leadGender,leadPhone, leadEmail, leadAddress);
                                assignmentRedo.fillList();

                            }
                            catch (Exception exception){
                                continue;
                            }
                            break;
                        }
                    }
                    else if(readingInputsanitized.equals("delete")){

                        if (isThereaList){
                            while (true){
                                Scanner codeStringinput = new Scanner(System.in);
                                String readingCodestringInput = codeStringinput.nextLine();
                                boolean isCodestringAdded = false;
                                for (Assignmentredo assignmentRedo: listOfleads){
                                    if (assignmentRedo.getCodeString().equals(readingCodestringInput.toLowerCase().trim())){
                                        isCodestringAdded = true;
                                    }
                                }
                                if (isCodestringAdded){
                                    try {
                                        FileWriter fileWriter = new FileWriter("leadInformation.csv");
                                        for (Assignmentredo assignmentRedo: listOfleads){
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTime(assignmentRedo.getBirthdate());
                                            if(assignmentRedo.getCodeString().equals(readingCodestringInput)){

                                            }
                                            else {
                                                fileWriter.write(assignmentRedo.getCodeString());
                                                fileWriter.write(',');
                                                fileWriter.write(assignmentRedo.getName());
                                                fileWriter.write(',');
                                                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                                fileWriter.write(',');
                                                fileWriter.write(String.valueOf(assignmentRedo.getGender()));
                                                fileWriter.write(',');
                                                fileWriter.write(assignmentRedo.getPhone());
                                                fileWriter.write(',');
                                                fileWriter.write(assignmentRedo.getEmail());
                                                fileWriter.write(',');
                                                fileWriter.write(assignmentRedo.getAddress());
                                                fileWriter.write('\n');
                                            }
                                        }
                                        fileWriter.close();
                                    }
                                    catch (Exception exception){

                                    }




                                    if(isThereaList1){
                                        try {
                                            FileWriter fileWriter = new FileWriter("interactionInformation.csv");
                                            for (Assignmentcomponent assignmentComponent: listOfinteraction){
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.setTime(assignmentComponent.getDate());
                                                if(assignmentComponent.getAssignmentRedo().getCodeString().equals(readingCodestringInput)){

                                                }
                                                else {
                                                    fileWriter.write(assignmentComponent.getStringCode());
                                                    fileWriter.write(',');
                                                    fileWriter.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                                    fileWriter.write(',');
                                                    fileWriter.write(assignmentComponent.getAssignmentRedo().getCodeString());
                                                    fileWriter.write(',');
                                                    fileWriter.write(assignmentComponent.getMean());
                                                    fileWriter.write(',');
                                                    fileWriter.write(assignmentComponent.getStatus());
                                                    fileWriter.write('\n');
                                                }
                                                System.out.print("testing");

                                            }
                                            fileWriter.close();



                                        }
                                        catch (IOException ioException){

                                        }
                                    }

                                    break;
                                }

                            }
                        }
                    }
                    else if (readingInputsanitized.equals("update")){

                        if(isThereaList){
                            while(true){
                                Scanner codeStringinput = new Scanner(System.in);
                                String readingCodestringInput = codeStringinput.nextLine();
                                boolean isCodestringAdded = false;
                                int secondIndex = 0;
                                int count = 0;
                                for (Assignmentredo assignmentRedo: listOfleads){
                                    if (assignmentRedo.getCodeString().equals(readingCodestringInput)){
                                        isCodestringAdded = true;
                                        secondIndex = count;
                                    }
                                    count += 1;

                                }

                                if (isCodestringAdded){

                                    while (true){
                                        Scanner informationSectioninput = new Scanner(System.in);
                                        String readingInformationsectionInput = informationSectioninput.nextLine();
                                        Scanner newUpdateinput = new Scanner(System.in);
                                        String readingInformationsectionInputsanitized = readingInformationsectionInput.toLowerCase().trim();
                                        if(readingInformationsectionInputsanitized.equals("name")){
                                            while (true){
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                String readingNewupdateInputsanitized = readingInputsanitized.toLowerCase().trim();
                                                try {

                                                    Pattern patternOfname = Pattern.compile("[a-z ]+");
                                                    Matcher nameMatcher = patternOfname.matcher(readingNewupdateInputsanitized);
                                                    if (nameMatcher.matches()){

                                                    }
                                                    else {
                                                        throw new IllegalArgumentException();
                                                    }

                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setName(readingNewupdateInput);
                                                break;
                                            }
                                        }
                                        else if(readingInformationsectionInputsanitized.equals("birthdate")){
                                            while (true){
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                Date date;
                                                try {
                                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                    date = getFormat.parse(readingNewupdateInput);
                                                }
                                                catch (ParseException parseException){
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setBirthdate(date);
                                                break;
                                            }

                                        }

                                        else if(readingInformationsectionInputsanitized.equals("gender")){
                                            while (true){


                                                try {
                                                    boolean readingNewupdateInput = newUpdateinput.nextBoolean();
                                                    listOfleads[secondIndex].setGender(readingNewupdateInput);
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    continue;
                                                }

                                                break;
                                            }

                                        }
                                        else if(readingInformationsectionInputsanitized.equals("phone")){
                                            while (true){
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                try {
                                                    Pattern patternOfphone = Pattern.compile("[0-9 ]+");
                                                    Matcher phoneMatcher = patternOfphone.matcher(readingNewupdateInput);
                                                    if (!phoneMatcher.matches()){
                                                        throw new IllegalArgumentException();
                                                    }
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setPhone(readingNewupdateInput);
                                                break;
                                            }
                                        }
                                        else if(readingInformationsectionInputsanitized.equals("email")){
                                            while (true){
                                                String readingNewupdateInput = newUpdateinput.nextLine();
                                                try {
                                                    Pattern patternOfEmail=  Pattern.compile("[a-zA-Z0-9]*[@][a-zA-z]*[.][a-zA-Z]*[.][a-zA-Z]*");
                                                    Matcher emailMatcher = patternOfEmail.matcher(readingNewupdateInput);
                                                    if (!emailMatcher.matches()){


                                                        throw new IllegalArgumentException();
                                                    }
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setEmail(readingNewupdateInput);
                                                break;
                                            }
                                        }

                                        else if(readingInformationsectionInputsanitized.equals("address")){
                                            while (true){
                                                String readingNewupdateInput = newUpdateinput.nextLine();

                                                try {
                                                    Pattern patternOfaddress = Pattern.compile("[a-zA-Z0-9 ]+");
                                                    Matcher addressMatcher = patternOfaddress.matcher(readingNewupdateInput);
                                                    if(!addressMatcher.matches()){

                                                        throw new IllegalArgumentException();
                                                    }
                                                }
                                                catch (IllegalArgumentException illegalArgumentexception){
                                                    continue;
                                                }
                                                listOfleads[secondIndex].setAddress(readingNewupdateInput);
                                                break;
                                            }
                                        }

                                        else {
                                            continue;
                                        }



                                        break;
                                    }

                                    try {
                                        FileWriter fileWriter = new FileWriter("leadInformation.csv");
                                        for (Assignmentredo assignmentRedo: listOfleads){
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTime(assignmentRedo.getBirthdate());
                                            fileWriter.write(assignmentRedo.getCodeString());
                                            fileWriter.write(',');
                                            fileWriter.write(assignmentRedo.getName());
                                            fileWriter.write(',');
                                            fileWriter.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                            fileWriter.write(',');
                                            fileWriter.write(String.valueOf(assignmentRedo.getGender()));
                                            fileWriter.write(',');
                                            fileWriter.write(assignmentRedo.getPhone());
                                            fileWriter.write(',');
                                            fileWriter.write(assignmentRedo.getEmail());
                                            fileWriter.write(',');
                                            fileWriter.write(assignmentRedo.getAddress());
                                            fileWriter.write('\n');
                                        }
                                        fileWriter.close();
                                    }
                                    catch (IOException ioException){

                                    }


                                    break;
                                }



                            }
                        }
                    }


                    else if(readingInputsanitized.equals("exit")){
                        break;
                    }
                    else {
                        continue;
                    }







                }
            }
            else if(readingFunctioninput1Sanitized.equals("interaction")){
                if(isLeadinformationAdded){



                    while (true){



                        int lengthOfarray1 = 0;
                        boolean isThereaList1 = false;
                        try{
                            Scanner fileReading = new Scanner(new File("leadInformation.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                lengthOfarray1 = lengthOfarray1 + 1;
                            }
                            if(lengthOfarray1>0){
                                isThereaList1 = true;
                            }
                            fileReading.close();

                        }
                        catch (FileNotFoundException fileNotfoundException){

                        }
                        Assignmentredo[] listOfleads = new Assignmentredo[lengthOfarray1];
                        int index1 = 0;

                        try{
                            Scanner fileReading = new Scanner(new File("leadInformation.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                String[] theArray = eachLine.split(",");
                                SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = simpleDateformat.parse(theArray[2]);
                                if (theArray[3].equals("true")){
                                    Assignmentredo assignmentRedo = new Assignmentredo(theArray[0],theArray[1],date,true,theArray[4],theArray[5],theArray[6]);
                                    listOfleads[index1] = assignmentRedo;
                                }
                                else {
                                    Assignmentredo assignmentRedo = new Assignmentredo(theArray[0],theArray[1],date,false,theArray[4],theArray[5],theArray[6]);
                                    listOfleads[index1] = assignmentRedo;
                                }

                                index1 += 1;
                            }
                            fileReading.close();


                        }catch (Exception exception){

                        }







                        int lengthOfarray = 0;
                        boolean isThereaList = false;
                        try{
                            Scanner fileReading = new Scanner(new File("interactionInformation.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                lengthOfarray = lengthOfarray + 1;
                            }
                            if(lengthOfarray>0){
                                isThereaList = true;
                            }
                            fileReading.close();

                        }
                        catch (FileNotFoundException fileNotfoundException){

                        }
                        Assignmentcomponent[] listOfinteraction = new Assignmentcomponent[lengthOfarray];
                        int index = 0;

                        try{
                            Scanner fileReading = new Scanner(new File("interactionInformation.csv"));
                            while (fileReading.hasNext()){
                                String eachLine = fileReading.nextLine();
                                String[] theArray = eachLine.split(",");
                                SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = simpleDateformat.parse(theArray[1]);
                                for (Assignmentredo assignmentRedo: listOfleads){
                                    if (assignmentRedo.getCodeString().equals(theArray[2])){
                                        Assignmentcomponent assignmentComponent = new Assignmentcomponent(theArray[0],date,assignmentRedo,theArray[3],theArray[4]);
                                        listOfinteraction[index] = assignmentComponent;
                                    }
                                }

                                index += 1;
                            }
                            fileReading.close();
                        }catch (Exception exception){

                        }



                        Scanner functionInput = new Scanner(System.in);
                        System.out.print("Welcome to CRM");
                        System.out.print('\n');
                        System.out.print("What would you like to do?");
                        System.out.print('\n');
                        System.out.print("- View interaction in detail");
                        System.out.print('\n');
                        System.out.print("- Add interaction detail");
                        System.out.print('\n');
                        System.out.print("- Delete interaction detail");
                        System.out.print('\n');
                        System.out.print("- Update interaction detail");
                        System.out.print("\n");
                        System.out.print("Type view for viewing, add for adding, delete for deleting, update for updating and exit for stopping");

                        String readingInput = functionInput.nextLine();
                        String readingInputsanitized = readingInput.toLowerCase().trim();
                        if (readingInputsanitized.equals("view")){
                            if (isThereaList){
                                for (Assignmentcomponent assignmentComponent: listOfinteraction){
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(assignmentComponent.getDate());
                                    System.out.print(assignmentComponent.getStringCode());
                                    System.out.print(',');
                                    System.out.print(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                    System.out.print(',');
                                    System.out.print("");
                                    System.out.print(',');
                                    System.out.print(assignmentComponent.getMean());
                                    System.out.print(',');
                                    System.out.print(assignmentComponent.getStatus());
                                    System.out.print('\n');
                                }
                            }
                            else {
                                System.out.print("");
                                continue;
                            }
                        }
                        else if(readingInputsanitized.equals("add")){
                            while (true){
                                try{


                                    String codeString = "inter"+'_';
                                    int codeInteger = 0;
                                    try{
                                        Scanner fileReading = new Scanner(new File("interactionInformation.csv"));
                                        while (fileReading.hasNext()){
                                            String eachLine = fileReading.nextLine();
                                            codeInteger = Integer.parseInt(eachLine.split(",")[0].split("_")[1]);
                                        }

                                        fileReading.close();

                                    }
                                    catch (FileNotFoundException fileNotFoundexception){

                                    }
                                    codeInteger = codeInteger + 1;
                                    for (int i = 0; i < 3 - String.valueOf(codeInteger).length();++i){
                                        codeString = codeString + '0';
                                    }

                                    codeString = codeString + codeInteger;





                                    Scanner input = new Scanner(System.in);


                                    System.out.print("input the date");
                                    String interactionDate = input.nextLine();
                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = getFormat.parse(interactionDate);




                                    System.out.print("input codeString of the lead");
                                    String interactionLead = input.nextLine();
                                    boolean isStringcodeOfleadNotadded = true;
                                    try{
                                        Scanner fileReading = new Scanner(new File("leadInformation.csv"));

                                        while (fileReading.hasNext()){
                                            String eachLine = fileReading.nextLine();
                                            if (eachLine.split(",")[0].equals(interactionLead)){
                                                isStringcodeOfleadNotadded = false;
                                                break;
                                            }
                                            else {
                                                isStringcodeOfleadNotadded = true;
                                            }
                                        }
                                        fileReading.close();
                                        if (isStringcodeOfleadNotadded){
                                            throw new IllegalArgumentException();
                                        }


                                    }
                                    catch (FileNotFoundException fileNotfoundException){
                                        break;
                                    }

                                    System.out.print("input the mean");
                                    String interactionMean = input.nextLine();

                                    System.out.print("input the status");
                                    System.out.print("\n");
                                    System.out.print("Neutral"+','+"negative"+','+"or"+"positive");
                                    String interactionStatus = input.nextLine();
                                    if (interactionStatus.trim().toLowerCase().equals("neutral")||interactionStatus.trim().toLowerCase().equals("negative")||interactionStatus.trim().toLowerCase().equals("positive")){

                                    }
                                    else {
                                        throw new IllegalArgumentException();
                                    }

                                    for (Assignmentredo assignmentRedo: listOfleads){
                                        if(assignmentRedo.getCodeString().equals(interactionLead)){
                                            Assignmentcomponent assignmentComponent = new Assignmentcomponent(codeString, date, assignmentRedo, interactionMean, interactionStatus);
                                            assignmentComponent.fillList();
                                        }
                                    }



                                }
                                catch (Exception exception){
                                    continue;
                                }
                                break;
                            }
                        }
                        else if(readingInputsanitized.equals("delete")){

                            if (isThereaList){
                                while (true){
                                    Scanner codeStringinput = new Scanner(System.in);
                                    String readingCodestringInput = codeStringinput.nextLine();
                                    boolean isCodestringAdded = false;
                                    for (Assignmentcomponent assignmentComponent: listOfinteraction){
                                        if (assignmentComponent.getStringCode().equals(readingCodestringInput.toLowerCase().trim())){
                                            isCodestringAdded = true;
                                        }
                                    }
                                    if (isCodestringAdded){
                                        try {
                                            FileWriter fileWriter = new FileWriter("interactionInformation.csv");
                                            for (Assignmentcomponent assignmentComponent: listOfinteraction){
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.setTime(assignmentComponent.getDate());
                                                if(assignmentComponent.getStringCode().equals(readingCodestringInput)){

                                                }
                                                else {
                                                    fileWriter.write(assignmentComponent.getStringCode());
                                                    fileWriter.write(',');
                                                    fileWriter.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                                    fileWriter.write(',');
                                                    fileWriter.write(assignmentComponent.getAssignmentRedo().getCodeString());
                                                    fileWriter.write(',');
                                                    fileWriter.write(assignmentComponent.getMean());
                                                    fileWriter.write(',');
                                                    fileWriter.write(assignmentComponent.getStatus());
                                                    fileWriter.write('\n');
                                                }
                                            }
                                            fileWriter.close();
                                        }
                                        catch (Exception exception){

                                        }
                                        break;
                                    }

                                }
                            }
                        }
                        else if (readingInputsanitized.equals("update")){

                            if(isThereaList){
                                while(true){
                                    Scanner codeStringinput = new Scanner(System.in);
                                    String readingCodestringInput = codeStringinput.nextLine();
                                    boolean isCodestringAdded = false;
                                    int secondIndex = 0;
                                    int count = 0;
                                    for (Assignmentcomponent assignmentComponent: listOfinteraction){
                                        if (assignmentComponent.getStringCode().equals(readingCodestringInput)){
                                            isCodestringAdded = true;
                                            secondIndex = count;
                                        }
                                        count += 1;

                                    }

                                    if (isCodestringAdded){

                                        while (true){
                                            Scanner informationSectioninput = new Scanner(System.in);
                                            String readingInformationsectionInput = informationSectioninput.nextLine();
                                            Scanner newUpdateinput = new Scanner(System.in);
                                            String readingInformationsectionInputsanitized = readingInformationsectionInput.toLowerCase().trim();
                                            if (readingInformationsectionInputsanitized.equals("date")){
                                                while (true){
                                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                                    Date date;
                                                    try {
                                                        SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                        date = getFormat.parse(readingNewupdateInput);
                                                    }
                                                    catch (ParseException parseException){
                                                        continue;
                                                    }
                                                    listOfinteraction[secondIndex].setDate(date);
                                                    break;
                                                }

                                            }
                                            else if(readingInformationsectionInputsanitized.equals("lead")){
                                                while (true){
                                                    String readingNewupdateInput = newUpdateinput.nextLine();

                                                    try {

                                                        boolean isStringcodeOfleadNotadded = true;
                                                        try{
                                                            Scanner fileReading = new Scanner(new File("leadInformation.csv"));

                                                            while (fileReading.hasNext()){
                                                                String eachLine = fileReading.nextLine();
                                                                if (eachLine.split(",")[0].equals(readingNewupdateInput)){
                                                                    isStringcodeOfleadNotadded = false;
                                                                    break;
                                                                }
                                                                else {
                                                                    isStringcodeOfleadNotadded = true;
                                                                }
                                                            }
                                                            fileReading.close();
                                                            if (isStringcodeOfleadNotadded){
                                                                throw new IllegalArgumentException();
                                                            }


                                                        }
                                                        catch (FileNotFoundException fileNotfoundException){
                                                            break;
                                                        }

                                                    }
                                                    catch (IllegalArgumentException illegalArgumentexception){
                                                        continue;
                                                    }
                                                    for (Assignmentredo assignmentRedo: listOfleads){
                                                        if (assignmentRedo.getCodeString().equals(readingNewupdateInput)){
                                                            listOfinteraction[secondIndex].setAssignmentRedo(assignmentRedo);
                                                        }
                                                    }

                                                    break;
                                                }
                                            }
                                            else if(readingInformationsectionInputsanitized.equals("mean")){
                                                while (true){
                                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                                    String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                    try {
                                                        if(!(readingNewupdateInputsanitized.equals("ftf")||readingNewupdateInputsanitized.equals("facetoface")||readingNewupdateInputsanitized.equals("face-to-face")||readingNewupdateInputsanitized.equals("message")||readingNewupdateInputsanitized.equals("socialmedia")||readingNewupdateInputsanitized.equals("facebook"))){
                                                            throw new IllegalArgumentException();
                                                        }
                                                    }
                                                    catch (IllegalArgumentException illegalArgumentexception){
                                                        continue;
                                                    }
                                                    listOfinteraction[secondIndex].setMean(readingNewupdateInput);
                                                    break;
                                                }
                                            }
                                            else if(readingInformationsectionInputsanitized.equals("status")){
                                                while (true){
                                                    String readingNewupdateInput = newUpdateinput.nextLine();
                                                    String readingNewupdateInputsanitized = readingNewupdateInput.toLowerCase().trim();
                                                    try {
                                                        if(!(readingNewupdateInputsanitized.equals("neutral")||readingNewupdateInputsanitized.equals("negative")||readingNewupdateInputsanitized.equals("positive"))){
                                                            throw new IllegalArgumentException();
                                                        }
                                                    }
                                                    catch (IllegalArgumentException illegalArgumentexception){
                                                        continue;
                                                    }
                                                    listOfinteraction[secondIndex].setStatus(readingNewupdateInput);
                                                    break;
                                                }
                                            }
                                            else {
                                                continue;
                                            }



                                            break;
                                        }

                                        try {
                                            FileWriter fileWriter = new FileWriter("interactionInformation.csv");
                                            for (Assignmentcomponent assignmentComponent: listOfinteraction){
                                                Calendar calendar = Calendar.getInstance();
                                                calendar.setTime(assignmentComponent.getDate());
                                                fileWriter.write(assignmentComponent.getStringCode());
                                                fileWriter.write(',');
                                                fileWriter.write(calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE));
                                                fileWriter.write(',');
                                                fileWriter.write(assignmentComponent.getAssignmentRedo().getCodeString());
                                                fileWriter.write(',');
                                                fileWriter.write(assignmentComponent.getMean());
                                                fileWriter.write(',');
                                                fileWriter.write(assignmentComponent.getStatus());
                                                fileWriter.write('\n');
                                            }
                                            fileWriter.close();
                                        }
                                        catch (IOException ioException){

                                        }


                                        break;
                                    }



                                }
                            }
                        }


                        else if(readingInputsanitized.equals("exit")){
                            break;
                        }
                        else {
                            continue;
                        }







                    }



                }
            }


            else if(readingFunctioninput1Sanitized.equals("exit")){
                break;
            }
            else {
                continue;
            }

        }
    }
}
