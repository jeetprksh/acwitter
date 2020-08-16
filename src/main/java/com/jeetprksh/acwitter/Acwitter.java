package com.jeetprksh.acwitter;

import com.jeetprksh.actions.action.Action;
import com.jeetprksh.actions.action.ActionsFactory;
import com.jeetprksh.actions.config.ExecutionConfigFactory;
import com.jeetprksh.actions.config.pojo.ExecutionConfig;
import com.jeetprksh.actions.credentials.CredentialFactory;
import com.jeetprksh.actions.credentials.Credentials;
import com.jeetprksh.actions.endpoint.BaseEndpoint;
import com.jeetprksh.acwitter.actions.TwitterActionsFactory;
import com.jeetprksh.acwitter.config.TwitterExecutionConfigFactory;
import com.jeetprksh.acwitter.credentials.TwitterCredentialFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Jeet Prakash
 */
public class Acwitter extends BaseEndpoint {

  private final Logger logger = Logger.getLogger(Acwitter.class.getName());

  private final String configFile = System.getProperty("user.home") + "/.acwitter/config.json";

  private final CredentialFactory credentialFactory = new TwitterCredentialFactory();
  private final ActionsFactory actionsFactory = new TwitterActionsFactory();
  private final ExecutionConfigFactory configFactory = new TwitterExecutionConfigFactory();

  private ExecutionConfig executionConfig;
  private Credentials credentials;
  private final LinkedHashMap<String, Action> actions = new LinkedHashMap<>();

  @Override
  public void init() throws Exception {
    this.executionConfig = configFactory.create(configFile);

    this.credentials = credentialFactory.createCredentials(executionConfig.getCredentials());

    List<Action> actions = actionsFactory.createActions(Acwitter.class);
    for (Action action : actions) {
      this.actions.put(action.name(), action);
    }
    super.register(this);
    logger.info("Initialized and registered " + executionConfig.getName());
  }

  @Override
  public void startActions() throws Exception {
    super.startActions();
  }

  @Override
  public Credentials credentials() {
    return this.credentials;
  }

  @Override
  public LinkedHashMap<String, Action> actions() {
    return this.actions;
  }

  @Override
  public ExecutionConfig executionConfig() {
    return this.executionConfig;
  }

}
