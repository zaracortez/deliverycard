package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        String cities = "Майкоп, Горно-Алтайск, Уфа, Улан-Удэ, Махачкала, Магас, Нальчик, Элиста, Черкесск, Петрозаводск, Сыктывкар, Симферополь, Йошкар-Ола, Саранск, Якутск, Владикавказ, Казань, Кызыл, Ижевск, Абакан, Грозный, Чебоксары, Барнаул, Чита, Петропавловск-Камчатский, Краснодар, Красноярск, Пермь, Владивосток, Ставрополь, Хабаровск, Благовещенск, Архангельск, Астрахань, Белгород, Брянск, Владимир, Волгоград, Вологда, Воронеж, Мелитополь, Иваново, Иркутск, Калининград, Калуга, Кемерово, Киров, Кострома, Курган, Курск, Санкт-Петербург, Липецк, Магадан, Красногорск, Мурманск, Нижний Новгород, Великий Новгород, Новосибирск, Омск, Оренбург, Орёл, Пенза, Псков, Ростов-на-Дону, Рязань, Самара, Саратов, Южно-Сахалинск, Екатеринбург, Смоленск, Тамбов, Тверь, Томск, Тула, Тюмень, Ульяновск, Челябинск, Ярославль, Москва, Санкт-Петербург, Севастополь, Биробиджан, Нарьян-Мар, Ханты-Мансийск, Анадырь, Салехард";
        String[] currentCity = cities.split(", ");
        Random gen = new Random();
        int rand = gen.nextInt(currentCity.length);
        return currentCity[rand];
    }

    public static String generateName(String locale) {
        String name = faker.name().fullName().replace("ё", "е");
        return name;
    }

    public static String generatePhone(String locale) {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity("ru"), generateName("ru"), generatePhone("ru"));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
