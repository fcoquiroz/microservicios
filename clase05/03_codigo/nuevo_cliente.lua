math.randomseed(os.time())

counter = 0

request = function()
  counter = counter + 1
  number =  math.random()
  wrk.method = "POST"
  wrk.headers["Content-Type"] = "application/json"
  wrk.headers["Authorization"] = "Basic YWRtaW46c2VjcmV0bw=="

  wrk.body = '{"firstName":"Juan", "lastName":"Lopez", "taxId":"SSSSSSSS","email":"ess5' .. tostring(number) ..'@gmail.com", "creditThreshold":"20000", "verified":"true", "pedidosPendientes":"0"}'

  return wrk.format("POST", "/v1/clientes/v1/clientes")
end


