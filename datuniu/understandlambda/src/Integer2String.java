/**
 * Created by nuc on 2015/12/29.
 */
public class Integer2String {

    public static final int _10 = 0xA; // 1010
    public static final int _100 = 0x64; // 110 0100
    public static final int _1000 = 0x3E8; // 11 1110 1000
    public static final int _1000000 = 0xF4240; // 1111 0100 0010 0100 0000
    public static final int _1000000000 = 0x3B9ACA00; // 11 1011 1001 1010 1100 1010 0000 0000

    public static final String[][] a = {{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"},
            {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"},
            {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"}};

    private static void fd() {
        //999之内的
        int a = 99;
        if (a > 1000) {
            int x = a / 1000;
            int rmd = a % 1000;
        } else if (a > 100) {
            int x = a / 100;
            int rmd = a % 100;

        } else if (a > 10) {
            int x = a / 10;
            int rmd = a % 10;
        } else {

        }

    }

    public static void main(String[] args) {
        System.out.println(a[0].length);
        System.out.println(a[1].length);
        System.out.println(a[2].length);
    }
}
