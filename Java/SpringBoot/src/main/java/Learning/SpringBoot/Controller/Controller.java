package Learning.SpringBoot.Controller;

import Learning.Model.User;
import Learning.Resources.Queryes.Routes.Users.*;

import Learning.Model.Role;
import Learning.Resources.Queryes.Routes.Roles.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
         else {
            return ResponseEntity.status(404).body(Map.of("error", "Usuário não encontrado"));
        }
    }

    @GetMapping("/Users/All")
    public ResponseEntity<?> recoverUsers() {
        try {
            List<Map<String, Object>> userList = getUser.getUsers();
            
            if (userList != null && !userList.isEmpty()) {
                return ResponseEntity.ok(userList);
            } else {
                return ResponseEntity.ok(List.of());  
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Contate o administrador"));
        }
    }


    @Autowired
    @Qualifier("specificInsertUser")
    private InsertUser insertUser;

    @PostMapping("/Users")
    public ResponseEntity<String> SetUser(@RequestBody User user) {
        try {
            System.out.println("User Name: " + user.getUserName());
            System.out.println("User Function: " + user.getUserFunction());
            System.out.println("User Age: " + user.getUserAge());
            System.out.println("User CPF: " + user.getUserCpf());

            Long userId = insertUser.insertUser(
                    user.getUserName(),
                    user.getUserFunction(),
                    user.getUserAge(),
                    user.getUserCpf());

            return ResponseEntity.status(201).body("✅ User Created with ID: " + userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error creating user: " + e.getMessage());
        }
    }

    @Autowired
    @Qualifier("specificUpdateUser")
    private UpdateUser UpdateUser;    

    @PutMapping("/Users")
    public ResponseEntity<String> UpdateUser(@RequestParam("UserId")  int userId, @RequestBody User user ) {
        try {
            boolean success = UpdateUser.updateUser( 
                    userId,
                    user.getUserName(),
                    user.getUserFunction(),
                    user.getUserAge());

            if (success) {
                return ResponseEntity.ok("✅ User updated successfully");
            } else {
                return ResponseEntity.status(404).body("❌ User not found or not updated");
            }

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
            } else {
                return ResponseEntity.status(404).body("❌ User not found or could not be deleted");
            }
    
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
         else {
            return ResponseEntity.status(404).body(Map.of("error", "Usuário não encontrado"));
        }
    }

    @GetMapping("/Roles/All")
    public ResponseEntity<?> recoverRoles() {
        try {
            List<Map<String, Object>> roleList = getRole.getRoles();
            
            if (roleList != null && !roleList.isEmpty()) {
                return ResponseEntity.ok(roleList);
            } else {
                return ResponseEntity.ok(List.of());  
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Contate o administrador"));
        }
    }


    @Autowired
    @Qualifier("specificInsertRole")
    private InsertRole insertRole;

    @PostMapping("/Roles")
    public ResponseEntity<String> SetRole(@RequestBody Role role) {
        try {
            System.out.println("Role Name: " + role.getRoleName());
            System.out.println("Role Description: " + role.getDescription());
            System.out.println("Role Age: " + role.getRoleBudget());
           

            Long roleId = insertRole.insertRole(
                    role.getRoleName(),
                    role.getDescription(),
                    role.getRoleBudget()
            );

            return ResponseEntity.status(201).body("✅ User Created with ID: " + roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error creating user: " + e.getMessage());
        }
    }

    @Autowired
    @Qualifier("specificUpdateRole")
    private UpdateRole UpdateRole;    

    @PutMapping("/Roles")
    public ResponseEntity<String> UpdateRole(@RequestParam("RoleId")  int roleId, @RequestBody Role role ) {
        try {
            boolean success = UpdateRole.updateRole( 
                    roleId,
                    role.getRoleName(),
                    role.getDescription(),
                    role.getRoleBudget());

            if (success) {
                return ResponseEntity.ok("✅ Role updated successfully");
            } else {
                return ResponseEntity.status(404).body("❌ Role not found or not updated");
            }

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
            } else {
                return ResponseEntity.status(404).body("❌ Role not found or could not be deleted");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error deleting role: " + e.getMessage());
        }
    }
    
}
