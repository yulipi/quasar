### Documentación Operación Fuego Quasar

Para el desarrollo de este desafio se utilizó como lenguaje de programación java, maven, spring boot y AWS

Para el desarrollo de la función getLocation(), se utilizó la libreria Trilateration desarrollada por el MIT

#### CI/CD
Esta parte se implemento siguiendo los pasos del articulo *Deploy a Spring Boot App on AWS ECS With GitHub Actions*  que se encuentra en la siguiente dirección https://betterprogramming.pub/deploy-a-spring-boot-app-on-aws-ecs-with-github-actions-669928f62043

Pasos para el despliegue
- Cuando se hace un pull request, se ejecuta una accion de github que verifica que las pruebas unitarias y la compilación se hagan de manera correcta 
- Luego, se hace un push a la rama main, nuevamente se ejecuta una acción de github que realiza el despliegue a un ECS de AWS

#### Ejecución del programa
Para realizar la ejecución del API Rest ejecutar 
Para el nivel 2
```javascript
curl --request POST \
  --url http://3.80.80.122/topsecret \
  --header 'Content-Type: application/json' \
  --data '{
"satellites": [
	{
		"name": "kenobi",
		"distance": 100.0,
		"message": ["este", "", "", "mensaje", ""]
	},
	{
		"name": "skywalker",
		"distance": 115.5,
		"message": ["", "es", "", "", "secreto"]
	},
	{
		"name": "sato",
		"distance": 142.7,
		"message": ["este", "", "un", "", ""]
	}
		]
}'

```
Para el nivel 3
```javascript
`curl --request POST \
  --url http://3.80.80.122/topsecret_split/skywalker \
  --header 'Content-Type: application/json' \
  --data '{
	"distance": 120.7,
	"message": ["", "es", "", "", "secreto"]
}'`
```
```javascript
curl --request GET \
  --url http://3.80.80.122/topsecret_split/skywalker \
  --header 'Content-Type: application/json'
```
