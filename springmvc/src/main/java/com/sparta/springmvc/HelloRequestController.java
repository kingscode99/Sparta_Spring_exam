package com.sparta.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
public class HelloRequestController {

    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age)
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }

    //Get요청, Url에 나타남.
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    //Post요청 Url에 나타나지 않음.
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    //파람에서는 하나씩 받아왔다면, 여기서는 객체형식으로 받았음.(ModelAttribute) & @ModelAttribute는 생략이 가능하다.
    //객체에 @Setter가 붙어있어야 한다.
    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    //json형식으로 보내기.
    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }
}