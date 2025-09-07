package Learning.Model;

public class User {
    private Long id;
    private String userName;
    private String userFunction;
    private Integer userAge; // Inteiro (idade não tem casas decimais)
    private String userCpf;

    public User() {}

    public User(Long id, String userName, String userFunction, Integer userAge, String userCpf) {
        this.id = id;
        this.userName = userName;
        this.userFunction = userFunction;
        this.userAge = userAge;
        this.userCpf = userCpf;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserFunction() { return userFunction; }
    public void setUserFunction(String userFunction) { this.userFunction = userFunction; }

    public Integer getUserAge() { return userAge; }
    public void setUserAge(Integer userAge) { this.userAge = userAge; }

    public String getUserCpf() { return userCpf; }
    public void setUserCpf(String userCpf) { this.userCpf = userCpf; }
}
