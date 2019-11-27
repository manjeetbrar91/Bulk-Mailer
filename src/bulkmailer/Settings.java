/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulkmailer;

import java.io.Serializable;

/**
 *
 * @author MANJEET BRAR
 */
public class Settings implements Serializable {

    private String auth_required;
    private String protocol;
    private String host;
    private String port;
    private String enable_starttls;
    private String required_starttls;
    private String mail_from;
    private String username;
    private String password;

    public String getAuth_required() {
        return auth_required;
    }

    public void setAuth_required(String auth_required) {
        this.auth_required = auth_required;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getEnable_starttls() {
        return enable_starttls;
    }

    public void setEnable_starttls(String enable_starttls) {
        this.enable_starttls = enable_starttls;
    }

    public String getRequired_starttls() {
        return required_starttls;
    }

    public void setRequired_starttls(String required_starttls) {
        this.required_starttls = required_starttls;
    }

    public String getMail_from() {
        return mail_from;
    }

    public void setMail_from(String mail_from) {
        this.mail_from = mail_from;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
