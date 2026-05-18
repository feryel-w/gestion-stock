# Systeme de Gestion des Stocks Multi-Entrepots

## Description
Application web de gestion des stocks permettant de gerer les entrepots, produits, stocks et mouvements de stock avec un tableau de bord en temps reel.

## Technologies
- **Back-end:** Spring Boot 3, Spring Data JPA, Spring Validation
- **Base de donnees:** MySQL
- **Mapping:** ModelMapper
- **Documentation API:** Swagger / OpenAPI
- **Front-end:** Angular 19
- **Deploiement:** Docker, Docker Compose

## Architecture
- Back-end REST API sur le port 8080
- Front-end Angular sur le port 4200
- Base de donnees MySQL sur le port 3306

## Installation et Execution

### Avec Docker
```bash
docker-compose up --build
```
L'application sera disponible sur http://localhost:8080

### Sans Docker
1. Demarrer MySQL
2. Lancer le back-end Spring Boot depuis Eclipse
3. Lancer le front-end Angular:
```bash
cd stock-frontend
ng serve
```
L'application sera disponible sur http://localhost:4200

## Endpoints API
- Swagger UI: http://localhost:8080/swagger-ui.html
- Entrepots: http://localhost:8080/entrepots
- Produits: http://localhost:8080/produits
- Stocks: http://localhost:8080/stocks
- Mouvements: http://localhost:8080/mouvements
- Utilisateurs: http://localhost:8080/utilisateurs

## Fonctionnalites
- CRUD complet des entrepots, produits, stocks et mouvements
- Gestion des entrees et sorties de stock
- Alertes automatiques en cas de stock bas
- Tableau de bord avec statistiques en temps reel
- Gestion des utilisateurs avec roles (ADMIN, GESTIONNAIRE, CONSULTANT)
- Validation des donnees avec Spring Validator