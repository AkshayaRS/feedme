package com.consumer.feedme.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class OutcomeBody {

    private String marketId;
    private String outcomeId;
    private String name;
    private String price;
    private boolean displayed;
    private boolean suspended;
}
