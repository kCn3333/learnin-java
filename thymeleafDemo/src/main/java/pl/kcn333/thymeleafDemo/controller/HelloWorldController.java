package pl.kcn333.thymeleafDemo.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {


    @GetMapping("/showForm")
    public String showForm(){

        return "hello-form";
    }

    @RequestMapping("/processForm")
    public String processForm(){
        System.out.println("in processForm");
        return "helloworld";
    }

    @RequestMapping("/processFormVersionTwo")
    public String processForm2(HttpServletRequest request, Model model){
        String temp=request.getParameter("name");
        System.out.println("v2 | name is : "+temp);
                temp=temp.toUpperCase();
        System.out.println("v2 | uppercase name is : "+temp);
        String message ="hello "+temp;
        model.addAttribute("message", message);
        return "helloworld";
    }
    @PostMapping("/processFormVersionThree")
    public String processForm3(@RequestParam("name") String name, Model model){
        System.out.println("v3 | name is : "+name);
        String message ="hello "+name.toUpperCase();
        model.addAttribute("message", message);
        return "helloworld";
    }

}
