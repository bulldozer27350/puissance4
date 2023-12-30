package com.moe.puissance4Server.api;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
import com.moe.puissance4Server.internal.api_impl.multi.MultiGameServicesManagement;
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
	MultiGameServicesManagement multiGame;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@PostMapping("/initialize")
	@ApiOperation("Initialise une nouvelle partie de Puissance 4")
	public Placement initializeGame(@RequestBody Map<String, String> requestBody) {
		System.out.println("initializeGame");
		System.out.println(requestBody);
		
		int idPartie;
		if (requestBody.containsKey("idPartie")) {
			idPartie = Integer.parseInt(requestBody.get("idPartie"));
		} else {
			 idPartie = multiGame.createGame();
		}
		if (requestBody.containsKey("nomJoueur1") && requestBody.containsKey("nomJoueur2")) {
			String nomJoueur1 = requestBody.get("nomJoueur1");
		    String nomJoueur2 = requestBody.get("nomJoueur2");
			GameService game = multiGame.retrieveGame(idPartie);
			Placement placement = adapter.convert(game.initializeGame(nomJoueur1, nomJoueur2, 7, 6));
			placement.setPartieId(idPartie);
			System.out.println("retour : " + placement);
			return placement;
		}
		if (requestBody.containsKey("nomJoueur1")) {
			String nomJoueur1 = requestBody.get("nomJoueur1");
			GameService game = multiGame.retrieveGame(idPartie);
			Placement placement = adapter.convert(game.initializeGame(nomJoueur1, 7, 6));
			placement.setPartieId(idPartie);
			System.out.println("retour : " + placement);
			return placement;
		}
		GameService game = multiGame.retrieveGame(idPartie);
		Placement placement = adapter.convert(game.initializeGame(7, 6));
		placement.setPartieId(idPartie);
		System.out.println("retour : " + placement);
		return placement;
	}
	
	@PostMapping("/joinGame")
	@ApiOperation("Initialise une nouvelle partie de Puissance 4")
	public Placement joinGame(@RequestBody Map<String, String> requestBody) {
		System.out.println("joinGame");
		System.out.println(requestBody);
		int idPartie = Integer.parseInt(requestBody.get("idPartie"));
		String nomJoueur = requestBody.get("nomJoueur");
		
		GameService game = multiGame.retrieveGame(idPartie);
		Placement placement = adapter.convert(game.setJoueur2(nomJoueur));
		placement.setPartieId(idPartie);
		this.messagingTemplate.convertAndSend("/topic/game/join/" + idPartie, placement);
		System.out.println("retour : " + placement);
		return placement;
	}
	
	@PostMapping("/createGame")
	@ApiOperation("Créé une nouvelle partie de Puissance 4")
	public Placement createGame(@RequestBody Map<String, String> requestBody) {
		System.out.println("createGame");
		System.out.println(requestBody);
		int idPartie = Integer.parseInt(requestBody.get("idPartie"));
		String nomJoueur = requestBody.get("nomJoueur");
		GameService game = multiGame.retrieveGame(idPartie);
		Placement placement = adapter.convert(game.setJoueur1(nomJoueur));
		placement.setPartieId(idPartie);
		System.out.println("retour : " + placement);
		return placement;
	}

	@PostMapping("/play")
	@ApiOperation("Effectue un coup dans la colonne spécifiée. Le retour dépend de l'état du jeu : 1 : la partie est terminée, 2 : le numéro de colonne est invalide, 3 : la colonne est pleine, 4 : victoire du dernier joueur, 5 : égalité, 6 : le jeu peut continuer")
	public Placement playPiece(@RequestBody Map<String, String> requestBody) {
		System.out.println("playPiece");
		System.out.println(requestBody);
		int idPartie = Integer.parseInt(requestBody.get("idPartie"));
		int column = Integer.parseInt(requestBody.get("column"));
		GameService game = multiGame.retrieveGame(idPartie);
		InternalPlacement playPiece = game.playPiece(column);
		Placement convertedPlacement = adapter.convert(playPiece);
		convertedPlacement.setPartieId(idPartie);
		// Envoyer le placement à tous les clients connectés
	    this.messagingTemplate.convertAndSend("/topic/game/" + idPartie, convertedPlacement);
	    System.out.println("retour : " + convertedPlacement);
		return convertedPlacement;
	}

	// Nouvelle méthode pour vérifier l'état de la partie
	@GetMapping("/partieId")
	@ApiOperation("Réserve un numéro de partie")
	public int getFreePartieId() {
		return multiGame.createGame();
	}
	
	@GetMapping("/ended")
	@ApiOperation("Vérifie si la partie est terminée")
	public boolean isGameEnded(@RequestParam int idPartie) {
		GameService gameService = multiGame.retrieveGame(idPartie);
		return gameService.isGameEnded();
	}

	// Nouvelle méthode pour obtenir le gagnant
	@GetMapping("/winner")
	@ApiOperation("Obtient le gagnant de la partie (ou null si la partie n'est pas terminée)")
	public Joueur getWinner(@RequestParam int idPartie) {
		GameService gameService = multiGame.retrieveGame(idPartie);
		Optional<InternalJoueur> optionnalWinner = gameService.getWinner();
		if (optionnalWinner.isPresent()) {
			return adapter.convert(optionnalWinner.get());
		}
		return null;
	}
}
