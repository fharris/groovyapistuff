/* -----------------------------------
 *
 * Subsequent groovy script policy to place in the Response Pipeline
 *
 * -----------------------------------*/
ServiceResponse calloutResponse1 = (ServiceResponse)context.getAttribute("response1")
println "RESPONSE " + calloutResponse1.getBody().asString()

def newResponse = calloutResponse1.getBody().asString()
println "HTTP CODE " + calloutResponse1.getStatusCode()

context.apiResponse.setStatusCode(200).setStatusText(newResponse);
