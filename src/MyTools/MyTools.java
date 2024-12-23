/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyTools;

/**
 *
 * @author Admin
 */
import java.util.Date;  // Lớp mô tả ngày tháng
import java.text.SimpleDateFormat;  // Lớp giúp định dạng ngày tháng
import java.text.DateFormat;
import java.text.ParseException;    // Lớp mô tả lỗi khi phân tích String
import java.util.Calendar;      // Lớp mô tả lịch nói chung
import java.util.GregorianCalendar; // Lịch dương
import java.util.Scanner;
import java.util.List;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class MyTools {
    public static final Scanner sc = new Scanner(System.in);

    public static boolean parseBoolean(String input) {
        input = input.trim().toUpperCase();
        char c = input.charAt(0);
        return c=='T' || c=='1' || c=='Y' ;
    }

    /** Normalizing a date string: Using '-' to separate date parts only
     * @param dateStr: input date string
     * @return new string
     */
// " 7 ... --- 2 //// 2023 " --> "7-2-2013"
    public static String normalizeDateStr(String dateStr){
        // Loại bỏ tất cả các khoảng trống trong chuỗi nhập. Dùng replaceAll() với
        // regex phù hợp như " 7 ... --- 2 //// 2023 "  --> "7...---2////2023"
        String result = dateStr.replaceAll("[\\s]+", "");

        // Thay thế tất cả các nhóm ký tự . / - bằng '-'. Dùng replaceAll() với regex
        // phù hợp "7...---2////2023" --> "7-2-2023" rồi trả kết quả sau khi đã xử lý
        result = result.replaceAll("[./-]+", "-");
        return result;
    }

    /** Parsing the input string to date data
     * @param inputStr: date string input.
     * @param dateFormat: chosen date format.
     * @return Date object if successful and null if failed
     */
    public static Date parseDate(String inputStr, String dateFormat){
        inputStr = normalizeDateStr(inputStr); // chuẩn hóa chuỗi ngày tháng
        DateFormat formatter = new SimpleDateFormat(dateFormat); // Tạo DateFormat
        try{ // Dùng formatter parse chuỗi nhập rồi trả kết quả
            return formatter.parse(inputStr);
        }
        catch (ParseException e){
            System.err.println(e);
        }
        return null; // không thành công thì trả về null
    }

    public static String toString(Date date, String dateFormat) {
        if(date==null) return "";
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    /** Getting year of date data
     * @param d: Date data
     * @param calendarPart: date part is declared in the class Calendar
     * @return year in date data.
     */
    public static int getPart(Date d, int calendarPart){
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(calendarPart);
    }

//--- METHODS FOR READING DATA FROM KEYBOARD

    /** Reading a boolean data
     * @param prompt: prompt will be printed before inputting.
     * @return boolean data (true/false)
     */
    public static boolean readBoolean (String prompt){
        System.out.print(prompt + ": ");
        return parseBoolean(sc.nextLine());
    }

    /** Nhập chuỗi bất kỳ
     * @param prompt: prompt will be printed before inputting.
     * @return arbitrary input string
     */
    public static String readStr (String prompt){
        String input;
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }


    /** Reading a string using a pre-define pattern
     * @param prompt: prompt will be printed befor inputting.
     * @param pattern: pre-defined pattern of input
     * @return an input string which matches the pattern.
     */
    public static String readStr (String prompt, String pattern){
        String input;
        boolean valid = false;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            valid = input.matches(pattern);
        }
        while (valid == false);
        return input;
    }


    /** Reading a date data using a pre-define date format
     *  dd-MM-yyyy / MM-dd-yyyy/ yyyy-MM-dd
     * @param prompt: prompt will be printed befor inputting.
     * @param pattern: pre-defined pattern of input
     * @return an input string which matches the pattern.
     */
    public static Date readDate (String prompt, String dateFormat){
        String input;
        Date d;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            if (d==null) System.out.println("Date data is not valid!");
        }
        while (d==null);
        return d;
    }


    // Nhập ngày tháng sau một ngày cho trước
    public static Date readDateAfter (String prompt,
                                      String dateFormat, Date markerDate){
        String input;
        Date d;
        boolean ok = false;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            ok = (d!=null && d.after(markerDate));
            if (d==null) System.out.println("Date data is not valid!");
        }
        while (!ok);
        return d;
    }


    // Nhập ngày tháng trước một ngày cho trước
    public static Date readDateBefore (String prompt, String dateFormat,
                                       Date markerDate){
        String input;
        Date d;
        boolean ok = false;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            ok = (d!=null && d.before(markerDate));
            if (d==null) System.out.println("Date data is not valid!");
        }
        while (!ok);
        return d;
    }



    // Automatically generating an increasing code
// Thí dụ sinh ra mã: P0000123 -> prefix: P, length=7, curNumber= 123
    public static String generateCode(String prefix,
                                      int length, int curNumber) {
        String formatStr = "%0" + length + "d"; // -> %07d
        return prefix + String.format(formatStr, curNumber);
    }

    // Lấy phần số trong mã theo mẫu - Get number in a given code having prefix
// Thí dụ có mã: P0000123 -> 123
    public static int getNumberInCode(String code, String prefix) {
        return Integer.parseInt(code.substring(prefix.length()));
    }

    // Lấy phần số trong mã theo mẫu - Get number in a given code having prefix
// Thí dụ có mã: P0000123 -> 123
    public static int getNumberInCode(String code, int prefixLength) {
        return Integer.parseInt(code.substring(prefixLength));
    }

    // Ham cho menu
    public static int menu(Object... options) {
        int L = options.length;
        for (int i = 0; i < L; i++)
            System.out.println((i + 1) + "-" + options[i].toString());
        System.out.print("Choose (1 .. " + L + ") : ");
        return Integer.parseInt(sc.nextLine());
    }

    // Ham cho menu
    public static int menu(List options) {
        int L = options.size();
        for (int i = 0; i < L; i++)
            System.out.println((i + 1) + "-" + options.get(i).toString());
        System.out.print("Choose (1 .. " + L + ") : ");
        return Integer.parseInt(sc.nextLine());
    }

    // Ham cho menu chon 1 object
    public static Object chooseOne(Object... options) {
        int pos = menu(options);
        if (pos < 0 || pos >= options.length) return null;
        return options[pos - 1];
    }



// Hàm cho menu chọn 1 object

    public static Object chooseOne(List options) {
        int pos = menu(options);
        if(pos<0 || pos>options.size()) return null;
        return options.get(pos-1);
    }




}

