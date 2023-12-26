package com.moe.puissance4Server.api;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moe.puissance4Server.api.model.Joueur;
import com.moe.puissance4Server.api.model.Placement;
import com.moe.puissance4Server.internal.api_impl.ModelAdapter;
import com.moe.puissance4back.api.GameService;
import com.moe.puissance4back.core.InternalJoueur;
import com.moe.puissance4back.core.model.InternalPlacement;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/puissance4")
@Api(tags = "Puissance 4 API")
@CrossOrigin(origins = "*")
public class GameController {

	@Autowired
	ModelAdapter adapter;

	@Autowired
	private GameService gameService;

	@PostMapping("/initialize")
	@ApiOperation("Initialise une nouvelle partie de Puissance 4")
	public Placement initializeGame(@RequestBody Map<String, String> requestBody) {
		String nomJoueur1 = requestBody.get("nomJoueur1");
	    String nomJoueur2 = requestBody.get("nomJoueur2");
		return adapter.convert(gameService.initializeGame(nomJoueur1, nomJoueur2, 7, 6));
	}

	@PostMapping("/play")
	@ApiOperation("Effectue un coup dans la colonne spécifiée. Le retour dépend de l'état du jeu : 1 : la partie est terminée, 2 : le numéro de colonne est invalide, 3 : la colonne est pleine, 4 : victoire du dernier joueur, 5 : égalité, 6 : le jeu peut continuer")
	public Placement playPiece(@RequestBody Map<String, String> requestBody) {
		int column = Integer.parseInt(requestBody.get("column"));
		InternalPlacement playPiece = gameService.playPiece(column);
		return adapter.convert(playPiece);
	}

	// Nouvelle méthode pour vérifier l'état de la partie
	@GetMapping("/ended")
	@ApiOperation("Vérifie si la partie est terminée")
	public boolean isGameEnded() {
		return gameService.isGameEnded();
	}

	// Nouvelle méthode pour obtenir le gagnant
	@GetMapping("/winner")
	@ApiOperation("Obtient le gagnant de la partie (ou null si la partie n'est pas terminée)")
	public Joueur getWinner() {
		Optional<InternalJoueur> optionnalWinner = gameService.getWinner();
		if (optionnalWinner.isPresent()) {
			return adapter.convert(optionnalWinner.get());
		}
		return null;
	}
}
