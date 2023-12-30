<template>
    <div id="root" class="root">
        <div>
            Partie #{{ idPartie }} en cours. Les joueurs connectés sont : {{ joueur1 }} et {{ joueur2 }}. Bienvenue à vous ! <br>
            Votre pseudo est : {{ joueurLocal }}
        </div>
        <div id="board" class="blue">
            <table class="table">
                <tr v-for="(row, rowIndex) in board" :key="rowIndex">
                    <td v-for="(cell, colIndex) in row" 
                    :key="colIndex">
                    <div
                        :id="'cell-' + rowIndex + '-' + colIndex"
                        :class="[
                            { 'player1': joueur1 === cell },
                            { 'player2': joueur2 === cell },
                            animation[rowIndex][colIndex] ? 'dropAnimation' + rowIndex : null
                        ]">
                    </div>
                    <div
                        :id="'emptyCell-' + rowIndex + '-' + colIndex"
                        :class="'playerVide'"  
                        @click="playPiece(colIndex)">
                    </div>
                    </td>
                </tr>
            </table>
        </div>

        <div class="joueurEnCours">
            <p v-if="currentPlayer" :class="{ 'player1': joueur1 === currentPlayer, 'player2': joueur2 === currentPlayer}">C'est le tour de {{ currentPlayer }}</p>
            <p v-else>Partie non initialisée</p>
        </div>

        <div v-if="gameEnded">
            PARTIE REMPORTEE PAR : {{ currentPlayer }}
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import Stomp from 'stompjs';

