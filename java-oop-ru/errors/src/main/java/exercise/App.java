package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        long roundedSquare = 0;
        try {
            roundedSquare = Math.round(circle.getSquare());
            System.out.println(roundedSquare);
        } catch (Exception e) {
            System.out.println("Не удалось посчитать площадь");
        }
        System.out.println("Вычисление окончено");
    }
}
// END
