package spopularity

import java.io.FileReader

import com.google.gson.Gson
import org.junit._
import spopularity.json.{TopArtists, TopTracks}

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
      new FileReader("C:\\Users\\Admin\\Documents\\GitHub\\spopularity\\src\\test\\resources\\topArtists.json"),
      classOf[TopArtists])
    userTopTracks = gson.fromJson[TopTracks](
      new FileReader("C:\\Users\\Admin\\Documents\\GitHub\\spopularity\\src\\test\\resources\\topTracks.json"),
      classOf[TopTracks])
  }

//  @Test
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


