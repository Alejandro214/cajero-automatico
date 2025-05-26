💳 Cajero Automático - Challenge Técnico
Este proyecto implementa una solución de cajero automático utilizando una arquitectura de microservicios. Consta de:

Un microservicio backend (Spring Boot + H2) que gestiona las cuentas y transacciones.

Una aplicación de escritorio en consola que actúa como cliente del servicio.


🛠️ Instalación y ejecución
1. Clonar el repositorio
bash
Copy
Edit
git clone https://github.com/Alejandro214/cajero-automatico.git
cd cajero-automatico
Se debe ir a la clase Main y ejecutarla

🧪 Pruebas
Se incluyen datos precargados en la base de datos H2.

Se puede hacer login y ejecutar operaciones directamente desde la consola:

```
login 987654 
saldo 987654 12345
depositar 12345 0012345678901234567890
extraer 987654 12345 1000
salir
```



⚙️ Diseño de la solución
Se utilizó Java 17 y Spring Boot 3.5.0.

La base de datos es H2 en memoria, para facilitar pruebas sin configuración adicional.

El cliente de consola se conecta al backend vía HTTP usando Java puro.


