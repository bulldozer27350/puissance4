<template>
    <h1>Puissance 4</h1>

    <div v-if="!initialized" id="initialization" class="player-inputs">
      <label for="player1Name">Nom Joueur 1:</label>
      <input v-model="player1Name" id="player1Name" />

      <label for="player2Name">Nom Joueur 2:</label>
      <input v-model="player2Name" id="player2Name" />
      <br/>
      <button @click="initializeGame" :disabled="gameEnded">Commencer une partie</button>
    </div>

    <div v-if="initialized" id="board" class="blue">
      <table class="table">
        <tr v-for="(row, rowIndex) in board" :key="rowIndex">
          <td v-for="(cell, colIndex) in row" 
          :key="colIndex">
            <div
              :id="'cell-' + rowIndex + '-' + colIndex"
              :class="[
                { 'player1': player1Name === cell },
                { 'player2': player2Name === cell },
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
      <p v-if="currentPlayer" :class="{ 'player1': player1Name === currentPlayer.nom, 'player2': player2Name === currentPlayer.nom}">C'est le tour de {{ currentPlayer.nom }}</p>
      <p v-else>Partie non initialisée</p>
    </div>
    <div v-if="gameEnded">
      PARTIE REMPORTEE PAR : {{ currentPlayer.nom }}
    </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      initialized : false,
      player1Name: '',
      player2Name: '',
      currentPlayer: null,
      board: [],
      animation: [],
      winner: null,
      gameEnded: false,
    };
  },
  methods: {
    async initializeGame() {
      try {
        console.log('Initializing game with player names:', this.player1Name, this.player2Name);

        const response = await axios.post(
          'http://localhost:4444/api/puissance4/initialize',
          { nomJoueur1: this.player1Name, nomJoueur2: this.player2Name },
          {
            headers: {
                'Content-Type': 'application/json',
            },
          }
        );
        const placement = response.data;
        if (placement.status === 6) {
          console.log('Game initialized successfully:', placement);
          this.currentPlayer = placement.currentPlayer;
          this.board = Array.from({ length: 6 }, () => Array(7).fill(""));
          this.animation = Array.from({ length: 6 }, () => Array(7).fill(false));
          this.winner = null;
          this.gameEnded = false;
          this.initialized = true;
        } else {
          console.error('Erreur lors de l\'initialisation de la partie', placement);
        }
      } catch (error) {
        console.error('Erreur lors de l\'initialisation de la partie', error);
      }
    },

    async playPiece(columnIndex) {
      if (this.gameEnded) {
        return;
      }
      try {
        let ancienJoueur = this.currentPlayer.nom;
        const response = await axios.post('http://localhost:4444/api/puissance4/play', 
        { column: columnIndex},
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
          this.currentPlayer = placement.currentPlayer;
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
};
</script>

<style scoped>
.player-inputs {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
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