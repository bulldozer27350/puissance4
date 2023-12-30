<template>
    <div v-if="!init">
        Vous êtes dans la salle d'attente. Le numéro de la partie est : #{{ idPartie }}. <br>
        Dès qu'un joueur vous rejoint dans avec ce numéro, la partie pourra commencer. <br>
        {{joueurLocal}}, vous êtes le joueur 1. <br>
    </div>
    <component :is="content" :idPartie="idPartie" :joueurLocal="joueurLocal"/>
</template>

<script>
import Stomp from 'stompjs';
import MultiJoueurGame from './MultiJoueurGame.vue';
import axios from 'axios';

export default {
    name: 'WaitingRoom',
    data() {
        return {
            init: false,
            content: null,
        }
    },
    props:
    [
        'idPartie',
        'joueurLocal'
    ],
    mounted() {
        const self = this; // Stocker la référence à this
        const stompClient = Stomp.client('ws://localhost:4444/gs-guide-websocket');

        stompClient.connect({}, function (frame) {
            console.log('Connecté au serveur STOMP ' + frame);
            
            // Écoutez les messages STOMP
            stompClient.subscribe(`/topic/game/join/${self.$props.idPartie}`, (placement) => {
                console.log("Un nouveau joueur s'est connecté : " + placement.joueur2);
                self.init = true;
                self.content = MultiJoueurGame;
            });
        });

        axios.post(
          'http://localhost:4444/api/puissance4/createGame',
          { nomJoueur: this.$props.joueurLocal, idPartie: this.$props.idPartie },
          {
            headers: {
                'Content-Type': 'application/json',
            },
          }
        );
    }
}
</script>

<style>

</style>