import React, { Attributes } from "react";
import "./PitButton.css";

type PitProps = {
    stones: number;
    color: string;
    onClick(event: React.MouseEvent): void;
}

export function PitButton({ stones, color, onClick }: PitProps) {
    return (
        <button
            style={{backgroundColor: color, color: "white"}}
            onClick={onClick}
        >
            {stones}
        </button>
    )
}