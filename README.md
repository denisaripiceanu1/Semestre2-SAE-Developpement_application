# Semestre 2 - SAE – Développement d’une application avec une IHM

Note obtenue : 18,75

D’après le référentiel de formation du BUT informatique, cette SAE répond à la problématique suivante :
« En partant d’un besoin exprimé par un client et nécessitant une interface graphique, l’objectif est de formaliser les besoins, proposer une conception, implémenter et tester son développement. »

Notre mission était de développer une application en langage Java visant à reproduire une partie du site https://www.millevarietesanciennes.org/, qui s'occupe de la vente de graines de tomates. Pour ce faire, nous avons suivi un processus méthodique qui nous a conduit à concevoir à la fois le modèle métier et l'interface graphique de l'application.

Dans un premier temps, nous avons créé le modèle métier, que nous avons soigneusement organisé dans un paquetage dédié que nous avons nommé "modèle". Cette étape de conception a impliqué la création de diagrammes de classes détaillés, permettant de représenter de manière exhaustive la structure du modèle et de ses composants.

En parallèle, nous avons élaboré l'interface utilisateur (IHM) de l'application, en nous appuyant sur les enseignements de la ressource R2.02 - Développement d'applications avec IHM. L'IHM a été développée en utilisant l'outil WindowBuilder intégré à Eclipse, ce qui nous a permis de créer une interface graphique conviviale et fonctionnelle.

Les fonctionnalités que nous avons implémentées étaient variées et ont répondu aux besoins spécifiques du projet. Pour la visualisation des tomates, nous avons mis en place un système d'affichage de la liste complète des tomates, avec la possibilité de les trier par type et par couleur. Les utilisateurs pouvaient également effectuer des affichages combinés par type de tomates et couleur, ainsi que consulter les détails d'une tomate lors de sa sélection dans la liste. De plus, nous avons permis la sélection d'une quantité de tomates commandées, à condition qu'elles ne soient pas en rupture de stock.

La partie gestion du panier était essentielle pour notre application. Nous avons créé un panier vide au lancement de l'application et avons implémenté la fonctionnalité d'ajout d'une ligne de commande à chaque nouvelle commande de tomates. Il était crucial de s'assurer que chaque variété de tomates n'apparaissait qu'une seule fois dans le panier, et nous avons également intégré une fonction de réinitialisation du panier pour la convivialité de l'utilisateur.

Enfin, la validation du panier a été gérée en permettant aux utilisateurs de saisir leurs informations client, de choisir leur mode de paiement et d'adhérer à une newsletter. Nous avons inclus la possibilité d'éditer une facture pour chaque commande.

En termes de qualité de développement, nous avons réalisé des tests unitaires pour valider le bon fonctionnement des classes du domaine métier. Ces tests ont couvert divers aspects, de la vérification des filtres par type et couleur des tomates à la gestion du panier (ajout, modification, suppression) ainsi que les calculs sur la facture.

Dans l'ensemble, notre équipe a travaillé de manière collaborative pour atteindre ces objectifs et créer une application Java fonctionnelle qui répond aux besoins de notre client
