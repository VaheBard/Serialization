import java.io.*;

public class Basket implements Serializable {
    private static String[] products;
    private static int[] prices;
    private static int[] totalBasket;
    private static int sum;

    public Basket(String[] productsBasket, int[] pricesBasket) {
        this.products = productsBasket;
        this.prices = pricesBasket;
        this.totalBasket = new int[products.length];
        this.sum = 0;
    }

    public void addToCart(int productNum, int amount) {
        int currentPrice = prices[productNum];
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

    public void saveTxt(File textFile) {
        try (OutputStream os = new FileOutputStream(textFile)) {
            for (String product : products)
                os.write((product + " ").getBytes());
            os.write("\n".getBytes());
            for (int price : prices)
                os.write((price + " ").getBytes());
            os.write("\n".getBytes());
            for (int amount : totalBasket)
                os.write((amount + " ").getBytes());
            os.write(("\n" + sum).getBytes());
            os.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try (
                BufferedReader buf = new BufferedReader(new FileReader(textFile))
        ) {

            String[] strProd = buf.readLine().split(" ");
            for (int i = 0; i < products.length; i++) {
                products[i] = strProd[i];
            }
            String[] priceStr = buf.readLine().split(" ");
            for (int i = 0; i < prices.length; i++) {
                prices[i] = Integer.parseInt(priceStr[i]);
            }

            String[] strPrice = buf.readLine().split(" ");
            for (int i = 0; i < totalBasket.length; i++) {
                totalBasket[i] = Integer.parseInt(strPrice[i]);
            }
            String strSum = buf.readLine();
            sum = Integer.parseInt(strSum);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public int getSum() {
        return sum;
    }

}