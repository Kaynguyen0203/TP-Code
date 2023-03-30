public class User {
    private final String name;
    private final String password;
    private final String email;
    private final String address;
    private final String role;
    User (String name, String password, String email, String address, String role){
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
    }
    public String getName() {return name;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}
    public String getRole() {return role;}
}
