package com.jthinker.xplusz

import grails.converters.JSON
import xplusz.HttpService

import javax.annotation.Resource

class IndexController {

    private static final String CLIENT_ID = "2725511718"
    private static final String APPSECRET = "f3ce0d6f7092e05244c6198af042901a"
    private static final String APPKEY = "2725511718"


    private static final String REDIRECTURL = "http://xplusz.jthinker.com/index/callback"


    private static final String URL_PUBLIST = "https://api.weibo.com/2/statuses/public_timeline.json"
    private static final String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";

    @Resource
    HttpService httpService;

    def index() {
        if (!session.login) {
            redirect(url: "https://api.weibo.com/oauth2/authorize?client_id=" + APPKEY + "&redirect_uri=" + REDIRECTURL)
        }
    }

    def callback() {
        def code = params.code

        def postParams = new HashMap();
        postParams.put("client_id", CLIENT_ID);
        postParams.put("client_secret", APPSECRET);
        postParams.put("grant_type", "authorization_code")
        postParams.put("redirect_uri", REDIRECTURL);
        postParams.put("code", code)


        def plain = httpService.req(TOKEN_URL, postParams)
        def json = JSON.parse(plain);

        if (json) {
            session.login = true
            session.token = json.access_token
            redirect(controller: 'index', action: 'index')
        }

    }


    def list() {

        def getParams = new HashMap();

        if (!session.token) {
            render(contentType: "application/json") {
                list = Collections.emptyList()
            }
        }

        getParams.put("access_token", session.token);
        //TODO page没用?
        getParams.put("page", WebUtils.param(params, "page", 1))
        getParams.put("count", WebUtils.param(params, "pageSize", 10))
//        params.put("client_secret", APPSECRET);
//        params.put("grant_type", "authorization_code")
//        params.put("redirect_uri", REDIRECTURL);

        println getParams

        String plain = httpService.req(URL_PUBLIST, getParams, "GET")

        render(contentType: "application/json") {
            JSON.parse(plain)
        }
    }
}
