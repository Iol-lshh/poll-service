package lshh.pollservice.domain;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.domain.component.user.UserMemberRepository;
import lshh.auth.lib.type.UserRoleAuthority;
import lshh.pollservice.dto.user.UserRoleUpdate;
import lshh.pollservice.dto.user.UserSignUp;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMemberRepository userMemberRepository;

    @Nested
    class SignUpTest {
        @Test
        void signUp() {
            UserSignUp command = new UserSignUp("testSignUp", "tester", "password");
            var result = userService.signUp(command);
            assertNotNull(result);
            assertNotNull(result.id());
        }
    }

    @Nested
    class AddRoleTest {
        @Test
        void addRole() {
            UserSignUp signUpCommand = new UserSignUp("testAddRole", "tester", "password");
            var user = userService.signUp(signUpCommand);

            UserRoleUpdate command = new UserRoleUpdate(user.loginId(), UserRoleAuthority.USER);
            var result = userService.addRole(command);

            log.info("addRole: " + result);
            assertNotNull(result);
            assertEquals(2, result.roles().size());
            List<String> checkRoles = List.of("USER", "TEMP_USER");
            checkRoles.forEach(checkRole -> {
                assertTrue(result.roles().stream().anyMatch(_role -> _role.name().equals(checkRole)));
            });
        }
    }
}