package Learning.SpringBoot.Controller;

import Learning.Model.User;
import Learning.Resources.Queryes.GetUser;
import Learning.Resources.Queryes.InsertUser;
import Learning.Resources.Queryes.DeleteUser;
import Learning.Resources.Queryes.UpdateUser;

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
    
}
