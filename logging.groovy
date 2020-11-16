"-------------------------------------------------------------------------------------------------------"
"Message Payload"
${headers}
${payload}
{"Content-Type":"${headers.Content-Type}"}
{"Latitude":"${payload.Envelope.Body.GetSunSetRiseTimeResponse.GetSunSetRiseTimeResult.Latitude}"}
String strbody = (String)context.getServiceResponse().getBody().asString()
${strbody}
"-------------------------------------------------------------------------------------------------------"