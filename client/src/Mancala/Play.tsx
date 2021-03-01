import React, { useState } from "react";
import type { GameState } from "../gameState";
import {PitButton} from "./PitButton";
import {ReplayButton} from "./ReplayButton";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {

    const [errorMessage, setErrorMessage] = useState("");
    const [winnerMessage, setWinner] = useState("");

    async function tryPlayPit(pitIndex: number, e: React.MouseEvent) {
        e.preventDefault();
        const playerNr = (pitIndex<7) ? 0 : 1;
        if (!gameState.players[playerNr].hasTurn) {
            setErrorMessage("This pit cannot be played");
            return;
        }
        if (pitIndex==6 || pitIndex==13) {
            setErrorMessage("A Kalaha pit cannot be played");
            return;
        }
        setErrorMessage("");
        
        try {
            const response = await fetch('mancala/api/play', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ index: pitIndex})
            });

            if (response.ok) {
                const gameState = await response.json();
                setGameState(gameState);
                if (gameState.gameStatus.endOfGame){
                    setWinner(gameState.gameStatus.winner+" has won this game!")
                }
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }
    }

    async function tryRestartGame(e: React.MouseEvent) {
        e.preventDefault();
        try {
            const response = await fetch('mancala/api/start', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nameplayer1: gameState.players[0].name, nameplayer2: gameState.players[1].name })
            });
                
            if (response.ok) {
                const gameState = await response.json();
                setGameState(gameState);
                setWinner(""); 
            } else {
                console.error(response.statusText);
            }
        } catch (error) {
            console.error(error.toString());
        }
    }

    return (
        <div>
            <p>{gameState.players[0].name} vs {gameState.players[1].name}</p>
            <div>{gameState.players[0].pits.map(function(pit){
                return <PitButton stones={pit.nrOfStones} color="red" onClick={(e)=>tryPlayPit(pit.index, e)} key={pit.index}/>
            })}</div>
            <div>{gameState.players[1].pits.map(function(pit){
                return <PitButton stones={pit.nrOfStones} color="blue" onClick={(e)=>tryPlayPit(pit.index, e)} key={pit.index}/>
            })}</div>

            <p className="errorMessage">{errorMessage}</p>
            <div className="winner">{winnerMessage}</div>
            {gameState.gameStatus.endOfGame && 
                <div>
                    <ReplayButton onClick={(e)=>tryRestartGame(e)}/>
                </div>
            }
        </div>
    )
}
