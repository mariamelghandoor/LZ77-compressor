import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static String read_file(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            return lines.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<List<String>> Compress_in_77(String data, int look_ahead_window, int search_window) {
        int i = 0, j;
        int size = data.length();
        boolean found;
        int pos = 0, len = 0;
        String next_letter;
        int count_ahead = 0;
        int count_search = 0;
        List<List<String>> result = new ArrayList<>();
        while (i < size) {
            j = i;
            found = false;

            while (found == false && j-- > 0 && i < size && count_search++ <= search_window) {
                if (data.charAt(i) == data.charAt(j)) {
                    found = true;
                    pos = i - j;
                    len = -1;

                    for (int k = j; k < i; k++) {
                        if (i >= size || data.charAt(k) != data.charAt(i) || count_ahead >= look_ahead_window) {
                            break;
                        }
                        i++;
                        len++;
                        count_ahead++;
                    }
                    count_ahead = 0;
                }
            }
            if (i == size)
                next_letter = "";
            else
                next_letter = String.valueOf(data.charAt(i++));

            result.add(List.of(String.valueOf(pos), String.valueOf(len), next_letter));
            pos = 0;
            len = 0;
            count_search = 0;
        }
        return result;
    }

    public static String DeCompress_in_77(List<List<String>> data) {
        String result = "";
        for (List<String> d : data) {
            if (!d.get(0).equals("0")) {
                int letter_place = result.length() - Integer.valueOf(d.get(0));
                for (int i = letter_place; i <= letter_place + Integer.valueOf(d.get(1)); i++) {
                    result += result.charAt(i);
                }
            }
            result += d.get(2);
        }

        return result;
    }

    public static boolean Compute_similarity(String data, String genrated) {
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) != genrated.charAt(i)) {
                System.out.println(data.charAt(i) + " " + genrated.charAt(i));
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    public static int Get_Max_bits(int place, List<List<String>> data) {
        int max = 0;
        for (List<String> d : data) {
            if (Integer.valueOf(d.get(place)) > max) {
                max = Integer.valueOf(d.get(place));
            }
        }
        return Integer.toBinaryString(max).length();
    }

    public static void main(String[] args) throws Exception {

        // read from text file

        // String filePath = "resources/data.txt";
        // String filePath = "resources/data_with_rep.txt";
        // String filePath = "resources/small_text.txt";
        String filePath = "resources/dd.txt";

        String data = read_file(filePath);
        data = data.replace("[", "");
        data = data.replace("]", "");

        // boolean lets_continue  = true;
        // while(lets_continue){
        //     System.out.println("compress or decompress? (c/d)");
        //     Scanner input = new Scanner(System.in);
        //     String response = input.nextLine();
        //     if(response.equals("c")){
        //         System.out.println("Enter the data:");
        //         String data = input.nextLine();
        //         List<List<String>> result = Compress_in_77(data, 30, 30);
        //         System.out.println("The Compresed data is: " + result);
        //         System.out.println("The data size is: " + (data.length() * 8) + " bits while the compresed data is: "
        //                 + ((Get_Max_bits(0, result) + Get_Max_bits(1, result) + 8) * result.size()) + " bits");
        //     }
        //     else{
        //         Scanner scanner = new Scanner(System.in);
        //         List<List<String>> listOfLists = new ArrayList<>();
        //         System.out.print("Enter the number of lists: ");
        //         int numOfLists = Integer.parseInt(scanner.nextLine());

        //         for (int i = 0; i < numOfLists; i++) {
        //             System.out.print("Enter elements for list " + (i + 1) + " separated by spaces: ");
        //             String input2 = scanner.nextLine();
        //             String[] elements = input2.split(" ");
        //             List<String> innerList = new ArrayList<>();
        //             for (String element : elements) {
        //                 innerList.add(element);
        //             }
        //             listOfLists.add(innerList);
        //         }
        //         String data = input.nextLine();
        //         String genrated = DeCompress_in_77(listOfLists);
        //         System.out.println("The genrated data is:" + genrated);
        //         System.out.println("bits while the compresed data is: "
        //                 + ((Get_Max_bits(0, listOfLists) + Get_Max_bits(1, listOfLists) + 8) * listOfLists.size()) + " bits");
        //     }
        //     System.out.println("Do you want to continue? (y/n)");
        //     String response_ = input.nextLine();
        //     if(response_.equals("n")){
        //         lets_continue = false;
        //         input.close();
        //     }
        // }

        List<List<String>> result = Compress_in_77(data, 30, 30);
        System.out.println("The Compresed data is: " + result);
        String genrated = DeCompress_in_77(result);
        System.out.println("The genrated data is:" + genrated);
        System.out.println("The original data is:" + data);

        System.out.println("Similarity test: " + Compute_similarity(data, genrated));
        System.out.println("The data size is: " + (data.length() * 8) + " bits while the compresed data is: "
                + ((Get_Max_bits(0, result) + Get_Max_bits(1, result) + 8) * result.size()) + " bits");


    }
}
