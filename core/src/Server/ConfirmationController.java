package Server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmationController {

    @GetMapping("/confirm")
    public String confirmEmail(@RequestParam String token) {
        System.out.println("Received token: " + token);
        Server.receiveToken(token);
        return "";
    }
}

