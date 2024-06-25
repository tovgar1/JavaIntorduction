import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class PhoneBook {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public void add(String name, Integer phoneNum) {

        if (phoneBook.containsKey(name)) {
            phoneBook.get(name).add(phoneNum);
        } else {

            ArrayList<Integer> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(phoneNum);
            phoneBook.put(name, phoneNumbers);
        }
    }
    public void remove(String name) {

        if (!phoneBook.containsKey(name)) {
            return;
        } else {

            phoneBook.remove(name);
        }
    }

    public ArrayList<Integer> find(String name) {

        if (phoneBook.containsKey(name)) {
            return phoneBook.get(name);
        } else {

            return new ArrayList<>();
        }
    }

    public static Map<String, ArrayList<Integer>> getPhoneBook() {

        List<Map.Entry<String, ArrayList<Integer>>> entryList = new ArrayList<>(phoneBook.entrySet());

        entryList.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));

        LinkedHashMap<String, ArrayList<Integer>> sortedPhoneBook = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : entryList) {
            sortedPhoneBook.put(entry.getKey(), entry.getValue());
        }

        return sortedPhoneBook;
    }
}

public class аттестацияJava {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", 11111);
        phoneBook.add("Ivanov", 12345);
        phoneBook.add("Petrova", 123467);

        System.out.println(phoneBook.find("Petrova"));
        
        System.out.println(phoneBook.getPhoneBook());

        phoneBook.remove("Petrova");
        System.out.println(phoneBook.getPhoneBook());
    }
}
