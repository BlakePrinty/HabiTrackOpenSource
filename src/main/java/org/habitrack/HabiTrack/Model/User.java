package org.habitrack.HabiTrack.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @NotNull
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]{3,256}$")
    private String username;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%&*]{3,256}$")
    private String password;

    private BigDecimal totalPercentage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.totalPercentage = BigDecimal.ZERO;
    }

    public User() {}

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getTotalPercentage() {
        BigDecimal top = BigDecimal.ZERO;
        BigDecimal bottom = BigDecimal.ZERO;

        for (Habit habit : habits) {
            top = top.add(habit.getCurrentCount());
            bottom = bottom.add(habit.getGoal());
        }

        if (bottom.compareTo(BigDecimal.ZERO) == 0) {
            totalPercentage = BigDecimal.ZERO;
        } else {
            totalPercentage = top.divide(bottom, 2, RoundingMode.UNNECESSARY).multiply(BigDecimal.valueOf(100));
        }

        return totalPercentage;
    }

    public List<Habit> getHabits() {
        return habits;
    }
}
