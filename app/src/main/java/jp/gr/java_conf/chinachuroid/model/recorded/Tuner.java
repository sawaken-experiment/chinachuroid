package jp.gr.java_conf.chinachuroid.model.recorded;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Tuner {

    @Expose
    private String name;
    @Expose
    private Boolean isScrambling;
    @Expose
    private List<String> types = new ArrayList<String>();
    @Expose
    private String command;
    @Expose
    private Long n;

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
     * @return The isScrambling
     */
    public Boolean getIsScrambling() {
        return isScrambling;
    }

    /**
     * @param isScrambling The isScrambling
     */
    public void setIsScrambling(Boolean isScrambling) {
        this.isScrambling = isScrambling;
    }

    /**
     * @return The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
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

}
