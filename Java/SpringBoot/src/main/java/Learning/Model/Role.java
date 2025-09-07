package Learning.Model;

public class Role {
    private Long id;
    private String roleName;
    private String description;
    private Double roleBudget; // Pode ter centavos

    public Role() {}

    public Role(Long id, String roleName, String description, Double roleBudget) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.roleBudget = roleBudget;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getRoleBudget() { return roleBudget; }
    public void setRoleBudget(Double roleBudget) { this.roleBudget = roleBudget; }
}
