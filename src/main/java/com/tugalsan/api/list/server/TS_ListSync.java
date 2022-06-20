package com.tugalsan.api.list.server;

import com.tugalsan.api.executable.client.TGS_ExecutableType1;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.validator.client.TGS_ValidatorType1;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TS_ListSync<T> {

    private final ConcurrentLinkedQueue<T> list = new ConcurrentLinkedQueue();

    public List<T> toList() {
        List<T> o = TGS_ListUtils.of();
        forEach(item -> o.add(item));
        return o;
    }

//    public List<T> toListUnmodifiable() {//NOT SUPPORTED BY GWT
//        return Collections.unmodifiableList(toListLinked());
//    }
    public TS_ListSync<T> forEach(TGS_ExecutableType1<T> item) {
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            item.execute(iterator.next());
        }
        return this;
    }

    public TS_ListSync<T> forEach(TGS_ValidatorType1<T> condition, TGS_ExecutableType1<T> item) {
        return forEach(nextItem -> {
            if (condition.validate(nextItem)) {
                item.execute(nextItem);
            }
        });
    }

    public TS_ListSync<T> clear() {
        list.clear();
        return this;
    }

    public int size() {
        return list.size();
    }

    public int size(TGS_ValidatorType1<T> condition) {
        var count = 0;
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (condition.validate(item)) {
                count++;
            }
        }
        return count;
    }

    public boolean isEmpty(TGS_ValidatorType1<T> condition) {
        return size(condition) == 0;
    }

    public boolean isPresent(TGS_ValidatorType1<T> condition) {
        return !isEmpty(condition);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public T add(T item) {
        list.add(item);
        return item;
    }

    public List<T> add(List<T> items) {
        items.forEach(item -> add(item));
        return items;
    }

    public T[] add(T[] items) {
        Arrays.stream(items).forEach(item -> add(item));
        return items;
    }

    public List<T> set(List<T> items) {
        list.clear();
        return add(items);
    }

    public T[] set(T[] items) {
        list.clear();
        return add(items);
    }

    public boolean contains(T item) {
        return findFirst(o -> Objects.equals(o, item)) != null;
    }

    public T findFirst(TGS_ValidatorType1<T> condition) {
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (condition.validate(item)) {
                return item;
            }
        }
        return null;
    }

    public int idxFirst(TGS_ValidatorType1<T> condition) {
        var idx = 0;
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (condition.validate(item)) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    public List<T> findAll(TGS_ValidatorType1<T> condition) {
        List<T> foundItems = TGS_ListUtils.of();
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (condition.validate(item)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    public List<Integer> idxAll(TGS_ValidatorType1<T> condition) {
        List<Integer> foundItems = TGS_ListUtils.of();
        var idx = 0;
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (condition.validate(item)) {
                foundItems.add(idx);
            }
            idx++;
        }
        return foundItems;
    }

    public boolean removeFirst(T item) {
        return removeFirst(o -> Objects.equals(o, item));
    }

    public boolean removeAll(T item) {
        return removeAll(o -> Objects.equals(o, item));
    }

    public boolean removeAll(TGS_ValidatorType1<T> condition) {
        var result = false;
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (condition.validate(item)) {
                iterator.remove();
                result = true;
            }
        }
        return result;
    }

    public boolean removeFirst(TGS_ValidatorType1<T> condition) {
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            var item = iterator.next();
            if (condition.validate(item)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
