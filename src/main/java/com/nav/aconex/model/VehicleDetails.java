package com.nav.aconex.model;

import com.nav.aconex.constants.Direction;

public class VehicleDetails {
    private final Integer counter;
    private final Direction direction;
    private final Integer frontAxleMilliSecondsAfterMidnight;
    private final Integer rearAxleMilliSecondsAfterMidnight;
    private final Integer speedInKph;
    private final Integer dayOfTheSurvey;


    public VehicleDetails(Integer counter, Direction direction, Integer frontAxleMilliSecondsAfterMidnight,
                          Integer rearAxleMilliSecondsAfterMidnight, Integer speedInKph, Integer dayOfTheSurvey) {
        this.counter = counter;
        this.direction = direction;
        this.frontAxleMilliSecondsAfterMidnight = frontAxleMilliSecondsAfterMidnight;
        this.rearAxleMilliSecondsAfterMidnight = rearAxleMilliSecondsAfterMidnight;
        this.speedInKph = speedInKph;
        this.dayOfTheSurvey = dayOfTheSurvey;
    }

    public Integer getCounter() {
        return counter;
    }

    public Direction getDirection() {
        return direction;
    }

    public Integer getFrontAxleMilliSecondsAfterMidnight() {
        return frontAxleMilliSecondsAfterMidnight;
    }

    public Integer getRearAxleMilliSecondsAfterMidnight() {
        return rearAxleMilliSecondsAfterMidnight;
    }

    public Integer getSpeedInKph() {
        return speedInKph;
    }

    public Integer getDayOfTheSurvey() {
        return dayOfTheSurvey;
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "counter=" + counter +
                ", direction=" + direction +
                ", frontAxleMilliSecondsAfterMidnight=" + frontAxleMilliSecondsAfterMidnight +
                ", rearAxleMilliSecondsAfterMidnight=" + rearAxleMilliSecondsAfterMidnight +
                ", speedInKph=" + speedInKph +
                ", dayOfTheSurvey=" + dayOfTheSurvey +
                '}';
    }
}
