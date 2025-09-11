package Learning.Resources.Queryes.Routes;

import Learning.Resources.Queryes.Routes.Users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Component;



@Component("specificDeleteRoute")
public class DeleteRoute {

    @Autowired
    @Qualifier("specificDeleteUser")
    private DeleteUser deleteUser;

    public int deleteRoute(int userId) {
        try {
            boolean success = deleteUser.deleteUser(userId);

            if (success) {
                return 200;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
        return 404;
    }
}
            