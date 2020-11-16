println "fharris Script to Get Headers and QueryParameters"

def key = 'user'      // QueryParameterのkey
def val = 'harris' // QueryParameterのValue

// Get all the headers
def headersMap = (Map) context.apiRequest.getHeaders()
println "Headers = ${headersMap}"

// Get Query Parameters
def queryParams = (Map)context.apiRequest.getQueryParams()
println "Query Params = ${queryParams }"

// Get Method
def method = context.apiRequest.getMethod()
println "method = ${method}"

// Get Path Info
def pathInfo = context.apiRequest.getPathInfo()
println "pathInfo = ${pathInfo}"

// Get Query String
def queryString = context.apiRequest.getQueryString()
println "query string = ${queryString}"

// Get Request URI
def requestUri = context.apiRequest.getRequestURI()
println "Request URI = ${requestUri}"

// No operation when default query parameters are specified
if( queryParams.isEmpty() || !queryParams.containsKey(key)) {
}
else {
    // Add default query parameters unless default query parameters are not specified
    context.ServiceRequest.setQueryParam(key, val)
}

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


