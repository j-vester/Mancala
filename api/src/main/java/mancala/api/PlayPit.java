package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaImpl;

@Path("/play")
public class PlayPit {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialize(
            @Context HttpServletRequest request,
            PitClicked pit) {
        int index = pit.getIndex();
            
        HttpSession session = request.getSession(false);
        MancalaImpl mancala = (MancalaImpl) session.getAttribute("mancala");
        mancala.playPit(index);
        String namePlayer1 = (String) session.getAttribute("player1");
        String namePlayer2 = (String) session.getAttribute("player2");
            
        var output = new Mancala(mancala, namePlayer1, namePlayer2);
        return Response.status(200).entity(output).build();
    }
}
