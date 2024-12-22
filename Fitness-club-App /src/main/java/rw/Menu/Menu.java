package rw.Menu;

import rw.Data.Data;
import rw.Member.Member;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;


public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<String> options = new ArrayList<>();

    static {
        options.add("1: Add a new member to the fitness club");
        options.add("2: Print all members");
        options.add("3: Print average calories among all members");
        options.add("4: Print members with only 10% body fat");
        options.add("5: Print all senior members");
        options.add("6: Print members with a calorie intake of < 1500");
        options.add("7: Print members with a weight of < 80 kg");
        options.add("8: Print All info of a member");
        options.add("9: Save my data");
        options.add("10: load my data");
        options.add("0: Exit the program");
    }

    private static String optMessage() {
        for (int i = 0; i < options.size(); i++) {
            System.out.println(options.get(i));
        }
        System.out.println();
        return "<--------------------Pls select one of the following!!!!!-------------------->";
    }

    public static void menuLoop() {
        System.out.println(optMessage());
        boolean validOption = true;
        do {
            try {
                String option = scanner.nextLine();
                if (option.isEmpty()) {
                    throw new IllegalArgumentException("Empty option");
                }
                int choice = Integer.parseInt(option);
                switch (choice) {
                    case 0 -> validOption = false;
                    case 1 -> addNewMember();
                    case 2 -> printAllMembers();
                    case 3 -> printAvgCalories();
                    case 4 -> printLowBodyFat();
                    case 5 -> printSeniorMembers();
                    case 6 -> printMemberLess1500cal();
                    case 7 -> printMemberLess80kg();
                    //case 8 -> printMemberAllInfo();
                    case 9 -> saveData();
                    case 10 -> loadData();
                    default -> System.out.println("Option is not recognized");
                }

                if (validOption) {
                    System.out.println("Waiting!!!!!............. ENTER A KEY TO CONTINUE AGAIN");
                    scanner.nextLine();
                    System.out.println(optMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (validOption);

        System.out.println("Thanks for using the fitness club program");
    }


    public static void saveData(){
        System.out.println("Enter filename to save: ");
        String filename = scanner.nextLine();
        Data.saveData(filename);
    }

    public static void loadData(){
        System.out.println("Enter filename to load:");
        String filename = scanner.nextLine();
        Data.loadData(filename);
    }



    public static void addNewMember() {
        boolean success = false;
        do {
            String name = getName();
            int memberID = getID();
            String birthdate = getBirthDate();
            double calories = getCalories();
            double bodyFat = getBodyFat();
            double sleepCycle = getSleepCycle();
            double weight = getWeight();
            String diet = getDiet();
            String activity = getActivity();
            success = Data.storeNewMember(name, memberID, birthdate,
                    calories, bodyFat, sleepCycle, weight,
                    diet, activity);
            if (!success) {
                System.out.println("member already exists!!!!!!!");
            }
        } while (!success);

    }

    private static String getActivity() {
        String activity = null;
        try {
            System.out.println("Pls enter the activity of customer");
            activity = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return activity;
    }

    private static int getID() {
        String idString = null;
        boolean validInput = false;
        int id = 0;
        do {
            try {
                System.out.println("Pls enter the Id of member");
                idString = scanner.nextLine().trim();
                id = Integer.parseInt(idString);
                if (id < 0) {
                    throw new IllegalArgumentException("ID cannot be negative");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid integer ID");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return id;
    }

    private static String getName() {
        String name = null;
        boolean validInput = false;
        do {
            try {
                System.out.println("Pls enter the name of member");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty");
                }
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return name;
    }

    // fix error handling for this after
    private static String getBirthDate() {
        String DOB = "";
        boolean validInput = false;
        do {
            try {
                System.out.println("Pls enter the DOB of member (MMMM d, yyyy)");
                DOB = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                LocalDate.parse(DOB, formatter);
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return DOB;
    }

    private static double getCalories() {
        String calories = null;
        boolean validInput = false;
        double cal = 0;
        do {
            try {
                System.out.println("Pls enter the calories of member");
                calories = scanner.nextLine().trim();
                cal = Integer.parseInt(calories);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid integer calories");
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return cal;
    }


    private static double getBodyFat() {
        String bodyfat = null;
        boolean validInput = false;
        double bodyFat = 0;
        do {
            try {
                System.out.println("Pls enter the body fat of member");
                bodyfat = scanner.nextLine().trim();
                bodyFat = Integer.parseInt(bodyfat);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid body fat");
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return bodyFat;
    }

    private static double getSleepCycle() {
        String sleepcycle = null;
        boolean validInput = false;
        double sleepCycle = 0;
        do {
            try {
                System.out.println("Pls enter the sleep cycle of member");
                sleepcycle = scanner.nextLine().trim();
                sleepCycle = Double.parseDouble(sleepcycle);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid sleep cycle");
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return sleepCycle;
    }

    private static double getWeight() {
        String weight = null;
        boolean validInput = false;
        double weightValue = 0;
        do {
            try {
                System.out.println("Pls enter the weight of member in Kgs");
                weight = scanner.nextLine().trim();
                weightValue = Double.parseDouble(weight);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid weight");
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return weightValue;
    }

    private static String getDiet() {
        String diet = null;
        boolean validInput = false;
        do {
            try {
                System.out.println("Pls enter the diet of member");
                diet = scanner.nextLine();
                if (diet.isEmpty()) {
                    throw new IllegalArgumentException("Diet cannot be empty");
                }
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (!validInput);
        return diet;
    }


    public static double printAvgCalories() {
        double calories = 0;
        int count = 0;
        double averageCal = 0;
        double temp;
        for (Member member : Data.getMembers()) {
            temp = member.getCalories();
            calories += temp;
            count++;
        }
        if (count > 0) {
            averageCal = calories / count;
        }
        return averageCal;
    }

    public static String printLowBodyFat() {
        StringBuilder output = new StringBuilder();
        if (Data.getMembers().isEmpty()){
            output.append("No member found\n");
        } else {
            Comparator<Member> bodyFatComparator = Comparator.comparingDouble(Member::getBodyFat);
            List<Member> sortedMembers = new ArrayList<>(Data.getMembers());
            Collections.sort(sortedMembers, bodyFatComparator);
            boolean memberfound = false;
            for (Member member : sortedMembers) {
                if (member.getBodyFat() < 15) {
                    output.append("Body fat less than 15%").append(" ").append(member.getName()).append(" & ").append("Id: ").append(member.getMemberID()).append("\n");
                    memberfound = true;
                }
            }
            if (!memberfound) {
                output.append("No member with body fat less than 15%");
            }
        }
        return output.toString();
    }

    public static String printAllMembers() {
        StringBuilder output = new StringBuilder();
        boolean membersFound = false;
        for (Member member : Data.getMembers()) {
            output.append("Member------>").append(" ").append(member.getName()).append(" & ").append("Id: ").append(member.getMemberID()).append("\n");
            membersFound = true;
        }
        if (!membersFound) {
            output.append("No members found\n");
        }
        return output.toString();
    }



    public static String printSeniorMembers() {
        StringBuilder output = new StringBuilder();
        if (Data.getMembers().isEmpty()) {
            output.append("No member found\n");
        } else {
            Comparator<Member> ageComparator = (m1, m2) -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dob1 = LocalDate.parse(m1.getBirthday(), formatter);
                LocalDate dob2 = LocalDate.parse(m2.getBirthday(), formatter);
                return dob2.compareTo(dob1);
            };

            List<Member> sortedMembers = new ArrayList<>(Data.getMembers());

            Collections.sort(sortedMembers, ageComparator);

            boolean seniorMemberFound = false;
            for (Member member : sortedMembers) {
                LocalDate dob = LocalDate.parse(member.getBirthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate currentDate = LocalDate.now();
                Period age = Period.between(dob, currentDate);

                if (age.getYears() > 65) {
                    output.append("Senior Member: ")
                            .append(member.getName())
                            .append(" (ID: ")
                            .append(member.getMemberID())
                            .append(", Age: ")
                            .append(age.getYears())
                            .append(")\n");
                    seniorMemberFound = true;
                }
            }
            if (!seniorMemberFound) {
                output.append("No senior member found");
            }
        }
        return output.toString();
    }

    public static String printMemberLess1500cal() {
        StringBuilder output = new StringBuilder();
        if (Data.getMembers().isEmpty()) {
            output.append("No member found\n");
        } else {
            Comparator<Member> weightComparator = Comparator.comparingDouble(Member::getWeight);

            List<Member> sortedMembers = new ArrayList<>(Data.getMembers());

            Collections.sort(sortedMembers, weightComparator);

            boolean memberFound = false;
            for (Member member : sortedMembers) {
                if (member.getWeight() < 80) {
                    output.append("Calories less than 1500: ")
                            .append(member.getName())
                            .append(" (ID: ")
                            .append(member.getMemberID())
                            .append(", Calories: ")
                            .append(member.getCalories())
                            .append(" cal)\n");
                    memberFound = true;
                }
            }
            if (!memberFound) {
                output.append("No member with calories less than 1500");
            }
        }
        return output.toString();
    }

    public static String printMemberLess80kg() {
        boolean memberFound = false;
        int counter = 1;
        double temp = 0;
        StringBuilder output = new StringBuilder();
        for (Member member : Data.getMembers()) {
            try {
                temp = (double) member.getWeight();
                if (temp < 80) {
                    output.append("Weight less than 80 kg------>").append(" ").append(member.getName()).append(" & ").append("Id: ").append(member.getMemberID()).append("\n");

                    memberFound = true;
                }
            } catch (Exception e) {
                output.append("Error getting the weight");
            }
        }
         if (!memberFound) {
             output.append("No member with weight less than 80 Kg");

         }
    return output.toString();
    }
    public static  String printMemberAllInfo(int member1){
        String output ="Member does not exist!!!";
        for (Member member : Data.getMembers()) {
            int temp = member.getMemberID();
            if(temp == member1){
                output= member.getMemberID()+"\n"+
                "Name: "+member.getName()+"\n"+
                "Birthdate: "+member.getBirthday()+"\n"+
                "Calories: "+member.getCalories()+"\n"+
                "BodyFat: "+member.getBodyFat()+"\n"+
                "SleepCycle: "+ member.getSleepCycle()+"\n"+
                "Weight: "+ member.getWeight()+"\n"+
                "Activity: " + member.getActivity()+"\n"+
                "Diet: " + member.getDiet();
                break;
            }
        }
        return output;
    }

}