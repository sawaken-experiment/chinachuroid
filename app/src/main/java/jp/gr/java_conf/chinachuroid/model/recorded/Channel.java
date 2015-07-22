package jp.gr.java_conf.chinachuroid.model.recorded;

import com.google.gson.annotations.Expose;

public class Channel {

    @Expose
    private Long n;
    @Expose
    private String type;
    @Expose
    private String channel;
    @Expose
    private String name;
    @Expose
    private String id;
    @Expose
    private String sid;

    /**
     * @return The n
     */
    public Long getN() {
        return n;
    }

    /**
     * @param n The n
     */
    public void setN(Long n) {
        this.n = n;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel The channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid The sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

}
