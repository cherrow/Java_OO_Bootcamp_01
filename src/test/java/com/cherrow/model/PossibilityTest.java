package com.cherrow.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PossibilityTest {

    @Test
    public void should_return_true() {
        //given

        //when
        Possibility possibility = new Possibility();

        //then
        assertTrue(possibility.initMethod());
    }
}