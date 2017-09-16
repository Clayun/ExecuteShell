package org.hph.shell.controller;

import org.hph.shell.model.XShell;
import org.hph.shell.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/XShell")
public class AppController {

    @Resource
    private AppService appService;

    @RequestMapping("/run")
    public List<String> runShell(XShell xShell){
        return appService.runShell(xShell);
    }

}
