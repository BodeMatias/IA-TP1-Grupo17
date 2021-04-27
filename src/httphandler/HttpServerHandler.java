package httphandler;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import SpritePositions.SpritePositions;

public class HttpServerHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		SpritePositions spritePositions = SpritePositions.getInstance();
			
		Gson gson = new Gson();
        String response = gson.toJson(spritePositions);
        //exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Content-Security-Policy", "default-src 'self'");
        exchange.sendResponseHeaders(200, response.length());
               
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
	}

}
