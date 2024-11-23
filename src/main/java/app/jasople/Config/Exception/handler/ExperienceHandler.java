package app.jasople.Config.Exception.handler;


import app.jasople.Config.Exception.GeneralException;
import app.jasople.Config.code.BaseErrorCode;

public class ExperienceHandler extends GeneralException {

    public ExperienceHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
