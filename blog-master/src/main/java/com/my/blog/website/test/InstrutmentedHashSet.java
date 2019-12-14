package com.my.blog.website.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InstrutmentedHashSet<E> extends HashSet<E> {

    private  int addCount=0;

    public InstrutmentedHashSet(){

    }

    public InstrutmentedHashSet(int initCap,float loadFactor){
        super(initCap,loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount+=c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }


    public static void main(String[] args) {
        InstrutmentedHashSet<String> s=new InstrutmentedHashSet<String>();
        s.addAll(Arrays.asList("test","hehe","pop"));
        System.out.println(s.getAddCount());
    }

}
