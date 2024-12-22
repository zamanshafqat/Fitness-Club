package rw.TrackerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rw.Data.Data;
import rw.Menu.Menu;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;



public class TrackerTest {

    @BeforeEach
    public void clearMemberData() {
        Data.getMembers().clear();
    }

    @Test
    public void testPrintMemberLess1500cal() {
        Data.storeNewMember("Arham", 1, "June-4-2005", 1400.0, 20.0, 4.0, 85.0, "normal", "active");
        String expectedOutput = "Calories less than 1500: Arham (ID: 1, Calories: 1400.0 cal)\n";
        String actualOutput = Menu.printMemberLess1500cal();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    // to test if the weight less than 80 are printed
    public void testPrintMemberLess80kg() {
        Data.storeNewMember("zaman", 1, "feb-4,-2004", 1400.0, 20.0, 4.0, 75, "normal", "active");
        String expectedOutput = "Weight less than 80 kg------> zaman & Id: 1\n";
        String actualOutput = Menu.printMemberLess80kg();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    // to test if the body fat less than 15% are printed
    public void testPrintMemberLessBodyFat() {
        Data.storeNewMember("zaman", 1, "feb 4, 2004", 1400.0, 10.0, 4.0, 75, "normal", "active");
        String expectedOutput = "Body fat less than 15% zaman & Id: 1\n";
        String actualOutput = Menu.printLowBodyFat();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    // to test if the member above 65 are printed
    public void testPrintMemberAbove65() {
        Data.storeNewMember("zaman", 1, "1920-01-01", 1400.0, 10.0, 4.0, 75, "normal", "active");
        String expectedOutput = "Senior Member: zaman (ID: 1, Age: 104)\n";
        String actualOutput = Menu.printSeniorMembers();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    // to test if all members are printed
    public void testPrintAllMembers() {
        Data.storeNewMember("zaman", 1, "1920-01-01", 1400.0, 10.0, 4.0, 75, "normal", "active");
        Data.storeNewMember("nathan", 2, "1920-01-01", 1400.0, 10.0, 4.0, 75, "normal", "active");
        Data.storeNewMember("Arham", 3, "1920-01-01", 1400.0, 10.0, 4.0, 75, "normal", "active");
        String Output1 = Menu.printAllMembers();
        //Member------> zaman & Id: 1
        assertTrue(Output1.contains("Member------> zaman & Id: 1"));
        assertTrue(Output1.contains("Member------> nathan & Id: 2"));
        assertTrue(Output1.contains("Member------> Arham & Id: 3"));
    }

    @Test
    // TO TEST if member with less 1500 calories are printed
    public void printMemberCaloriesLess1500() {
        Data.storeNewMember("zaman", 1, "feb 4, 1920", 1400.0, 10.0, 4.0, 75, "normal", "active");
        String expectedOutput = "Calories less than 1500: zaman (ID: 1, Calories: 1400.0 cal)\n";
        String actualOutput = Menu.printMemberLess1500cal();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    // TO TEST if average calories  are printed
    public void printAvgCalories() {
        Data.storeNewMember("zaman", 1, "feb 4, 1920", 1400.0, 10.0, 4.0, 75, "normal", "active");
        Data.storeNewMember("nathan", 2, "feb 4, 1920", 2000.0, 10.0, 4.0, 75, "normal", "active");
        double actual = Menu.printAvgCalories();
        assertEquals(1700.0, actual);
    }

    @Test
    // To test what would happen if printed low body fat with no members
    public void testPrintLowBodyFatWithNoMemebrs() {
        String expectedOutput = "No member found\n";
        String actualOutput = Menu.printLowBodyFat();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAddNewMemberWithNegativeId() {
        try {
            Data.storeNewMember("Name", -5, "....", 1400.0, 10.0, 4.0, 75, "cheese", "hopscotch");
        } catch (IllegalArgumentException e) {
        }
    }
    @Test
    public void testAddNewMemberWithDuplicateId() {
        Data.storeNewMember("Arham", 1, "June 4, 2005", 1400.0, 20.0, 4.0, 85.0, "Normal", "Active");
        // Attempt to add another member with the same ID
        boolean success =  Data.storeNewMember("Someone Else", 1, "...", 1200, 18, 5, 78, "Vegan", "Light");
        assertFalse(success);
    }
    @Test
    public void testAddNewMemberWithZeroCalories() {
        try {
            Data.storeNewMember("Name", 1, "....", 0, 10.0, 4.0, 75, "cheese", "hopscotch");
        } catch (IllegalArgumentException e) {
        }
    }


    @Test
    public void testSortingByMemberId() {
        // Add members directly to the Data class
        Data.storeNewMember("Nathan", 3, "June 6, 2001", 1800, 15, 7, 65, "Keto", "Moderate");
        Data.storeNewMember("Zaman", 1, "Feb 4, 2004", 1400, 20, 4, 75, "Normal", "Active");
        Data.storeNewMember("Arham", 2, "June 4, 2005", 1550, 12, 6, 70, "Vegan", "Light");

        // Sort
        Collections.sort(Data.getMembers()); // Sort using the Member class's compareTo

        assertEquals(1, Data.getMembers().get(0).getMemberID()); // Check if the first member has the lowest ID
        assertEquals(2, Data.getMembers().get(1).getMemberID());
        assertEquals(3, Data.getMembers().get(2).getMemberID());
    }
    @Test
    public void testPrintLowBodyFatSorted() {
        Data.storeNewMember("Nathan", 3, "June 6, 2001", 1800, 15, 7, 65, "Keto", "Moderate");
        Data.storeNewMember("Zaman", 1, "Feb 4, 2004", 1400, 20, 4, 75, "Normal", "Active");
        Data.storeNewMember("Arham", 2, "June 4, 2005", 1550, 12, 6, 70, "Vegan", "Light");

        String expectedOutput ="Body fat less than "+15+"% Arham & Id: "+2+"\n";
        String actualOutput = Menu.printLowBodyFat();

        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void testPrintMemberLess1500calSorted() {
        Data.storeNewMember("Nathan", 1, "January 1, 1990", 2000, 12.5, 7, 75, "Normal", "Active");
        Data.storeNewMember("Arham", 2, "May 10, 1985", 1200, 18.2, 6, 68, "Vegetarian", "Moderate");
        Data.storeNewMember("Zaman", 3, "September 15, 1995", 2200, 10.8, 8, 82, "Normal", "Active");


        String expectedOutput = "Calories less than 1500: Arham (ID: 2, Calories: 1200.0 cal)\n";
        String actualOutput = Menu.printMemberLess1500cal();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintSeniorMembersSorted() {
        Data.storeNewMember("Nathan", 1, "1945-01-01", 2000, 12.5, 7, 75, "Normal", "Active");
        Data.storeNewMember("Arham", 2, "1950-01-01", 1800, 18.2, 6, 68, "Vegetarian", "Moderate");
        Data.storeNewMember("Zaman", 3, "1995-01-01", 1800, 10.8, 8, 82, "Normal", "Active");

        String expectedOutput = "Senior Member: Arham (ID: 2, Age: 74)\n" +
                "Senior Member: Nathan (ID: 1, Age: 79)\n";
        String actualOutput = Menu.printSeniorMembers();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintMemberLess80kgSorted() {
        Data.storeNewMember("Nathan", 1, "January 1, 1990", 2000, 12.5, 7, 85, "Normal", "Active");
        Data.storeNewMember("Arham", 2, "May 10, 1985", 1800, 18.2, 6, 68, "Vegetarian", "Moderate");
        Data.storeNewMember("Zaman", 3, "September 15, 1995", 1800, 10.8, 8, 75, "Normal", "Active");

        String expectedOutput = "Weight less than 80 kg------> Arham & Id: 2"+"\n"+
                "Weight less than 80 kg------> Zaman & Id: 3\n";

        String actualOutput = Menu.printMemberLess80kg();

        assertEquals(expectedOutput, actualOutput);
    }
}
