package main.java.graphe.core;

import java.util.*;


public class Dijkstra {

    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
        // initialisation des structures de données
        Set<String> visite = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // Initialisation des distances à l'infini sauf pour la source qui est à 0
        for (String sommet : graphe.getSommets()) {
            dist.put(sommet, Integer.MAX_VALUE);
        }
        dist.put(source, 0);

        // Ajout du nœud source à la file de priorité
        queue.offer(new Node(source, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String u = current.getNode();

            // Vérifier si le nœud a déjà été visité
            if (visite.contains(u)) {
                continue;
            }

            // Marquer le nœud courant comme visité
            visite.add(u);

            // Parcourir tous les voisins du nœud courant
            for (String v : graphe.getSucc(u)) {
                int poidsUV = graphe.getValuation(u, v);

                // Ignorer les voisins sans chemin ou déjà visités
                if (poidsUV == -1 || visite.contains(v)) {
                    continue;
                }

                // Calculer la distance par le nœud courant
                int distanceParU = dist.get(u) + poidsUV;

                // Mettre à jour la distance et le prédécesseur si la nouvelle distance est plus courte
                if (distanceParU < dist.get(v)) {
                    dist.put(v, distanceParU);
                    pred.put(v, u);

                    // Ajouter le nœud mis à jour à la file de priorité
                    queue.offer(new Node(v, distanceParU));
                }
            }
        }
        for (String sommet : graphe.getSommets()) {
            if (dist.get(sommet) == Integer.MAX_VALUE)
                dist.remove(sommet);
        }
    }

    // Classe interne pour représenter un nœud avec sa distance
    private static class Node implements Comparable<Node> {
        private String node;
        private int distance;

        public Node(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public String getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(distance, other.distance);
        }
    }
}
