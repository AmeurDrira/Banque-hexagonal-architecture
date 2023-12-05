# 💰 **Bank Account** 💰

### Architecture hexagonale with SpringBoot

Implémentation de la logique métier d'un compte en banque:

```
Fonctionnalités attendues:

1. Dépot d'argent
2. Retrait d'argent
3. Consulter le solde actuel
4. Consulter les transactions précédentes

```
### DataBase Initialisation with docker:
```
  docker-compose up -d
```

### Swagger UI :

[ Swagger ](http://localhost:8083/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/)

## Curl request exemple :
* url : localhost
* port :8083


#### Deposit Transaction:
```
curl -X 'POST' \
  'http://localhost:8083/api/transaction' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "accountReceiverId": 1,
  "amount": 10000,
  "type": "DEPOT"
}'
```
#### withdrawal Transaction:
```
-------------------------------------------
curl -X 'POST' \
  'http://localhost:8083/api/transaction' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "accountReceiverId": 3,
  "amount": 20,
  "type": "RETRAIT"
}'
```



