
package com.jcdvorchak.spotify.json.playlisttracks;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ExternalUrls___ {

    @SerializedName("spotify")
    @Expose
    private String spotify;

    /**
     * 
     * @return
     *     The spotify
     */
    public String getSpotify() {
        return spotify;
    }

    /**
     * 
     * @param spotify
     *     The spotify
     */
    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

}
