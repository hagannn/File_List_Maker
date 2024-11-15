import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListMaker {
    static void displayList(ArrayList<String> list) {
        int i = 0;
        for (String item : list) {
            System.out.printf("%d %s\n", i, item);
            i += 1;
        }
    }

    static int writeList(ArrayList<String> list, String name) {
        try {
            FileWriter file = new FileWriter(name+".txt");

            for (String item : list) {
                file.write(item+"\n");
            }
            file.close();
        } catch (IOException e) {
            return -1;
        }

        return 0;
    }

    static int loadList(ArrayList<String> list, String name) {
        try {
            list.clear();
            File file = new File(name+".txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                list.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            return -1;
        }

        return 0;
    }

}
