package cloud.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {
    private final String env;

    public TestController(
            @Value("${app.name:qqq}")
            String env
    ) {
        this.env = env;
    }

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("testView")
    //due to hierarchy, will also be accessible to userEdit and userAdmin
    public Foo testView(Authentication auth) {
        log.info("/testView, auth: " + auth);
        var foo = new Foo();
        foo.setBar("view; env=" + env);
        return foo;
    }

    @GetMapping("testEdit")
    public Foo testEdit(Authentication auth) {
        log.info("/testEdit, auth: " + auth);
        var foo = new Foo();
        foo.setBar("edit");
        return foo;
    }

    //note we've not set @Secured/@PreAuthorize and it means role is not checked!
    //it merely requires any authenticated user (due to our securityFilterChain())
    @GetMapping("testUnsecuredMethod")
    public Foo testUnsecured(Authentication auth) {
        log.info("/testUnsecured, auth: " + auth);
        var foo = new Foo();
        foo.setBar("i'm unsecured!!");
        return foo;
    }
}