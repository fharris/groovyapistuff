// get the body of the response as text
String strbody = (String)context.getServiceResponse().getBody().asString()
    
def headerStr = "";
try
{
  int spanPos = strbody.indexOf("CF-RAY")
  int fstquote = strbody.indexOf(":", spanPos)
  int sndquote = strbody.indexOf(",", fstquote)
  headerStr = strbody.substring(0, fstquote) + ":\"Fernando Harris\"" + strbody.substring(sndquote)
}
catch (Exception err)
{
  headerStr = strbody+ "An exception occurred in groovyResponseId.groovy";
}
def aBody = new StringBodyImpl(headerStr, " application/json")
context.getApiResponse().setBody(aBody)