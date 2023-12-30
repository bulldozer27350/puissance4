Projet Puissance 4
découpé en 5 projets

- Puissance4Back
    offre le métier pour les calculs permettant de gérer la position des jetons, ainsi que les 2 joueurs actifs de la partie.
- Puissance4Server
    offre l'API REST (GameController), openAPI, ainsi qu'une websocket permettant le temps réel. (serveur sous localhost:4444 spécifié dans application.properties)
- Puissance4ApiClient
    offre la consommation de l'openAPI de Puissance4Server pour construire les objets pour un client java.
- Puissance4FrontJava
    offre une interface graphique permettant de jouer au puissance 4 (pas encore développée)
- Puissance4VueJs
    offre une interface web en Vue 3. Possibilité de
    - jouer à deux joueurs sur le même ordinateur (mode single client),
    - de créer une partie (obtention d'un identifiant de la partie)
    - de rejoindre une partie via son identifiant.
  => mettre à jour les @IP dans le fichier configuration/url.js pour spécifier où se trouve le serveur
  => lancement de l'application via la commande npm run dev -- --host --port 1444
  => accessible depuis le localhost:1444 (ou via l'adresse donnée par vite lors du démarrage)

Ordre de compilation : 
1. Puissance4Back
2. Puissance4Server
3. Puissance4ApiClient
4. Puissance4FrontJava
