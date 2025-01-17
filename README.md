<h1 align="center"> Proyecto: Tienda de Electrodomésticos con Microservicos </h1>

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

## Descripción del Proyecto:
<div align="justify">
Este es un proyecto basado en microservicios, desarrollado en Java, que simula una tienda de electrodomésticos en línea. La arquitectura se compone de tres microservicios principales: Producto, Carrito de Compras, y Ventas. Utiliza Spring Boot para la creación de microservicios, con funcionalidades como descubrimiento de servicios, enrutamiento, balanceo de carga, tolerancia a fallos y centralización de configuraciones.
</div>

## Funcionalidades Principales:

### Microservicio de Productos

 - Gestiona la información de los electrodomésticos disponibles.
 - Permite listar los productos con detalles como: código, nombre, marca y precio.

### Microservicio de Carrito de Compras

- Permite crear y gestionar carritos de compras.
- Los usuarios pueden agregar y quitar productos del carrito.
- Calcula automáticamente el precio total del carrito, gracias a la consulta del microservicio Productos.

### Microservicio de Ventas

- Registra las ventas realizadas, asociándolas con carritos de compras.
- Cada venta tiene un identificador único y una fecha.
- La venta consulta el carrito para obtener el monto total y los productos incluidos.

## Arquitectura del proyecto:

- **Java y Spring Boot:** Plataforma para desarrollar los microservicios.
- **Eureka Server y API Gateway:** Para descubrimiento de servicios y gestión de enrutamiento.
- **Feign Client:** Para la comunicación entre microservicios.
- **Resilience4j (circuit breaker y retry):** Estrategias de tolerancia a fallos.
- **Load Balancer:** Para distribuir la carga entre los microservicios.
- **Config Server:** Para centralizar la gestión de configuraciones.

## Dependencias Clave:

- _**Maven:**_ Gestión de dependencias y construcción del proyecto.
- _**Lombok:**_ Para reducir el boilerplate code en entidades y DTOs.
- _**JPA + Hibernate:**_ Para la persistencia de datos en MySQL.
- _**Spring Web:**_ Para la creación de APIs RESTful.
- _**Base de Datos:**_ MySQL, ejecutado mediante **XAMPP** para la gestión y almacenamiento de datos.
- _**Postman:**_ Para pruebas de los endpoints.
- _**Docker:**_ Para la simulación de despliegue y contenedores.

## Diagrama de FLujo

![Diagrama de flujo](./zrecursos/Sistema%20Tienda%20electrodomésticos.png)

### Explicación

- **API Gateway:** Recibe las peticiones del usuario y las distribuye a los microservicios correspondientes.
- **Eureka Server:** Se encarga de conocer la ubicación de todos los microservicios registrados, lo que permite a la API Gateway redirigir correctamente las peticiones.
- **Load Balancer:** Consulta Eureka Server para saber qué instancias del microservicio están disponibles y selecciona una para manejar la solicitud, distribuyendo la carga.
- **Microservicios:** Procesan la solicitud asignada por el load balancer e interactúan con la base de datos MySQL.
- **Circuit Breaker** Lo utilizan los microservicios que consultan a otros, para manejar posibles fallos.
- **Base de Datos MySQL:** Cada microservicio cuenta con su base de datos para almacenar sus datos correspondinetes.
- **Config Server:** Proporciona una visión unificada de la configuración y una forma eficiente de gestionar actualizaciones en nuestros microservicios.

## Endpoints del Proyecto:

#### Nota:
<div align="justify">
Todos los servicios están gestionados a través de un <strong>API Gateway</strong> que se ejecuta en <code>localhost:444</code>. Las URLs para interactuar con los microservicios siguen la siguiente estructura:
</div>

<br>
<code>localhost:444/{nombre-del-microservicio}/{endpoint}</code>
<br>
<br>

donde `{nombre-del-microservicio}` puede ser:
- `producto-service` para el microservicio de productos.
- `carrito-service` para el microservicio de carrito de compras.
- `ventas-service` para el microservicio de ventas.
- `config-server` para entrar al config-server del sistema.

<br>

### Config Server:
- `GET` /config-server/{service}/default

  - **Descripción:** Obtiene la configuración del servicio especificado (por ejemplo, `carrito-service`).
  - **Ejemplo de URL:** `localhost:444/config-server/carrito-service/default`

<br>

### Microservicio de Producto:
1. `POST` /producto-service/productos/crear
- **Descripción:** Crea un nuevo producto en la tienda.
- **Body:**
![Body para crear producto](./zrecursos/body-producto.png)
- **Ejemplo de URL:** `localhost:444/producto-service/productos/crear`

<br>

2. `GET` /producto-service/productos/traer
- **Descripción:** Obtiene todos los productos disponibles en la tienda.
- **Ejemplo de URL:** `localhost:444/producto-service/productos/traer`

<br>

3. `GET` /producto-service/productos/traer/{id}

