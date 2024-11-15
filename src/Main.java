import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static void displayMenu() {
        System.out.println("A - Add an item to list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("V - Print the list to the screen");
        System.out.println("M - Move an item");
        System.out.println("O - Open a list file from disk");
        System.out.println("S - Save the current list file to disk");
        System.out.println("C - Removes all the elements from the current list");
        System.out.println("Q - Quit the program");
    }

    static String getOption() throws IOException {
        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);

        ArrayList<String> options = new ArrayList<String>();
        options.add("a");
        options.add("d");
        options.add("i");
        options.add("v");
        options.add("q");
        options.add("m");
        options.add("o");
        options.add("s");
        options.add("c");
        options.add("v");

        String userInput = SafeInput.getNonZeroLenString(br, "Enter option: ");
        userInput = userInput.toLowerCase();

        if (options.contains(userInput)) {
            return userInput;
        } else {
            return getOption();
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);

        ArrayList<String> list = new ArrayList<String>();
        boolean edited = false;
        boolean playing = true;

        while (playing) {
            System.out.println();
            displayMenu();
            System.out.println();
            String option = "";

            option = getOption();

            switch (option) {
                case "a":
                    String item = SafeInput.getNonZeroLenString(br, "Enter item: ");
                    list.add(item);
                    edited = true;
                    break;
                case "d":
                    int index = SafeInput.getRangedInt(br, "Enter number: ", 0, list.size()-1);
                    list.remove(index);
                    edited = true;
                    break;
                case "i":
                    int insertIndex = SafeInput.getRangedInt(br, "Enter number: ", 0, list.size()-1);
                    String insertItem = SafeInput.getNonZeroLenString(br, "Enter item: ");
                    list.set(insertIndex, insertItem);
                    edited = true;
                    break;
                case "v":
                    ListMaker.displayList(list);
                    break;
                case "m":
                    int sourceIndex = SafeInput.getRangedInt(br, "Enter number: ", 0, list.size()-1);
                    int dstIndex = SafeInput.getRangedInt(br, "Enter number: ", 0, list.size()-1);

                    String sourceValue = list.get(sourceIndex);
                    String dstValue = list.get(dstIndex);

                    list.set(sourceIndex, dstValue);
                    list.set(dstIndex, sourceValue);

                    edited = true;
                    break;
                case "o":
                    if (edited) {
                        System.out.println("You must save your unsaved changes");
                        break;
                    }
                    String fileName = SafeInput.getNonZeroLenString(br, "Enter filename: ");
                    ListMaker.loadList(list, fileName);
                    System.out.println("Opened list from file");
                    break;
                case "s":
                    String fileName1 = SafeInput.getNonZeroLenString(br, "Enter filename: ");

                    int status = ListMaker.writeList(list, fileName1);

                    if (status == -1) {
                        System.out.println("Fail to write to file");
                        break;
                    }

                    edited = false;
                    break;
                case "c":
                    if (list.size() == 0) {
                        break;
                    }
                    list.clear();
                    break;
                case "q":
                    if (edited) {
                        System.out.println("Must save unsaved changes");
                        break;
                    }

                    playing = false;

            }

        }

    }
}
