import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] totalBasket;
    private int sum;

    public Basket(String[] products, int[] pricesBasket) {
        this.products = products;
        this.prices = pricesBasket;
        this.totalBasket = new int[products.length];
        this.sum = 0;
    }

    public void addToCart(int productNum, int amount) {
        int currentPrice = prices[productNum];  //цена выбранного продукта
        totalBasket[productNum] += amount;
        sum += currentPrice * amount;
    }

    public void printCart() {
        for (int i = 0; i < totalBasket.length; i++) {
            if (!(totalBasket[i] == 0)) {
                System.out.println(products[i] + " " + totalBasket[i] + " шт, по цене " +
                        prices[i] + " руб/шт " + totalBasket[i] * prices[i] +
                        " руб в сумме");
            }
        }
    }

    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            oos.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return basket;
    }

    public int getSum() {
        return sum;
    }
}