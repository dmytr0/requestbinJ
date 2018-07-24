package test.http.receiver;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import test.MainService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private MainService service;

    @RequestMapping(value = "/test", method = {GET, POST, PUT, DELETE})
    @ResponseStatus(HttpStatus.OK)
    public String test(@RequestBody String request, @RequestHeader HttpHeaders headers) throws InterruptedException {

        service.add(request, headers);

        log.info("Ok");
        return "{\"message\":\"HELLO WORLD\"}";
    }

    @RequestMapping(value = "/view", method = GET, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String getResult() {

        return service.getAll();

    }

    @RequestMapping(value = "/reset", method = GET)
    @ResponseStatus(HttpStatus.OK)
    public String removeAll() {

        service.removeAll();

        return "OK";
    }
}
