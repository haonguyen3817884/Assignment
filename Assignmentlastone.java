package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Assignmentlastone {

    public static void main(String[] args){
        while(true){
            boolean isInteractioninformationAdded = false;
            int countLines = 0;
            try {
                Scanner fileReading = new Scanner(new File("interaction.csv"));
                while (fileReading.hasNext()){
                    String eachLine = fileReading.nextLine();
                    countLines += 1;
                }
                fileReading.close();
                if(countLines>0){
                    isInteractioninformationAdded = true;
                }

            }
            catch (FileNotFoundException fileNotfoundException){

            }

            Scanner functionInput = new Scanner(System.in);
            System.out.print("Welcome to CRM");
            System.out.print('\n');
            System.out.print("- View the report of the lead number by age");
            System.out.print('\n');
            System.out.print("- View the report of the interaction number of different status by date");
            System.out.print('\n');
            System.out.print("- View the report of the interaction number by date ");
            System.out.print('\n');
            System.out.print("Type view lead for viewing the lead number, view status for viewing the interaction number of different status, view interaction for viewing the interaction number or exit for stopping");
            String readingFunctioninput = functionInput.nextLine();
            String readingFunctioninputSanitized = readingFunctioninput.trim().toLowerCase();

            if(readingFunctioninputSanitized.equals("view lead")){
                long count0_10 = 0;
                long count10_20 = 0;
                long count20_60 = 0;
                long count60 = 0;
                Date currentTime = new Date();
                long currentSeconds = currentTime.getTime()/1000;
                try{
                    Scanner fileReading = new Scanner(new File("lead.csv"));
                    while(fileReading.hasNext()){
                        String eachLine = fileReading.nextLine();
                        String dob = eachLine.split(",")[2];
                        SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = getFormat.parse(dob);
                        long age = (currentSeconds-(date.getTime()/1000))/(24*60*60*365);
                        if(age>=0 && age<10){
                            count0_10 +=1;
                        }else if(age>=10 && age <20){
                            count10_20+=1;
                        }else if(age>=20 && age <60){
                            count20_60+=1;
                        }else{
                            count60+=1;
                        }
                    }
                    System.out.printf("%20s","0-10  (years old)");
                    System.out.printf("%20s","10-20  (years old)");
                    System.out.printf("%20s","20-60  (years old)");
                    System.out.printf("%20s%n",">60  (years old)");
                    System.out.printf("%20s",count0_10);
                    System.out.printf("%20s",count10_20);
                    System.out.printf("%20s",count20_60);
                    System.out.printf("%20s%n",count60);
                }catch (Exception exception) {

                }

            }

            else if(readingFunctioninputSanitized.equals("view status")){
                if(isInteractioninformationAdded){
                    while (true){
                        long countPositive = 0;
                        long countNeutral = 0;
                        long countNegative = 0;

                        try{
                            Scanner fileReading = new Scanner(new File("interaction.csv"));
                            Scanner startDateinput = new Scanner(System.in);
                            System.out.printf("Input the start date: %n");
                            String readingStartdateInput = startDateinput.nextLine();
                            Date startDate = new Date();
                            if(Integer.parseInt(readingStartdateInput.split("-")[2]) <= 31 && Integer.parseInt(readingStartdateInput.split("-")[1]) <= 12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                startDate = getFormat.parse(readingStartdateInput);
                            }
                            else if(Integer.parseInt(readingStartdateInput.split("-")[0])<=12 && Integer.parseInt(readingStartdateInput.split("-")[1])<=31){
                                SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                startDate = getFormat.parse(readingStartdateInput);
                            }
                            else if(Integer.parseInt(readingStartdateInput.split("-")[0])<=31 && Integer.parseInt(readingStartdateInput.split("-")[1])<=12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("dd-MM-yyyy");
                                startDate = getFormat.parse(readingStartdateInput);
                            }
                            else {
                                throw new Exception();
                            }

                            Scanner endDateinput = new Scanner(System.in);
                            System.out.printf("Input the end date: %n");
                            String readingEnddateInput = endDateinput.nextLine();
                            Date endDate = new Date();
                            if(Integer.parseInt(readingEnddateInput.split("-")[2]) <= 31 && Integer.parseInt(readingEnddateInput.split("-")[1]) <= 12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                endDate = getFormat.parse(readingEnddateInput);
                            }
                            else if(Integer.parseInt(readingEnddateInput.split("-")[0])<=12 && Integer.parseInt(readingEnddateInput.split("-")[1])<=31){
                                SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                endDate = getFormat.parse(readingEnddateInput);
                            }
                            else if(Integer.parseInt(readingEnddateInput.split("-")[0])<=31 && Integer.parseInt(readingEnddateInput.split("-")[1])<=12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("dd-MM-yyyy");
                                endDate = getFormat.parse(readingEnddateInput);
                            }
                            else {
                                throw new Exception();
                            }

                            if(startDate.compareTo(endDate)<=0){
                                while (fileReading.hasNext()){
                                    String eachLine = fileReading.nextLine();
                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date DateOfInteraction = getFormat.parse(eachLine.split(",")[1]);
                                    String potential = eachLine.split(",")[4];
                                    if(DateOfInteraction.compareTo(startDate) >=0 && DateOfInteraction.compareTo(endDate)<=0){
                                        if(potential.equals("neutral")){
                                            countNeutral +=1;
                                        }else if(potential.equals("negative")){
                                            countNegative+=1;
                                        }else{
                                            countPositive +=1;
                                        }
                                    }
                                }
                                fileReading.close();
                            }
                            else{
                                fileReading.close();
                                throw new Exception();
                            }
                            System.out.printf("%20s","Positive");
                            System.out.printf("%20s","Neutral");
                            System.out.printf("%20s%n","Negative");
                            System.out.printf("%20s",countPositive);
                            System.out.printf("%20s",countNeutral);
                            System.out.printf("%20s%n",countNegative);


                        }
                        catch (Exception exception){
                            continue;
                        }
                        break;




                    }
                }
            }

            else if(readingFunctioninputSanitized.equals("view interaction")){
                if(isInteractioninformationAdded){

                    while (true){

                        try{
                            Scanner fileReading = new Scanner(new File("interaction.csv"));
                            Scanner startDateinput = new Scanner(System.in);
                            System.out.println("INPUT START DATE: ");
                            String readingStartdateInput = startDateinput.nextLine();
                            Date startDate = new Date();
                            if(Integer.parseInt(readingStartdateInput.split("-")[2]) <= 31 && Integer.parseInt(readingStartdateInput.split("-")[1]) <= 12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                startDate = getFormat.parse(readingStartdateInput);
                            }
                            else if(Integer.parseInt(readingStartdateInput.split("-")[0])<=12 && Integer.parseInt(readingStartdateInput.split("-")[1])<=31){
                                SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                startDate = getFormat.parse(readingStartdateInput);
                            }
                            else if(Integer.parseInt(readingStartdateInput.split("-")[0])<=31 && Integer.parseInt(readingStartdateInput.split("-")[1])<=12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("dd-MM-yyyy");
                                startDate = getFormat.parse(readingStartdateInput);
                            }
                            else {
                                throw new Exception();
                            }

                            Scanner endDateinput = new Scanner(System.in);
                            System.out.println("INPUT END DATE: ");
                            String readingEnddateInput = endDateinput.nextLine();
                            Date endDate = new Date();
                            Date now = new Date();

                            if(Integer.parseInt(readingEnddateInput.split("-")[2]) <= 31 && Integer.parseInt(readingEnddateInput.split("-")[1]) <= 12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                endDate = getFormat.parse(readingEnddateInput);
                            }
                            else if(Integer.parseInt(readingEnddateInput.split("-")[0])<=12 && Integer.parseInt(readingEnddateInput.split("-")[1])<=31){
                                SimpleDateFormat getFormat = new SimpleDateFormat("MM-dd-yyyy");
                                endDate = getFormat.parse(readingEnddateInput);
                            }
                            else if(Integer.parseInt(readingEnddateInput.split("-")[0])<=31 && Integer.parseInt(readingEnddateInput.split("-")[1])<=12){
                                SimpleDateFormat getFormat = new SimpleDateFormat("dd-MM-yyyy");
                                endDate = getFormat.parse(readingEnddateInput);
                            }
                            else {
                                throw new Exception();
                            }


                            if(startDate.compareTo(endDate)<0 && endDate.compareTo(now) <= 0){

                                String storedString = "";
                                long countForcheck = 0;
                                while(fileReading.hasNext()){
                                    String eachLine = fileReading.nextLine();
                                    String dateOfInteractionString = eachLine.split(",")[1];
                                    SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date dateOfInteraction = getFormat.parse(dateOfInteractionString);
                                    if(dateOfInteraction.compareTo(startDate)>=0 && dateOfInteraction.compareTo(endDate)<=0){
                                        storedString = storedString + dateOfInteractionString +",";
                                        countForcheck += 1;
                                    }
                                }
                                if(!(countForcheck > 0)){
                                    break;
                                }
                                Date[] dates = new Date[storedString.split(",").length];
                                for(int i = 0; i <storedString.split(",").length; ++i ){
                                    try{
                                        SimpleDateFormat getFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = getFormat.parse(storedString.split(",")[i]);
                                        dates[i] = date;
                                    }catch (ParseException parseException){

                                    }
                                }
                                Arrays.sort(dates);
                                for (int i = 0; i< dates.length; ++i){
                                    System.out.print(dates[i]);
                                    System.out.print('\n');
                                }

                                String[] NewStoredString = new String[dates.length];
                                for (int i = 0; i<dates.length; ++i){
                                    String NewMonthYear = dates[i].toString().split(" ")[1] + " " + dates[i].toString().split(" ")[5];
                                    System.out.println(NewMonthYear);
                                    NewStoredString[i] = NewMonthYear;
                                }
                                int countLength = 1;
                                for(int i =0; i< NewStoredString.length-1; ++i){
                                    if(!NewStoredString[i].equals(NewStoredString[i+1])){
                                        countLength += 1;
                                    }
                                }
                                System.out.println("Length :"+ countLength);
                                String[] titles = new String[countLength];
                                int[] countInteractions = new int[countLength];
                                int index = 0;
                                titles[0] = NewStoredString[0];
                                for(int i = 0; i < NewStoredString.length-1; ++i){
                                    if(!NewStoredString[i].equals(NewStoredString[i+1])){
                                        index+=1;
                                        titles[index] = NewStoredString[i+1];
                                    }
                                }
                                for(int i = 0; i < countLength; ++i){
                                    int count = 0;
                                    for(int j = 0; j < NewStoredString.length; ++j){
                                        if(titles[i].equals(NewStoredString[j])){
                                            count +=1;
                                        }
                                    }
                                    countInteractions[i] = count;
                                }
//
//            System.out.println(titles[0] + counts[0]);
//            System.out.println(titles[1]+ counts[1]);
//            System.out.println(titles[2]+counts[2]);
//            System.out.println(titles[3]+counts[3]);
                                for(int i = 0; i < titles.length; ++i ){
                                    System.out.printf("%20s", titles[i]);
                                }
                                System.out.printf("%n");
                                for(int i = 0; i < countInteractions.length; ++i){
                                    System.out.printf("%20s",countInteractions[i]);
                                }
                                System.out.printf("%n");
                                fileReading.close();
                            }
                            else{
                                fileReading.close();
                                throw new Exception();

                            }



                        }
                        catch (Exception exception){
                            continue;
                        }
                        break;




                    }

                }


            }


            else if(readingFunctioninputSanitized.equals("exit")){
                break;
            }
            else {
                continue;
            }




        }
    }
}
