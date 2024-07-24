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

    public Owner(String fn, String ln,String cont, String lic){
        this.firstname = fn;
        this.lastname = ln;
        this.contact = cont;
        this.Lic = lic;


    }


    // Getter and Setter for firstname
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // Getter and Setter for lastname
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for contact
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Getter and Setter for Lic
    public String getLic() {
        return Lic;
    }

    public void setLic(String lic) {
        Lic = lic;
    }

    public String getString() {
        return String.format("ID: %d\n" +
                        "First Name: %s\n" +
                        "Last Name: %s\n" +
                        "Contact: %s\n",
                id, firstname, lastname, contact);
    }



}
