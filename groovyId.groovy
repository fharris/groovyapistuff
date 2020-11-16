// determine if the traceId has been provided in the 
// API Call header
if (!context.getApiRequest().getHeaders().containsKey ("X-B3-TraceId"))
{
  // use the current time in milliseconds as a seed to 
  // the random number generator
  def instant = java.time.Instant.now()
  def random = new java.util.Random(instant.getEpochSecond())
    
  // take 8 bytes aka 64 bits from the random 
  // number generator
  byte[] b1 = new byte[8]
  random.nextBytes(b1)
    
  // convert the bytes into a lower case hex
  // representation as per definition of TraceId
  final Writable printableHex = b1.encodeHex()
  final String traceId=printableHex.toString() 
    
  context.getServiceRequest().setHeader("X-B3-TraceId", traceId)
  context.getServiceRequest().setHeader("X-B3-SpanId", traceId)
    
  // record that header has been set 
  final String log = "apply header change - traceid " + traceId
    
  println (log)
}