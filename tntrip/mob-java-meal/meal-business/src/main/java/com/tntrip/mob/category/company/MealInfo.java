package com.tntrip.mob.category.company;

/**
 * Created by libing2 on 2015/7/25.
 */
public class MealInfo implements Comparable<MealInfo>{
    public String sequence;
    public String saler;
    public String owner;
    public String bookDate;
    public String offWorkTime;
    public String where;
    public String deptName;
    public String addUser;
    public String addTimestamp;
    public String operation;

    @Override
    public String toString() {
        return "MealInfo{" +
                "sequence='" + sequence + '\'' +
                ", saler='" + saler + '\'' +
                ", owner='" + owner + '\'' +
                ", bookDate='" + bookDate + '\'' +
                ", offWorkTime='" + offWorkTime + '\'' +
                ", where='" + where + '\'' +
                ", deptName='" + deptName + '\'' +
                ", addUser='" + addUser + '\'' +
                ", addTimestamp='" + addTimestamp + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealInfo mealInfo = (MealInfo) o;

        if (!sequence.equals(mealInfo.sequence)) return false;
        if (!saler.equals(mealInfo.saler)) return false;
        if (!owner.equals(mealInfo.owner)) return false;
        if (!bookDate.equals(mealInfo.bookDate)) return false;
        if (!where.equals(mealInfo.where)) return false;
        return deptName.equals(mealInfo.deptName);

    }

    @Override
    public int hashCode() {
        int result = sequence.hashCode();
        result = 31 * result + saler.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + bookDate.hashCode();
        result = 31 * result + where.hashCode();
        result = 31 * result + deptName.hashCode();
        return result;
    }

    public String showString() {
        String delimiter = "        ";
        return saler + delimiter + deptName + delimiter + owner;
    }


    @Override
    public int compareTo(MealInfo other) {
        return this.deptName.compareTo(other.deptName);
    }
}
