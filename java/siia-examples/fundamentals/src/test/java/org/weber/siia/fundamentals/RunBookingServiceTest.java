package org.weber.siia.fundamentals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wxu on 4/22/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:siia/fundamentals/context.xml")
public class RunBookingServiceTest {
    @Autowired
    @Qualifier("mealPreferenceUpdatesChannel")
    private MessageChannel messageChannel;

    @Test
    public void updateMealPreferences() {
       this.messageChannel.send(MessageBuilder.withPayload(new MealPreference()).build());
    }
}
