package com.devoxx.connection.model;

import java.io.Serializable;
import java.util.List;

public class TracksApiModel implements Serializable {
	public String content;
	public List<TrackApiModel> tracks;
}
