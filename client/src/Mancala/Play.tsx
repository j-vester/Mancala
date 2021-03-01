import React, { useState } from "react";
import type { GameState } from "../gameState";
import {PitButton} from "./PitButton";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {

    const [errorMessage, setErrorMessage] = useState("");

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
                return <PitButton stones={pit.nrOfStones} color="red" onClick={(e)=>tryPlayPit(pit.index, e)}/>
            })}</div>
            <div>{gameState.players[1].pits.map(function(pit){
                return <PitButton stones={pit.nrOfStones} color="blue" onClick={(e)=>tryPlayPit(pit.index, e)}/>
            })}</div>

            <p className="errorMessage">{errorMessage}</p>
        </div>
    )
}
