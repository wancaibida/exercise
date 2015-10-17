package com.jthinker.xplusz

import xplusz.HttpService

import javax.annotation.Resource

class IndexController {

    String client_id = "2725511718"
    String appSecret = "f3ce0d6f7092e05244c6198af042901a"
    String appKey = "2725511718"


    String redirectUrl = "http://xplusz.jthinker.com/index/callback"
    String url = apiUrl + "?source=" + appKey


    String apiUrl = "https://api.weibo.com/2/statuses/public_timeline.json"
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



        def plain = httpService.post(tokenUrl, params)

        render(contentType: "application/json") {
            books = plain
        }


    }


    def list() {
        render(contentType: "application/json") {
            books = array {
                for (b in results) {
                    book title: b.title
                }
            }
        }
    }
}
