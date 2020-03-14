package com.getflix.getflixproject.model;

import java.sql.Date;

public interface RentNameDetails {
     int getId();
     String getUserName() ;
     int getUserId();
     String getMovieName() ;
     int getMovieId();
     Date getStartDate();
     Date getEndDate();
     Boolean getIsReturned();
}
