package com.jcdvorchak.spotify

import scala.collection.JavaConversions._
import java.io.FileReader

import com.google.gson.Gson
import com.jcdvorchak.spotify.json.topartists.{Item, TopArtists}
import com.jcdvorchak.spotify.json.toptracks.{Item, TopTracks}
import com.jcdvorchak.spotify.stats.Stats
import org.springframework.http.{HttpEntity, HttpHeaders, HttpMethod}
import org.springframework.web.client.RestTemplate

/**
 * Hello world!
 *
 */
object App {
  val stats: Stats = new Stats()

  def main(args: Array[String]): Unit ={
    if (args.length!=1) {
      System.out.println("usage: App <authToken>")
      System.exit(1)
    }
    val authToken = args(0)

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

  def runWithRest(authToken: String): Unit = {
    val restTemplate = new RestTemplate

    val headers = new HttpHeaders
    headers.set("Authorization", "Authorization: Bearer "+authToken);
    val headerEntity = new HttpEntity[String]("parameters", headers);

    val topArtistsResponse = restTemplate.exchange("https://api.spotify.com/v1/me/top/artists", HttpMethod.GET, headerEntity, classOf[TopArtists])
    val topArtists = topArtistsResponse.getBody

    val topTracksResponse = restTemplate.exchange("https://api.spotify.com/v1/me/top/tracks", HttpMethod.GET, headerEntity, classOf[TopTracks])
    val topTracks = topTracksResponse.getBody

    val top40

    val stats: Stats = new Stats()

    val hipsterStatus = amIHipster(topArtists,topTracks)
    println("Am I a hipster?\n" + hipsterStatus)

  }

  def amIHipster(topArtists: TopArtists, topTracks: TopTracks): String = {
    val avgPop = stats.averagePopularity(topArtists,topTracks)

    // TODO percentage of top tracks that are the artists top tracks
    // request for each top tracks artists top tracks
    // key-val pair of artists and the tracks you have
    // req for each artist and compare

    // TODO crossreference with the top overall songs and artists
    // one request for top # playlists

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
