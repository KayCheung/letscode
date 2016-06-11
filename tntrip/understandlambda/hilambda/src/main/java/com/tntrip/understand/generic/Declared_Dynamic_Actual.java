package com.tntrip.understand.generic;


import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuc on 2016/6/11.
 */
public class Declared_Dynamic_Actual<E> {
    /**
     * <pre>
     * declared type: Entry<E>
     * actual type: Entry。正是由于 type erasure，actual type 总是 raw type
     * Entry<E>是 parameterized type（而不是 raw type）
     * first的declared type是Entry<E>，这个 Entry<E> 是 generict type Node<E> 的一个实例；并不是 raw type Entry的实例
     *
     * first的actual type是 raw type Entry；Entry本身是generic type，因为Entry有 formal type parameter
     *
     * </pre>
     */
    private Node<E> first = new Node<>(null, null, null);
    private List<Integer> second = new ArrayList<>();

    private static class Node<N> {
        N item;
        Node<N> next;
        Node<N> prev;

        Node(Node<N> prev, N element, Node<N> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void kindsOfType() throws Exception {
        Field field = Declared_Dynamic_Actual.class.getDeclaredField("second");
        System.out.println("------------getGenericType------------");
        AnalyzeType.analyzeType(field.getGenericType());

        Type gettype = field.getType();
        System.out.println("------------getType------------");
        AnalyzeType.analyzeType(gettype);

        System.out.println("------------getClass------------");
        Class<? extends List> aClass = new Declared_Dynamic_Actual().second.getClass();
        AnalyzeType.analyzeType(aClass);


    }

    public static void main(String[] args) throws Exception {
        kindsOfType();
    }
}
