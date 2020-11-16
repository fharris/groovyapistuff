ExternalServiceCallout callout = context.newCallout()
ExternalServiceCallout.Callback callback = new ExternalServiceCallout.Callback() {
  boolean onCompleted(ServiceResponse response) throws PolicyProcessingException {
  def body = response.getBody().asJSONObject() // The JSON response from worldclockapi
  def datetime = body.getString("currentDateTime") // extract the currentDateTime value
  body = context.getApiRequest().getBody().asJSONObject() // Get the API request body to be enriched
  body.put("date", datetime) // Add the datetime value to the Service Request Payload
  context.getServiceRequest().setBody(new StringBodyImpl(body.toString(), null)) // Reset the Service Request body with the enriched JSON
  return true
  }
  boolean onThrowable(Throwable throwable) throws PolicyProcessingException {
    return false
  }
}
callout.setMethod("GET")
callout.setRequestURL("http://worldclockapi.com/api/json/utc/now")
callout.sendAsync(callback)

//Another Groovy ServiceCallout Example
/* 
ExternalServiceCallout callout = context.newCallout()
ExternalServiceCallout.Callback callback = new ExternalServiceCallout.Callback() 
{   boolean onCompleted(ServiceResponse response) throws PolicyProcessingException 
{     context.getServiceRequest().setBody(response.getBody())     return true   }   
boolean onThrowable(Throwable throwable) throws PolicyProcessingException {     return true   } 
} callout.setHeader("Content-Type", context.getApiRequest().getHeader("Content-Type")) 
callout.setBody(context.getApiRequest().getBody()) 
callout.setMethod("POST") 
callout.setRequestURL("http://localhost:2222/getzip") 
callout.sendAsync(callback) */

String strbody = (String)context.getServiceResponse().getBody().asString()
${strbody}

ExternalServiceCallout callout = context.newCallout()
ExternalServiceCallout.Callback callback = new ExternalServiceCallout.Callback() {
  boolean onCompleted(ServiceResponse response) throws PolicyProcessingException {
  def body = response.getBody().asJSONObject() // The JSON response from worldclockapi
  def datetime = body.getString("currentDateTime") // extract the currentDateTime value
  body = context.getApiRequest().getBody().asJSONObject() // Get the API request body to be enriched
  body.put("date", datetime) // Add the datetime value to the Service Request Payload
  context.getServiceRequest().setBody(new StringBodyImpl(body.toString(), null)) // Reset the Service Request body with the enriched JSON
  return true
  }
  boolean onThrowable(Throwable throwable) throws PolicyProcessingException {
    return false
  }
}
callout.setMethod("GET")
callout.setRequestURL("http://worldclockapi.com/api/json/utc/now")
callout.sendAsync(callback)