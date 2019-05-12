package com.curso.blog.controller;

import com.curso.blog.dto.HelloRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/blog/hi/{name}")
    //@RequestMapping(method = RequestMethod.GET, value = "/blog/hi")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/blog/hi")
    //@RequestMapping(method = RequestMethod.GET, value = "/blog/hi")
    public String helloWithParameters(@RequestParam(value = "nombre", required = false) String name,
                                      @RequestParam Integer age) {
        return "Hello " + name + ", Age: " + age;
    }

    @PostMapping("/hello/map")
    public String helloWithBodyMap(@RequestBody Map<String, Object> values) {

        if (values.containsKey("name")) {
            return "Hello " + values.get("name");
        }

        return "Hello Unknown";
    }

    @PostMapping("/hello/object")
    public String helloWithBodyObject(@Valid @RequestBody HelloRequest helloRequest) {
        return "Hello " + helloRequest.getName() + ", Age: " + helloRequest.getAge();
    }

}
