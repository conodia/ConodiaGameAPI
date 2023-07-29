package fr.pandaguerrier.conodiagameapi.websocket.client;

import fr.pandaguerrier.conodia.ConodiaLinks;
import fr.pandaguerrier.conodia.websocket.HeartBeat;
import fr.pandaguerrier.conodia.websocket.OpCode;
import fr.pandaguerrier.conodia.websocket.WebsocketManager;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;

import java.net.URI;
import java.util.Map;

/**
 * This example demonstrates how to create a websocket connection to a server. Only the most
 * important callbacks are overloaded.
 */
public class WebsocketRivalsClient extends WebSocketClient {

    public WebsocketRivalsClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebsocketRivalsClient(URI serverURI) {
        super(serverURI);
    }

    public WebsocketRivalsClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake data) {
        WebsocketManager websocketManager = new WebsocketManager(this);

        JSONObject connectPayload = new JSONObject();
        connectPayload.put("type", "minecraft");
        connectPayload.put("appId", ConodiaLinks.getInstance().getConfig().getString("api_id"));
        connectPayload.put("secret", ConodiaLinks.getInstance().getConfig().getString("api_secret"));

        System.out.println("Sending auth request");
        System.out.println(connectPayload.toJSONString());

        JSONObject payload = ConodiaLinks.getInstance().getApiManager().post("/auth", connectPayload);

        ConodiaLinks.getInstance().getApiManager().getHeaders().put("Authorization", (String) payload.get("token"));

        websocketManager.send(OpCode.AUTH, payload);
        HeartBeat heartBeat = new HeartBeat(40000, websocketManager);
        heartBeat.start();
        System.out.println("opened connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }
}