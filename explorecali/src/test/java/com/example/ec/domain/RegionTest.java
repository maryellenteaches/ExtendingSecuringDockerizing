package com.example.ec.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Mary Ellen Bowman.
 */
public class RegionTest {
    @Test
    public void findByLabel() throws Exception {
        assertThat(Region.Central_Coast, is(Region.findByLabel("Central Coast")));
        assertThat(Region.Northern_California, is(Region.findByLabel("Northern California")));
        assertThat(Region.Southern_California, is(Region.findByLabel("Southern California")));
        assertThat(Region.Varies, is(Region.findByLabel("Varies")));
    }

    @Test
    public void getLabel() throws Exception {
        assertThat(Region.Central_Coast.getLabel(), is("Central Coast"));
        assertThat(Region.Northern_California.getLabel(), is("Northern California"));
        assertThat(Region.Southern_California.getLabel(), is("Southern California"));
        assertThat(Region.Varies.getLabel(), is("Varies"));
    }

}