export default {
    name: 'MultiJoueurGame',
    props: ['idPartie', 'joueurLocal'],
    data() {
        return {
            joueur1: null,
            joueur2: null,
            jeuPossible: false,
            currentPlayer: null,
            board: [],
            animation: [],
            winner: null,
            gameEnded: false,
        }
    },
    mounted() {
        const self = this;
        axios.post('http://localhost:4444/api/puissance4/joinGame', {
            idPartie: self.$props.idPartie,
            nomJoueur: self.$props.joueurLocal,
        }).then(function (resp) {
            self.joueur1 = resp.data.joueur1.nom;
            self.joueur2 = resp.data.joueur2.nom;
            axios.post('http://localhost:4444/api/puissance4/initialize',
            { 
                idPartie: self.$props.idPartie,
            },
            {
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then(function(response){
                const placement = response.data;
                if (placement.status === 6) {
                    self.board = Array.from({ length: 6 }, () => Array(7).fill(""));
                    self.animation = Array.from({ length: 6 }, () => Array(7).fill(false));
                    self.winner = null;
                    self.gameEnded = false;
                    self.currentPlayer = placement.currentPlayer.nom;
                } else {
                    console.error('Erreur lors de l\'initialisation de la partie', placement);
                }
            }).catch (function(error) {
                console.error('Erreur lors de l\'initialisation de la partie', error);
            })
        }).catch(function (error) {
            console.log(error);
        });

        const stompClient = Stomp.client('ws://localhost:4444/gs-guide-websocket');
        stompClient.connect({}, function (frame) {
            stompClient.subscribe(`/topic/game/${self.$props.idPartie}`, async (message) => {
                const placement = JSON.parse(message.body);
                try {
                    let ancienJoueur = self.currentPlayer;
                    if (placement.status === 5) { // égalité
                        self.gameEnded = true;
                    } else if (placement.status === 4) { // gagnant
                        self.winner = placement.currentPlayer.nom;
                        self.gameEnded = true;
                    } else {
                        self.currentPlayer = placement.currentPlayer.nom;
                        self.board[placement.y][placement.x] = ancienJoueur;
                        self.animation[placement.y][placement.x] = true;
                        // Animation
                        await new Promise(resolve => {
                            setTimeout(resolve, 100*placement.y);
                        });
                        self.animation[placement.y][placement.x]=false;
                    }
                } catch (error) {
                    console.error('Erreur lors du coup dans la colonne', error);
                }
            });
        });
    },
    methods: {
        async playPiece(columnIndex) {
            this.jeuPossible = (this.currentPlayer === this.joueurLocal);
            if (this.gameEnded) { // On ne peut pas jouer si la partie est terminée
                console.log("Impossible de jouer ce coup ci ! La partie est terminée !");
                return;
            }
            if (!this.jeuPossible) { // Le joueur local ne peut jouer que si c'est son tour
                console.log("Impossible de jouer ce coup ci !");
                console.log("C'est le tour de " + this.currentPlayer + " et vous êtes le joueur " + this.joueurLocal);
                return;
            }
            try {
                let ancienJoueur = this.currentPlayer;
                const response = await axios.post('http://localhost:4444/api/puissance4/play', 
                {
                    column: columnIndex,
                    idPartie: this.idPartie,
                },
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });
                const placement = response.data;
                if (placement.status === 5) { // égalité
                    this.gameEnded = true;
                } else if (placement.status === 4) { // gagnant
                    console.log("On a un gagnant !")
                    this.winner = placement.currentPlayer;
                    this.gameEnded = true;
                } else {
                    this.currentPlayer = placement.currentPlayer.nom;
                    this.board[placement.y][placement.x] = ancienJoueur;
                    this.animation[placement.y][placement.x]=true;

                    // Animation
                    await new Promise(resolve => {
                        setTimeout(resolve, 100*placement.y)
                    });
                    this.animation[placement.y][placement.x]=false;
                }
            } catch (error) {
                console.error('Erreur lors du coup dans la colonne', error);
            }
        },
    },
}
</script>

<style scoped>

.root{
    background-color: black;
    color: white;
    font-size: medium;
}

.joueurEnCours {
    top: 20px;
    position: relative;
}

.player1 {
    width: 65px;
    height: 65px;
    border-radius: 50%; /* Rend la cellule ronde */
    text-align: center;
    position: absolute;
    top: 0;
    left: 0;
    background-color: red; /* Couleur de fond pour le joueur 1 */
    background-image: url("./assets/rouge.png");
    background-size: cover; /* Ajuste la taille pour couvrir l'élément */
    background-position: center; /* Centre l'image */
    z-index: 10;
}

.player2 {
    width: 65px;
    height: 65px;
    border-radius: 50%; /* Rend la cellule ronde */
    text-align: center;
    position: absolute;
    top: 0;
    left: 0;
    background-color: yellow; /* Couleur de fond pour le joueur 2 */
    background-image: url("./assets/jaune.png");
    background-size: cover; /* Ajuste la taille pour couvrir l'élément */
    background-position: center; /* Centre l'image */
    z-index: 10;
}

.playerVide {
    width: 65px;
    height: 65px;
    border-radius: 50%; /* Rend la cellule ronde */
    text-align: center;
    position: absolute;
    top: 0;
    left: 0;
    background-color: white;
    z-index: 5;
}

.blue {
    background-color: blue;
    z-index: 10;
    width: 500px;
}

.table {
    background-color: blue; /* Couleur de fond pour la table */
    width: 500px;
}

.table td {
    text-align: center;
    width: 50px;
    height: 75px;
    position: relative;
}

.table td div {
    width: 100%;
    height: 100%;
}

.dropAnimation0 {
    animation: drop0 0.1s linear infinite;
}
.dropAnimation1 {
    animation: drop1 0.2s linear infinite;
}
.dropAnimation2 {
    animation: drop2 0.3s linear infinite;
}
.dropAnimation3 {
    animation: drop3 0.4s linear infinite;
}
.dropAnimation4 {
    animation: drop4 0.5s linear infinite;
}
.dropAnimation5 {
    animation: drop5 0.6s linear infinite;
}

@keyframes drop0 {
    0% {
        transform: translateY(-100%);
    }
    50% {
        transform: translateY(0);
    }
    60% {
        transform: translateY(-25%);
    }
    75% {
        transform: translateY(0%);
    }
    90% {
        transform: translateY(-15%);
    }
    100% {
        transform: translateY(0%);
    }
}

@keyframes drop1 {
    0% {
        transform: translateY(-200%);
    }
    50% {
        transform: translateY(0);
    }
    60% {
        transform: translateY(-25%);
    }
    75% {
        transform: translateY(0%);
    }
    90% {
        transform: translateY(-15%);
    }
    100% {
        transform: translateY(0%);
    }
}

@keyframes drop2 {
    0% {
        transform: translateY(-300%);
    }
    50% {
        transform: translateY(0);
    }
    60% {
        transform: translateY(-25%);
    }
    75% {
        transform: translateY(0%);
    }
    90% {
        transform: translateY(-15%);
    }
    100% {
        transform: translateY(0%);
    }
}

@keyframes drop3 {
    0% {
        transform: translateY(-400%);
    }
    50% {
        transform: translateY(0);
    }
    60% {
        transform: translateY(-25%);
    }
    75% {
        transform: translateY(0%);
    }
    90% {
        transform: translateY(-15%);
    }
    100% {
        transform: translateY(0%);
    }
}

@keyframes drop4 {
    0% {
        transform: translateY(-500%);
    }
    50% {
        transform: translateY(0);
    }
    60% {
        transform: translateY(-25%);
    }
    75% {
        transform: translateY(0%);
    }
    90% {
        transform: translateY(-15%);
    }
    100% {
        transform: translateY(0%);
    }
}

@keyframes drop5 {
    0% {
        transform: translateY(-600%);
    }
    50% {
        transform: translateY(0);
    }
    60% {
        transform: translateY(-25%);
    }
    75% {
        transform: translateY(0%);
    }
    90% {
        transform: translateY(-15%);
    }
    100% {
        transform: translateY(0%);
    }
}
</style>