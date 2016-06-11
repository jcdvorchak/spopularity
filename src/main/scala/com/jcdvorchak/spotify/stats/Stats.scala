package com.jcdvorchak.spotify.stats

import scala.collection.JavaConversions._
import com.jcdvorchak.spotify.json.topartists.TopArtists
import com.jcdvorchak.spotify.json.toptracks.TopTracks

import scala.collection.mutable

/**
  * Created by jcdvorchak on 6/10/2016.
  */
class Stats {

  def averagePopularity(topArtists: TopArtists, topTracks: TopTracks): Double = {
    val artistPop, trackPop: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]()

    topArtists.getItems.foreach { artist =>
      artistPop.put(artist.getName, artist.getPopularity)
    }

    topTracks.getItems.foreach { track =>
      trackPop.put(track.getName, track.getPopularity)
    }


    var totalPop = 0
    artistPop.foreach { pair =>
      totalPop += pair._2
    }
    val avgArtistPop = totalPop / artistPop.size

    totalPop = 0
    trackPop.foreach { pair =>
      totalPop += pair._2
    }
    val avgTrackPop = totalPop / trackPop.size


    println("average artist popularity: " + avgArtistPop)
    println("average track popularity: " + avgTrackPop)
    println("total average: " + (avgArtistPop + avgTrackPop) / 2.0)

    ((avgArtistPop + avgTrackPop) / 2.0)
  }

}
