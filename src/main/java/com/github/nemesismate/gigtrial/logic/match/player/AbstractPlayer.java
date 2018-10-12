package com.github.nemesismate.gigtrial.logic.match.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public abstract class AbstractPlayer implements Player {

    @Getter @NonNull
    final String name;

}
