package org.habitrack.HabiTrack.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    public PageController() {}

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/start")
    public String startPage() {
        return "start";
    }
}
