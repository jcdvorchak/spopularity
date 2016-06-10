package com.jcdvorchak.spotify

import scala.collection.JavaConversions._
import java.io.FileReader

import com.google.gson.Gson
import com.jcdvorchak.spotify.json.artists.{Item, TopArtists}
import com.jcdvorchak.spotify.json.tracks.{Item, TopTracks}
import com.jcdvorchak.spotify.stats.Stats

/**
 * Hello world!
 *
 */
object App {
  val stats: Stats = new Stats()

  def main(args: Array[String]): Unit ={
    // initialize Gson
    val gson = new Gson

    // read in top artists and tracks
    val topArtists = gson.fromJson[TopArtists](
      new FileReader("C:\\Users\\Admin\\Documents\\GitHub\\spotify-analysis\\src\\main\\resources\\topArtists.json"),
      classOf[TopArtists])
    val topTracks = gson.fromJson[TopTracks](
      new FileReader("C:\\Users\\Admin\\Documents\\GitHub\\spotify-analysis\\src\\main\\resources\\topTracks.json"),
      classOf[TopTracks])

    //    topArtists.getItems.foreach { artist =>
    //      println(artist.getName)
    //    }
    //
    //    topTracks.getItems.foreach { track =>
    //      println(track.getName)
    //    }

    val stats: Stats = new Stats()

    val hipsterStatus = amIHipster(topArtists,topTracks)
    println("Am I a hipster?\n" + hipsterStatus)
  }

  def amIHipster(topArtists: TopArtists, topTracks: TopTracks): String = {
    val avgPop = stats.averagePopularity(topArtists,topTracks)

    var hipsterness: String = new String()
    if (avgPop >= 90.0) {
      hipsterness = "LOL no"
    } else if (avgPop >= 80.0) {
      hipsterness = "LOL no"
    }  else if (avgPop >= 70.0) {
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
