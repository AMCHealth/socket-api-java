package com.scispike.ws;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

public abstract class SockJsClient extends WebSocketTransport {

  public SockJsClient(URL url) {
    super(process(url));
  }

  private static URI process(URL parsed) {

    if ("http".equals(parsed.getProtocol())) {
    } else if ("https".equals(parsed.getProtocol())) {
    } else {
      throw new RuntimeException("invalid url");
    }

    if (parsed.getPath().equals("/")) {
      parsed = parseUrl(parsed.getProtocol(), parsed.getHost(), parsed.getPort(),
          "");
    }

    long serverId = Math.round(Math.random() * 999);
    UUID sessionId = UUID.randomUUID();

      return parse(parsed.getProtocol().equals("https") ? "wss" : "ws",
          parsed.getHost(), parsed.getPort(),
          parsed.getPath() + "/" + serverId + "/" + sessionId+"/websocket");
  }

  private static URI parse(String protocol, String host, int port, String file) {
      try {
        return new URI(protocol, null,host, port, file,null,null);
      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      }
  }
  private static URL parseUrl(String protocol, String host, int port, String file) {
    try {
      return new URL(protocol, host, port, file);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
