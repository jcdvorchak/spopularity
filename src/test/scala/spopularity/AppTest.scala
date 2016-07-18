package com.jcdvorchak.spotify

import java.io.FileReader

import org.junit._
import com.google.gson.Gson
import com.jcdvorchak.spotify.json.topartists.TopArtists
import com.jcdvorchak.spotify.json.toptracks.TopTracks

@Test
class AppTest {
  val gson: Gson = new Gson
  var userTopArtists: TopArtists = null
  var userTopTracks: TopTracks = null

  // TODO move to a prop file so I'm not pushing my token
  val authToken=""

  @Before
  def setup() = {
    // read in top artists and tracks test files
    userTopArtists = gson.fromJson[TopArtists](
      new FileReader("C:\\Users\\Admin\\Documents\\GitHub\\spotify-analysis\\src\\test\\resources\\topArtists.json"),
      classOf[TopArtists])
    userTopTracks = gson.fromJson[TopTracks](
      new FileReader("C:\\Users\\Admin\\Documents\\GitHub\\spotify-analysis\\src\\test\\resources\\topTracks.json"),
      classOf[TopTracks])
  }

  @Test
  def testMain() = {
    val args = new Array[String](1)
    args(0)=authToken

    App.main(args)
  }

  @Test
  def testHipsterByPopularity() = {
    val hipsterStatus = App.hipsterByPopularity(userTopArtists, userTopTracks)
    println("Am I a hipster?\n" + hipsterStatus)
  }

}


