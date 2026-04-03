import $ivy.`com.lihaoyi::requests:0.9.0`
import java.util.concurrent.TimeUnit
import java.lang.Math

def retry[T](max: Int,delay: Int)(f: => T): T = {
    var tries = 0
    var delay_c = delay
    var result: Option[T] = None
    while (result == None) {
        TimeUnit.MILLISECONDS.sleep(delay_c);
        try { result = Some(f) 
        }
        catch {case e: Throwable =>
            tries += 1
            if (tries > max) throw e
            else {
            delay_c = delay_c + (Math.pow(tries,2) * delay).toInt
            println(s"failed, retry #$tries after delay #$delay_c")
            }
            }
        }
    result.get
}

val httpbin = "https://httpbin.org"

// exponential backoff, sleeping between retries, with a configurable initial delay in milliseconds
retry(max = 5, delay = 100 /*milliseconds*/) {
    // Only succeeds with a 200 response
    // code 1/3 of the time
    requests.get(
    s"$httpbin/status/200,400,500"
    )
}












