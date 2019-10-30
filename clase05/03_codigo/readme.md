# Verificar las rutas del API en el Gateway

  http :8765/actuator/routes -a admin:secreto -v

# Dar de alta un cliente

  http :8765/v1/clientes/v1/clientes/ firstName=Domingo lastName=Suarez taxId="SUTD790217" email=doingo@dd.com creditThreshold=50000 verified=true pedidosPendientes=0 -a admin:secreto -v

# Meter carga a la creación de usuarios

  wrk -c 200 -t 1 -d 120s http://localhost:8765/ -s nuevo_cliente.lua

export clientId=5c0c0fb7-80de-4931-a26a-8b2be9ec805e

# verificar pedidos

  http localhost:8765/v1/pedidos/v1/pedidos/client/${clientId} -a admin:secreto -v

# hacer pedido

  http localhost:8765/v1/clientes/v1/clientes/${clientId}/pedidos importe=4000 -a admin:secreto -v