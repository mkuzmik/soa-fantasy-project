from zeep import Client

client = Client('http://localhost:8080/soap/CategoryDefinitionService?wsdl')
result = client.service.getAll()

print(result)