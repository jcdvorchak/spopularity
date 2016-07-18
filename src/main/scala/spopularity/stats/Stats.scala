package com.jcdvorchak.spotify.stats

import scala.collection.JavaConversions._
import com.jcdvorchak.spotify.json.topartists.TopArtists
import com.jcdvorchak.spotify.json.toptracks.TopTracks

import scala.collection.mutable

/**
  * Calculate values on spotify data
  */
class Stats {

  /**
    * Calculate the popularity mean weighted by it's rank
    *
    * @param rankPop HashMap(name,(rank,popularity))
    * @return Double weightedMean
    */
  def popularityWeightedMean(rankPop: mutable.HashMap[String, (Int, Int)]): Double = {
    val size = rankPop.size.asInstanceOf[Double]
    var total, div, weight: Double = 0.0

    var rank, pop: Int = 0
    rankPop.foreach { curr =>
      //      println(curr._1)
      //      println(curr._2)
      rank = curr._2._1
      pop = curr._2._2

      // calculate the weight based on the rank
      // i.e. rank 5 for 20 ranks ==> (20-(5-1)/20) = .8
      weight = (size - (rank - 1)) / size
      // add the pop as many times as the weight
      total += pop * (weight * size)
      // add however many times you added the pop to divide by later
      div += weight * size
    }

    total / div
  }

  // calc percentage of x in y.
  // weight them on the rank of both
  def percentageOfHits(userRankPop: mutable.HashMap[String, (Int, Int)], hitRankMap: mutable.HashMap[String, Int]): Double = {

    var total = 0
    userRankPop.foreach { curr =>
      if (hitRankMap.contains(curr._1)) {
        total += 1
      }
    }

    total / userRankPop.size.asInstanceOf[Double]

    // WEIGHTING ATTEMPT ... no bueno
    //    val avgSize = (userRankPop.size+hitRankMap.size)/2.0
    //    var total, div, weight,avgRank: Double = 0.0
    //    var userRank, hitRank: Int = 0
    //
    //    userRankPop.foreach{curr =>
    //      if (hitRankMap.contains(curr._1)) {
    //        userRank = curr._2._1
    //        hitRank = hitRankMap.get(curr._1).get
    //        avgRank = (userRank+hitRank)/2.0
    //
    //        weight = (avgSize - (avgRank - 1)) / avgSize
    //
    //        total += (weight*avgSize)
    //        div += (weight*avgSize)
    //      }
    //    }
  }


  def basicPopularity(topArtists: TopArtists, topTracks: TopTracks): Double = {
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
