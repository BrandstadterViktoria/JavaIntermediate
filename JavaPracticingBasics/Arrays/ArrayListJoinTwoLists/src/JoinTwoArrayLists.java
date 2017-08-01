import java.util.ArrayList;
import java.util.Arrays;

public class JoinTwoArrayLists {

    public static void main(String[] args) {
        ArrayList<String> girls = new ArrayList<String>(Arrays.asList("Eve", "Ashley", "Bözsi", "Kat", "Jane"));
        ArrayList<String> boys = new ArrayList<String>(Arrays.asList("Joe", "Fred", "Béla", "Todd", "Neef", "Jeff"));
        ArrayList<String> order = new ArrayList<String>(girls.size()); // Make a new list
        for (int i = 0; i < girls.size(); i++) { // Loop through every name/phone number combo
            order.add(girls.get(i) + " " + boys.get(i));
        }
        System.out.print(order);
    }
}
