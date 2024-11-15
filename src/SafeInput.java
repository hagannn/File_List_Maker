import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeInput {
    public static String getNonZeroLenString(BufferedReader br, String prompt) throws IOException {
        System.out.print(prompt);
        String data = br.readLine();

        if (data.isEmpty()) {
            return getNonZeroLenString(br, prompt);
        }
        return data;
    }

    public static int getInt(BufferedReader br, String prompt) throws IOException {
        int data;
        System.out.print(prompt);
        // No need to clear buffer as I am using the BufferReader class and not the Scanner
        String input_data = br.readLine();

        try {
            data = Integer.parseInt(input_data);
        } catch (Exception e) {
            return getInt(br, prompt);
        }

        return data;
    }

    public static double getDouble(BufferedReader br, String prompt) throws IOException {
        double data;
        System.out.print(prompt);
        // No need to clear buffer as I am using the BufferReader class and not the Scanner
        String input_data = br.readLine();

        try {
            data = Double.parseDouble(input_data);
        } catch (Exception e) {
            return getDouble(br, prompt);
        }

        return data;
    }

    public static int getRangedInt(BufferedReader br, String prompt, int low, int high) throws IOException {
        int data;
        System.out.print(prompt);
        String input_data = br.readLine();

        try {
            data = Integer.parseInt(input_data);
        } catch (Exception e) {
            return getRangedInt(br, prompt, low, high);
        }

        if (data < low || data > high) {
            return getRangedInt(br, prompt, low, high);
        }

        return data;
    }

    public static double getRangedDouble(BufferedReader br, String prompt, double low, double high) throws IOException {
        double data;
        System.out.print(prompt);
        String input_data = br.readLine();

        try {
            data = Double.parseDouble(input_data);
        } catch (Exception e) {
            return getRangedDouble(br, prompt, low, high);
        }

        if (data < low || data > high) {
            return getRangedDouble(br, prompt, low, high);
        }

        return data;
    }

    public static boolean getYNConfirm(BufferedReader br, String prompt) throws IOException{
        boolean result;
        System.out.print(prompt);
        String input_data = br.readLine().toLowerCase();

        return switch (input_data) {
            case "y" -> true;
            case "n" -> false;
            default -> getYNConfirm(br, prompt);
        };

    }

    public static String getRegExString(BufferedReader br, String prompt, String regEx) throws IOException {
        System.out.print(prompt);
        String input_data = br.readLine();

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input_data);

        if (!matcher.find()) {
            return getRegExString(br, prompt, regEx);
        }

        return input_data;
    }

}