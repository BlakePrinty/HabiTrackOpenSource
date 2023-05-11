package org.habitrack.HabiTrack.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
public class Habit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column(nullable = false)
    private BigDecimal goal;

    private String color;
    private String icon;
    private String iconColor;
    private BigDecimal currentCount;

    private BigDecimal progress;
    private boolean completed;

    @ManyToOne @JoinColumn(name = "uuid")
    private User user;

    public Habit(String name, String description, String color, String type, BigDecimal goal, User user) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.type = type;
        this.goal = goal;
        this.user = user;

        if (Objects.equals(type, "Build")) {
            this.icon = "buildHabitIcon";
            this.iconColor = "buildHabitIconColor";
        } else {
            this.icon = "breakHabitIcon";
            this.iconColor = "breakHabitIconColor";
        }

        this.currentCount = BigDecimal.ZERO;
        this.progress = BigDecimal.ZERO;
        this.completed = false;
    }

    public Habit() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public String getIconColor() {
        return iconColor;
    }

    public BigDecimal getCurrentCount() {
        return currentCount;
    }

    public BigDecimal getProgress() {
        BigDecimal numerator = getCurrentCount();
        BigDecimal denominator = getGoal();

        if (denominator.compareTo(BigDecimal.ZERO) == 0) {
            progress = BigDecimal.ZERO;
        } else {
            progress = numerator.divide(denominator, 2, RoundingMode.UNNECESSARY).multiply(BigDecimal.valueOf(100));
        }

        return progress;
    }

    public boolean isCompleted() {
        BigDecimal habitProgress = getProgress();

        if (habitProgress.compareTo(BigDecimal.valueOf(100)) >= 0) {
            completed = true;
        }

        return completed;
    }

    public User getUser() {
        return user;
    }
}
