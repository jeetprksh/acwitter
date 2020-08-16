package com.jeetprksh.acwitter.credentials;

import com.jeetprksh.actions.credentials.CredentialFactory;
import com.jeetprksh.actions.credentials.Credentials;

import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Jeet Prakash
 */
public class TwitterCredentialFactory implements CredentialFactory {

  private final Logger logger = Logger.getLogger(TwitterCredentialFactory.class.getName());

  @Override
  public Credentials createCredentials(Map<String, String> params) {
    logger.info("Creating Twitter credentials");
    return new TwitterCredentials();
  }

}
