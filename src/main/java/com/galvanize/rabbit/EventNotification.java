package com.galvanize.rabbit;

import lombok.Builder;
import lombok.Data;
@Data
public class EventNotification {

    private String blueprintID;
    private String currentprocessingphase;
    private String filelocation;
    private String createtime;
    private Long pages;

    @Builder
    public EventNotification(String blueprintID, String currentprocessingphase, String filelocation, String createtime, Long pages) {
        this.blueprintID = blueprintID;
        this.currentprocessingphase = currentprocessingphase;
        this.filelocation = filelocation;
        this.createtime = createtime;
        this.pages = pages;
    }
}
