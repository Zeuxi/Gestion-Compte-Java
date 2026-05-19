# Application de Gestion de Portefeuille Électronique

Projet réalisé en Java dans le cadre du module de Programmation Java (Licence 2 IT).

## Description

Cette application console permet de simuler le fonctionnement d’un portefeuille électronique.

Le programme permet :

- la création de comptes clients ;
- la consultation du solde ;
- le transfert d’argent entre clients ;
- la recharge de compte ;
- la gestion d’un compte administrateur ;
- l’affichage des utilisateurs.

Le système applique automatiquement une taxe de 1% sur chaque transfert effectué entre clients.

---

## Fonctionnalités

### Côté Administrateur

L’administrateur peut :

- créer des comptes clients ;
- consulter le solde d’un client ;
- recharger le compte d’un client ;
- effectuer un transfert entre deux clients ;
- afficher les informations d’un client ;
- afficher la liste des utilisateurs ;
- se déconnecter.

---

### Côté Client

Le client peut :

- consulter son solde ;
- transférer de l’argent à un autre client.

---

## Technologies utilisées

- Java
- Programmation Orientée Objet (POO)
- Encapsulation
- Vector
- Gestion des entrées/sorties avec `BufferedReader`

---

## Structure du Projet

```text
GestCompte.java
```

Le fichier contient :

- la classe `compte`
- la classe principale `GestCompte`

---

## Concepts Java utilisés

- Classes et objets
- Constructeurs
- Encapsulation
- Attributs privés
- Accesseurs et mutateurs (getters/setters)
- Tableaux dynamiques avec `Vector`
- Structures conditionnelles
- Boucles
- Gestion des exceptions

---

## Fonctionnement des transferts

Lorsqu’un client effectue un transfert :

- 1% du montant est reversé au compte administrateur ;
- le solde du client expéditeur est diminué du montant + taxe ;
- le destinataire reçoit le montant envoyé.

### Exemple

Pour un transfert de 1000 FCFA :

- montant débité : 1010 FCFA
- montant reçu : 1000 FCFA
- frais admin : 10 FCFA

---

## Sécurité et Vérifications

Le programme interdit :

- les transferts si le solde est insuffisant ;
- les transferts vers soi-même ;
- les identifiants dupliqués ;
- l’utilisation du compte ADMIN comme client.

---

## Compilation et Exécution

### Compilation

```bash
javac GestCompte.java
```

### Exécution

```bash
java GestCompte
```

---

## Auteur

Projet réalisé par :

- KABORE B. Frank

---

## Contexte Académique

Projet de Programmation Java  
Licence 2 IT  
