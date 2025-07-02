# Crear imagen Docker con Google Jib y ejecutar contenedor

Este documento describe los pasos para construir una imagen Docker usando
el plugin **Google Jib** en un proyecto Java o Spring Boot, y luego 
ejecutar un contenedor con los puertos mapeados y en modo desacoplado.

---

## ✅ Requisitos previos

- Tener instalado:
  - Java JDK (11 o superior)
  - Maven
  - Docker
- Proyecto Java o Spring Boot configurado con Maven

---

## 1. Agregar el plugin Jib a tu proyecto

Agrega el plugin de Jib en el archivo `pom.xml` dentro de `<build>` y define un nombre para la imagen,
{project.artifactId} tomará el nombre del artifactId para asignarlo en el nombre de la imagen

<build>
  <plugins>
    <plugin>
		<groupId>com.google.cloud.tools</groupId>
		<artifactId>jib-maven-plugin</artifactId>
		<version>3.4.6</version>
		<configuration>
			<to>
		    	<image>albertoesteva88/${project.artifactId}:s4</image>
			</to>
		</configuration>
    </plugin>
  </plugins>
</build>

--
## 🔨 Paso 2: Construir la imagen

mvn compile jib:dockerBuild

Si se agrega el plugin sin la seccion configuration para asignar el nombre
se puede especificar el nombre con la siguiente instruccion:

mvn compile jib:dockerBuild -Dimage=albertoesteva88/cards:s4

Esto generará una imagen del proyecto ademas de descargar 
otras que serviran de apoyo en la creacion de la imagen.
La imagen se genera con tiempo de creacion de mas de 50 años, 
no es un error es una caracteristica de `Google Jib`.

--
## 🚀 Paso 3: Crear y ejecutar el contenedor
docker run -d -p 9000:9000 albertoesteva88/cards:s4

Al ejecutar esta instruccion si se cambia el puerto crea un 
contenedor nuevo con un nombre diferente generado automaticamente

si se agrega el tag name se puede poner un nombre personalizado

docker run -d -p 9000:9000 --name cards albertoesteva88/cards:s4
