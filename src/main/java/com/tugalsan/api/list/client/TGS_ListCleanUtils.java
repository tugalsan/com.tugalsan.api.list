package com.tugalsan.api.list.client;

import com.tugalsan.api.function.client.TGS_Func_In1;
import com.tugalsan.api.function.client.TGS_Func_OutBool_In1;
import com.tugalsan.api.stream.client.*;
import com.tugalsan.api.string.client.*;
import java.util.*;
import java.util.stream.*;

public class TGS_ListCleanUtils {

    public static List deleteIf(TGS_Func_OutBool_In1<Integer> deleteIf, List main, TGS_Func_In1<Integer> optionalExecutionForEveryDeleteOp) {
        TGS_StreamUtils.reverse(0, main.size()).forEach(i -> {
            if (deleteIf.validate(i)) {
                main.remove(i);
                optionalExecutionForEveryDeleteOp.run(i);
            }
        });
        return main;
    }

    public static List<String> cleanEmptyOrNulls(List<String> main, TGS_Func_In1<Integer> optionalExecutionForEveryDeleteOp) {
        TGS_StreamUtils.reverse(0, main.size()).forEach(i -> {
            var str = main.get(i);
            if (TGS_StringUtils.cmn().isNullOrEmpty(str)) {
                main.remove(i);
                optionalExecutionForEveryDeleteOp.run(i);
            }
        });
        return main;
    }

    public static List<String> cleanEmptyOrNulls(List<String> list) {
        TGS_StreamUtils.reverse(0, list.size()).forEach(i -> {
            var str = list.get(i);
            if (TGS_StringUtils.cmn().isNullOrEmpty(str)) {
                list.remove(i);
            }
        });
        return list;
    }

    public static List<String> trim(List<String> list) {
        IntStream.range(0, list.size()).forEach(i -> {
            var str = list.get(i);
            if (str == null) {
                return;
            }
            list.set(i, str.trim());
        });
        return list;
    }

    public static <T> List<T> cleanNulls(List<T> list) {
        TGS_StreamUtils.reverse(0, list.size()).forEach(i -> {
            var o = list.get(i);
            if (o == null) {
                list.remove(i);
            }
        });
        return list;
    }

    public static <T> List<T> cleanNulls(List<T> list, TGS_Func_In1<Integer> optionalExecutionForEveryDeleteOp) {
        TGS_StreamUtils.reverse(0, list.size()).forEach(i -> {
            var o = list.get(i);
            if (o == null) {
                list.remove(i);
                optionalExecutionForEveryDeleteOp.run(i);
            }
        });
        return list;
    }
}
