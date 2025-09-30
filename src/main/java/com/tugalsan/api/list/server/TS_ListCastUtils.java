package com.tugalsan.api.list.server;

import module com.tugalsan.api.list;
import module com.tugalsan.api.stream;
import java.util.*;

public class TS_ListCastUtils {

    public List<String> toList(StringTokenizer st) {
        return TGS_StreamUtils.toLst(
                Collections.list(st).stream()
                        .map(token -> (String) token)
        );
    }

    public static List<String> toString(StringTokenizer input) {
        if (input == null) {
            return null;
        }
        List<String> lst = TGS_ListUtils.of();
        while (input.hasMoreTokens()) {
            lst.add(input.nextToken());
        }
        return lst;
    }
}
