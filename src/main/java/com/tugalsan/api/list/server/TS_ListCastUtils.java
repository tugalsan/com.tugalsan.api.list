package com.tugalsan.api.list.server;

import com.tugalsan.api.list.client.TGS_ListUtils;
import java.util.List;
import java.util.StringTokenizer;

public class TS_ListCastUtils {

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
