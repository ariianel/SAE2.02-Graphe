![Graphe-banniere](https://user-images.githubusercontent.com/112662431/227766722-1fdd8c12-1962-41f1-9612-b8f01848e8a6.png)
```diff
- Issues : Impossible d'importer les dossiers graphes et reponses à cause de leurs trop grandes tailles
```
<h1 align="center">Objectif</h1>
<p align="center"><i>Réalisation en Java d'une application permettant de manipuler des graphes. Une application qui permet de déterminer le plus court chemin d'un itinéraire.<i></p><br>

       

          
**Liste des participants de l'équipe :**
- Ariane
- Océane
- Romain
- John                               
<h1 align="center">Quelles classes de graphes parmi les 4 demandées ont été codées ?</h1>  
<p align="center"><i>R : Les 4 classes ont été implémentées : GrapheHHAdj, GrapheLAdj, GrapheLArcs, GrapheMAdj<i></p><br>
          
<h1 align="center">Est-ce que Dijkstra passe les tests fournis sur Moodle avec tous les graphes fournis ? Jusqu’à quelle taille (selon le type1 de graphe).</h1>
     <p align="center"><i>R : Oui, Dijkstra passe les tests fournis avec tous les types de graphes(DorogvtsevMendes, Barabasi, et fullconnected).<i></p><br>

X = Trop long pour obtenir un résultat

**Taille maximale pour:**
- DorogvtsevMendes : 1 000 000
          GrapheLArcs: X
          GrapheLAdj : 1267 millisecondes
          GrapheMAdj : X
          GrapheHHAdj : 1479 millisecondes
- Barabasi : 100 000
          GrapheHHAdj : 3561651 millisecondes
- fullconnected : 5000
          GrapheLArc : X
          GrapheLAdj : 33325 millisecondes
          GrapheMAdj : 878 millisecondes GrapheHHAdj : 1599 millisecondes
