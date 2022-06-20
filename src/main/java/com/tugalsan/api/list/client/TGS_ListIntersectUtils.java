package com.tugalsan.api.list.client;

import com.tugalsan.api.stream.client.*;
import java.util.*;

public class TGS_ListIntersectUtils {

    public static List<String> intersect(List<String> listA, List<String> listB) {
        return TGS_StreamUtils.toList(listA.stream().filter(c -> listB.contains(c)));
    }

    public static List<String> nonintersect(List<String> approvedList, List<String> dirtyList) {
        return TGS_StreamUtils.toList(dirtyList.stream().filter(c -> !approvedList.contains(c)));
    }
}
