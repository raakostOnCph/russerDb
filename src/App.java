import Util.ConnectionConfiguration;
import com.daoImpl.PersonDaoImpl;
import com.entities.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {

        PersonDaoImpl pdi = new PersonDaoImpl();

        pdi.createPersonTable();

//        Person person = new Person("doller","Peternes");
//
//        pdi.insert(person);

//        pdi.insert(new Person("jørgen","cockburn"));
//        pdi.insert(new Person("rikke","numbKast"));
//
//        Person person = pdi.selectById(1);
//
//        System.out.println(person.getFirstName());
//        System.out.println(person.getLastName());

        List<Person> personList = pdi.selectAll();

        udskrivAlle(personList);

        pdi.delete(3);

        System.out.println("nu fjerner vi jørgen");

        udskrivAlle(personList);


        System.out.println("updatere element 2 til lars... ");
        pdi.update(new Person("lars","larsen"),2);


        pdi.selectAll();

        udskrivAlle(personList);
    }

    private static void udskrivAlle(List<Person> personList) {
        for (Person p: personList
             ) {
            System.out.println(p.getId());
            System.out.println(p.getFirstName());
            System.out.println(p.getLastName());
        }
    }


}
