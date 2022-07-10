import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws Exception {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if(components.length != 4){
            throw new Exception();
        }

        Pattern patternPhone = Pattern.compile( "^[8+]?[7-]?\\s?\\(?\\d\\d\\d\\)?[\\s-]?\\d\\d\\d-?\\d\\d-?\\d\\d");
        Matcher matcherPhone = patternPhone.matcher(components[INDEX_PHONE]);
        if(!matcherPhone.matches()){
            throw new Exception();
        }

        Pattern patternEmail = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcherEmail = patternEmail.matcher(components[INDEX_EMAIL]);
        if(!matcherEmail.matches()){
            throw new Exception();
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
