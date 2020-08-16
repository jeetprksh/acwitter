package com.jeetprksh.acwitter.actions;

import com.jeetprksh.actions.action.Action;
import com.jeetprksh.actions.action.ActionsFactory;
import com.jeetprksh.actions.endpoint.Endpoint;
import com.jeetprksh.acwitter.Acwitter;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Jeet Prakash
 */
public class TwitterActionsFactory implements ActionsFactory {

  private final Logger logger = Logger.getLogger(TwitterActionsFactory.class.getName());

  @Override
  public List<Action> createActions(Class<? extends Endpoint> endpointClass) throws Exception {
    List<Action> actions = new ArrayList<>();
    String pack = Acwitter.class.getPackage().getName();
    Reflections reflections = new Reflections(pack);

    Set<Class<? extends Action>> definedActions = reflections.getSubTypesOf(Action.class);
    for (Class<? extends Action> klass : definedActions) {
      Action action = klass.newInstance();
      actions.add(action);
      logger.info("Found action " + action.name());
    }
    return actions;
  }

}
