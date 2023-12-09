package fr.pandaguerrier.conodiagameapi.websocket.client;

import fr.pandaguerrier.conodiagameapi.ConodiaGameAPI;
import fr.pandaguerrier.conodiagameapi.utils.Parser;
import fr.pandaguerrier.conodiagameapi.websocket.HeartBeat;
import fr.pandaguerrier.conodiagameapi.websocket.enums.EventEnum;
import fr.pandaguerrier.conodiagameapi.websocket.enums.OpCode;
import fr.pandaguerrier.conodiagameapi.websocket.WebsocketManager;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;

import java.net.URI;
import java.util.Map;

public class WebsocketConodiaClient extends WebSocketClient {
    private WebsocketManager websocketManager;
    public WebsocketConodiaClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebsocketConodiaClient(URI serverURI) {
        super(serverURI);
    }

    public WebsocketConodiaClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake data) {
        websocketManager = new WebsocketManager(this);

        JSONObject connectPayload = new JSONObject();
        connectPayload.put("type", "minecraft");
        connectPayload.put("appId", ConodiaGameAPI.getInstance().getConfig().getString("api_id"));
        connectPayload.put("secret", ConodiaGameAPI.getInstance().getConfig().getString("api_secret"));

        JSONObject payload = ConodiaGameAPI.getInstance().getApiManager().post("/auth", connectPayload);
        ConodiaGameAPI.getInstance().getApiManager().getHeaders().put("Authorization", (String) payload.get("token"));

        websocketManager.send(OpCode.AUTH, payload);
        HeartBeat heartBeat = new HeartBeat(40000, websocketManager);
        heartBeat.start();
    }

    @Override
    public void onMessage(String message) {
        JSONObject jsonMessage = Parser.parse(message);
        System.out.println("§9" + jsonMessage.toJSONString());
        EventEnum eventEnum = EventEnum.get(Integer.parseInt(jsonMessage.get("type").toString()));
        if (eventEnum == null) {
            System.out.println("Event not found: " + jsonMessage.get("type").toString());
            return;
        }
        this.websocketManager.dispacth(eventEnum, (JSONObject) jsonMessage.get("data"));
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

    public WebsocketManager getWebsocketManager() {
        return websocketManager;
    }
}