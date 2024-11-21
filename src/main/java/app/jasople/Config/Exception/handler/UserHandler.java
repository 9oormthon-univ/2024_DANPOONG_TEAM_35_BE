package app.jasople.Config.Exception.handler;


import app.jasople.Config.Exception.GeneralException;
import app.jasople.Config.code.BaseErrorCode;

public class UserHandler extends GeneralException {

    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
