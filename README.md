# proyecto_final_vuejs
Integrantes
1. Flavio Calizaya Cahuana
2. Liseth Rojas Lopez
3. Dimar Bernabe
4. Juan José Callahuara

Backend.
Prerequisitos.
* JDK java 11 o mayor
* PostgressSql Sugerencia 14.0.1 o mayor, puedes utilizar otro gestor de base de datos para ello debes configurar el archivo aplication.properties

Pasos Para Ejecutar la aplicacion

* Abrir con un IDE el archivo de Backend (Sugerencia IntelliJ)
* Cambiar los datos de usuario y password en application.properties
* Crear una base de datos news
* se descargara automaticamente las dependencias de gradle
* Ejecutar NewsApplication

*Frontend
Prerequisitos
* Vuejs 2.0
* nodejs

Pasos para ejecutar la aplicacion
* abrir el archivo frontend/gr10 con un editor, (sugerencia visual studio)
* abrir la terminal ejecutar los siguientes comandos
* npm install
* npm run serve 

El servicio de authentication del backend esta realizado con una arquitectura hexagonal (DDD pattern)
Se tienen los siguientes modelos
* User
* Account

El servicio de noticias del backend esta realizado con una arquitectura MCS
Se tienen los siguientes modelos
* New
* Category
las relaciones y generacion de los campos en la base de datos es manejado automaticamente mediante Hibernate 

El frontend esta desarrollado mediante Vuejs
![image](https://user-images.githubusercontent.com/51680932/187286569-58af2dc0-63ca-4705-8456-60a1cc0222a3.png)
