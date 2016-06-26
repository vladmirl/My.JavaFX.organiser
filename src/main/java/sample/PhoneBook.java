package sample;

import java.io.*;
import java.util.*;

/**
 * Created by Володимир on 06.04.2016.
 */
public class PhoneBook implements Comparable<PhoneBook> {

    private TreeMap<String, Person> book = new TreeMap<>();

    public PhoneBook() {
        File file = new File("PhoneBook.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectInputStream inputPerson = new ObjectInputStream(new FileInputStream(file));

            book = (TreeMap<String, Person>) inputPerson.readObject();
            inputPerson.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TreeMap<String, Person> getTreeMap() {
        return book;
    }

    public void setSerialisibe() {
        try {
            ObjectOutputStream outputPerson = new ObjectOutputStream(new FileOutputStream("PhoneBook.txt"));
            outputPerson.writeObject(book);
            outputPerson.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        StringBuilder builder = new StringBuilder();
        builder.append(book.entrySet());
        for (Person person : book.values()) {
            builder.append(person.getContact());
        }
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("All organiser contacts.txt")));
            out.writeUTF(builder.toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("All organiser contacts.txt")));
            System.out.println(in.readUTF());
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PhoneBook Book = new PhoneBook();
        System.out.println(Book.getTreeMap().values().isEmpty());
    }

    public int compareTo(PhoneBook another) {
        return Integer.compare(this.hashCode(), another.hashCode());
    }
}
