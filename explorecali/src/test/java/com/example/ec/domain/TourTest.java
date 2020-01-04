package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Mary Ellen Bowman.
 */
public class TourTest {
    @Test
    public void testConstructorAndGetters() throws Exception {
        TourPackage p = new TourPackage("CC","name");
        Tour tour = new Tour("title","description","blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.Difficult, Region.Central_Coast);
        assertNull(tour.getId());
        assertThat(tour.getTitle(), is("title"));
        assertThat(tour.getDescription(), is("description"));
        assertThat(tour.getBlurb(), is("blurb"));
        assertThat(tour.getPrice(), is(50));
        assertThat(tour.getDuration(), is("1 day"));
        assertThat(tour.getBullets(), is("bullet"));
        assertThat(tour.getKeywords(), is("keywords"));
        assertThat(tour.getTourPackage().getCode(), is("CC"));
        assertThat(tour.getDifficulty(), is(Difficulty.Difficult));
        assertThat(tour.getRegion(), is(Region.Central_Coast));

    }

    @Test
    public void equalsHashcodeVerify() {
        TourPackage p = new TourPackage("CC","name");
        Tour tour1 = new Tour("title","description","blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.Difficult, Region.Central_Coast);
        Tour tour2 = new Tour("title","description","blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.Difficult, Region.Central_Coast);

        assertThat(tour1, is(tour2));
        assertThat(tour1.hashCode(), is(tour2.hashCode()));
    }

}