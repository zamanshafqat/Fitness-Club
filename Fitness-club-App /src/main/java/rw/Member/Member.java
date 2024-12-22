package rw.Member;

public class Member implements Comparable<Member> {
    private String name;
    private int memberID;
    private String birthday;
    private double calories;
    private double bodyFat;
    private double sleepCycle;
    private double weight;
    private String diet;
    private String activity;

    public Member(String name, int memberID, String birthday, double calories, double bodyFat,
                  double sleepCycle, double weight, String diet, String activity) {
        this.name = name;
        this.memberID = memberID;
        this.birthday = birthday;
        this.calories = calories;
        this.bodyFat = bodyFat;
        this.sleepCycle = sleepCycle;
        this.weight = weight;
        this.diet = diet;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getCalories() {
        return calories;
    }

    public double getBodyFat() {
        return bodyFat;
    }

    public double getSleepCycle() {
        return sleepCycle;
    }

    public double getWeight() {
        return weight;
    }

    public String getDiet() {
        return diet;
    }

    public String getActivity() {
        return activity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setBodyFat(double bodyFat) {
        this.bodyFat = bodyFat;
    }

    public void setSleepCycle(double sleepCycle) {
        this.sleepCycle = sleepCycle;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
    @Override
    public String toString() {
        return name + "," + memberID + "," + birthday + "," + calories + "," + bodyFat + "," + sleepCycle + "," + weight + "," + diet + "," + activity + ".";
    }
    @Override
    public int compareTo(Member otherMember) {
        if (this.memberID < otherMember.memberID) {
            return -1;
        } else if (this.memberID > otherMember.memberID) {
            return 1;
        } else {
            return 0;
        }
    }
}

