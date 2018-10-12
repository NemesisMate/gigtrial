package com.github.nemesismate.gigtrial.logic.match;

import com.github.nemesismate.gigtrial.logic.match.player.Player;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public class MatchContext {

    private final int rounds;

    @NonNull
    private final Player firstPlayer;

    @NonNull
    private final Player secondPlayer;


    private final List<RoundData> finishedRounds = new ArrayList<>();

    public void addFinishedRound(RoundData roundData) {
        getFinishedRounds().add(roundData);
    }

    public int getTotalWinsFirstPlayer() {
        return getTotalWins(this::isFirstPlayer);
    }

    public int getTotalWinsSecondPlayer() {
        return getTotalWins(this::isSecondPlayer);
    }

    public int getTotalDraws() {
        return getTotalWins(Player.DRAW::equals);
    }

    public boolean isFirstPlayer(Player player) {
        return player == firstPlayer;
    }

    public boolean isSecondPlayer(Player player) {
        return player == secondPlayer;
    }

    private int getTotalWins(Predicate<Player> playerValidator) {
        return (int) getFinishedRounds().stream()
                .map(RoundData::getWinner)
                .filter(playerValidator)
                .count();
    }


    @Getter
    public class RoundData {

        Player winner;

        Element firstPlayerChoice;
        Element secondPlayerChoice;

        void setChoice(Player player, Element choice) {
            if(isFirstPlayer(player)) {
                setFirstPlayerChoice(choice);
                return;
            }

            if(isSecondPlayer(player)) {
                setSecondPlayerChoice(choice);
                return;
            }

            throw new IllegalStateException();
        }

        void setFirstPlayerChoice(Element choice) {
            if(firstPlayerChoice != null) {
                throw new IllegalStateException();
            }

            firstPlayerChoice = choice;
        }

        void setSecondPlayerChoice(Element choice) {
            if(secondPlayerChoice != null) {
                throw new IllegalStateException();
            }

            secondPlayerChoice = choice;
        }

        public Player getWinner() {
            if(winner == null) {
                Element winnerElement = getFirstPlayerChoice().calculateWinner(getSecondPlayerChoice());

                if(winnerElement == getFirstPlayerChoice()) {
                    winner = firstPlayer;
                } else if(winnerElement == getSecondPlayerChoice()) {
                    winner = secondPlayer;
                } else {
                    winner = Player.DRAW;
                }
            }

            return winner;
        }

    }

}
