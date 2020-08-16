package com.jeetprksh.acwitter.actions;

import com.jeetprksh.actions.action.Action;
import com.jeetprksh.actions.action.ActionContext;

import java.util.logging.Logger;

/**
 * @author Jeet Prakash
 */
public class CreateTweet implements Action {

  private final Logger logger = Logger.getLogger(CreateTweet.class.getName());

  private final String actionName = "createTweet";

  public CreateTweet() {
  }

  @Override
  public void act(ActionContext actionContext) throws Exception {
    logger.info("Starting action " + actionName);

  }

  @Override
  public String name() {
    return this.actionName;
  }

}
