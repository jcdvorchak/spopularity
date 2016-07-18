package spopularity.rest

import org.springframework.http.{HttpEntity, HttpHeaders, HttpMethod}
import org.springframework.web.client.RestTemplate
import spopularity.json.{PlaylistTracks, TopArtists, TopTracks}

/**
  * Invokes Spotify REST API.
  *
  * Multiple instances would be needed for a multiple users.
  * However in the scope of this project that *should* never happen.
  *
  * @param userAuthToken user authorization token
  */
class SpotifyRequestHandler(userAuthToken: String) {
  val restTemplate = new RestTemplate
  val headers = new HttpHeaders
  headers.set("Authorization", "Authorization: Bearer " + userAuthToken)
  val headerEntity = new HttpEntity[String]("parameters", headers)

  def getUserTopTracks: TopTracks = {
    restTemplate.exchange("https://api.spotify.com/v1/me/top/tracks",
      HttpMethod.GET,
      headerEntity,
      classOf[TopTracks]).getBody
  }

  def getUserTopArtists: TopArtists = {
    restTemplate.exchange("https://api.spotify.com/v1/me/top/artists",
      HttpMethod.GET,
      headerEntity,
      classOf[TopArtists]).getBody
  }

  def getTopHitsTracks: PlaylistTracks = {
    restTemplate.exchange("https://api.spotify.com/v1/users/spotify/playlists/5FJXhjdILmRA2z5bvz4nzf/tracks",
      HttpMethod.GET,
      headerEntity,
      classOf[PlaylistTracks]).getBody
  }

  def getViralHitsTracks: PlaylistTracks = {
    restTemplate.exchange("https://api.spotify.com/v1/users/spotify/playlists/2qTeRwnwFquJUKrAFWnolb/tracks",
      HttpMethod.GET,
      headerEntity,
      classOf[PlaylistTracks]).getBody
  }

}
