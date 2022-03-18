package ru.olofmart;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countUnderage = persons.stream().filter(x -> x.getAge() < 18).count();
        System.out.println("\nКоличество несовершеннолетних: " + countUnderage);

        List<String> listRecruiters = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() < 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println("\nФамилии первых 15 призывников из списка:");
        listRecruiters.stream().limit(15).forEach(System.out::println);

        List<Person> listWorkingPersons = persons.stream()
                .filter(x -> (x.getAge() >= 18))
                .filter(x -> ((x.getAge() < 60) && (x.getSex().equals(Sex.WOMAN))) || ((x.getAge() < 65) && (x.getSex().equals(Sex.MAN))))
                .sorted((x1, x2) -> x2.getFamily().compareTo(x2.getFamily()))
                .collect(Collectors.toList());
        System.out.println("\nДанные первых 15 работоспособных человек из списка:");
        listWorkingPersons.stream().limit(15).forEach(System.out::println);
    }
}
