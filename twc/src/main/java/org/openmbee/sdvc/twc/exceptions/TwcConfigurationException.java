package org.openmbee.sdvc.twc.exceptions;

import org.openmbee.sdvc.core.exceptions.SdvcException;
import org.springframework.http.HttpStatus;

public class TwcConfigurationException extends SdvcException {
    public TwcConfigurationException(HttpStatus code, Object messageObject) {
        super(code, messageObject);
    }

    public TwcConfigurationException(int code, Object messageObject) {
        super(code, messageObject);
    }
}
