package dn.mrv.wm.controller;

import dn.mrv.wm.utils.Constants;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = Constants.CrossOrigin)
@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping(Constants.SLASH)
    public String wellcome(){
        return "mrv api !!!";
    }
}
