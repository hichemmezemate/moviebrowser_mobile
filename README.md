# MovieBrowser (mobile)

MovieBrowser est une application mobile Android (Java) destinée à parcourir, rechercher et découvrir des films depuis un appareil mobile. Conçue comme une application légère et pédagogique, elle permet d'explorer des catalogues de films, consulter des fiches détaillées (affiche, synopsis, casting, note) et naviguer parmi les tendances et classements.

## Description du projet
L'objectif principal de MovieBrowser est de fournir une expérience utilisateur simple et rapide pour la découverte de films sur Android, tout en servant de base d'apprentissage pour des bonnes pratiques Android (gestion du réseau asynchrone, architecture modulaire, mise en cache, tests). L'application consomme des données depuis une API de films publique (ex. The Movie Database - TMDB) et expose des écrans pour la recherche, la navigation par catégories et la visualisation détaillée d'un film.

## Données
- Source de données typique : API publique de catalogue de films (ex. TMDB).
- Contenu attendu par film : identifiant, titre, synopsis, date de sortie, genres, note, liste d'acteurs, URL d'affiche/visuel.
- Volume attendu : la base d'une API publique contient des milliers à des dizaines de milliers de titres — l'application est pensée pour charger dynamiquement les pages/résultats et mettre en cache localement les ressources critiques (affiches, données récentes).

## Fonctionnalités principales
- Recherche de films par titre (saisie libre, suggestions instantanées).
- Listes par catégories : Populaires, Top-rated, Nouveautés, Recommandés.
- Fiche détaillée d'un film : affiche, synopsis, date, genres, note, casting et bande-annonce (si disponible).
- Pagination / chargement infini pour listes de films.
- Chargement paresseux (lazy loading) et mise en cache des images.
- Gestion des états UI : chargement, vide, erreur réseau.
- Mode d'affichage : grille et liste.
- Possibilité d'ajouter un film aux Favoris (stockage local).
- Architecture permettant d'ajouter facilement : watchlist, authentification, synchronisation cloud.

## Classement et pertinence
- Résultats de recherche présentés par pertinence (correspondance titre, popularité, date).
- Tri et filtres : par date de sortie, note, popularité, genre.
- Possibilité d'améliorer la pertinence en combinant correspondance textuelle et métriques d'engagement (popularité).

## Architecture technique
Frontend (mobile)
- Langage : Java (Android)
- UI : Activities / Fragments (ou architecture basée sur Jetpack Components si adaptée)
- Navigation : Android Navigation Component ou gestion manuelle d'Intents/Fragments
- Image loading : Glide ou Picasso
- Asynchronous networking : Retrofit + OkHttp
- Parsing JSON : Gson ou Moshi
- Persistance locale : Room (ou SharedPreferences pour les préférences simples)
- Patterns recommandés : MVVM / Repository / Use Cases pour testabilité

Backend / API
- Aucune couche serveur dédiée requise si l'application consomme une API publique (ex. TMDB).
- Si vous ajoutez un backend, exposer une API REST pour l'authentification, la gestion des favoris et la synchronisation.

Sécurité
- Ne jamais committer les clés API dans le dépôt.
- Charger la clé API depuis `local.properties`, `gradle.properties` non versionné ou variables d'environnement CI.
- Utiliser HTTPS pour toutes les requêtes réseau.

## Endpoints (exemples avec TMDB)
- Rechercher un film : GET /search/movie?api_key=API_KEY&query={query}
- Films populaires : GET /movie/popular?api_key=API_KEY&page={page}
- Détails d'un film : GET /movie/{movie_id}?api_key=API_KEY&append_to_response=credits,videos

(Remplacez par les endpoints effectifs si vous utilisez une autre API.)

## Installation et exécution (développeur)
1. Cloner le dépôt :
   git clone https://github.com/hichemmezemate/moviebrowser_mobile.git
2. Ouvrir le projet dans Android Studio.
3. Synchroniser Gradle et installer les SDK demandés.
4. Configurer la clé API (exemple `local.properties`) :
   TMDB_API_KEY=Votre_Cle_API_Ici
   - Le code doit récupérer la clé via BuildConfig, gradle.properties ou une utilitaire sécurisé.
5. Lancer l'application sur un émulateur ou appareil Android :
   - Depuis Android Studio : Run → Run 'app'
   - En ligne de commande si Gradle wrapper présent :
     ./gradlew assembleDebug
     ./gradlew installDebug

## Structure de projet proposée
- app/src/main/java/... : code Java (Activities, Fragments, ViewModels, Repositories)
- app/src/main/res : layouts, drawables, ressources
- app/src/main/assets ou docs/screenshots : captures d'écran et ressources de documentation
- buildSrc / gradle : configuration build et injection de variables (API key non committée)

## Tests
- Tests unitaires : logique métier, parsing JSON, repositories.
- Tests d'instrumentation : scénarios UI critiques (recherche, affichage fiche).
- Recommandation : CI exécute ./gradlew test et ./gradlew connectedAndroidTest si applicable.

## Bonnes pratiques
- Séparer la couche réseau, la couche données et la couche UI.
- Traiter proprement les erreurs réseau et afficher des messages utilisateurs clairs.
- Mettre en place un cache pour les images et données fréquemment consultées.
- Documenter les endpoints et la structure des réponses attendues.
- Respecter les quotas API de la source utilisée (backoff, caching).

## Idées d'évolution
- Authentification et profils utilisateurs.
- Synchronisation des favoris sur un backend.
- Recommandations basées sur l'historique et préférences.
- Support hors-ligne avancé et préchargement.
- Internationalisation (i18n) et thèmes (sombre/clair).
- Intégration de trailers via YouTube et partages sociaux.

