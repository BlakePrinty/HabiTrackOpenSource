package org.habitrack.HabiTrack;

import org.habitrack.HabiTrack.Model.Habit;
import org.habitrack.HabiTrack.Model.User;
import org.habitrack.HabiTrack.Repository.HabitRepository;
import org.habitrack.HabiTrack.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class HabiTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabiTrackApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(UserRepository userRepository, HabitRepository habitRepository) {
		return (args) -> {
			String demoUsername = "DemoUser";
			String demoPassword = "DemoPassword123";

			if (userRepository.findByUsername(demoUsername).isEmpty()) {
				User demoUser = new User(demoUsername, demoPassword);
				userRepository.save(demoUser);

				Habit firstHabit = new Habit("Drink Water", "I want to drink more water each day.", "#000000", "Build", BigDecimal.valueOf(42), demoUser);
				habitRepository.save(firstHabit);
				userRepository.save(demoUser);

				Habit secondHabit = new Habit("Go to the Gym", "I want to go to the gym more", "#001000", "Build", BigDecimal.valueOf(5), demoUser);
				habitRepository.save(secondHabit);
				userRepository.save(demoUser);

				Habit thirdHabit = new Habit("Stop drinking Coffee", "I need to eliminate the amount of coffee I drink", "#f1f1f1", "Break", BigDecimal.valueOf(10), demoUser);
				habitRepository.save(thirdHabit);
				userRepository.save(demoUser);
			}
		};
	}
}
