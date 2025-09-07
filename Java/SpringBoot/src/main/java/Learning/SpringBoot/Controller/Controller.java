package Learning.SpringBoot.Controller;

import Learning.Model.User;
import Learning.Model.Role;
import Learning.Resources.Queryes.Routes.Users.*;
import Learning.Resources.Queryes.Routes.Roles.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SpringApi")
public class Controller {

    // ::::::::::: ----- Users Interactions ----- :::::::::::

    @Autowired
    @Qualifier("specificGetUser")
    private GetUser getUser;

    @GetMapping("/Users")
    public ResponseEntity<?> recoverUser(@RequestParam("UserId") int userId) {
        Map<String, Object> userData = getUser.getUserById(userId);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        }
        return ResponseEntity.status(404).body(Map.of("error", "Usuário não encontrado"));
    }

    @GetMapping("/Users/All")
    public ResponseEntity<?> recoverUsers() {
        try {
            List<Map<String, Object>> userList = getUser.getUsers();
            return ResponseEntity.ok(userList != null ? userList : List.of());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Contate o administrador"));
        }
    }

    @Autowired
    @Qualifier("specificInsertUser")
    private InsertUser insertUser;

    @PostMapping("/Users")
    public ResponseEntity<String> setUser(@RequestBody User user) {
        try {
            System.out.println("Creating User: " + user);

            Long userId = insertUser.insertUser(
                    user.getUserName(),
                    user.getUserFunction(),
                    user.getUserAge(),
                    user.getUserCpf()
            );

            return ResponseEntity.status(201).body("✅ User Created with ID: " + userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error creating user: " + e.getMessage());
        }
    }

    @Autowired
    @Qualifier("specificUpdateUser")
    private UpdateUser updateUserService;

    @PutMapping("/Users")
    public ResponseEntity<String> updateUser(@RequestParam("UserId") int userId, @RequestBody User user) {
        try {
            boolean success = updateUserService.updateUser(
                    userId,
                    user.getUserName(),
                    user.getUserFunction(),
                    user.getUserAge()
            );

            if (success) {
                return ResponseEntity.ok("✅ User updated successfully");
            }
            return ResponseEntity.status(404).body("❌ User not found or not updated");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error updating user: " + e.getMessage());
        }
    }

    @Autowired
    @Qualifier("specificDeleteUser")
    private DeleteUser deleteUser;

    @DeleteMapping("/Users")
    public ResponseEntity<String> deleteUser(@RequestParam("UserId") int userId) {
        try {
            boolean success = deleteUser.deleteUser(userId);

            if (success) {
                return ResponseEntity.ok("✅ User deleted successfully");
            }
            return ResponseEntity.status(404).body("❌ User not found or could not be deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error deleting user: " + e.getMessage());
        }
    }

    // ::::::::::: ----- Roles Interactions ----- :::::::::::

    @Autowired
    @Qualifier("specificGetRole")
    private GetRole getRole;

    @GetMapping("/Roles")
    public ResponseEntity<?> recoverRole(@RequestParam("RoleId") int roleId) {
        Map<String, Object> roleData = getRole.getRoleById(roleId);
        if (roleData != null) {
            return ResponseEntity.ok(roleData);
        }
        return ResponseEntity.status(404).body(Map.of("error", "Role não encontrado"));
    }

    @GetMapping("/Roles/All")
    public ResponseEntity<?> recoverRoles() {
        try {
            List<Map<String, Object>> roleList = getRole.getRoles();
            return ResponseEntity.ok(roleList != null ? roleList : List.of());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Contate o administrador"));
        }
    }

    @Autowired
    @Qualifier("specificInsertRole")
    private InsertRole insertRole;

    @PostMapping("/Roles")
    public ResponseEntity<String> setRole(@RequestBody Role role) {
        try {
            System.out.println("Creating Role: " + role);

            Long roleId = insertRole.insertRole(
                    role.getRoleName(),
                    role.getDescription(),
                    role.getRoleBudget()
            );

            return ResponseEntity.status(201).body("✅ Role Created with ID: " + roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error creating role: " + e.getMessage());
        }
    }

    @Autowired
    @Qualifier("specificUpdateRole")
    private UpdateRole updateRoleService;

    @PutMapping("/Roles")
    public ResponseEntity<String> updateRole(@RequestParam("RoleId") int roleId, @RequestBody Role role) {
        try {
            boolean success = updateRoleService.updateRole(
                    roleId,
                    role.getRoleName(),
                    role.getDescription(),
                    role.getRoleBudget()
            );

            if (success) {
                return ResponseEntity.ok("✅ Role updated successfully");
            }
            return ResponseEntity.status(404).body("❌ Role not found or not updated");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error updating role: " + e.getMessage());
        }
    }

    @Autowired
    @Qualifier("specificDeleteRole")
    private DeleteRole deleteRole;

    @DeleteMapping("/Roles")
    public ResponseEntity<String> deleteRole(@RequestParam("RoleId") int roleId) {
        try {
            boolean success = deleteRole.deleteRole(roleId);

            if (success) {
                return ResponseEntity.ok("✅ Role deleted successfully");
            }
            return ResponseEntity.status(404).body("❌ Role not found or could not be deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error deleting role: " + e.getMessage());
        }
    }
}
