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
depositar 987654 0012345678901234567890 1000
extraer 987654 12345 1000
salir
```

⚙️ Diseño de la solución
Se utilizó Java 17 y Spring Boot 3.5.0.

La base de datos es H2 en memoria, para facilitar pruebas sin configuración adicional.

El cliente de consola se conecta al backend vía HTTP usando Java puro.

✅ Requisitos Funcionales
Login
Se implementó un método que verifica si el número de tarjeta existe y si está activa.
🔧 Implementación:
Método en CardRepository: existsByNumberCardAndActiva(numberCard, activa)

Extracción (extraer)
Permite debitar un importe de la cuenta asociada, siempre que haya saldo suficiente.
🔧 Se valida que:

La tarjeta esté asociada a la cuenta.

El saldo actual alcance.

Se actualiza el saldo y se persiste.

Depósito (depositar)
Acredita el importe a la cuenta cuyo CBU coincida, sin requerir que sea del mismo dueño.
🔧 Se busca la cuenta por CBU y se actualiza su saldo.

Consulta de saldo (consultar_saldo)
Muestra el saldo actual de la cuenta si está asociada a la tarjeta.
🔧 Se consulta vía AccountCard para verificar la relación entre tarjeta y cuenta.

En cada metodo se verifica que la tarjeta ingresada exista y este activa antes de realizar cualquier operacion, caso contrario se lanzara un error


