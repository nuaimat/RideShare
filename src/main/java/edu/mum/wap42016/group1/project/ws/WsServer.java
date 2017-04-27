package edu.mum.wap42016.group1.project.ws;

/**
 * Created by Mo nuaimat on 4/26/17.
 */

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/websocketendpoint")
public class WsServer {
    private static WsServer instance = null;

    public static WsServer getInstance() {
        if (instance == null) {
            instance = new WsServer();
        }
        return instance;
    }

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WsServer Open Connection ...");
        peers.add(session);
        /*try {
            session.getBasicRemote().sendText("good to be in touch");
        } catch (IOException e) {
        }*/
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WsServer Close Connection ...");
        peers.remove(session);
    }

    @OnMessage
    public String onMessage(String message) {

        /* for (Session s : peers) {
            if(!s.isOpen()){
                continue;
            }
            try {
                s.getBasicRemote().sendText(" >>> " + message);
                System.out.println("send message to peer ");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } */

        return "client not allowed to send msg";
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }


    public static void sendMsgToAllClients(String msg) {
        System.out.println("WsServer sendMsgToAllClients: Sending msg " + msg);
        //allSessions = allSessions.getOpenSessions();
        List<Session> obseleteSessions = new ArrayList<>();
        for (Session sess : peers) {
            if (sess.isOpen()) {
                try {
                    sess.getBasicRemote().sendText(msg);
                } catch (Exception ioe) {
                    System.out.println("WsServer: " + ioe.getMessage());
                    continue;
                }
            } else {
                obseleteSessions.add(sess);
            }
        }

        peers.removeAll(obseleteSessions);
    }

}