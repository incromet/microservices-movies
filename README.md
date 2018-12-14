# microservice-movies
[![Heroku](https://wmpics.pics/di-D9YP.png)](https://microservices-movies.herokuapp.com/)

---
## Descripción de la arquitectura implementada
![](img/diagram.png)
El cliente se conecta a un REST API que provee un index.html que explica cómo usar el API y a través del botón proporcionado; se llama a una función del JavaScript para que ésta, tomando el valor del campo de entrada, se comunique con el SpringBoot (REST API) mediante axios y de allí se obtenga la fórmula y valores para realizar el cálculo/conversión. Finalmente todo se despliega en el PaaS de Heroku.

---
## Atributos de calidad
- Usabilidad: se tiene usabilidad al ser una página web agradable con explicación que permite al usuario tener una guía.
- Mantenibilidad: se hace posiblee extender el código mediante clases, métodos o cualquier otra función extra que se requiera.

---
## Limitaciones de la arquitectura
- No hace llamados a APIs externos por lo tanto es únicamente dependiente de las fórmulas que se encuentran embebidas en el API, entonces ante un cambio en las fórmulas, habría que actualizar la lógica.
- No mantiene registros o trazas de lo que se ha realizado entonces no posee persistencia afectando el atributo de calidad de Funcionalidad.
- No hace uso de autenticación (por TOKEN) afectando la seguridad del sistema.

---
### ARSW-2018-2
### Daniel Beltrán