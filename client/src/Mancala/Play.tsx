import React from "react";
import type { GameState } from "../gameState";
import {PitButton} from "./PitButton";
import "./Play.css";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {
    return (
        <div>
            <p>{gameState.players[0].name} vs {gameState.players[1].name}</p>
            <div>{gameState.players[0].pits.map(function(pit){
                return <PitButton stones={pit.nrOfStones} color="red" onClick={(()=>console.log("Button was clicked"))}/>
            })}</div>
            <div>{gameState.players[1].pits.map(function(pit){
                return <PitButton stones={pit.nrOfStones} color="blue" onClick={(()=>console.log("Button was clicked"))}/>
            })}</div>
        </div>
    )
}
