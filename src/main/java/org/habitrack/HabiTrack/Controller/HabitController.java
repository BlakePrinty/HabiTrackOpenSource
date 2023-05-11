package org.habitrack.HabiTrack.Controller;

import org.habitrack.HabiTrack.Model.Habit;
import org.habitrack.HabiTrack.Repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HabitController {
    @Autowired
    HabitRepository habitRepository;

    public HabitController(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @RequestMapping("/dashboard/habit")
    public String userHabit(Model model, @RequestParam("id") Long habitId) {
        if (habitRepository.findById(habitId).isPresent()) {
            Habit habit = habitRepository.findById(habitId).get();
            model.addAttribute("habit", habit);
            return "habit";
        }
        return "error";
    }
}
