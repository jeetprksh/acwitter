package com.jeetprksh.acwitter.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jeetprksh.actions.config.ExecutionConfigFactory;
import com.jeetprksh.actions.config.pojo.Action;
import com.jeetprksh.actions.config.pojo.ExecutionConfig;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TwitterExecutionConfigFactory implements ExecutionConfigFactory {

  private final Logger logger = Logger.getLogger(TwitterExecutionConfigFactory.class.getName());

  @Override
  public ExecutionConfig create(String path) throws Exception {
    logger.info("Reading the Execution Config");
    try {
      FileInputStream fis = new FileInputStream(path);
      JsonElement element = JsonParser.parseString(IOUtils.toString(fis, StandardCharsets.UTF_8));
      JsonObject jsonConfigObject = element.getAsJsonObject();

      String endpointName = jsonConfigObject.get("name").getAsString();

      Map<String, String> credsMap = new HashMap<>();
      JsonObject credentialsObject = jsonConfigObject.getAsJsonObject("credentials");
      credentialsObject.keySet().forEach(k -> credsMap.put(k, credentialsObject.get(k).getAsString()));

      List<Action> actions = new ArrayList<>();
      jsonConfigObject.getAsJsonArray("actions").forEach(action -> {
        JsonObject jsonAction = (JsonObject) action;
        Action a = new Action(
                jsonAction.get("name").getAsString(),
                jsonAction.get("input").getAsJsonObject().toString(),
                jsonAction.get("brokerAddress").getAsString(),
                jsonAction.get("listenOn").getAsString(),
                jsonAction.get("listenFor").getAsString(),
                jsonAction.get("publishOn").getAsString());
        actions.add(a);
      });

      return new ExecutionConfig(endpointName, credsMap, actions);
    } catch (FileNotFoundException ex) {
      throw new Exception("No Configuration file found, stopping the application");
    }
  }

  private List<String> convertToStringList(JsonElement jsonElement) {
    List<String> list = new ArrayList<>();
    jsonElement.getAsJsonArray().forEach(s -> list.add(s.getAsString()));
    return list;
  }

}
