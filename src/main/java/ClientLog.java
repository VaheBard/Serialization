import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ClientLog {
    ArrayList<String []> list = new ArrayList<>();
    private Basket basket;
    public ClientLog(Basket basket){
        this.basket = basket;
    }



    public void log(int productNum, int amount){
        String [] st = new String[2];

        st[0] = Integer.toString(productNum);
        st[1] = Integer.toString(amount);

        list.add(st);
    }

    public ArrayList<String []> getList(){
        return list;
    }


    public void exportAsCSV(File file) throws IOException {

        try(CSVWriter writer = new CSVWriter(new FileWriter(file))){

            String[] head = new String[]{"productNum", "amount"};
            writer.writeAll(Collections.singleton(head));

            writer.writeAll(list, true);
            writer.flush();
        }
    }

}