- **Descripción:** Obtiene los detalles de un producto específico mediante su código.
- **Ejemplo de URL:** `localhost:444/producto-service/productos/traer/1` _(Reemplaza el "1" por el código del producto deseado)_

<br>

4. `PUT` /producto-service/productos/editar/{id}

- **Descripción:** Edita los detalles de un producto existente.
- **Body:**
![Body para editar producto](./zrecursos/body-producto-editar.png)
- **Ejemplo de URL:** `localhost:444/producto-service/productos/editar/1` _(Reemplaza el "1" por el ID del producto)_

<br>

5. `DELETE` /producto-service/productos/borrar/{id}

- **Descripción:** Elimina un producto de la tienda mediante su ID.
- **Ejemplo de URL:** `localhost:444/producto-service/productos/borrar/1` _(Reemplaza el "1" por el ID del producto)_

<br>

### Microservicio de Carrito:

1. `POST` /carrito-service/carrito/crear

- **Descripción:** Crea un carrito de compras con los productos seleccionados.
- **Body:**
![Body para crear carrito](./zrecursos/body-carrito.png)
- **Ejemplo de URL:** `localhost:444/carrito-service/carrito/crear`

<br>

2. `GET` /carrito-service/carrito/traer-todos

- **Descripción:** Obtiene todos los carritos de compras.
- **Ejemplo de URL:** `localhost:444/carrito-service/carrito/traer-todos`

<br>

3. `GET`/carrito-service/carrito/traer/{id}

- **Descripción:** Obtiene un carrito específico mediante su ID.
- **Ejemplo de URL:** `localhost:444/carrito-service/carrito/traer/2` _(Reemplaza el "2" por el ID del carrito)_

<br>

4. `PUT` /carrito-service/carrito/editar/{id}

- **Descripción:** Edita un carrito de compras existente.
- **Body:**
![Body para editar carrito](./zrecursos/body-carrito-editar.png)
- **Ejemplo de URL:** `localhost:444/carrito-service/carrito/editar/1` _(Reemplaza el "1" por el ID del carrito)_

<br>

5. `DELETE` /carrito-service/carrito/borrar/{id}

- **Descripción:** Elimina un carrito de compras mediante su ID.
- **Ejemplo de URL:** `localhost:444/carrito-service/carrito/borrar/1` _(Reemplaza el "1" por el ID del carrito_

<br>

### Microservicio de Ventas:

1. `POST` /ventas-service/ventas/crear

- **Descripción:** Crea una nueva venta, asociada a un carrito de compras.
- **Body:**
![Body para crear venta](./zrecursos/body-venta.png)
- **Ejemplo de URL:** `localhost:444/ventas-service/ventas/crear`

<br>

2. `GET` /ventas-service/ventas/traer

- **Descripción:** Obtiene todas las ventas realizadas.
- **Ejemplo de URL:** `localhost:444/ventas-service/ventas/traer`

<br>

3. `GET` /ventas-service/ventas/traer/{id}

- **Descripción:** Obtiene los detalles de una venta específica mediante su ID.
- **Ejemplo de URL:** `localhost:444/ventas-service/ventas/traer/1` _(Reemplaza el "1" por el ID de la venta)_

<br>

4. `PUT` /ventas-service/ventas/editar/{id}

- **Descripción:** Edita una venta existente.
- **Body:**
![Body para editar venta](./zrecursos/body-venta-editar.png)
- **Ejemplo de URL:** `localhost:444/ventas-service/ventas/editar/1` _(Reemplaza el "1" por el ID de la venta)_

<br>

5. `DELETE` /ventas-service/ventas/borrar/{id}

- **Descripción:** Elimina una venta mediante su ID.
- **Ejemplo de URL:** `localhost:444/ventas-service/ventas/borrar/1` _(Reemplaza el "1" por el ID de la venta)_

<br>

## Detalles Técnicos

- **Escalabilidad:** El proyecto está preparado para escalar horizontalmente utilizando un balanceador de carga.
- **Tolerancia a fallos:** Resilience4j está implementado para manejar caídas en los servicios con estrategias de Circuit Breaker y Retry.
- **Centralización de Configuraciones:** Utilizamos Config Server para centralizar las configuraciones de los microservicios.

<br>

## Despliegue con Docker:
<div align="justify">
Este proyecto está configurado para ser desplegado utilizando Docker. Docker permite empaquetar la aplicación junto con todas sus dependencias en un contenedor, asegurando que la aplicación funcione de la misma manera en cualquier entorno (local o en la nube).
</div>

### Pasos para Ejecutar con Docker:

1. Construye las imágenes de Docker para cada microservicio. Esto se puede hacer con el siguiente comando:

`docker-compose build`

2. Levanta los contenedores utilizando Docker Compose. Esto iniciará todos los microservicios definidos en el archivo docker-compose.yml.

`docker-compose up`

<div align="justify">
El archivo 'docker-compose.yml' está configurado para ejecutar varias imágenes que representan cada microservicio. Como también la creación de cada base de datos que utiliza cada microservicio. 
</div>


