package by.shumchenia;

import by.shumchenia.model.Animal;
import by.shumchenia.model.Car;
import by.shumchenia.model.Flower;
import by.shumchenia.model.House;
import by.shumchenia.model.Person;
import by.shumchenia.util.Util;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        System.out.println("##############");
        task2();
        System.out.println("##############");
        task3();
        System.out.println("##############");
        task4();
        System.out.println("##############");
        task5();
        System.out.println("##############");
        task6();
        System.out.println("##############");
        task7();
        System.out.println("##############");
        task8();
        System.out.println("##############");
        task9();
        System.out.println("##############");
        task10();
        System.out.println("##############");
        task11();
        System.out.println("##############");
        task12();
        System.out.println("##############");
        task13();
        System.out.println("##############");
        task14();
        System.out.println("##############");
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() < 20)
                .sorted((f, s) -> f.getAge() < s.getAge() ? -1 : 0)
                .skip(14)
                .limit(7)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> "Japanese".equals(x.getOrigin()))
                .map(animal -> "Female".equals(animal.getGender()) ?
                        animal.getBread().toUpperCase() : animal.getBread())
                .forEach(System.out::println);

    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> x.getAge() > 30)
                .filter(x -> x.getOrigin().charAt(0) == 'A')
                .map(Animal::getOrigin)
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();

        long size = animals.stream()
                .filter(x -> "Female".equals(x.getGender()))
                .count();

        System.out.println(size);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean bool = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(x -> "Hungarian".equals(x.getOrigin()));
        System.out.println(bool);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();

        boolean bool = animals.stream()
                .allMatch(x -> "Female".equals(x.getOrigin()) || "Male".equals(x.getOrigin()));
        System.out.println(bool);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean bool = animals.stream()
                .noneMatch(x -> "Oceania".equals(x.getOrigin()));
        System.out.println(bool);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .sorted(Comparator.comparing(Animal::getAge))
                .skip(99)
                .forEach(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(x -> x.length))
                .ifPresent(x -> System.out.println(x.length));
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .map(Animal::getAge)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(x -> "Indonesian".equals(x.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(x -> "Male".equals(x.getGender()))
                .filter(x -> Period.between(x.getDateOfBirth(), LocalDate.now()).getYears() > 17)
                .filter(x -> Period.between(x.getDateOfBirth(), LocalDate.now()).getYears() < 27)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);

    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        houses.stream().
                flatMap(x -> x.getPersonList().stream()
                        .map(y -> Map.entry("Hospital".equals(x.getBuildingType()) ? 1
                                : Period.between(y.getDateOfBirth(), LocalDate.now()).getYears() < 18 ||
                                Period.between(y.getDateOfBirth(), LocalDate.now()).getYears() > 58 &&
                                        "Female".equals(y.getGender()) ||
                                Period.between(y.getDateOfBirth(), LocalDate.now()).getYears() > 63 &&
                                        "Male".equals(y.getGender()) ? 2 : 3, y)))
                .sorted(Map.Entry.comparingByKey())
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();

        List<Car> turkmenistan = cars.stream()
                .filter(x -> "Jaguar".equals(x.getCarMake()) || "White".equals(x.getColor()))
                .toList();
        cars.removeAll(turkmenistan);

        List<Car> uzbekistan = cars.stream()
                .filter(x -> x.getMass() < 1500 ||
                        "BMW".equals(x.getCarMake()) ||
                        "Lexus".equals(x.getCarMake()) ||
                        "Chrysler".equals(x.getCarMake()) ||
                        "Toyota".equals(x.getCarMake()))
                .toList();

        cars.removeAll(uzbekistan);

        List<Car> kazahstan = cars.stream()
                .filter(x -> "Black".equals(x.getColor()) && x.getMass() > 4000 ||
                        "GMC".equals(x.getCarMake()) ||
                        "Dodge".equals(x.getCarMake()))
                .toList();

        cars.removeAll(kazahstan);

        List<Car> kirgistan = cars.stream()
                .filter(x -> x.getReleaseYear() < 1982 ||
                        "Civic".equals(x.getCarMake()) ||
                        "Cherokee".equals(x.getCarMake()))
                .toList();

        cars.removeAll(kirgistan);

        List<Car> russia = cars.stream()
                .filter(x -> !"Yellow".equals(x.getColor()) ||
                        !"Red".equals(x.getColor()) ||
                        !"Green".equals(x.getColor()) ||
                        !"Blue".equals(x.getColor()) ||
                        x.getPrice() > 40000)
                .toList();

        cars.removeAll(russia);

        List<Car> mongolia = cars.stream()
                .filter(x -> x.getVin().contains("59"))
                .toList();

        Double sum = Stream.of(turkmenistan, uzbekistan, kazahstan, kirgistan, russia, mongolia)
                .map(x -> x.stream()
                        .mapToInt(Car::getMass)
                        .sum())
                .map(x -> x * 0.00714)
                .peek(System.out::println)
                .mapToDouble(x -> x)
                .sum();
        System.out.println(sum);
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();

        double sum = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(x -> x.getCommonName().charAt(0) <= 'C' || x.getCommonName().charAt(0) >= 'S')
                .filter(Flower::isShadePreferred)
                .filter(x-> x.isShadePreferred() &&
                        (x.getFlowerVaseMaterial().contains("Glass") ||
                                x.getFlowerVaseMaterial().contains("Aluminium") ||
                                x.getFlowerVaseMaterial().contains("Steel")))
                .mapToDouble(x -> x.getPrice() + (x.getWaterConsumptionPerDay() * 5 * 365 * 1.35))
                .sum();
        System.out.println(sum);
    }
}