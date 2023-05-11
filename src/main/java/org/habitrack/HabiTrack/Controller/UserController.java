package org.habitrack.HabiTrack.Controller;

import org.habitrack.HabiTrack.Model.User;
import org.habitrack.HabiTrack.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/dashboard")
    public String userDashboard(Model model, @RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            if (Objects.equals(userRepository.findByUsername(username).get().getPassword(), password)) {
                model.addAttribute("user", userRepository.findByUsername(username).get());
                return "dashboard";
            } else {
                return "start";
            }
        } else {
            User user = new User(username, password);
            userRepository.save(user);
            model.addAttribute("user", userRepository.findByUsername(username).get());
            return "dashboard";
        }
    }
}
