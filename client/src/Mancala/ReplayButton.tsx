import React from "react";
import "./ReplayButton.css";

type ReplayProps = {
    onClick(event: React.MouseEvent): void;
}

export function ReplayButton({onClick}: ReplayProps) {
    return (
        <button
            style={{backgroundColor: "green", color: "white"}}
            onClick={onClick}
        >
            Replay!
        </button>
    )
}