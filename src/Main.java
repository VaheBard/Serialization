import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        /*пользователь вводит номер продукта, тобиш выбирает продукт, затем пробел и указыат количество
        * выбранного пробукта, так до тех пор пока не введет "end", после чего программа выводит итог
        * например : Молоко 10 шт 50 руб/шт 500 руб в сумме
                     Гречневая крупа 1 шт 80 руб/шт 80 руб в сумме
                     Итого 580 руб*/
        String[] products = {"Молоко", "Хлеб", "Гречка"};

        int[] prices = {50, 14, 80};
        System.out.println("Список возможных товаров для покупки");

//выводим на экран информацию о продуктах и их цен с помощью цыкла for
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }

        File file = new File("basket.txt");

        Scanner scanner = new Scanner(System.in);

        Basket basket = new Basket(products, prices);
        /*Проверяем существование файла, если его нет, то все начинается с нуля, иначе загружается существующая корзина
        и следующие покупки добавляются на нее
         */
        if (file.exists()) {
            Basket.loadFromTxtFile(file);

            basket.printCart();

        } else {
            file.createNewFile();

            System.out.print("Корзина пустая! ");
        }
        int total = 0;

        while (true) {

            System.out.println("\nДля продолжения покупки выберите товар затем пробел зтем количество  \n" +
                    "Для завершения покупки нужно вводить end затем нажать на ENTER! ");

            String enter = scanner.nextLine();

            if ("end".equals(enter)) {
                System.out.println("Вы завершили покупки, к оплате " + basket.getSum() + " руб\n");
                break;
            }
            String[] parts = enter.split(" ");

            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            total += prices[productNumber] * productCount;
            basket.addToCart(productNumber, productCount);
            basket.saveTxt(file);


            System.out.println("Ваша корзина:");
            basket.printCart();
        }
    }
}



