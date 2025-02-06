package org.fastcampus.common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;


class PositiveIntegerCounterTest {

    @Test
    void givenCreated_whenIncrease_thenCountIsOne(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.increase();

        //then
        Assertions.assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();
        //when
        counter.decrease();

        //then
        Assertions.assertEquals(0, counter.getCount());
    }

    @Test
    void givenCreated_whenDecrease_thenCountIsZero(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        //when
        counter.decrease();

        //then
        Assertions.assertEquals(0, counter.getCount());
    }
}
