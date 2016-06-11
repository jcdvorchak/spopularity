package com.jcdvorchak.spotify

import com.jcdvorchak.spotify.json.topartists.TopArtists
import com.jcdvorchak.spotify.json.toptracks.TopTracks
import com.jcdvorchak.spotify.rest.SpotifyRequestHandler
import com.jcdvorchak.spotify.stats.Stats

import scala.collection.JavaConversions._

import scala.collection.mutable

// TODO based on country hitting from
// TODO dynamically get the playlist url incase user or name changes

/**
  * Main
  */
object App {
  val stats: Stats = new Stats()

  def main(args: Array[String]): Unit = {

    if (args.length != 1) {
      System.out.println("usage: App <userAuthToken>")
      System.exit(1)
    }
    val userAuthToken = args(0)

    val spotifyReq = new SpotifyRequestHandler(userAuthToken)

    // request data from the REST API
    val userTopTracks = spotifyReq.getUserTopTracks
    val userTopArtists = spotifyReq.getUserTopArtists
    val topHits = spotifyReq.getTopHitsTracks
    val viralHits = spotifyReq.getViralHitsTracks

    // TODO filter the objects for what I need
    // userArtistRankPop
    // userTrackRankPop
    // topTrackRank
    // topArtistRank
  }

  // calculate the average pop of users top artists and tacks
  def hipsterByPopularity(topArtists: TopArtists, topTracks: TopTracks): String = {

    val artistPop, trackPop, artistRank, trackRank: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]()
    topArtists.getItems.foreach { artist =>
      artistPop.put(artist.getName, artist.getPopularity)
    }
    topTracks.getItems.foreach { track =>
      trackPop.put(track.getName, track.getPopularity)
    }

    val avgPop = stats.averagePopularity(topArtists, topTracks)

    var hipsterness: String = new String()
    if (avgPop >= 90.0) {
      hipsterness = "LOL no"
    } else if (avgPop >= 80.0) {
      hipsterness = "LOL no"
    } else if (avgPop >= 70.0) {
      hipsterness = "LOL no"
    } else if (avgPop >= 60.0) {
      hipsterness = "I mean.. at least you're trying?"
    } else if (avgPop >= 50.0) {
      hipsterness = "meh"
    } else if (avgPop >= 40.0) {
      hipsterness = "sure.."
    } else if (avgPop >= 30.0) {
      hipsterness = "Listening to things before they are cool"
    } else if (avgPop >= 20.0) {
      hipsterness = "This site 2 meta"
    } else if (avgPop >= 10.0) {
      hipsterness = "Why are you even here?"
    } else {
      hipsterness = "You are who you are."
    }

    hipsterness
  }
}
