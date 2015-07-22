package jp.gr.java_conf.chinachuroid.model.recorded;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class RecordedItem {

    @Expose
    private String id;
    @Expose
    private Channel channel;
    @Expose
    private String category;
    @Expose
    private String title;
    @Expose
    private String subTitle;
    @Expose
    private String fullTitle;
    @Expose
    private String detail;
    @Expose
    private Long episode;
    @Expose
    private Long start;
    @Expose
    private Long end;
    @Expose
    private Long seconds;
    @Expose
    private List<String> flags = new ArrayList<String>();
    @Expose
    private Boolean isConflict;
    @Expose
    private Boolean isSigTerm;
    @Expose
    private Tuner tuner;
    @Expose
    private String recorded;
    @Expose
    private String command;

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
     * @return The channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @param channel The channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The subTitle
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * @param subTitle The subTitle
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * @return The fullTitle
     */
    public String getFullTitle() {
        return fullTitle;
    }

    /**
     * @param fullTitle The fullTitle
     */
    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    /**
     * @return The detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail The detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return The episode
     */
    public Long getEpisode() {
        return episode;
    }

    /**
     * @param episode The episode
     */
    public void setEpisode(Long episode) {
        this.episode = episode;
    }

    /**
     * @return The start
     */
    public Long getStart() {
        return start;
    }

    /**
     * @param start The start
     */
    public void setStart(Long start) {
        this.start = start;
    }

    /**
     * @return The end
     */
    public Long getEnd() {
        return end;
    }

    /**
     * @param end The end
     */
    public void setEnd(Long end) {
        this.end = end;
    }

    /**
     * @return The seconds
     */
    public Long getSeconds() {
        return seconds;
    }

    /**
     * @param seconds The seconds
     */
    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    /**
     * @return The flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * @param flags The flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    /**
     * @return The isConflict
     */
    public Boolean getIsConflict() {
        return isConflict;
    }

    /**
     * @param isConflict The isConflict
     */
    public void setIsConflict(Boolean isConflict) {
        this.isConflict = isConflict;
    }

    /**
     * @return The isSigTerm
     */
    public Boolean getIsSigTerm() {
        return isSigTerm;
    }

    /**
     * @param isSigTerm The isSigTerm
     */
    public void setIsSigTerm(Boolean isSigTerm) {
        this.isSigTerm = isSigTerm;
    }

    /**
     * @return The tuner
     */
    public Tuner getTuner() {
        return tuner;
    }

    /**
     * @param tuner The tuner
     */
    public void setTuner(Tuner tuner) {
        this.tuner = tuner;
    }

    /**
     * @return The recorded
     */
    public String getRecorded() {
        return recorded;
    }

    /**
     * @param recorded The recorded
     */
    public void setRecorded(String recorded) {
        this.recorded = recorded;
    }

    /**
     * @return The command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command The command
     */
    public void setCommand(String command) {
        this.command = command;
    }

}
