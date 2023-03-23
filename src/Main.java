import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        File fileBinary = new File("basket.bin");
        int[] prices = {50, 14, 80};
        String[] products = {"Молоко", "Хлеб", "Гречка"};
        Scanner scanner = new Scanner(System.in);
        /*пользователь вводит номер продукта, тобиш выбирает продукт, затем пробел и указыат количество
        * выбранного пробукта, так до тех пор пока не введет "end", после чего программа выводит итог
        * например : Молоко 10 шт 50 руб/шт 500 руб в сумме
                     Гречневая крупа 1 шт 80 руб/шт 80 руб в сумме
                     Итого 580 руб*/
        System.out.println("Список возможных товаров для покупки");

//выводим на экран информацию о продуктах и их цен с помощью цыкла for
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }

        Basket basket;
        /*Проверяем существование файла, если его нет, то все начинается с нуля, иначе загружается существующая корзина
        и следующие покупки добавляются на нее
         */
        if (fileBinary.exists()) {
            basket = Basket.loadFromBinFile(fileBinary);

            basket.printCart();

        } else {
            basket = new Basket(products, prices);

            System.out.print("Корзина пустая! ");
        }

        while (true) {

            System.out.println("\nДля продолжения покупки выберите товар затем пробел зтем количество  \n" +
                    "Для завершения покупки нужно вводить end затем нажать на ENTER! ");

            String enter = scanner.nextLine();

            if ("end".equals(enter)) {
                System.out.println("Вы завершили покупки\n");
                break;
            }
            String[] parts = enter.split(" ");

            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);

            basket.addToCart(productNumber, productCount);
            basket.saveBin(fileBinary);
            System.out.println("Ваша корзина:");
            basket.printCart();
        }

    }
}



