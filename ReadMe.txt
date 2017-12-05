
###########################################Emplacement des fichiers###################################################
Les fichiers du jeu seront a mettre dans core/src/com/mygdx/game/VotrePackage

Le main windows (A lancer aussi pour les tests) est desktop/src/com/mygdx/game/desktop/DesktopLauncher.java

##############################################Lancement du projet#####################################################


Pour utiliser le fichier, telechargez libgdx, puis crées un projet (nommé ici "test") Il faut cacher la case "eclipse" dans advanced avant de generer.
Remplacez ensuite le fichier Projet/core/.classpath par test/core/.classpath et
				  le fichier Projet/desktop/.classpath par test/desktop/.classpath
				  De meme pour les autres plateformes
		
Il y aura peut etre un probleme pour la compilation avec my-game-core. Il faudra alors juste le supprimer dans Build path/library dans les options java
		  
Enfin, il pourrait rester une unique erreur dans DesktopLauncherjava, il suffit de cliquer droit sur l'erreur et cliquer sur le fix.

Sous eclipse, le projet apparait comme divisé en 4.  