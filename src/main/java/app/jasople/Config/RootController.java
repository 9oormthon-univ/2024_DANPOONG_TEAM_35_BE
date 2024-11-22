package app.jasople.Config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    // 빈스톡 health check endpoint
    @GetMapping("/api/health")
    public String healthCheck() {
        return "ok";
    }
}