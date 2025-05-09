package com.tugalsan.api.list.client;

import com.tugalsan.api.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutBool_In2;
import java.util.*;

public class TGS_ListConcatUtils {
    
    private TGS_ListConcatUtils(){
        
    }

//    public static List<String> distinctString(boolean ordered, List<String>... lists) {
//        return filtered((union, item) -> !union.contains(item), ordered, lists);
//    }
//
//    public static List<Long> distinctLong(boolean ordered, List<Long>... lists) {
//        return filtered((union, item) -> {
//            return union.stream().anyMatch(it -> it != item.longValue());//GWT does not like isEmpty; check on 2.10 version again!
//        }, ordered, lists);
//    }
//
//    public static List<Integer> distinctInteger(boolean ordered, List<Integer>... lists) {
//        return filtered((union, item) -> {
//            return union.stream().anyMatch(it -> it != item.longValue());//GWT does not like isEmpty; check on 2.10 version again!
//        }, ordered, lists);
//    }
    public static <T> List<T> distinct(boolean sorted, List<T>... lists) {
        return filtered(sorted, (union, item) -> !union.contains(item), lists);
    }

    public static <T> List<T> filtered(boolean sorted, TGS_FuncMTU_OutBool_In2<List<T>, T> union_item, List<T>... lists) {
        List<T> union = TGS_ListUtils.of();
        if (union_item == null) {
            Arrays.stream(lists).forEach(lst -> union.addAll(lst));
            return union;
        }
        Arrays.stream(lists).forEach(lst -> {
            var s = lst.stream().filter(item -> union_item.validate(union, item));
            if (sorted) {
                s.forEachOrdered(i -> union.add(i));
            } else {
                s.forEach(i -> union.add(i));
            }
        });
        return union;
    }

    public static <T> List<T> concat(Class<T> clazz, List<T>... lists) {
        List<T> newList = new ArrayList();
        for (var list : lists) {
            newList.addAll(list);
        }
        return newList;
    }

//    @Deprecated //GWT DONT LIKE U
//    @GwtIncompatible
//    public static <T> T[] concat(Class<T> clazz, T[]... arrays) {
//        var totalSize = IntStream.range(0, arrays.length).map(i -> arrays[i].length).sum();
//        @SuppressWarnings("unchecked")
//        var o = (T[]) Array.newInstance(clazz, totalSize);
//        var offset = 0;
//        for (var array : arrays) {
//            System.arraycopy(array, 0, o, offset, array.length);
//            offset = array.length;
//        }
//        return o;
//    }
    public static String[] concat(String[] s1, String[] s2) {
        var o = new String[s1.length + s2.length];
        System.arraycopy(s1, 0, o, 0, s1.length);
        System.arraycopy(s2, 0, o, s1.length, s2.length);
        return o;
    }

    public static Object[] concat(Object[] s1, Object[] s2) {
        var o = new Object[s1.length + s2.length];
        System.arraycopy(s1, 0, o, 0, s1.length);
        System.arraycopy(s2, 0, o, s1.length, s2.length);
        return o;
    }

    public static Object[][] concat(Object[][] s1, Object[][] s2) {
        var o = new Object[s1.length + s2.length][];
        System.arraycopy(s1, 0, o, 0, s1.length);
        System.arraycopy(s2, 0, o, s1.length, s2.length);
        return o;
    }
}
