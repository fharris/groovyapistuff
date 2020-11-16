def soapPayload = context.getServiceResponse().getBody().asString()
def soapObject = new XmlSlurper().parseText(soapPayload) 
def sunSetTime = soapObject['Body']['GetSunSetRiseTimeResponse']['GetSunSetRiseTimeResult']['SunSetTime'] 
def sunRiseTime = soapObject['Body']['GetSunSetRiseTimeResponse']['GetSunSetRiseTimeResult']['SunRiseTime']