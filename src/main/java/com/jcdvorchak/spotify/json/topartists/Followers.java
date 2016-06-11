
package com.jcdvorchak.spotify.json.topartists;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Followers {

    @SerializedName("href")
    @Expose
    private Object href;
    @SerializedName("total")
    @Expose
    private Object total;

    /**
     * 
     * @return
     *     The href
     */
    public Object getHref() {
        return href;
    }

    /**
     * 
     * @param href
     *     The href
     */
    public void setHref(Object href) {
        this.href = href;
    }

    /**
     * 
     * @return
     *     The total
     */
    public Object getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Object total) {
        this.total = total;
    }

}
