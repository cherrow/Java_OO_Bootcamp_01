package com.cherrow.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PossibleThingTest {

    @Test
    public void should_throw_exception_when_possibility_invalid() {
        //given
        double possibility = 2.3;

        //when

        //then
        assertThatThrownBy(() -> new PossibleThing(possibility))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("概率必须在 0 到 1 之间！");
    }

    @Test
    public void should_get_expected_possibility_when_one_thing_not_happen() {
        //given
        double possibility = 0.3;
        double expectedPossibility = 0.7;

        //when
        PossibleThing possibleThing = new PossibleThing(possibility);

        //then
        assertThat(possibleThing.getNotHappenPossibility()).isEqualTo(expectedPossibility);
    }

    @Test
    public void should_get_expected_possibility_when_both_things_happen() {
        //given
        double oneThingPossibility = 0.3;
        double anotherThingPossibility = 0.5;
        double expectedPossibility = 0.15;

        //when
        PossibleThing onePossibleThing = new PossibleThing(oneThingPossibility);
        PossibleThing anotherPossibleThing = new PossibleThing(anotherThingPossibility);
        PossibleThing andPossibleThing = onePossibleThing.and(anotherPossibleThing);

        //then
        assertThat(andPossibleThing.getHappenPossibility()).isEqualTo(expectedPossibility);
    }

    @Test
    public void should_get_expected_possibility_when_at_least_one_thing_happen() {
        //given
        double firstThingPossibility = 0.3;
        double secondThingPossibility = 0.2;
        double thirdThingPossibility = 0.1;
        double expectedPossibility = 0.6;

        //when
        PossibleThing firstPossibleThing = new PossibleThing(firstThingPossibility);
        PossibleThing secondPossibleThing = new PossibleThing(secondThingPossibility);
        PossibleThing thirdPossibleThing = new PossibleThing(thirdThingPossibility);
        PossibleThing orPossibleThing = firstPossibleThing.or(secondPossibleThing).or(thirdPossibleThing);

        //then
        assertThat(orPossibleThing.getHappenPossibility()).isEqualTo(expectedPossibility);
    }

    @Test
    public void should_get_one_hundred_percent_when_possibility_sum_greater_than_one() {
        //given
        double firstThingPossibility = 0.5;
        double secondThingPossibility = 0.2;
        double thirdThingPossibility = 0.4;
        double expectedPossibility = 1;

        //when
        PossibleThing firstPossibleThing = new PossibleThing(firstThingPossibility);
        PossibleThing secondPossibleThing = new PossibleThing(secondThingPossibility);
        PossibleThing thirdPossibleThing = new PossibleThing(thirdThingPossibility);
        PossibleThing orPossibleThing = firstPossibleThing.or(secondPossibleThing).or(thirdPossibleThing);

        //then
        assertThat(orPossibleThing.getHappenPossibility()).isEqualTo(expectedPossibility);
    }
}