package com.github.nemesismate.gigtrial.logic.match;

import com.github.nemesismate.gigtrial.logic.match.player.Player;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Match {

    @NonNull
    private final ExecutorService executorService;

    @NonNull
    private final CompletionService<PlayerResult> completionService;

    @Getter @NonNull
    private final MatchContext matchContext;

    private int rounds;

    public Match(MatchContext matchContext) {
        this.matchContext = matchContext;
        this.rounds = matchContext.getRounds();

        this.executorService = Executors.newFixedThreadPool(2);
        this.completionService = new ExecutorCompletionService<>(executorService);
    }

    public void nextRound() {
        rounds--;

        matchContext.addFinishedRound(new Round().run());

        if(!hasMoreRounds()) {
            executorService.shutdown();
        }
    }

    public boolean hasMoreRounds() {
        return rounds > 0;
    }


    @RequiredArgsConstructor
    private static class PlayerResult {
        @Getter @NonNull
        final Player player;

        @Getter @NonNull
        final Element element;
    }

    private class Round {

        MatchContext.RoundData run() {

            submitPlayerChoiceRequest(matchContext.getFirstPlayer());
            submitPlayerChoiceRequest(matchContext.getSecondPlayer());

            return getAllPlayersResult();
        }

        private void submitPlayerChoiceRequest(Player player) {
            completionService.submit(() -> new PlayerResult(player, player.getNextChoice()));
        }

        private MatchContext.RoundData getAllPlayersResult() {
            MatchContext.RoundData roundData = matchContext.new RoundData();

            populateWithNextPlayerResult(roundData);
            populateWithNextPlayerResult(roundData);

            return roundData;
        }

        private void populateWithNextPlayerResult(MatchContext.RoundData roundData) {
            PlayerResult playerResult = getNextAvailablePlayerResult();
            roundData.setChoice(playerResult.getPlayer(), playerResult.getElement());
        }

        private PlayerResult getNextAvailablePlayerResult() {
            try {
                return completionService.take().get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
