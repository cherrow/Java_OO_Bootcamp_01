package com.cherrow.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChanceTest {

    @Test
    public void should_throw_exception_when_possibility_invalid() {
        //given
        double possibility = 2.3;

        //when

        //then
        assertThatThrownBy(() -> new Chance(possibility))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("概率必须在 0 到 1 之间！");
    }

    @Test
    public void should_get_expected_possibility_when_one_thing_not_happen() {
        //given
        Chance chance = new Chance(0.3);
        double expectedPossibility = 0.7;

        //when
        Chance notChance = chance.not();

        //then
        assertThat(notChance).hasFieldOrPropertyWithValue("possibility", expectedPossibility);
    }

    @Test
    public void should_get_expected_possibility_when_both_things_happen() {
        //given
        Chance oneChance = new Chance(0.3);
        Chance anotherChance = new Chance(0.5);
        double expectedPossibility = 0.15;

        //when
        Chance andChance = oneChance.and(anotherChance);

        //then
        assertThat(andChance).hasFieldOrPropertyWithValue("possibility", expectedPossibility);
    }

    @Test
    public void should_get_expected_possibility_when_at_least_one_thing_happen() {
        //given
        Chance firstChance = new Chance(0.3);
        Chance secondChance = new Chance(0.2);
        Chance thirdChance = new Chance(0.1);
        double expectedPossibility = 0.404;

        //when
        Chance orChance = firstChance.or(secondChance).or(thirdChance);

        //then
        assertThat(orChance).hasFieldOrPropertyWithValue("possibility", expectedPossibility);
    }
}