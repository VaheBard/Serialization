import java.io.*;

public class Basket implements Serializable {
    /*если поля будут статические, то они пренадлежат не объекту а классу.
     как следствие, изменить значения только для одного объекта корзины
      не будет возможным. Поэтому *//**ПОЛЯ ДОЛЖНЫ БЫТЬ НЕ СТАТИЧЕСКИЕ*/
    private final String[] products;
    private final int[] prices;
    private final int[] totalBasket;
    private int sum;

    public Basket(String[] productsBasket, int[] pricesBasket) {
        this.products = productsBasket;
        this.prices = pricesBasket;
        this.totalBasket = new int[products.length];
        this.sum = 0;
    }

    public Basket(String[] products, int[] prices, int[] totalBasket, int sum) {
        /*этот конструктор создали для восстановления объекта из файла*/
        this.products = products;
        this.prices = prices;
        this.totalBasket = totalBasket;
        this.sum = sum;
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
        String[] loadedProducts;
        int[] loadedPrices;
        int[] loadedTotalBasket;
        int loadedSum;
        try (
                BufferedReader buf = new BufferedReader(new FileReader(textFile))
                /*создаем объект класса BufferedReader, который будет посьрочно читать файл,
                * передаваемый в аргумент этому методу */
        ) {

            loadedProducts = buf.readLine().split(" ");
            /*первая сторка прочтена, значение ввиде массива типа стринг передана loadedProducts*/
            String[] priceStr = buf.readLine().split(" ");
            /*вторая строка прочтена, полученныя значения типа стринг приведем в инт, и поочередно
            помещаем в массив loadedPrices*/
            loadedPrices = new int[priceStr.length];
            for (int i = 0; i < priceStr.length; i++) {
                loadedPrices[i] = Integer.parseInt(priceStr[i]);
            }

            String[] strPrice = buf.readLine().split(" ");
            /*третья строка прочтена, полученныя значения типа стринг приведем в инт, и поочередно
            помещаем в массив loadedBasket*/
            loadedTotalBasket = new int[strPrice.length];
            for (int i = 0; i < strPrice.length; i++) {
                loadedTotalBasket[i] = Integer.parseInt(strPrice[i]);
            }
            String strSum = buf.readLine();
            /*четвертая строка прочтена, там одно значение типа, приведем его в инт
            * и полученный присвоим loadedSum*/
            loadedSum = Integer.parseInt(strSum);
            return new Basket(loadedProducts, loadedPrices, loadedTotalBasket, loadedSum);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public int getSum() {
        return sum;
    }

}