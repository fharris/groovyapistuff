println "fharris Script Policy to Get QueryParameters"

def key = 'when'      // QueryParameterのkey
def val = 'tomorrow' // QueryParameterのValue

/* // Get all the headers
def headersMap = (Map) context.apiRequest.getHeaders()
println "Headers = ${headersMap}" */

// Get Query Parameters
def queryParams = (Map)context.apiRequest.getQueryParams()
println "Query Params = ${queryParams }"

/* // Get Method
def method = context.apiRequest.getMethod()
println "method = ${method}"

// Get Path Info
def pathInfo = context.apiRequest.getPathInfo()
println "pathInfo = ${pathInfo}" */

// Get Query String
def queryString = context.apiRequest.getQueryString()
println "query string = ${queryString}"

/* // Get Request URI
def requestUri = context.apiRequest.getRequestURI()
println "Request URI = ${requestUri}" */

// No operation when default query parameters are specified
if( queryParams.isEmpty() || !queryParams.containsKey(key)) {
    println "query parameter = ${key} not sent or not found!"
    // In the only case of returning Error Message due to message validation error
/*   
    def messageBody = ["errorcode": "XXXX"]
    println "messageBody = ${messageBody}"
    def json = groovy.json.JsonOutput.toJson(messageBody)
    println "json = ${json}"
    def body = new StringBodyImpl(json.toString(), "application/json")
    println "body = ${body}"
    def length = body.asString().length().toString()
    println "length = ${length}"
    def errorId = "A001"
    println "errorId = ${errorId}"

    context.ApiResponse.setBody(body).setHeader("Content-Length",length).setHeader("Content-Type","application/json")
throw new oracle.apiplatform.policies.sdk.exceptions.PolicyProcessingException(errorId, 400, "Exception message")
 */
else {
    // Add default query parameters unless default query parameters are not specified
    context.ServiceRequest.setQueryParam(key, val)

    ExternalServiceCallout callout = context.newCallout()
    ExternalServiceCallout.Callback callback = new ExternalServiceCallout.Callback() {

    boolean onCompleted(ServiceResponse response) throws PolicyProcessingException {
        println "Response: " + response.getBody().asString()
        // If you want to use the response later, store the response using setAttribute() or setEdr() 
        context.setAttribute("response1", response)
        return true
    }

    boolean onThrowable(Throwable throwable) throws PolicyProcessingException {
        return true
    }
}

// As ExternalServiceCallout extends ServiceRequest, you can invoke any APIs even if request message is mandatory.
callout.setRequestURL("http://worldclockapi.com/api/json/utc/tomorrow").setMethod("GET")
callout.sendAsync(callback)


}




