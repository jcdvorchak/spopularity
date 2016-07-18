
package spopularity.json;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ArtistTopTracks {

    @SerializedName("tracks")
    @Expose
    private List<Track> tracks = new ArrayList<Track>();

    /**
     * 
     * @return
     *     The tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * 
     * @param tracks
     *     The tracks
     */
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

}
