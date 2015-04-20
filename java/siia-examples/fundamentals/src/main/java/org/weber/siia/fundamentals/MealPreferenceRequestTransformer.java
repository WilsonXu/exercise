package org.weber.siia.fundamentals;

import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * Created by wilson on 15/4/21.
 */
public class MealPreferenceRequestTransformer {
    public Source buildMealPreferenceUpdateRequest(MealPreference mealPreference) {
        return new StringSource(
                "<updateMealPreference>" +
                "<flightRef>" +
                mealPreference.getFlightReference() +
                "</flightRef>" +
                "<mealPreference>" +
                mealPreference + "" +
                "</mealPreference>" +
                "</updateMealPreference>");
    }
}
