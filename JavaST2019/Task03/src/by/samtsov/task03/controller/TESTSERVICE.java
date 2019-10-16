package by.samtsov.task03.controller;

public class TESTSERVICE {
    public void start() {
        String string = "Строка — ряд знаков, написанных или напечатанных в одну линию. Строка может также означать: Строковый тип — тип данных в программировании.";
        print(string.split("—"));
        print(string.split("\\s"));
        //string.matches() //todo
    }

    public void print(String[] args) {
        for (String s:args
             ) {
            System.out.println(s);
        }
    }

}
