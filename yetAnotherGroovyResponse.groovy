// Add HTTP Header to API Response
context.ApiResponse.setHeader("Content-Type", "application/xml")

def targetResponse = context.getServiceResponse().getBody().asString()
def varDummy = '123'

def xmlText3 = '<?xml version="1.0" encoding="UTF-8" ?><OUT_PUT><dummyId>'+ varDummy + '</dummyId><originalResponse>'+ targetResponse +'</originalResponse><notes/></OUT_PUT>'


context.apiResponse.setBody(new StringBodyImpl(xmlText3, "application/xml"))