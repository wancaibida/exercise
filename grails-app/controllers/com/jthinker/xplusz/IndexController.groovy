package com.jthinker.xplusz

import grails.converters.JSON
import xplusz.HttpService

import javax.annotation.Resource

class IndexController {

    String client_id = "2725511718"
    String appSecret = "f3ce0d6f7092e05244c6198af042901a"
    String appKey = "2725511718"


    String redirectUrl = "http://xplusz.jthinker.com/index/callback"


    String url_publist = "https://api.weibo.com/2/statuses/public_timeline.json"
    String tokenUrl = "https://api.weibo.com/oauth2/access_token";

    @Resource
    HttpService httpService;

    def index() {
        if (!session.login) {
            redirect(url: "https://api.weibo.com/oauth2/authorize?client_id=" + appKey + "&redirect_uri=" + redirectUrl)
        }
    }

    def callback() {
        def code = params.code

        def params = new HashMap();
        params.put("client_id", client_id);
        params.put("client_secret", appSecret);
        params.put("grant_type", "authorization_code")
        params.put("redirect_uri", redirectUrl);
        params.put("code", code)


        def plain = httpService.req(tokenUrl, params)
        def json = JSON.parse(plain);

        if (json) {
            session.login = true
            session.token = json.access_token
            redirect(controller: 'index', action: 'index')
        }

    }


    def list() {

        def params = new HashMap();

        if (!session.token) {
            render(contentType: "application/json") {
                list = Collections.emptyList()
            }
        }

        params.put("access_token", session.token);
//        params.put("client_secret", appSecret);
//        params.put("grant_type", "authorization_code")
//        params.put("redirect_uri", redirectUrl);

        String plain = httpService.req(url_publist, params, "GET")

        render(contentType: "application/json") {
            books = JSON.parse(plain)
        }
    }
}
