package com.github.nemesismate.gigtrial.logic.output;

import com.github.nemesismate.gigtrial.logic.match.MatchContext;

import java.io.IOException;

public interface Outputter {

    void out(MatchContext matchContext) throws IOException;

}
