package com.github.nemesismate.gigtrial.logic.output;

import com.github.nemesismate.gigtrial.logic.match.MatchContext;
import com.github.nemesismate.gigtrial.logic.match.player.Player;
import lombok.NonNull;

import java.io.IOException;
import java.io.Writer;

public class WriterOutputter implements Outputter {

    @NonNull
    private final Writer writer;

    public WriterOutputter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void out(MatchContext matchContext) throws IOException {
        writer.append("Match result: ").append("\n");

        Player player1 = matchContext.getFirstPlayer();
        Player player2 = matchContext.getSecondPlayer();

        int i = 0;
        for(MatchContext.RoundData roundData : matchContext.getFinishedRounds()) {
            writer.append("Round ").append(String.valueOf(i++)).append(": ")
                    .append(roundData.getFirstPlayerChoice().toString())
                    .append(" vs ")
                    .append(roundData.getSecondPlayerChoice().toString())
                    .append(" - Winner: ")
                    .append(roundData.getWinner().getName())
                    .append("\n");
        }

        writer.append("Wins ").append(player1.getName()).append(": ").append(String.valueOf(matchContext.getTotalWinsFirstPlayer()))
                .append(", Wins ").append(player2.getName()).append(": ").append(String.valueOf(matchContext.getTotalWinsSecondPlayer()))
                .append(", Draws: ").append(String.valueOf(matchContext.getTotalDraws()));

        writer.append("\n").flush();
    }
}
