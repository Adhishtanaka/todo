package at.todo.utils;


import at.todo.models.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public static UserModel getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserModel) {
            return (UserModel) principal;
        }
        return null;
    }

    public static String getCurrentUserId() {
        UserModel user = getCurrentUser();
        return user != null ? user.getId().toString() : null;
    }
}