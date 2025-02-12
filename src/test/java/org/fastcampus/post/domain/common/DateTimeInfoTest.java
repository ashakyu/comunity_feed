package org.fastcampus.post.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated(){

        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime now = dateTimeInfo.getDateTime();

        //when
        dateTimeInfo.updateEditDateTime();

        //then
        Assertions.assertTrue(dateTimeInfo.isEdited());
        Assertions.assertNotEquals(now, dateTimeInfo.getDateTime());
    }

}
