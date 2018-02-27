# examen-ml

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

## Prerequisites

Es necesario tener instalado:

```
Java 8
Maven
DynamoDB
```


## Installing

- Clonar el repositorio. git clone https://github.com/pdbenitez/examen-ml.git
- Bajar las dependencias, ejecutar los tests mvn test
- Descargar DynamoDB para ejecucion local: 
- Levantar DynamoDB, chequear que este todo bien entrando a http://localhost:8000/shell/
- En la shell de DynamoDB crear 2 tablas e insertar un registro:

```javascript
/*Tabla que va a contener todos los ADN*/
var params = {TableName: 'PersonsDna', KeySchema: [ {AttributeName: 'Dna',KeyType: 'HASH',}],
AttributeDefinitions: [ { AttributeName: 'Dna', AttributeType: 'S',	}],
ProvisionedThroughput: {ReadCapacityUnits: 1, WriteCapacityUnits: 1, }};
dynamodb.createTable(params, function(err, data) { if (err) ppJson(err); else ppJson(data); });

/*Tabla que va a funcionar como contador de humanos y mutantes*/
var params = {TableName: 'DnaCounters',KeySchema: [{AttributeName: 'dna',KeyType: 'HASH',}],
AttributeDefinitions: [{AttributeName: 'dna',AttributeType: 'S',}],
ProvisionedThroughput: {ReadCapacityUnits: 1,WriteCapacityUnits: 1,}};
dynamodb.createTable(params, function(err, data) { if (err) ppJson(err); else ppJson(data);});

/*Registro base que luego se incrementara segun el tipo de ADN que se consulte*/
var params = {TableName: 'DnaCounters', Item: {dna: 'DNA',count_mutant_dna: 0,count_human_dna: 0}};
console.log("Calling PutItem"); ppJson(params);
docClient.put(params, function(err, data) { if (err) ppJson(err); else console.log("PutItem returned successfully");});
```

- Levantar la app local: mvn spring-boot:run



## Usage

La api /mutant/ detecta si un ADN de un humano es mutante o no mediante un HTTP POST con un Json el cual tenga el siguiente formato:

POST → /mutant/
{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

En caso de verificar un mutante, devuelve un HTTP 200-OK, en caso contrario un 403-Forbidden.




La api /stats devuelve un Json con las estadisticas de las verificaciones de ADN:

 {"count_mutant_dna":40, "count_human_dna":100, "ratio":0.4}

GET → /stats



## Architecture




