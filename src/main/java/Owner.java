import java.sql.ResultSet;
import java.sql.SQLException;

public class Owner {

    String firstname;
    String lastname;
    int id;
    String contact;

    //Licence
    public Owner(ResultSet r) throws SQLException {
        this.firstname = r.getString(1);
        this.lastname = r.getString(2);
        this.id = r.getInt(3);
        this.contact = r.getString(4);
        //Licence
    }

    public String getString() {
        return String.format("ID: %d\n" +
                        "First Name: %s\n" +
                        "Last Name: %s\n" +
                        "Contact: %s\n",
                id, firstname, lastname, contact);
    }



}
