package com.example.ec.web;

import com.example.ec.domain.Tour;
import com.example.ec.domain.TourRating;
import com.example.ec.service.TourRatingService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 *
 * Invoke the Controller methods via HTTP.
 * Do not invoke the tourRatingService methods, use Mock instead
 * Created by Mary Ellen Bowman.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TourRatingControllerTest {

    //These Tour and rating id's do not already exist in the db
    private static final int TOUR_ID = 999;
    private static final int CUSTOMER_ID = 1000;
    private static final int SCORE = 3;
    private static final String COMMENT = "comment";
    private static final String TOUR_RATINGS_URL = "/tours/" + TOUR_ID + "/ratings";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TourRatingService serviceMock;

    @Mock
    private TourRating tourRatingMock;

    @Mock
    private Tour tourMock;

    private RatingDto ratingDto = new RatingDto(SCORE, COMMENT,CUSTOMER_ID);

    @Before
    public void setupReturnValuesOfMockMethods() {
        when(tourRatingMock.getComment()).thenReturn(COMMENT);
        when(tourRatingMock.getScore()).thenReturn(SCORE);
        when(tourRatingMock.getCustomerId()).thenReturn(CUSTOMER_ID);
        when(tourRatingMock.getTour()).thenReturn(tourMock);
        when(tourMock.getId()).thenReturn(TOUR_ID);
    }

    /**
     *  HTTP POST /tours/{tourId}/ratings
     */
    @Test
    public void createTourRating() throws Exception {
        restTemplate.postForEntity(TOUR_RATINGS_URL, ratingDto, Void.class);

        verify(this.serviceMock).createNew(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT);
    }

    /**
     *  HTTP DELETE /tours/{tourId}/ratings
     */
    @Test
    public void delete() throws Exception {
        restTemplate.delete(TOUR_RATINGS_URL + "/" + CUSTOMER_ID);

        verify(serviceMock).delete(TOUR_ID, CUSTOMER_ID);
    }

    /**
     *  HTTP POST /tours/{tourId}/ratings/{score}?customers={ids..}
     */
    @Test
    public void createManyTourRatings() throws Exception {
        restTemplate.postForEntity(TOUR_RATINGS_URL + "/" + SCORE + "?customers=" + CUSTOMER_ID, ratingDto, Void.class);
        verify(serviceMock).rateMany(TOUR_ID, SCORE, new Integer[] {CUSTOMER_ID});
    }

    /**
     *  HTTP GET /tours/{tourId}/ratings
     */
    @Test
    public void getAllRatingsForTour() throws Exception {
        List<TourRating> listOfTourRatings = Arrays.asList(tourRatingMock);
        Page<TourRating> page = new PageImpl(listOfTourRatings, PageRequest.of(0,10),1);
        when(serviceMock.lookupRatings(anyInt(),any(Pageable.class))).thenReturn(page);

        ResponseEntity<String> response = restTemplate.getForEntity(TOUR_RATINGS_URL,String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        verify(serviceMock).lookupRatings(anyInt(), any(Pageable.class));
    }

    /**
     *  HTTP GET /tours/{tourId}/ratings/average
     */
    @Test
    public void getAverage() throws Exception {
        when(serviceMock.getAverageScore(TOUR_ID)).thenReturn(3.2);

        ResponseEntity<String> response = restTemplate.getForEntity(TOUR_RATINGS_URL + "/average", String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("{\"average\":3.2}"));
    }

    /**
     *  HTTP PUT /tours/{tourId}/ratings
     */
    @Test
    public void updateWithPut() throws Exception {
        when(serviceMock.update(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT)).thenReturn(tourRatingMock);

        restTemplate.put(TOUR_RATINGS_URL, ratingDto);

        verify(serviceMock).update(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT);
    }

    /**
     *  HTTP PATCH /tours/{tourId}/ratings
     */

    /**
     *  RestTemplate Patch only works if it uses httpclient. Method will only work if:
     *  1. Include dependency
     *      <dependency>
     *            <groupId>org.apache.httpcomponents</groupId>
     *           <artifactId>httpclient</artifactId>
     *           <version>4.4.1</version>
     *       </dependency>
     *  2. Attach httpclient
     *      restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
     */
    @Test
    @Ignore
    public  void updateWithPatch() {

        when(serviceMock.updateSome(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT)).thenReturn(tourRatingMock);

        restTemplate.patchForObject(TOUR_RATINGS_URL, ratingDto, RatingDto.class);

        verify(serviceMock).updateSome(TOUR_ID, CUSTOMER_ID, SCORE, COMMENT);

    }

}