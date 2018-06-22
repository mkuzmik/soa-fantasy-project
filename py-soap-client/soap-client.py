from zeep import Client

client = Client('http://localhost:8080/soap/CategoryDefinitionService?wsdl')

# category = 'pythons'
# field = 'size'
# element = 'python'
# elem_field = 'length'
# enum1 = 'range'
# enum2 = 'whatever'
#
# client.service.create(category, field, element, elem_field, enum1, enum2)

client.service.updateElement(56, 1000)