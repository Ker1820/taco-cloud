package com.example.tacocloud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
    @RequestMapping("/status_widget")
    public String getStatus(){
        return "{\"data\": [1, 2, 3, 4, 123] }";
    }
    @RequestMapping("/chart_widget")
    public String getChart(){
        return "{\"data\": [1, 2, 3, 4, 5] }";
    }
    @RequestMapping("/bar_widget")
    public String getBar(){
        return "{\"data\": [1, 2, 3, 4, 5] }";
    }
    @RequestMapping("/pie_widget")
    public String getPie(){
        return "{\"data\":[321, 431, 525, 684, 781],\"backgroundColor\": [\"#4e73df\",\"#e25647\", \"#cc0b00\", \"#24cc85\", \"#c8c9ca\"],\"hoverBackgroundColor\": [\"#4053a7\", \"#b24a3d\", \"#710900\", \"#178959\", \"#838485\"],\"hoverBorderColor\": \"rgba(234, 236, 244, 1)\"}";
    }
}
