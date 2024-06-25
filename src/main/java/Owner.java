import java.sql.ResultSet;
import java.sql.SQLException;

public class Owner {

    String firstname;
    String lastname;
    int id;
    String contact;
    String Lic;

    //Licence
    public Owner(ResultSet r) throws SQLException {
        //r.next();
        this.id = r.getInt(1);
        this.firstname = r.getString(2);
        this.lastname = r.getString(3);
        this.contact = r.getString(4);
        this.Lic = r.getString(5);
    }

    public int getId(){ return this.id;}

    public String getString() {
        return String.format("ID: %d\n" +
                        "First Name: %s\n" +
                        "Last Name: %s\n" +
                        "Contact: %s\n",
                id, firstname, lastname, contact);
    }



}
