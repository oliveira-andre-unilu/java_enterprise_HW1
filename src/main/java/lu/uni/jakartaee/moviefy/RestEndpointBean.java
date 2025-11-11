package lu.uni.jakartaee.moviefy;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@Path("Example")
public class RestEndpointBean {

    public RestEndpointBean() {
        /* Needed for a JavaBean */
    }

    /*
     * The standard hello world example: http://localhost:8080/REST-Example/REST/Example
     */

    @GET
    public String greeting()
    {
        return("String response from a REST ENDPOINT: Please use http://localhost:8080/REST-Example/REST/Example/1 to see an example for a JSON response.");
    }

    /*
     * An example that returns a Java Object "Result", converted to JSON:
     *  http://localhost:8080/REST-Example/REST/Example/1
     */

    @GET
    @Path("{x}")
    @Produces({MediaType.APPLICATION_JSON})
    public Result add(@PathParam("x") int x)
    {
      Result r = new Result();
      r.setMsg("Result of increment operation for x = " + x);
      r.setValue(Integer.toString(x+1));
      return (r);
    }
}
