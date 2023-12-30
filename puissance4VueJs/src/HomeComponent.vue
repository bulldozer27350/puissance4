<template>
  <div id="globalRoot" class="root">
      <h1>Puissance 4</h1>

      <div v-if="!initialized" class="playerInputs">
        A quel type de jeu voulez-vous jouer ?
        <div>
          <input type="radio" name="typeJeu" value="local" @click="rejoindre()" id="local"/>
          <label for="local">A deux sur le même terminal</label>
        </div>

        <div>
          <input type="radio" name="typeJeu" value="creation" @click="rejoindre()" id="creation"/>
          <label for="creation">Créer une partie et attendre un joueur</label>
        </div>

        <div>
          <input type="radio" name="typeJeu" value="joindre" @click="rejoindre()" id="joindre"/>
          <label for="joindre">Rejoindre une partie</label>
          <br>
          <label v-if="rejoindreBooleen" for="idPartie">Numéro de partie : </label><input v-if="rejoindreBooleen" v-model="idPartie" />
        </div>

        <div>
          Choisissez votre pseudo : <br />
          <input v-model="nomJoueur1" />
        </div>
        <div v-if="choixNomJoueur2">
          Choisissez le pseudo du second joueur : <br />
          <input v-model="nomJoueur2" />
        </div>

        <button @click="valider()">Valider</button>
        
      </div>
      <component :is="content" :idPartie="idPartie" :joueurLocal="nomJoueur1"/>
    </div>

</template>

<script>
import axios from 'axios';
import LocalGame from './LocalGame.vue';
import WaitingRoom from './WaitingRoom.vue';
import MultiJoueurGame from './MultiJoueurGame.vue'

export default {
  name: 'HomeComponent',
  components: {
    LocalGame,
    WaitingRoom,
    MultiJoueurGame,
  },
  data() {
    return {
        rejoindreBooleen: false,
        content: null,
        idPartie: null,
        nomJoueur1: null,
        nomJoueur2: null,
        choixNomJoueur2: false,
        initialized: false,
    };
  },
  mounted() {
    axios.get('http://localhost:4444/api/puissance4/partieId').then(response => {
      this.idPartie = response.data;
    });
  },
  methods: {
    rejoindre() {
      if (document.getElementById('joindre').checked) {
        this.rejoindreBooleen = true;
        this.choixNomJoueur2 = false;
      }
      if (document.getElementById('creation').checked) {
        this.rejoindreBooleen = false;
        this.choixNomJoueur2 = false;
      }
      if (document.getElementById('local').checked) {
        this.rejoindreBooleen = false;
        this.choixNomJoueur2 = true;
      }
    },
    async valider() {
      this.initialized = true;
      if (document.getElementById('joindre').checked) {
        console.log("joindre a été sélectionné");
        const response = await axios.post(
          'http://localhost:4444/api/puissance4/joinGame',
          { nomJoueur: this.nomJoueur1, idPartie: this.idPartie },
          {
            headers: {
                'Content-Type': 'application/json',
            },
          }
        );
        const placement = response.data;
        this.idPartie = placement.partieId;
        this.content = MultiJoueurGame;
      }
      else if (document.getElementById('creation').checked) {
        console.log("creation a été sélectionné");
        const response = await axios.post(
          'http://localhost:4444/api/puissance4/initialize',
          { nomJoueur1: this.nomJoueur1},
          {
            headers: {
                'Content-Type': 'application/json',
            },
          }
        );
        const placement = response.data;
        console.log(placement.partieId);
        this.idPartie = placement.partieId;
        this.content = WaitingRoom;
      }
      else if (document.getElementById('local').checked) {
        console.log("local a été sélectionné");
        const response = await axios.post(
          'http://localhost:4444/api/puissance4/initialize',
          { nomJoueur1: this.nomJoueur1, nomJoueur2: this.nomJoueur2 },
          {
            headers: {
                'Content-Type': 'application/json',
            },
          }
        );
        const placement = response.data;
        this.idPartie = placement.partieId;
        this.content = LocalGame;
      }
    }
  },
};
</script>

<style scoped>
.root{
    background-color: black;
    color: white;
    font-size: medium;
}

.playerInputs {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

</style>