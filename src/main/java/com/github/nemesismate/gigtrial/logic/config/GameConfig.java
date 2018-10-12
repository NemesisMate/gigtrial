package com.github.nemesismate.gigtrial.logic.config;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
public class GameConfig  {

    @Getter @NonNull
    MatchMode matchMode;

    @Getter @NonNull
    OutputMode outputMode;

}
