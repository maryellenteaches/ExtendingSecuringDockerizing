package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by Mary Ellen Bowman
 */
public class TourRatingTest {

    private Tour tour = new Tour("title","description","blurb", 50, "1 day", "bullet",
            "keywords",new TourPackage("CC","name"), Difficulty.Difficult, Region.Central_Coast);

    @Test
    public void testConstructor1() throws Exception {
        TourRating rating = new TourRating(tour, 1, 1, "comment");
        testIt(rating);
        assertThat(rating.getComment(), is("comment"));
    }
    @Test
    public void testConstructor2() throws Exception {
        TourRating rating = new TourRating(tour, 1, 1);
        testIt(rating);
        assertThat(rating.getComment(), is("Terrible"));
    }

    private void testIt(TourRating rating){
        assertThat(rating.getId(), is(nullValue()));
        assertThat(rating.getTour(), is(tour));
        assertThat(rating.getScore(), is(1));
        assertThat(rating.getCustomerId(), is(1));
    }

    @Test
    public void equalsHashcodeVerify() {
        TourRating rating1 = new TourRating(tour, 1, 1, "comment");
        TourRating rating2 = new TourRating(tour, 1, 1, "comment");

        assertEquals(rating1,rating2);
        assertEquals(rating1.hashCode(), rating2.hashCode());
    }
}