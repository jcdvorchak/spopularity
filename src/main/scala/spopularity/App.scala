package spopularity

import spopularity.json.{PlaylistTracks, TopArtists, TopTracks}
import spopularity.rest.SpotifyRequestHandler
import spopularity.stats.Stats

import scala.collection.mutable
import collection.JavaConversions._


/**
  * Main
  */
object App {
  val stats: Stats = new Stats()
  var spotifyReq: SpotifyRequestHandler = null

  def main(args: Array[String]): Unit = {

    if (args.length != 1) {
      System.out.println("usage: App <userAuthToken>")
      System.exit(1)
    }
    val userAuthToken = args(0)

    spotifyReq = new SpotifyRequestHandler(userAuthToken)

    // request data from the REST API
    val userTopTracks = spotifyReq.getUserTopTracks
    val userTopArtists = spotifyReq.getUserTopArtists
    val topHits = spotifyReq.getTopHitsTracks
    //    topHits.getItems.addAll(spotifyReq.getViralHitsTracks.getItems) // adding this will mess up the rank

    val hipsterness = amIHipster(userTopTracks, userTopArtists, topHits)

    println(hipsterness)
  }

  /**
    * Calculate and return Hipsterness based the users favorite
    * tracks and artists popularity.
    *
    * @return String hipsterness
    */
  def amIHipster(userTopTracks: TopTracks, userTopArtists: TopArtists, topHits: PlaylistTracks): String = {
    // filter rest objects
    val userTrackRankPop, userArtistRankPop: mutable.HashMap[String, (Int, Int)] = new mutable.HashMap[String, (Int, Int)]
    val hitsTrackRank, hitsArtistRank: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]
    var rank = 1
    userTopTracks.getItems.foreach { track =>
      userTrackRankPop.put(track.getName, (rank, track.getPopularity))
      rank += 1
    }
    rank = 1
    userTopArtists.getItems.foreach { artist =>
      userArtistRankPop.put(artist.getName, (rank, artist.getPopularity))
      rank += 1
    }
    rank = 1
    topHits.getItems.foreach { track =>
      hitsTrackRank.put(track.getTrack.getName, rank)
      rank += 1
    }
    rank = 1
    var artistName = ""
    topHits.getItems.foreach { track =>
      track.getTrack.getArtists.foreach { artist =>
        artistName = artist.getName
        if (!hitsArtistRank.containsKey(artistName)) {
          if (artistName.contains("feat.")) {
            artistName = artistName.replace("feat.", "")
          }
          hitsArtistRank.put(artist.getName.trim, rank)
        }
      }
      rank += 1
    }

    //    println(userTrackRankPop + "\n\n")
    //    println(userArtistRankPop + "\n\n")
    //    println(hitsTrackRank + "\n\n")
    //    println(hitsArtistRank + "\n\n")

    // users average track popularity weighted by rank
    val avgTrackPopRank = stats.popularityWeightedMean(userTrackRankPop)
    //    println(avgTrackPopRank)

    // users average artist popularity weighted by rank
    val avgArtistPopRank = stats.popularityWeightedMean(userArtistRankPop)
    //    println(avgArtistPopRank)

    // percentage of user tracks in the hits weighted by rank
    val usersPopularTracks = stats.percentageOfHits(userTrackRankPop, hitsTrackRank)
    //    println(usersPopularTracks)

    // percentage of user artists in the hits weighted by rank
    val userPopularArtists = stats.percentageOfHits(userArtistRankPop, hitsArtistRank)
    //    println(userPopularArtists)

    val totalAvgPop = (avgTrackPopRank + avgArtistPopRank) / 2.0
    val totalPercHits = (usersPopularTracks + userPopularArtists) / 2.0

    println("users track/artist avg popularity: " + totalAvgPop)
    println("percentage of user tracks that are hits: " + totalPercHits)

    "Your hipsterness calulation comes out to %" + (100-totalAvgPop.asInstanceOf[Int]) + " hipster."
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

    val avgPop = stats.basicPopularity(topArtists, topTracks)

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
