package rw.Data;

import rw.Member.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {

    private static final ArrayList<Member> members = new ArrayList<>();

    public static boolean storeNewMember(String name, int memberID, String birthday,
           double calories, double bodyFat,double sleepCycle,double weight,
            String diet, String activity){
        if (!memberExist(memberID)) {
            Member member = new Member(name,
                    memberID,
                    birthday,
                    calories,
                    bodyFat,
                    sleepCycle,
                    weight,
                    diet,
                    activity);
            members.add(member);
            System.out.println("Stored!");
            return true;
        }
        else {
            return false;
        }
    }



    public static boolean memberExist(int memberId) {
        boolean memberExist = false;
        for (Member member : members) {
            if ((int) member.getMemberID() == memberId) {
                memberExist = true;
                break;
            }
        }
    return memberExist;

    }
    public static ArrayList<Member> getMembers() {
        return members;
    }
    public static void saveData(String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (Member member : members) {
                System.out.println(member.toString());
                fileWriter.write(member.toString());
                fileWriter.write("\n");
            }
            System.out.println("Data saved to " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving data: File not found - " + filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadData(String filename) {
        members.clear();
        try (Scanner fileReader = new Scanner(new File(filename))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] fields = line.split(",");
                Member member = new Member(fields[0], Integer.parseInt(fields[1]), fields[2],
                        Double.parseDouble(fields[3]), Double.parseDouble(fields[4]),
                        Double.parseDouble(fields[5]), Double.parseDouble(fields[6]),
                        fields[7], fields[8]);
                members.add(member);
            }
            System.out.println("Data loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading data: File not found - " + filename);
        }
    }
}
