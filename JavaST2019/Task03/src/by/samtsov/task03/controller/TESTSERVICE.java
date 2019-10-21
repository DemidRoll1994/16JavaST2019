package by.samtsov.task03.controller;

public class TESTSERVICE {

    // todo
    // • Битовые выражения, встречающиеся в тексте, должны быть вычислены. И в итоговый текст (структуру данных) должно войти вычисленное значение. Использовать Interpreter с применением функциональных интерфейсов.
    // • Код должен быть покрыт тестами.
    // чекстайл
    // сонар
    // тесты
    // логи
    // сущностям доавить equals. hashcode toString
    // удалить ненужные классы
















/* // todo delete class
    private static final Logger logger = LogManager.getLogger();

    public void start() {
        //print(getString().split("(?<=[!?.]{1,3})\r\n"));
        //print(getString().split("(?<=[!?.]{1,3})\\s+"));
        //print(getString().split("(?=[!?.,;])"));
        //print(getString().split("(?<=[!?.]{1,3})\r\n"));
        //getString().matches() //todo


        regWorker("[\\w]+|[^\\w\\s]+");
        System.out.println(7 + 6.0 < +9 / -9 ? ("name" != "name") : 'c' == 'с');


    }


    public void test() {

    }


    public void print(String[] args) {
        for (String s : args) {
            System.out.println(s + " |");
        }
    }

    public String getString() {
        String string = null;
        try {
            string = new FileReader().singleLineRead("data/input.txt");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return string;
    }

    public void regWorker(String reg) {

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(getString());
        while (matcher.find()) {
            System.out.println(matcher.group() + "|");
        }
    }
*/
}
