package com.example.fulvo.comunicacionAPI;

@SuppressWarnings("unused")
public class APIRespuestaEvento {

    private Boolean success;
    private String env;
    private APIRespuestaObjetoEvento event;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public APIRespuestaObjetoEvento getEvent() {
        return event;
    }

    public void setEvent(APIRespuestaObjetoEvento event) {
        this.event = event;
    }
}
