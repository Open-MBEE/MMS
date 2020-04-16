package org.openmbee.sdvc.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@JsonIgnoreProperties({"empty", BaseJson.REFID, BaseJson.COMMITID, BaseJson.PROJECTID,
    BaseJson.CREATOR, BaseJson.CREATED, BaseJson.MODIFIER, BaseJson.MODIFIED, BaseJson.NAME})
public class CommitUpdatedJson extends BaseJson<CommitUpdatedJson> {

    public static final String PREVIOUS = "_previousDocId";

    @Schema(accessMode = AccessMode.READ_ONLY)
    @JsonProperty(PREVIOUS)
    public String getPreviousDocId() {
        return (String) this.get(PREVIOUS);
    }

    @JsonProperty(PREVIOUS)
    public CommitUpdatedJson setPreviousDocId(String previousDocId) {
        this.put(PREVIOUS, previousDocId);
        return this;
    }
}