package com.coden.kantools.util.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CreatePhoneTools {

    public static String[] FRISTNUMLIST = {"130", "131", "132", "145", "155", "156", "185", "186", "176", "175", "133", "149", "153", "180", "181", "189", "177", "134", "139", "147",
            "150", "152"};

    //    public static List createPhoneNumber(ArrayList<String> firstNumList, int len) {
//        ArrayList re = new ArrayList();
//        String first = null;
//        String last = null;
//        for (int i = 0; i <= len; i++) {
//            first = firstNumList.get((int) Math.floor(Math.random() * firstNumList.size()));
//            last = String.valueOf((int) Math.floor(Math.random() * 100000000));
//            if (last.length() < 8) {
//                last = d(last);
//            }
//            re.add(first + last);
//        }
//        return re;
//    }
//    public static ArrayList unrepeated(List reArray, String[] firstNumList) {
//        HashSet reSet = new HashSet(reArray);
//        ArrayList NewArray = new ArrayList();
//        System.out.println("Now is " + reSet.size());
//        if (reSet.size() < reArray.size()) {
//            int diff =  reArray.size() - reSet.size();
//            NewArray.addAll(reSet);
//            NewArray.addAll(createPhoneNumber(firstNumList, diff, false));
//            System.out.println("this is "+ diff + "___" + NewArray.size());
//            NewArray = unrepeated(NewArray, firstNumList);
//
//        }else{
//            NewArray = (ArrayList) reArray;
//        }
//
//        return NewArray;
//    }


    public static ArrayList<String> createPhoneNumber(String[] firstNumList, int len, boolean unrepeat) {
        ArrayList re = new ArrayList();
        String first;
        String last;
        for (int i = 0; i < len; i++) {
            first = firstNumList[(int) Math.floor(Math.random() * firstNumList.length)];
            last = String.valueOf((int) Math.floor(Math.random() * 100000000));
            if (last.length() < 8) {
                last = d(last);
            }
            re.add(first + last);
        }

        if (unrepeat) {
            re = unrepeated(re, firstNumList);
        }

        return re;
    }


    private static ArrayList unrepeated(List reArray, String[] firstNumList) {
        HashSet reSet = new HashSet(reArray);

        System.out.println("Now is " + reSet.size());
        if (reSet.size() < reArray.size()) {
            int diff = reArray.size() - reSet.size();
            reArray.clear();
            reArray.addAll(reSet);
            reArray.addAll(createPhoneNumber(firstNumList, diff, false));
            System.out.println("this is " + diff + "___" + reArray.size());
            reArray = unrepeated(reArray, firstNumList);
        }

        return (ArrayList) reArray;
    }


    private static String d(String a) {
        String temp = null;
        for (int i = 8 - a.length(); i > 0; i--) {
            temp = String.valueOf((int) Math.floor(Math.random() * 10));
            a = a + temp;
        }
        return a;
    }

//    public static void main(String[] args) {
//        String[] s = {"158", "132", "166"};
//        ArrayList sss = (ArrayList) createPhoneNumber(s, 1000000, true);
//        System.out.println("最终结果 = " + new HashSet(sss).size());
//        System.out.println(sss);
//    }
}
