
request = function()
  wrk.method = "POST"
  wrk.headers["Content-Type"] = "application/json"  

  wrk.body = ''

  return wrk.format("POST", "/queue")
end


