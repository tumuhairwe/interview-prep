package com.tumuhairwe.prep;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class FordCredit {

    @Ascychronous
    public static Future<Employee> main(String[] args) {
        Employee e = new Employee();
        e.name= "Judes";

        List<Employee> list1 = List.of(e);
        List<Employee> list2 = List.of(e);

         list2.addAll(list1);

        Optional<Employee> matchingEmployee = list1.stream()
                .filter(em -> em.name.equals("Judes"))
                //.sorted(Employee::)
                .findFirst();

        Comparator

        Comparator<Employee> comparator = new Comparator<>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return 0;
            }
        };

        return null;
    }


    static class Employee implements Comparable<Employee> {
        String name;
        String email;

        @Override
        public int compareTo(Employee o) {
            return this.name.compareTo(o.name);
        }
    }
}
